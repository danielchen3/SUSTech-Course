from __future__ import absolute_import
from __future__ import division
from __future__ import print_function

import argparse
import matplotlib.pyplot as plt
import torch
from torch.utils.data import DataLoader
import torch.nn as nn

from dataset import PalindromeDataset
from lstm import LSTM
from utils import AverageMeter, accuracy


def train(model, data_loader, optimizer, criterion, device, config):
    # TODO set model to train mode
    model.train()
    losses = AverageMeter("Loss")
    accuracies = AverageMeter("Accuracy")
    for step, (batch_inputs, batch_targets) in enumerate(data_loader):
        # Add more code here ...
        batch_inputs, batch_targets = batch_inputs.to(device), batch_targets.to(device)
        outputs = model(batch_inputs)
        loss = criterion(outputs, batch_targets)
        acc = accuracy(outputs, batch_targets)

        optimizer.zero_grad()
        loss.backward()

        # the following line is to deal with exploding gradients
        torch.nn.utils.clip_grad_norm_(model.parameters(), max_norm=config.max_norm)

        optimizer.step()
        losses.update(loss.item(), batch_inputs.size(0))
        accuracies.update(acc, batch_inputs.size(0))

        # Add more code here ...
        # if step % 10 == 0:
        #     print(f"[{step}/{len(data_loader)}]", losses, accuracies)
    return losses.avg, accuracies.avg


@torch.no_grad()
def evaluate(model, data_loader, criterion, device, config):
    # TODO set model to evaluation mode
    model.eval()
    losses = AverageMeter("Loss")
    accuracies = AverageMeter("Accuracy")
    for step, (batch_inputs, batch_targets) in enumerate(data_loader):
        # Add more code here ...

        batch_inputs, batch_targets = batch_inputs.to(device), batch_targets.to(device)
        outputs = model(batch_inputs)
        loss = criterion(outputs, batch_targets)
        acc = accuracy(outputs, batch_targets)

        losses.update(loss.item(), batch_inputs.size(0))
        accuracies.update(acc, batch_inputs.size(0))

        # if step % 10 == 0:
        #     print(f"[{step}/{len(data_loader)}]", losses, accuracies)
    return losses.avg, accuracies.avg


def main(config):
    device = "cuda" if torch.cuda.is_available() else "cpu"

    print(f"device is {device}")

    # Initialize the model
    model = LSTM(
        config.input_length, config.input_dim, config.num_hidden, config.num_classes
    )
    model.to(device)

    # Initialize the dataset and data loader
    dataset = dataset = PalindromeDataset(
        input_length=config.input_length, total_len=config.data_size
    )

    # Split dataset into train and validation sets
    train_size = int(config.portion_train * len(dataset))
    test_size = int(len(dataset) - train_size)
    train_dataset, val_dataset = torch.utils.data.random_split(
        dataset, [train_size, test_size]
    ) 

    # Create data loaders for training and validation
    train_dloader = DataLoader(
        train_dataset, batch_size=config.batch_size, shuffle=True
    )  
    val_dloader = DataLoader(val_dataset, batch_size=config.batch_size, shuffle=True)

    # Setup the loss and optimizer
    criterion = nn.CrossEntropyLoss()
    optimizer = torch.optim.RMSprop(
        model.parameters(), lr=config.learning_rate, alpha=0.99
    )
    scheduler = torch.optim.lr_scheduler.StepLR(
        optimizer=optimizer, step_size=100, gamma=0.1
    )

    train_accs = []
    val_accs = []

    for epoch in range(config.max_epoch):
        # Train the model for one epoch
        train_loss, train_acc = train(
            model, train_dloader, optimizer, criterion, device, config
        )

        # Evaluate the trained model on the validation set
        val_loss, val_acc = evaluate(model, val_dloader, criterion, device, config)

        train_accs.append(train_acc)
        val_accs.append(val_acc)

        print(
            f"Train part at [{epoch}/{config.max_epoch}]: train_loss is {train_loss}, train_acc is {train_acc}"
        )

        print(
            f"Validation part at [{epoch}/{config.max_epoch}]: val_loss is {val_loss}, val_acc is {val_acc}"
        )

        scheduler.step()
        
    # 绘制训练和验证准确度的变化图
    plt.plot(range(config.max_epoch), train_accs, label='Train Accuracy')
    plt.plot(range(config.max_epoch), val_accs, label='Validation Accuracy')
    plt.xlabel('Epoch')
    plt.ylabel('Accuracy')
    plt.title('Train and Validation Accuracy over Epochs')
    plt.legend()
    plt.savefig('accuracy_plot.png')

    print("Done training.")


if __name__ == "__main__":

    # Parse training configuration
    parser = argparse.ArgumentParser()

    # Model params
    parser.add_argument(
        "--input_length", type=int, default=25, help="Length of an input sequence"
    )
    parser.add_argument(
        "--input_dim", type=int, default=1, help="Dimensionality of input sequence"
    )
    parser.add_argument(
        "--num_classes", type=int, default=10, help="Dimensionality of output sequence"
    )
    parser.add_argument(
        "--num_hidden",
        type=int,
        default=128,
        help="Number of hidden units in the model",
    )
    parser.add_argument(
        "--batch_size",
        type=int,
        default=128,
        help="Number of examples to process in a batch",
    )
    parser.add_argument(
        "--learning_rate", type=float, default=0.001, help="Learning rate"
    )
    parser.add_argument(
        "--max_epoch", type=int, default=40, help="Number of epochs to run for"
    )
    parser.add_argument("--max_norm", type=float, default=10.0)
    parser.add_argument(
        "--data_size", type=int, default=100000, help="Size of the total dataset"
    )
    parser.add_argument(
        "--portion_train",
        type=float,
        default=0.8,
        help="Portion of the total dataset used for training",
    )

    config = parser.parse_args()
    # Train the model
    main(config)
