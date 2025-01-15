import argparse
import numpy as np
from mlp_numpy import MLP
from modules import CrossEntropy, Linear
from sklearn.datasets import make_moons
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt

# Default constants
DNN_HIDDEN_UNITS_DEFAULT = "20"
LEARNING_RATE_DEFAULT = 1e-2
MAX_EPOCHS_DEFAULT = 1500  # adjust if you use batch or not
EVAL_FREQ_DEFAULT = 10
SGD_DEFAULT = True


def accuracy(predictions, targets):
    """
    Computes the prediction accuracy, i.e., the percentage of correct predictions.

    Args:
        predictions: 2D float array of size [number_of_data_samples, n_classes]
        targets: 2D int array of size [number_of_data_samples, n_classes] with one-hot encoding

    Returns:
        accuracy: scalar float, the accuracy of predictions as a percentage.
    """
    # TODO: Implement the accuracy calculation
    # Hint: Use np.argmax to find predicted classes, and compare with the true classes in targets
    # Get predicted class labels by taking argmax over classes (axis 1)
    pred_labels = np.argmax(predictions, axis=1)
    # Get true class labels from one-hot encoded targets
    true_labels = np.argmax(targets, axis=1)
    # Calculate the percentage of correct predictions
    accuracy = np.mean(pred_labels == true_labels)
    return accuracy


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


def train(dnn_hidden_units, learning_rate, max_steps, eval_freq, sgd=False):
    """
    Performs training and evaluation of MLP model.

    Args:
        dnn_hidden_units: Comma separated list of number of units in each hidden layer
        learning_rate: Learning rate for optimization
        max_steps: Number of epochs to run trainer
        eval_freq: Frequency of evaluation on the test set
        NOTE: Add necessary arguments such as the data, your model...
    """
    # TODO: Load your data here
    X_train, X_test, y_train, y_test = load_data()

    dnn_hidden_units = list(map(int, dnn_hidden_units.split(",")))

    # TODO: Initialize your MLP model and loss function (CrossEntropy) here
    input_dim = X_train.shape[1]
    output_dim = y_train.shape[1]

    print(f"input_dim:{input_dim}")
    print(f"output_dim:{output_dim}")
    model = MLP(input_dim, dnn_hidden_units, output_dim)
    loss_function = CrossEntropy()

    # 创建用于存储训练和测试 Loss 的列表
    train_losses = []
    test_losses = []
    train_accuracies = []
    test_accuracies = []

    for step in range(max_steps):
        # print(f"s")
        if sgd:
            indices = np.random.permutation(X_train.shape[0])
            X_train_shuffled = X_train[indices]
            y_train_shuffled = y_train[indices]

            batch_size = 200

            for i in range(
                0, X_train.shape[0], batch_size
            ):  # Iterate over each data sample
                x_i = X_train_shuffled[i : i + batch_size]  # Get the single sample
                y_i = y_train_shuffled[
                    i : i + batch_size
                ]  # Get the corresponding label

                # 1. Forward pass
                prediction = model.forward(x_i)

                # 2. Compute loss
                loss = loss_function.forward(prediction, y_i)

                # 3. Backward pass (compute gradients)
                dout = loss_function.backward(prediction, y_i)
                model.backward(dout)

                # 4. Update weights
                for layer in reversed(model.layers):
                    if isinstance(layer, Linear):
                        layer.params["weight"] -= learning_rate * layer.grads["weight"]
                        layer.params["bias"] -= learning_rate * layer.grads["bias"]

                # Record Loss
                train_losses.append(loss)

            # In the end of every round, compute the accuracy of the model
            train_predictions = model.forward(X_train)
            train_accuracy = accuracy(
                train_predictions, y_train
            )  # 计算整个训练集的准确率
            train_accuracies.append(train_accuracy)
        else:
            # TODO: Implement the training loop
            # 1. Forward pass
            # 2. Compute loss
            # 3. Backward pass (compute gradients)
            # 4. Update weights
            prediction = model.forward(X_train)

            print(f"prediction:{prediction.shape}, {prediction}")
            print(f"y_train:{y_train.shape},{y_train}")
            
            # final output layer propagation
            loss = loss_function.forward(prediction, y_train)
            # print(f"lossx:{loss}")
            dloss = loss_function.backward(prediction, y_train)
            model.backward(dloss)

            for layer in reversed(model.layers):
                if isinstance(layer, Linear):
                    layer.params["weight"] -= learning_rate * layer.grads["weight"]
                    layer.params["bias"] -= learning_rate * layer.grads["bias"]

            train_losses.append(loss)

            train_accuracy = accuracy(prediction, y_train)
            train_accuracies.append(train_accuracy)

        if step % eval_freq == 0 or step == max_steps - 1:
            # TODO: Evaluate the model on the test set
            # 1. Forward pass on the test set
            # 2. Compute loss and accuracy
            test_predictions = model.forward(X_test)
            test_loss = loss_function.forward(test_predictions, y_test)
            test_accuracy = accuracy(test_predictions, y_test)
            # 记录测试集 Loss
            test_losses.append(test_loss)
            test_accuracies.append(test_accuracy)
            print(
                f"Step: {step}, Loss: {test_loss:.4f}, Accuracy: {test_accuracy * 100:.2f}%"
            )

    print("Training complete!")

    if sgd == False:
        # 训练结束后绘制 Loss 变化图
        plt.figure(figsize=(10, 6))
        plt.plot(train_losses, label="Training Loss")
        # plt.plot(range(0, max_steps + 1, eval_freq), test_losses, label="Test Loss")
        plt.xlabel("Step")
        plt.ylabel("Loss")
        plt.title("Loss Change Over Steps")
        plt.legend()
        plt.show()

    # 训练结束后绘制 test 变化图
    plt.figure(figsize=(10, 6))
    plt.plot(train_accuracies, label="Training Accuracy")
    plt.plot(
        range(0, max_steps + 1, eval_freq), test_accuracies, label="Test accuracies"
    )
    plt.xlabel("Step")
    plt.ylabel("Accuracy")
    plt.title("Accuracy changes over steps")
    plt.legend()
    plt.show()


def main():
    """
    Main function.
    """
    # Parsing command line arguments
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
        help="Number of epochs to run trainer",
    )
    parser.add_argument(
        "--eval_freq",
        type=int,
        default=EVAL_FREQ_DEFAULT,
        help="Frequency of evaluation on the test set",
    )
    parser.add_argument(
        "--if_sgd",
        type=bool,
        default=SGD_DEFAULT,
        help="If using stochastic gradient descent method",
    )
    FLAGS = parser.parse_known_args()[0]

    train(
        FLAGS.dnn_hidden_units,
        FLAGS.learning_rate,
        FLAGS.max_steps,
        FLAGS.eval_freq,
        FLAGS.if_sgd,
    )


if __name__ == "__main__":
    main()
