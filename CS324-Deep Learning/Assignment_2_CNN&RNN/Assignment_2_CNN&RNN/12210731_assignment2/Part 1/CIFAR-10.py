from torch.utils.data import DataLoader
import numpy as np
import torch
import torch.nn as nn
from pytorch_mlp import MLP
import argparse
import matplotlib.pyplot as plt

from sklearn.datasets import make_moons
from sklearn.model_selection import train_test_split
from torch.utils.data import DataLoader, TensorDataset

DNN_HIDDEN_UNITS_DEFAULT = "200, 300"
LEARNING_RATE_DEFAULT = 0.001
MAX_EPOCHS_DEFAULT = 500
EVAL_FREQ_DEFAULT = 10
DECAY = 0.001

FLAGS = None


def unpickle(file):
    import pickle

    with open("data/cifar-10-batches-py/" + file, "rb") as fo:
        dict = pickle.load(fo, encoding="bytes")
    return dict

def load_cifar10_data():
    data_files = ['data_batch_1', 'data_batch_2', 'data_batch_3', 'data_batch_4', 'data_batch_5']
    
    all_data = []
    all_labels = []
    
    # load all data
    for file in data_files:
        data_dict = unpickle(file)
        all_data.append(data_dict[b'data'])
        all_labels.append(data_dict[b'labels'])
    
    all_data = np.vstack(all_data)  # 合并所有数据
    all_labels = np.concatenate(all_labels)  # 合并所有标签

    return all_data, all_labels

def get_train_test_data(test_file):
    train_data, train_label = load_cifar10_data()

    print(f"训练数据的形状: {train_data.shape}")
    print(f"训练标签的形状: {train_label.shape}")

    test_data_batch = unpickle(test_file)

    test_data = test_data_batch[b"data"]
    test_label = test_data_batch[b"labels"]

    # 把字典的值转成array格式，方便操作
    train_data = np.array(train_data)
    # print(f"train_data:{train_data.shape},{train_label}")
    train_label = np.array(train_label)
    # print(f"train_label:{train_label.shape},{train_label}")

    test_data = np.array(test_data)
    # print(f"test_data:{test_data.shape},{test_data}")
    test_label = np.array(test_label)
    # print(f"test_label:{test_label.shape},{test_label}")
    return train_data, train_label, test_data, test_label


def tensor_tranfer(train_data, train_label, test_data, test_label, device):
    # Convert training and testing data to PyTorch tensors
    train_data_tensor = torch.tensor(train_data, dtype=torch.float32).to(device)
    train_label_tensor = torch.tensor(train_label, dtype=torch.long).to(
        device
    )  # Convert to class indices
    test_data_tensor = torch.tensor(test_data, dtype=torch.float32).to(device)
    test_label_tensor = torch.tensor(test_label, dtype=torch.long).to(device)
    return (
        train_data_tensor / 255,
        train_label_tensor,
        test_data_tensor / 255,
        test_label_tensor,
    )


def calculate_accuracy(output, target):
    # 输出为 logits
    predicted = torch.argmax(output, dim=1)
    # 检查预测是否正确
    correct = (predicted == target).sum().item()
    # 计算准确率
    accuracy = correct / target.size(0)
    return accuracy


# # 定义数据转换（如归一化）
# transform = transforms.Compose(
#     [
#         transforms.ToTensor(),  # 转换为 PyTorch 张量
#         transforms.Normalize((0.5, 0.5, 0.5), (0.5, 0.5, 0.5)),  # 归一化
#     ]
# )

# # 加载 CIFAR-10 数据集
# train_dataset = CIFAR10(root="./data", train=True, download=True, transform=transform)
# test_dataset = CIFAR10(root="./data", train=False, download=True, transform=transform)


