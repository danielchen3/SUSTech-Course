import numpy as np


class Linear(object):
    def __init__(self, in_features, out_features):
        """
        Initializes a linear (fully connected) layer.
        TODO: Initialize weights and biases.
        - Weights should be initialized to small random values (e.g., using a normal distribution).
        - Biases should be initialized to zeros.
        Formula: output = x * weight + bias
        """
        # Initialize weights and biases with the correct shapes.
        self.params = {"weight": None, "bias": None}
        self.grads = {"weight": None, "bias": None}
        # initialize with relative small numbers
        self.params["weight"] = np.random.randn(in_features, out_features) * 0.01
        self.params["bias"] = np.zeros(out_features)

    def forward(self, x):
        """
        Performs the forward pass using the formula: output = xW + b
        TODO: Implement the forward pass.
        """
        self.x = x
        return x @ self.params["weight"] + self.params["bias"]

    def backward(self, dout):
        """
        Backward pass to calculate gradients of loss w.r.t. weights and inputs.
        TODO: Implement the backward pass.
        """

        # print(f"dout shape before computation: {dout.shape}")
        # print(f"x shape: {self.x.shape}")

        self.grads["weight"] = self.x.T @ dout
        self.grads["bias"] = np.sum(dout, axis=0)

        return dout @ self.params["weight"].T


class ReLU(object):
    def forward(self, x):
        """
        Applies the ReLU activation function element-wise to the input.
        Formula: output = max(0, x)
        TODO: Implement the forward pass.
        """
        self.if_back = (x > 0)
        return np.maximum(0, x)

    def backward(self, dout):
        """
        Computes the gradient of the ReLU function.
        TODO: Implement the backward pass.
        Hint: Gradient is 1 for x > 0, otherwise 0.
        """
        return dout * self.if_back


class SoftMax(object):
    def forward(self, x):
        """
        Applies the softmax function to the input to obtain output probabilities.
        Formula: softmax(x_i) = exp(x_i) / sum(exp(x_j)) for all j
        TODO: Implement the forward pass using the Max Trick for numerical stability.
        """
        x_shifted = x - np.max(x, axis=1, keepdims=True)  # To avoid overflow
        exp_x = np.exp(x_shifted)
        self.probs = exp_x / np.sum(exp_x, axis=1, keepdims=True)  # compute softmax
        return self.probs

    def backward(self, dout):
        """
        The backward pass for softmax is often directly integrated with CrossEntropy for simplicity.
        TODO: Keep this in mind when implementing CrossEntropy's backward method.
        """
        return None


class CrossEntropy(object):
    def forward(self, x, y):
        """
        Computes the CrossEntropy loss between predictions and true labels.
        Formula: L = -sum(y_i * log(p_i)), where p is the softmax probability of the correct class y.
        TODO: Implement the forward pass.
        """
        self.y = y
        return -np.sum(y * np.log(x + 1e-12))  # Add a relative small number to avoid the occurrence of log(0)

    def backward(self, x, y):
        """
        Computes the gradient of CrossEntropy loss with respect to the input.
        TODO: Implement the backward pass.
        Hint: For softmax output followed by cross-entropy loss, the gradient simplifies to: p - y.
        """
        return x - y
