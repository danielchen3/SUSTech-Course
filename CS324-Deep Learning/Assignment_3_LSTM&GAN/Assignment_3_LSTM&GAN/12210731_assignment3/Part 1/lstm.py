from __future__ import absolute_import
from __future__ import division
from __future__ import print_function

import torch
import torch.nn as nn

################################################################################


class LSTM(nn.Module):

    def __init__(self, seq_length, input_dim, hidden_dim, output_dim):
        super(LSTM, self).__init__()
        # Initialization
        self.input_dim = input_dim
        self.hidden_dim = hidden_dim
        self.output_dim = output_dim
        self.seq_length = seq_length

        # Initialize weight matrices
        self.W_f = nn.Parameter(
            torch.randn(hidden_dim, input_dim + hidden_dim)
        )  # Forget gate weights
        self.W_i = nn.Parameter(
            torch.randn(hidden_dim, input_dim + hidden_dim)
        )  # Input gate weights
        self.W_C = nn.Parameter(
            torch.randn(hidden_dim, input_dim + hidden_dim)
        )  # State weights
        self.W_o = nn.Parameter(
            torch.randn(hidden_dim, input_dim + hidden_dim)
        )  # Output gate weights

        # Initialize bias terms
        self.b_f = nn.Parameter(torch.zeros(hidden_dim))  
        self.b_i = nn.Parameter(torch.zeros(hidden_dim)) 
        self.b_C = nn.Parameter(torch.zeros(hidden_dim)) 
        self.b_o = nn.Parameter(torch.zeros(hidden_dim))  

        # Fully connected layer
        self.fc = nn.Linear(hidden_dim, output_dim)

    def forward(self, x):
        batch_size, _, _ = x.shape
        
        device = x.device

        # Initialize cell state and hidden state
        h_t = torch.zeros(batch_size, self.hidden_dim, device=device) 
        C_t = torch.zeros(batch_size, self.hidden_dim, device=device)  

        for t in range(self.seq_length):
            x_t = x[:, t, :]  
            combined = torch.cat(
                (h_t, x_t), dim=1
            )  

        
            f_t = torch.sigmoid(
                torch.matmul(combined, self.W_f.T) + self.b_f
            ) 
            i_t = torch.sigmoid(
                torch.matmul(combined, self.W_i.T) + self.b_i
            )
            C_hat_t = torch.tanh(
                torch.matmul(combined, self.W_C.T) + self.b_C
            ) 
            o_t = torch.sigmoid(
                torch.matmul(combined, self.W_o.T) + self.b_o
            ) 

            # Update
            C_t = f_t * C_t + i_t * C_hat_t  
            h_t = o_t * torch.tanh(C_t) 

        output = self.fc(h_t)
        return output

