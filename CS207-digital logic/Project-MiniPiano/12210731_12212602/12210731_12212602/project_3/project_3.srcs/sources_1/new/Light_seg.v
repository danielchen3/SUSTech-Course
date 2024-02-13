`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2023/12/09 21:14:43
// Design Name: 
// Module Name: Light_seg
// Project Name: 
// Target Devices: 
// Tool Versions: 
// Description: 
// 
// Dependencies: ;+
// 
// Revision:
// Revision 0.01 - File Created
// Additional Comments:
// 
//////////////////////////////////////////////////////////////////////////////////


module Light_seg( 
input clk,
input rst_n,
input [`seginput_WIDTH/2 - 1:0] x,
output reg [`seg_WIDTH - 1:0] seg_out, 
output reg [`segenable_WIDTH/2 - 1:0] seg_en
); 
reg [`segin_WIDTH - 1:0]in;


wire [`s_WIDTH - 1:0]s;//time of one seg light   `define s_WIDTH          2
reg[`d_WIDTH - 1:0]clkdiv;//`define d_WIDTH          21
//counter decide how long each digital tube will be lighting
always @(posedge clk, negedge rst_n) begin
    if(~rst_n)
        clkdiv<=0;
    else
        clkdiv<=clkdiv+1;
end
assign s = clkdiv[20:19];//2bit represent four positions
//select location
always@*begin
    seg_en = 4'b0000;
    seg_en[s]=1;
end
//select content
always@*begin
    case(s)
        0:in=x[4:0];
        1:in=x[9:5];
        2:in=x[14:10];
        3:in=x[19:15];
        default:;
    endcase
end
//in:0-30 corresponds to the following 31 cases, and some alphanumeric tubes cannot be displayed
always @ * begin 
case(in) 
//nums
5'b0_0000: seg_out = 8'b1111_1100; //"0" : abcdef_ _ 
5'b0_0001: seg_out = 8'b0110_0000; //"1": _bc_ _ _ _ _ _ 
5'b0_0010: seg_out = 8'b1101_1010; //"2": ab_de_g_ 
5'b0_0011: seg_out = 8'b1111_0010; //"3": abcd_ _ g _ 
5'b0_0100: seg_out = 8'b0110_0110; //"4": _bc _ _fg_ 
5'b0_0101: seg_out = 8'b1011_0110; //"5": a_cd_fg_ 
5'b0_0110: seg_out = 8'b1011_1110; //"6": a_cdefg_ 
5'b0_0111: seg_out = 8'b1110_0000; //"7": abc_ _ _ _ _ 
5'b0_1000: seg_out = 8'b1111_1110; //"8": abcdefg_ 
5'b0_1001: seg_out = 8'b1110_0110; //"9": abc_ _ fg_ 
//letters Note: The letters k, m, v, w, x, z cannot be represented
5'b0_1010: seg_out = 8'b1110_1110; //"A"10
5'b0_1011: seg_out = 8'b0011_1110; //"b"11
5'b0_1100: seg_out = 8'b1001_1100; //"C"12
5'b0_1101: seg_out = 8'b0111_1010; //"d"13
5'b0_1110: seg_out = 8'b1001_1110; //"E"14
5'b0_1111: seg_out = 8'b1000_1110; //"F"15
5'b1_0000: seg_out = 8'b1011_1100; //"G"16
5'b1_0001: seg_out = 8'b0110_1110; //"H"17
5'b1_0010: seg_out = 8'b1010_0000; //"i"18
5'b1_0011: seg_out = 8'b1011_0000; //"j"19
5'b1_0100: seg_out = 8'b0001_1100; //"L"20
5'b1_0101: seg_out = 8'b1110_1100; //"N"21
5'b1_0110: seg_out = 8'b1111_1100; //"O"22
5'b1_0111: seg_out = 8'b1100_1110; //"P"23
5'b1_1000: seg_out = 8'b1110_0110; //"q"24 .
5'b1_1001: seg_out = 8'b0000_1010; //"r"25
5'b1_1010: seg_out = 8'b1011_0110; //"S"26 .
5'b1_1011: seg_out = 8'b0001_1110; //"t"27
//5'b1_1100: seg_out = 8'b0111_1100; //"U"28
//5'b1_1101: seg_out = 8'b0111_0110; //"y"29
//5'b1_1110: seg_out = 8'b0000_0010; //"-"30: _ _ _ _ _ _ g _ 
5'b1_1100: seg_out = 8'b0110_0001; //"1."28
5'b1_1101: seg_out = 8'b1101_1011; //"2."29
5'b1_1110: seg_out = 8'b1111_0011; //"3."30
5'b1_1111: seg_out = 8'b0000_0000; //" "31
default: seg_out = 8'b0000_0000; //" ": 
endcase 
end 


endmodule