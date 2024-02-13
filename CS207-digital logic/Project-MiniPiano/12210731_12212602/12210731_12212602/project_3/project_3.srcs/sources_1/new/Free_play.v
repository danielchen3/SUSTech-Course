`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2023/12/11 22:40:03
// Design Name: 
// Module Name: Free_play
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


module Free_play(
    input clk,
    input rst_n,
    input s_t,
    input [`key_WIDTH - 1:0] key_pitch,
    output reg [`notes_WIDTH - 1:0] notes
    );

     //switch tone by button
     reg [`switchtone_WIDTH - 1:0]switch_tone;
     
     always @ (posedge s_t)begin
        if(~rst_n)begin
            switch_tone <= 3'b000;
        end else if(switch_tone==3'b000) begin
            switch_tone <= 3'b100;
        end else if(switch_tone==3'b100) begin
            switch_tone <= 3'b001;
        end else if(switch_tone==3'b001) begin
            switch_tone <= 3'b010;
        end else if(switch_tone==3'b010) begin
            switch_tone <= 3'b100;
        end 
     end
     
     
     always @(posedge clk) begin
        if(~rst_n) begin
            notes<=5'd0;
        end
        else
        case(switch_tone)
            //mid
            3'b010,3'b000: begin
                if(key_pitch == 7'b000000) notes<=5'd0;
                else if(key_pitch == 7'b1000000) notes<=5'd1;
                else if(key_pitch == 7'b0100000) notes<=5'd2;
                else if(key_pitch == 7'b0010000) notes<=5'd3;
                else if(key_pitch == 7'b0001000)notes<=5'd4;
                else if(key_pitch == 7'b0000100) notes<=5'd5;
                else if(key_pitch == 7'b0000010) notes<=5'd6;
                else if(key_pitch == 7'b0000001) notes<=5'd7;
                else notes<=5'd0;
            end
            //low
            3'b001: begin
                 if(key_pitch == 7'b000000) notes<=5'd0;
                 else if(key_pitch == 7'b1000000) notes<=5'd8;
                 else if(key_pitch == 7'b0100000) notes<=5'd9;
                 else if(key_pitch == 7'b0010000) notes<=5'd10;
                 else if(key_pitch == 7'b0001000) notes<=5'd11;
                 else if(key_pitch == 7'b0000100) notes<=5'd12;
                 else if(key_pitch == 7'b0000010) notes<=5'd13;
                 else if(key_pitch == 7'b0000001) notes<=5'd14;
                 else notes<=5'd0;
             end
             //high
             3'b100: begin 
                 if(key_pitch == 7'b000000) notes<=5'd0;
                 else if(key_pitch == 7'b1000000) notes<=5'd15;
                 else if(key_pitch == 7'b0100000) notes<=5'd16;
                 else if(key_pitch == 7'b0010000) notes<=5'd17;
                 else if(key_pitch == 7'b0001000) notes<=5'd18;
                 else if(key_pitch == 7'b0000100) notes<=5'd19;
                 else if(key_pitch == 7'b0000010) notes<=5'd20;
                 else if(key_pitch == 7'b0000001) notes<=5'd21;
                 else notes<=5'd0;
              end
              default: notes<=5'd0;
        endcase
     end
 
    
endmodule
