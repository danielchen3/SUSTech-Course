import argparse
import os

import torch
import torch.nn as nn
import torchvision.transforms as transforms
from torchvision.utils import save_image
from torchvision import datasets


class Generator(nn.Module):
    def __init__(self, latent_dim):
        super(Generator, self).__init__()

        # Construct generator. You should experiment with your model,
        # but the following is a good start:
        #   Linear args.latent_dim -> 128
        #   LeakyReLU(0.2)
        #   Linear 128 -> 256
        #   Bnorm
        #   LeakyReLU(0.2)
        #   Linear 256 -> 512
        #   Bnorm
        #   LeakyReLU(0.2)
        #   Linear 512 -> 1024
        #   Bnorm
        #   LeakyReLU(0.2)
        #   Linear 1024 -> 768
        #   Output non-linearity

        # self.latent_dim = latent_dim

        self.model = nn.Sequential(
            nn.Linear(latent_dim, 128),
            nn.LeakyReLU(0.2),
            nn.Linear(128, 256),
            nn.BatchNorm1d(256),
            nn.LeakyReLU(0.2),
            nn.Linear(256, 512),
            nn.BatchNorm1d(512),
            nn.LeakyReLU(0.2),
            nn.Linear(512, 1024),
            nn.BatchNorm1d(1024),
            nn.LeakyReLU(0.2),
            nn.Linear(1024, 784),
            nn.Tanh(),
        )

    def forward(self, z):
        # Generate images from z
        img = self.model(z)
        img = img.view(img.size(0), 1, 28, 28)
        return img
        pass


class Discriminator(nn.Module):
    def __init__(self):
        super(Discriminator, self).__init__()

        # Construct distriminator. You should experiment with your model,
        # but the following is a good start:
        #   Linear 784 -> 512
        #   LeakyReLU(0.2)
        #   Linear 512 -> 256
        #   LeakyReLU(0.2)
        #   Linear 256 -> 1
        #   Output non-linearity
        self.model = nn.Sequential(
            nn.Linear(784, 512),
            nn.LeakyReLU(0.2),
            nn.Linear(512, 256),
            nn.LeakyReLU(0.2),
            nn.Linear(256, 1),
            nn.Sigmoid(),
        )

    def forward(self, img):
        # return discriminator score for img
        img_flat = img.view(img.size(0), -1)
        validity = self.model(img_flat)
        return validity
        pass


def train(
    dataloader,
    discriminator,
    generator,
    optimizer_G,
    optimizer_D,
    criterion,
    latent_dim,
    device,
):
    for epoch in range(args.n_epochs):
        for i, (imgs, _) in enumerate(dataloader):
            imgs = imgs.to(device)
            # imgs.cuda()

            # gen_imgs = None
            # Train Generator
            # ---------------
            # Adversarial ground truths
            valid = torch.ones(imgs.size(0), 1, device=device)
            fake = torch.zeros(imgs.size(0), 1, device=device)
            # Train Generator
            optimizer_G.zero_grad()
            # Sample noise as generator input
            z = torch.randn(size=(imgs.size(0), latent_dim), device=device)

            # noise = torch.randn(args.batch_size, args.latent_dim).to(device)
            # fake_img = generator(noise)
            # output = discriminator(fake_img.detach())
            generator.train()
            
            # Generate a batch of images
            gen_imgs = generator(z)
            # print(f"Noise shape: {z.shape}, Generated images shape: {gen_imgs.shape}")

            # Calculate generator loss
            g_loss = criterion(discriminator(gen_imgs), valid)

            # Backpropagation for generator
            g_loss.backward()
            optimizer_G.step()

            # Train Discriminator
            # -------------------
            discriminator.train()
            optimizer_D.zero_grad()

            # Calculate discriminator loss
            real_loss = criterion(discriminator(imgs), valid)
            fake_loss = criterion(discriminator(gen_imgs.detach()), fake)
            d_loss = real_loss + fake_loss

            # Backpropagation for discriminator
            d_loss.backward()
            optimizer_D.step()
            
            # Thresholding predictions: 0.5 is used because of Sigmoid activation

            # Logging
            # print(
            #     f"[Epoch {epoch}/{args.n_epochs}] [Batch {i}/{len(dataloader)}] "
            #     f"[D loss: {d_loss.item():.4f}] [G loss: {g_loss.item():.4f}]"
            # )

            # Save Images
            # -----------
            batches_done = epoch * len(dataloader) + i
            # Save generated images
            if batches_done % args.save_interval == 0:
                save_image(
                    gen_imgs[:25],
                    f"images/{epoch * len(dataloader) + i}.png",
                    nrow=5,
                    normalize=True,
                    value_range=(-1, 1),
                )
                
                # Predictions for real and fake images
                # real_pred = discriminator(imgs)
                # fake_pred = discriminator(gen_imgs.detach())
                
                # real_correct = (real_pred > 0.5).float().eq(valid).sum().item()
                # fake_correct = (fake_pred <= 0.5).float().eq(fake).sum().item()

                # # Total accuracy
                # accuracy = (real_correct + fake_correct) / (2 * imgs.size(0))  # Both real and fake contribute equally

                # # 输出准确率
                # print(f"Discriminator Accuracy: {accuracy:.4f}")
                
                # You can use the function save_image(Tensor (shape Bx1x28x28),
                # filename, number of rows, normalize) to save the generated
                # images, e.g.:
                # save_image(gen_imgs[:25],
                #            'images/{}.png'.format(batches_done),
                #            nrow=5, normalize=True, value_range=(-1,1))
                pass


def main():
    # Create output image directory
    os.makedirs("images", exist_ok=True)

    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

    print(f"device is {device}")

    # load data
    dataloader = torch.utils.data.DataLoader(
        datasets.MNIST(
            "./data/mnist",
            train=True,
            download=True,
            transform=transforms.Compose(
                [transforms.ToTensor(), transforms.Normalize((0.5), (0.5))]
            ),
        ),
        batch_size=args.batch_size,
        shuffle=True,
    )

    # Initialize models and optimizers
    generator = Generator(args.latent_dim).to(device)
    discriminator = Discriminator().to(device)
    optimizer_G = torch.optim.Adam(generator.parameters(), lr=args.lr)
    optimizer_D = torch.optim.Adam(discriminator.parameters(), lr=args.lr)

    # Loss function
    criterion = nn.BCELoss()

    print(f"latent is {args.latent_dim}")

    # Start training
    train(
        dataloader,
        discriminator,
        generator,
        optimizer_G,
        optimizer_D,
        criterion,
        args.latent_dim,
        device,
    )

    # You can save your generator here to re-use it to generate images for your
    # report, e.g.:
    torch.save(generator.state_dict(), "mnist_generator.pt")


if __name__ == "__main__":
    parser = argparse.ArgumentParser()
    parser.add_argument("--n_epochs", type=int, default=200, help="number of epochs")
    parser.add_argument("--batch_size", type=int, default=64, help="batch size")
    parser.add_argument("--lr", type=float, default=0.0002, help="learning rate")
    parser.add_argument(
        "--latent_dim", type=int, default=100, help="dimensionality of the latent space"
    )
    parser.add_argument(
        "--save_interval",
        type=int,
        default=500,
        help="save every SAVE_INTERVAL iterations",
    )
    args = parser.parse_args()

    main()
