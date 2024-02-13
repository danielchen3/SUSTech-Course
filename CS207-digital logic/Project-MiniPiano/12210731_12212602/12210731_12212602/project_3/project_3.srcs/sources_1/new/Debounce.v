`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2023/12/16 23:05:47
// Design Name: 
// Module Name: Debounce
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: 
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////

module Debounce (
  input wire clk, 
  input wire button, 
  output wire debounced
);
reg q1, q2;

always @(posedge clk) begin
  q1 <= button;
  q2 <= q1;
end

assign debounced = q1 & (~q2);
 
endmodule

