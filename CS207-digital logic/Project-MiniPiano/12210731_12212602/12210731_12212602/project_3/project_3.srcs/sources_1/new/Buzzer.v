`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2023/12/11 13:14:01
// Design Name: 
// Module Name: Buzzer
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


module Buzzer(
    input clk,
    input [`notes_WIDTH - 1:0] note,//001 do 010 re 011 mi 100 fa 101 so 110 la 111 xi
    output wire speaker
    );
    
    wire [`one_note_WIDTH - 1:0] notes[`all_notes_WIDTH - 1:0];
    reg[`one_note_WIDTH - 1:0] counter;
    reg pwm;
    //frequencies of mid do...
        assign notes[1] = 32'd93941;
        assign notes[2] = 32'd85136;
        assign notes[3] = 32'd75838;
        assign notes[4] = 32'd71582;
        assign notes[5] = 32'd63776;
        assign notes[6] = 32'd56818;
        assign notes[7] = 32'd50618;
        
        //frequencies of low do, low re, low mi...
        assign notes[8] = 32'd191110;
        assign notes[9] = 32'd170259;
        assign notes[10] = 32'd151685;
        assign notes[11] = 32'd143172;
        assign notes[12] = 32'd127554;
        assign notes[13] = 32'd113636;
        assign notes[14] = 32'd101239;
        
        //fequencies of high do, high re, high mi...
        assign notes[15] = 32'd47778;
        assign notes[16] = 32'd42567;
        assign notes[17] = 32'd37921;
        assign notes[18] = 32'd36498;
        assign notes[19] = 32'd31888;
        assign notes[20] = 32'd28409;
        assign notes[21] = 32'd25309;
    
    //low
//    assign notes[1] = 381680;
//    assign notes[2] = 340136;
//    assign notes[3] = 303030;
//    assign notes[4] = 285714;
//    assign notes[5] = 255102;
//    assign notes[6] = 227273;
//    assign notes[7] = 202429;
    
//    //mid
//    assign notes[8] = 381680/2;
//    assign notes[9] = 340136/2;
//    assign notes[10] = 303030/2;
//    assign notes[11] = 285714/2;
//    assign notes[12] = 255102/2;
//    assign notes[13] = 227273/2;
//    assign notes[14] = 202429/2;
    
//    //high
//    assign notes[15] = 381680/4;
//    assign notes[16] = 340136/4;
//    assign notes[17] = 303030/4;
//    assign notes[18] = 285714/4;
//    assign notes[19] = 255102/4;
//    assign notes[20] = 227273/4;
//    assign notes[21] = 202429/4;
    
    initial begin
        pwm = 0;
    end
    
    always @(posedge clk) begin
        if(counter<notes[note]||note==1'b0) begin
            counter<=counter+1'b1;
            end
         else begin
            pwm = ~pwm;
            counter<=0;
         end
    end
    assign speaker = pwm;
    
//    Fre_div #(382200/2) low_do(clk,low[0]);
//    Fre_div #(340500/2) low_re(clk,low[1]);
//    Fre_div #(303300/2) low_mi(clk,low[2]);
//    Fre_div #(286300/2) low_fa(clk,low[3]);
//    Fre_div #(255100/2) low_so(clk,low[4]);
//    Fre_div #(227200/2) low_la(clk,low[5]);
//    Fre_div #(202500/2) low_xi(clk,low[6]);
    
//    Fre_div #(199100/2) mid_do(clk,mid[0]);
//    Fre_div #(170200/2) mid_re(clk,mid[1]);
//    Fre_div #(151600/2) mid_mi(clk,mid[2]);
//    Fre_div #(143100/2) mid_fa(clk,mid[3]);
//    Fre_div #(127500/2) mid_so(clk,mid[4]);
//    Fre_div #(113600/2) mid_la(clk,mid[5]);
//    Fre_div #(101200/2) mid_xi(clk,mid[6]);
    
//    Fre_div #(95600/2) high_do(clk,high[0]);
//    Fre_div #(85100/2) high_re(clk,high[1]);
//    Fre_div #(75800/2) high_mi(clk,high[2]);
//    Fre_div #(71500/2) high_fa(clk,high[3]);
//    Fre_div #(63700/2) high_so(clk,high[4]);
//    Fre_div #(56800/2) high_la(clk,high[5]);
//    Fre_div #(50600/2) high_xi(clk,high[6]);
    
    
    
endmodule
