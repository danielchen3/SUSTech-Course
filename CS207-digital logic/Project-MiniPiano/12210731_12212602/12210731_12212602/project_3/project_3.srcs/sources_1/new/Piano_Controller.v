`timescale 1ns / 1ps
`include "Connstants.vh"
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2023/12/11 19:53:36
// Design Name: 
// Module Name: Piano_Controller
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


module Piano_Controller(
    input clk,
    input rst_n,
    input switch_tone,
    input [`key_WIDTH - 1:0] key_pitch,
    input changemode,
    input start_pause,
    input changeflag,
    input changeuser,
    input save,
    output beep,
    output [`led_WIDTH  - 1:0]led,
    output sd,
    output  [`segenable_WIDTH - 1:0] tub_sel_out, //Select location
    output  [`seg_WIDTH - 1:0] light_seg, //Select section
    output  [`seg_WIDTH - 1:0] light_seg1
    );
    //debounce¡¤
//    wire rst_n;//Debounced rst
//    Debounce rst0(clk,rst,rst_n);
    wire s_t;//Debounced switch_tone
    Debounce switch_tone0(clk,switch_tone,s_t);
    wire cm;//Debounced changemode
    Debounce changemode0(clk,changemode,cm);
    wire cf;//Debounced changeflag
    Debounce changeflag0(clk,changeflag,cf);
    wire cu;//Debounced changeuser
    Debounce changeuser0(clk,changeuser,cu);
    wire save_user;//Debounced save
    Debounce save_userd(clk,save,save_user);
    //
    
    wire [`notes_WIDTH  - 1:0] notes_auto;
    wire [`notes_WIDTH  - 1:0] notes_free;
    wire [`notes_WIDTH  - 1:0] notes_learn;
    
    reg [`mode_WIDTH - 1:0]mode;
  
    reg [`notes_WIDTH  - 1:0] notes;

    reg [`seginput_WIDTH - 1:0]seg_in;
    
    assign sd = 1'b1;
    //free-play
    Free_play freeplay(clk,rst_n,s_t,key_pitch,notes_free);
    //auto_play
    wire [`seginput_WIDTH - 1:0]song_name;
    Auto_play autoplay(clk,rst_n,cf,start_pause,notes_auto, song_name);
    //study mode
    wire [`seginput_WIDTH - 1:0]grades_rank;
    Studymode study(clk,rst_n,start_pause,key_pitch, cf, cu, save_user, notes_learn, grades_rank);
    

    //change mode
    always @(posedge cm)begin
        if(~rst_n)begin
            mode <= 2'b00;
        end 
        else if(mode == 2'b10)begin
            mode <= 2'b00;
        end
        else if(mode == 2'b00) begin
            mode <= 2'b01;        
        end
        else if(mode == 2'b01) begin
            mode <= 2'b10;  
        end
    end
    
    always @(*)begin
        if(mode == 2'b00) begin
        
        notes = notes_learn;       
        seg_in = grades_rank;
        end
        else if(mode == 2'b01)begin
        notes = notes_free;
        seg_in = {5'd15, 5'd25, 5'd14, 5'd14,  5'd31, 5'd31 , 5'd31 , 5'd31};//free
        end
        else if(mode == 2'b10)begin
        notes = notes_auto;
        seg_in = song_name;
        end
    end


    Buzzer buzzer(clk,notes,beep);
    Led led_light(notes,led);
    Light_seg autolight0(clk,rst_n,seg_in[39:20],light_seg,tub_sel_out[7:4]);
    Light_seg autolight1(clk,rst_n,seg_in[19:0],light_seg1,tub_sel_out[3:0]);
endmodule
