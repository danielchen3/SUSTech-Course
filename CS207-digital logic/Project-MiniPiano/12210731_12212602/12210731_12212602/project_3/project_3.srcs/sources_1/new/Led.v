`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2023/12/15 17:35:33
// Design Name: 
// Module Name: Led
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


module Led(
    input [4:0] notes,
    output reg [6:0] led
    );
    always @(*)
    begin
    if(notes == 1'b0)begin
        led = 7'b0000000; 
        end else begin
      case (notes % 7)
        1: led = 7'b1000000;   
        2: led = 7'b0100000;   
        3: led = 7'b0010000;   
        4: led = 7'b0001000;   
        5: led = 7'b0000100;   
        6: led = 7'b0000010;   
        0: led = 7'b0000001;   
        default: led = 7'b0000000;   
      endcase
      end
    end
endmodule
