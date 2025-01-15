from __future__ import absolute_import, division, print_function
import torch
import torch.nn as nn


class CNN(nn.Module):
    def __init__(self, n_channels, n_classes):
        """
        Initializes CNN object.
        Args:
          n_channels: number of input channels
          n_classes: number of classes of the classification problem
        """
        super(CNN, self).__init__()

        # Define the layers as per the simplified VGG architecture
        self.conv_layers = nn.Sequential(
            nn.Conv2d(n_channels, 64, kernel_size=3, padding=1),
            nn.ReLU(),
            nn.Conv2d(64, 64, kernel_size=3, padding=1),
            nn.ReLU(),
            nn.MaxPool2d(kernel_size=3, stride=2),
            nn.Conv2d(64, 128, kernel_size=3, padding=1),
            nn.ReLU(),
            nn.Conv2d(128, 128, kernel_size=3, padding=1),
            nn.ReLU(),
            nn.MaxPool2d(kernel_size=3, stride=2),
        )

        # Fully connected layers
        self.fc_layers = nn.Sequential(
            nn.Linear(
                128 * 7 * 7 , 1 * 1 * 512
            ),  # Adjust input size based on your data's resolution
            nn.ReLU(),
            nn.Linear(512, n_classes),
        )

    def forward(self, x):
        """
        Performs forward pass of the input.
        Args:
          x: input to the network
        Returns:
          out: outputs of the network
        """
        x = self.conv_layers(x)
        x = x.view(x.size(0), -1)  # Flatten the tensor
        # print(f"x_size:{x.shape}")
        out = self.fc_layers(x)
        return out
