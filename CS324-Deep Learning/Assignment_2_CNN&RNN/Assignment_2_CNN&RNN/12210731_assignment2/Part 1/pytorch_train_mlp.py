from __future__ import absolute_import
from __future__ import division
from __future__ import print_function

import argparse
import numpy as np
import os
import torch
from pytorch_mlp import MLP

from sklearn.datasets import make_moons
from sklearn.model_selection import train_test_split
from torch.utils.data import DataLoader, TensorDataset

import torch.nn as nn

# Default constants
DNN_HIDDEN_UNITS_DEFAULT = "200"
LEARNING_RATE_DEFAULT = 1e-2
MAX_EPOCHS_DEFAULT = 1500
EVAL_FREQ_DEFAULT = 10

FLAGS = None


def load_data():
    """
    Placeholder for your data loading function.
    Here you should load your dataset, split into train and test sets, and return them.
    """
    # Make_moon methods to generate dataset with noise = 0.2
    X, y = make_moons(n_samples=1000, noise=0.2)

    # One-hot encode the labels
    num_classes = len(np.unique(y))
    y_one_hot = np.eye(num_classes)[y]

    # Split into train and test sets
    X_train, X_test, y_train, y_test = train_test_split(X, y_one_hot, test_size=0.2)

    # print(y_one_hot)

    # 可视化生成的样本点
    # plt.figure(figsize=(8, 6))
    # plt.scatter(
    #     X_train[:, 0],
    #     X_train[:, 1],
    #     c=y_train[:, 0],
    #     cmap=plt.cm.spring,
    #     edgecolor="k",
    #     label="Training Data",
    # )
    # plt.scatter(
    #     X_test[:, 0], X_test[:, 1], c="black", marker="x", label="Testing Data"
    # )  # 用黑色交叉标记测试集
    # plt.xlabel("Feature 1")
    # plt.ylabel("Feature 2")
    # plt.title("Generated Moons Dataset with Training and Testing Split")
    # plt.legend()
    # plt.show()

    return X_train, X_test, y_train, y_test


def accuracy(predictions, targets):
    """
    Computes the prediction accuracy, i.e., the average of correct predictions
    of the network.
    Args:
        predictions: 2D float array of size [number_of_data_samples, n_classes]
        labels: 2D int array of size [number_of_data_samples, n_classes] with one-hot encoding of ground-truth labels
    Returns:
        accuracy: scalar float, the accuracy of predictions.
    """
    pred_labels = np.argmax(predictions, axis=1)
    # Get true class labels from one-hot encoded targets
    true_labels = np.argmax(targets, axis=1)
    # Calculate the percentage of correct predictions
    accuracy = np.mean(pred_labels == true_labels)
    return accuracy


def train():
    """
    Performs training and evaluation of MLP model.
    NOTE: You should the model on the whole test set each eval_freq iterations.
    """
    # YOUR TRAINING CODE GOES HERE

    X_train, X_test, y_train, y_test = load_data()

    print(f"torch.cude.is_avaiable{torch.cuda.is_available()}")
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

    print(f"device:{device}")

    dnn_hidden_units = list(map(int, FLAGS.dnn_hidden_units.split(",")))
    print(f"dnn_hidden_units are :{dnn_hidden_units}")

    print(f"X_train:{X_train}")
    print(f"y_train:{y_train}")

    input_dim = X_train.shape[1]
    output_dim = y_train.shape[1]

    print(f"outputdim:{output_dim}")

    model = MLP(input_dim, dnn_hidden_units, output_dim)
    loss = nn.CrossEntropyLoss()
    optimizer = torch.optim.SGD(model.parameters(), lr=FLAGS.learning_rate)

    print(f"y_train shape: {y_train.shape}")

    BATCH_SIZE = 200
    # Convert training and testing data to PyTorch tensors
    X_train_tensor = torch.tensor(X_train, dtype=torch.float32).to(device)
    y_train_tensor = torch.tensor(y_train, dtype=torch.long).to(
        device
    )  # Convert to class indices
    X_test_tensor = torch.tensor(X_test, dtype=torch.float32).to(device)
    y_test_tensor = torch.tensor(y_test, dtype=torch.long).to(device)

    print(f"X_train_tensor:{X_train_tensor}")
    print(f"Y_train_tensor:{y_train_tensor}")

    # 创建 TensorDataset 和 DataLoader
    train_dataset = TensorDataset(X_train_tensor, y_train_tensor)
    train_loader = DataLoader(train_dataset, batch_size=BATCH_SIZE, shuffle=True)

    for epoch in range(FLAGS.max_steps):
        model.train()  # Set model to training mode

        for X_batch, y_batch in train_loader:
            # print(f"about y_barch:{y_batch.shape}, {y_batch}")
            optimizer.zero_grad()  # Clear gradients
            outputs = model(X_train_tensor)  # Forward pass

            # Check the shape of y_train_tensor
            # print(f"y_train_tensor shape: {y_train_tensor.shape}")

            # print(f"outputs:{outputs.shape}, {outputs}")
            # print(f"y_batch:{y_batch.shape}, {y_batch}")

            softmax = nn.Softmax(dim=1)  # 实例化 Softmax，指定维度
            outputs = softmax(outputs)  # 应用 Softmax
            # outputs = nn.Softmax(outputs, dim=1)

            # print(f"outputs2:{outputs.shape}, {outputs}")
            # print(f"y_batch2:{y_batch.shape}, {y_batch}")

            final_loss = loss(outputs, y_train_tensor.argmax(dim=1))  # Calculate loss
            # print(f"first_final_loss:{final_loss.item():.4f}")
            final_loss.backward()  # Backward pass
            optimizer.step()  # Update weights

        if (epoch + 1) % FLAGS.eval_freq == 0:
            model.eval()  # Set model to evaluation mode
            with torch.no_grad():
                train_predictions = model(X_train_tensor)
                test_predictions = model(X_test_tensor)

                # print(f"train_prediction:{train_predictions.shape},{train_predictions}")

                # Compute accuracy
                train_accuracy = accuracy(
                    train_predictions.cpu().numpy(), y_train_tensor.cpu().numpy()
                )
                test_accuracy = accuracy(
                    test_predictions.cpu().numpy(), y_test_tensor.cpu().numpy()
                )

                print(
                    f"Epoch [{epoch + 1}/{FLAGS.max_steps}], "
                    f"Loss: {final_loss.item():.4f}, "
                    f"Train Accuracy: {train_accuracy * 100:.2f}%, "
                    f"Test Accuracy: {test_accuracy * 100:.2f}%"
                )


def main():
    """
    Main function
    """
    train()


if __name__ == "__main__":
    # Command line arguments
    parser = argparse.ArgumentParser()
    parser.add_argument(
        "--dnn_hidden_units",
        type=str,
        default=DNN_HIDDEN_UNITS_DEFAULT,
        help="Comma separated list of number of units in each hidden layer",
    )
    parser.add_argument(
        "--learning_rate",
        type=float,
        default=LEARNING_RATE_DEFAULT,
        help="Learning rate",
    )
    parser.add_argument(
        "--max_steps",
        type=int,
        default=MAX_EPOCHS_DEFAULT,
        help="Number of epochs to run trainer.",
    )
    parser.add_argument(
        "--eval_freq",
        type=int,
        default=EVAL_FREQ_DEFAULT,
        help="Frequency of evaluation on the test set",
    )
    FLAGS, unparsed = parser.parse_known_args()

    print(torch.__version__)
    main()
