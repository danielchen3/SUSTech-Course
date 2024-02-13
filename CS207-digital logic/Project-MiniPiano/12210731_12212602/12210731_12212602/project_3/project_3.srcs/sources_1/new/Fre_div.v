`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2023/12/11 13:15:33
// Design Name: 
// Module Name: Fre_div
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


module Fre_div(
    input clk,
    output reg clk_out
    );
    
    parameter period = 50000000;
    reg [40:0] cnt;
    always @(posedge clk)
    begin
//        if(rst_n)begin
//            cnt<=0;
//            clk_out<=0;
//        end
//        else
            if(cnt==((period>>1)-1)) begin
                clk_out <= ~clk_out;
                cnt<=0;
            end
            else begin
                cnt<=cnt+1;
            end
    end
endmodule