def train():

    test_file = "test_batch"

    print(f"torch.cude.is_avaiable{torch.cuda.is_available()}")
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

    print(f"device:{device}")

    train_data, train_label, test_data, test_label = get_train_test_data(test_file)

    train_data_tensor, train_label_tensor, test_data_tensor, test_label_tensor = (
        tensor_tranfer(train_data, train_label, test_data, test_label, device)
    )

    print(f"train_data_tensor:{train_data_tensor}")
    print(f"train_label_tensor:{train_label_tensor}")

    dnn_hidden_units = list(map(int, FLAGS.dnn_hidden_units.split(",")))
    # print(f"dnn_hidden_units are :{dnn_hidden_units}")

    # print(f"train_label.shape:{train_label}")
    # print(f"train_data.shape[1]: {train_data.shape[1]}")

    model = MLP(train_data.shape[1], dnn_hidden_units, 10)
    model.to(device)
    loss = nn.CrossEntropyLoss()
    optimizer = torch.optim.Adam(model.parameters(), lr=LEARNING_RATE_DEFAULT, weight_decay=DECAY)

    BATCH_SIZE = 1000
    # Initialize lists to store metrics
    train_accuracies = []
    test_accuracies = []
    losses = []

    # 创建 TensorDataset 和 DataLoader
    train_dataset = TensorDataset(train_data_tensor, train_label_tensor)
    train_loader = DataLoader(train_dataset, batch_size=BATCH_SIZE, shuffle=True)

    for epoch in range(FLAGS.max_steps):
        model.train()  # Set model to training mode

        for X_batch, y_batch in train_loader:
            # print(f"about y_barch:{y_batch.shape}, {y_batch}")
            optimizer.zero_grad()  # Clear gradients
            outputs = model(X_batch)  # Forward pass

            # Check the shape of y_train_tensor
            # print(f"y_train_tensor shape: {y_train_tensor.shape}")
            final_loss = loss(outputs, y_batch)  # Calculate loss
            # print(f"first_final_loss:{final_loss.item():.4f}")
            final_loss.backward()  # Backward pass
            optimizer.step()  # Update weights
        
        # Record the last batch loss for this epoch
        losses.append(final_loss.item())

        if (epoch + 1) % FLAGS.eval_freq == 0:
            model.eval()  # Set model to evaluation mode
            with torch.no_grad():
                train_predictions = model(train_data_tensor)
                test_predictions = model(test_data_tensor)

                print(f"train_prediction:{train_predictions.shape},{train_predictions}")

                # Compute accuracy
                train_accuracy = calculate_accuracy(
                    train_predictions.to(device), train_label_tensor.to(device)
                )
                test_accuracy = calculate_accuracy(
                    test_predictions.to(device), test_label_tensor.to(device)
                )
                
                # Record accuracy
                train_accuracies.append(train_accuracy)
                test_accuracies.append(test_accuracy)
                
                print(
                    f"Epoch [{epoch + 1}/{FLAGS.max_steps}], "
                    f"Loss: {final_loss.item():.4f}, "
                    f"Train Accuracy: {train_accuracy * 100:.2f}%, "
                    f"Test Accuracy: {test_accuracy * 100:.2f}%"
                )
    # Plot accuracy and loss
    plt.figure(figsize=(10, 5))

    # Plot training and test accuracy
    plt.subplot(1, 2, 1)
    plt.plot(range(len(train_accuracies)), train_accuracies, label='Train Accuracy')
    plt.plot(range(len(test_accuracies)), test_accuracies, label='Test Accuracy')
    plt.xlabel('Evaluation Epoch')
    plt.ylabel('Accuracy')
    plt.title('Train and Test Accuracy')
    plt.legend()

    # Plot loss
    plt.subplot(1, 2, 2)
    plt.plot(range(len(losses)), losses, label='Loss')
    plt.xlabel('Epoch')
    plt.ylabel('Loss')
    plt.title('Training Loss')
    plt.legend()

    # Save the plot
    plt.tight_layout()
    plt.savefig('accuracy_loss_plot.png')  # Save to local file
    print("Plot saved as accuracy_loss_plot.png")


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
    train()
