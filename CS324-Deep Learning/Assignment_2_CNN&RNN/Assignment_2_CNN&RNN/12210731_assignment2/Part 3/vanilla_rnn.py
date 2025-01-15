from __future__ import absolute_import
from __future__ import division
from __future__ import print_function

import torch
import torch.nn as nn


class VanillaRNN(nn.Module):

    def __init__(self, input_length, input_dim, hidden_dim, output_dim):
        super(VanillaRNN, self).__init__()
        # Initialization here ...
        self.input_length = input_length    
        self.hidden_dim = hidden_dim
        self.Whx = nn.Linear(input_dim, hidden_dim, bias=False)
        self.Whh = nn.Linear(hidden_dim, hidden_dim, bias=True)

        self.bh = nn.Parameter(torch.zeros(hidden_dim))
        self.Wph = nn.Linear(hidden_dim, output_dim, bias=True)

    def forward(self, x):
        # Implementation here ...
        ## print(f"forward been activated!")
        batch_size, input_length, _ = x.size()

        # initialization
        h = torch.zeros(batch_size, self.hidden_dim, device=x.device)
        
        for t in range(input_length):
            x_t = x[:, t, :]
            h = torch.tanh(self.Whx(x_t) + self.Whh(h) + self.bh)

        # activation
        o = self.Wph(h)
        y_hat = torch.softmax(o, dim=1)

        return y_hat

    # add more methods here if needed
