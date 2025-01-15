import numpy as np

class Perceptron(object):

    def __init__(self, n_inputs, max_epochs=100, learning_rate=0.01):
        """
        Initializes the perceptron object.
        - n_inputs: Number of inputs.
        - max_epochs: Maximum number of training cycles.
        - learning_rate: Magnitude of weight changes at each training cycle.
        - weights: Initialize weights (including bias).
        """
        # Initialization
        self.n_inputs = n_inputs  
        self.max_epochs = max_epochs  
        self.learning_rate = learning_rate 
        # after adding bias, needs to add 1 to its dimension
        self.weights = np.zeros(n_inputs + 1)  # Fill in: Initialize weights with zeros 
        
    def forward(self, input_vec):
        """
        Predicts label from input.
        Args:
            input_vec (np.ndarray): Input array of training data, input vec must be all samples
        Returns:
            int: Predicted label (1 or -1) or Predicted lables.
        """
        # Add bias term (1) to the input vector
        input_with_bias = np.append(input_vec, 1)
        # Compute the dot product.
        activation = np.dot(self.weights, input_with_bias)
        # Return the predicted label (1 if positive, -1 if negative)
        return 1 if activation >= 0 else -1
        
    def train(self, training_inputs, labels):
        """
        Trains the perceptron.
        Args:
            training_inputs (list of np.ndarray): List of numpy arrays of training points.
            labels (np.ndarray): Array of expected output values for the corresponding point in training_inputs.
        """

        N = len(training_inputs)
        train_loss = []
        # we need max_epochs to train our model
        for epoch in range(self.max_epochs): 
            """
                What we should do in one epoch ? 
                you are required to write code for 
                1.do forward pass
                2.calculate the error
                3.compute parameters' gradient 
                4.Using gradient descent method to update parameters(not Stochastic gradient descent!,
                please follow the algorithm procedure in "perceptron_tutorial.pdf".)
            """

            # print(f"weights{self.weights}")
            misclassfied = []
            for i in range(N):
                input_vec = training_inputs[i]
                label = labels[i]
                prediction = self.forward(input_vec)

                if label * prediction < 0:
                    misclassfied.append(i)

            # if misclassification occur, then adjust the weights using gradient descent
            if len(misclassfied) > 0:
                gradient = np.zeros(self.n_inputs + 1)
                for i in misclassfied:
                    input_with_bias = np.append(training_inputs[i] , 1)
                    gradient -= labels[i] * input_with_bias
                
                gradient /= len(misclassfied)
                self.weights = self.weights - self.learning_rate * gradient

            # Optionally, print the number of misclassified points for debugging
            print(f'Epoch {epoch + 1}, Misclassified points: {len(misclassfied)}')
        
        # 5. Return the final weights after training
        return self.weights
    
    def test(self, test_inputs, test_labels):
        correct_predictions = 0
        total_samples = len(test_inputs)
        for i in range(total_samples):
            prediction = self.forward(test_inputs[i])
            if prediction == test_labels[i]:
                correct_predictions += 1
        accuracy = correct_predictions / total_samples
        return accuracy
            
