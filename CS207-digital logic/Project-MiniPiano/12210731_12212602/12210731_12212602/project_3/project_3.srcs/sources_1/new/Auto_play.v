`timescale 1ns / 1ps
`include "Connstants.vh"
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2023/12/17 14:06:45
// Design Name: 
// Module Name: Auto_play
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


module Auto_play(
    input clk,
    input rst_n,
    input cf,
    input start_pause,
    output [`notes_WIDTH - 1:0] notes,
    output reg [`seginput_WIDTH - 1:0]seg_in//songs name
    );
    //change song
    reg [`flag_WIDTH - 1:0]flag;
    always @(posedge cf)begin
        if(~rst_n)begin
            flag <= 2'b00;
            end 
            else if(flag == 2'b10)begin
                flag <= 2'b00;
            end 
            else if(flag == 2'b00)begin
                flag <= 2'b01;
            end
            else if(flag == 2'b01)begin
                flag <= 2'b10;
            end
        end
    
    reg[`cntsong_WIDTH - 1:0] cnt_flag0;
    reg[`cntsong_WIDTH - 1:0] cnt_flag1;
    reg[`cntsong_WIDTH - 1:0] cnt_flag2;
    reg[`cntsong_WIDTH - 1:0] cnt;

    
    wire clk_out;
    Fre_div frediv(clk,clk_out);
    //for reset button or recycle or pause
    always @(posedge clk_out)begin
            if(~rst_n)begin
                cnt_flag0<=8'd0;
            end
            // when it comes to the end of the first song
            else if(flag==2'b00 && cnt==8'd95) begin
                cnt_flag0<=8'd0;
            end
            else begin
                cnt_flag0<=cnt_flag0+1;
            end
        end
        
    //for reset button or recycle or pause
        always @(posedge clk_out)begin
                if(~rst_n)begin
                    cnt_flag1<=8'd0;
                end
                // when it comes to the end of the first song
                else if(flag==2'b01 && cnt==8'd77) begin
                    cnt_flag1<=8'd0;
                end
                else begin
                    cnt_flag1<=cnt_flag1+1;
                end
            end        

    //for reset button or recycle or pause
    always @(posedge clk_out)begin
            if(~rst_n)begin
                cnt_flag2<=8'd0;
            end
            // when it comes to the end of the first song
            else if(flag==2'b10 && cnt==8'd81) begin
                cnt_flag2<=8'd0;
            end
            else begin
                cnt_flag2<=cnt_flag2+1;
            end
        end        

        
        always @(*)begin
            if(flag == 2'b00) begin 
            cnt = cnt_flag0;
            seg_in = {5'd26, 5'd27, 5'd10, 5'd25,  5'd31, 5'd31 , 5'd31 , 5'd31};//star
            end
            else if(flag == 2'b01)begin 
            cnt = cnt_flag1;
            seg_in = {5'd27, 5'd18, 5'd16, 5'd16,  5'd14, 5'd25 , 5'd31 , 5'd31};//tigger
            end
            else if(flag == 2'b10)begin 
            cnt = cnt_flag2;
            seg_in = {5'd11, 5'd14, 5'd10, 5'd25,  5'd31, 5'd31 , 5'd31 , 5'd31};//bear
            end
        end
        
        //then after we get cnt and flag and the time we pause, we use library to get the notes accordingly
       Library lib(flag,cnt,start_pause,notes);

        
    
    
endmodule
