`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2023/12/18 16:55:18
// Design Name: 
// Module Name: Studymode
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


module Studymode(
    input clk,
    input rst_n,
    input pause_start,
    input [`key_WIDTH - 1:0] key_pitch,
    input cf,
    input cu,
    input save,
    output [`notes_WIDTH - 1:0] next_note,
    output [`seginput_WIDTH - 1:0]seg_in//grades and rank
    );
    
    //3 songs to learn
    wire [`notes_WIDTH - 1:0] melody_1[`melody_WIDTH - 1:0];
    wire [`notes_WIDTH - 1:0] melody_2[`melody_WIDTH - 1:0];
    wire [`notes_WIDTH - 1:0] melody_3[`melody_WIDTH - 1:0];
    
    reg[`cntsong_WIDTH -1:0] cnt_flag0;
    reg[`cntsong_WIDTH -1:0] cnt_flag1;
    reg[`cntsong_WIDTH -1:0] cnt_flag2;
    reg [`cntsong_WIDTH -1:0] cnt;
    wire clk_out;
    reg [`grades_WIDTH - 1:0]grades;
     
    reg [`melody_WIDTH - 1:0] cnt_fault0;
    reg [`melody_WIDTH - 1:0] cnt_fault1;
    reg [`melody_WIDTH - 1:0] cnt_fault2;

       
   reg [`grades_WIDTH - 1:0]rank;
   //user-n's rank
       reg [`grades_WIDTH - 1:0] user1_rank;
       reg [`grades_WIDTH - 1:0] user2_rank;
       reg [`grades_WIDTH - 1:0] user3_rank;
      
      //song and user num
         reg[`segin_WIDTH - 1:0] song;
         reg[`segin_WIDTH - 1:0] user;
    
    // change song
    reg [`flag_WIDTH - 1:0] flag;
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
        
        
     reg [`user_WIDTH - 1:0]choose_user;
         always @(posedge cu)begin
         if(~rst_n)begin
             choose_user <= 2'b11;
             end 
             else if(choose_user == 2'b10)begin
                 choose_user <= 2'b11;
             end 
             else if(choose_user == 2'b11)begin
                 choose_user <= 2'b01;
             end
             else if(choose_user == 2'b01)begin
                 choose_user <= 2'b10;
             end
         end
         
    always @(*) begin
         if(choose_user == 2'b11 ) begin
                                  rank = user1_rank;
                                  user = 5'd30;//3
                              end
                              else if(choose_user == 2'b01 ) begin
                                  rank = user2_rank;
                                  user = 5'd28;//1
                              end
                              else if(choose_user == 2'b10 ) begin
                                  rank = user3_rank;
                                  user = 5'd29;//2
                              end
         end
  
             always @(posedge save)begin
                 if(choose_user == 2'b11 ) begin
                     user1_rank <= (user1_rank + grades)/2;
                 end
                 else if(choose_user == 2'b01 ) begin
                     user2_rank <= (user2_rank + grades) / 2;
                 end
                 else if(choose_user == 2'b10 ) begin
                     user3_rank <= (user3_rank + grades) / 2;
                 end
             end            
        
     Fre_div #(50000000) fre2(clk,clk_out);
        assign melody_1[0] = 5'd1;
        assign melody_1[1] = 5'd0;
        assign melody_1[2] = 5'd1;
        assign melody_1[3] = 5'd0;
        assign melody_1[4] = 5'd5;
        assign melody_1[5] = 5'd0;
        assign melody_1[6] = 5'd5;
        assign melody_1[7] = 5'd0;
        assign melody_1[8] = 5'd6;
        assign melody_1[9] = 5'd0;
        assign melody_1[10] = 5'd6;
        assign melody_1[11] = 5'd0;
        assign melody_1[12] = 5'd5;
        assign melody_1[13] = 5'd0;
        assign melody_1[14] = 5'd0;
        assign melody_1[15] = 5'd0;
        assign melody_1[16] = 5'd4;
        assign melody_1[17] = 5'd0;
        assign melody_1[18] = 5'd4;
        assign melody_1[19] = 5'd0;
        assign melody_1[20] = 5'd3;
        assign melody_1[21] = 5'd0;
        assign melody_1[22] = 5'd3;
        assign melody_1[23] = 5'd0;
        assign melody_1[24] = 5'd2;
        assign melody_1[25] = 5'd0;
        assign melody_1[26] = 5'd2;
        assign melody_1[27] = 5'd0;
        assign melody_1[28] = 5'd1;
        assign melody_1[29] = 5'd0;
        assign melody_1[30] = 5'd0;
        assign melody_1[31] = 5'd0;
        assign melody_1[32] = 5'd5;
        assign melody_1[33] = 5'd0;
        assign melody_1[34] = 5'd5;
        assign melody_1[35] = 5'd0;
        assign melody_1[36] = 5'd4;
        assign melody_1[37] = 5'd0;
        assign melody_1[38] = 5'd4;
        assign melody_1[39] = 5'd0;
        assign melody_1[40] = 5'd3;
        assign melody_1[41] = 5'd0;
        assign melody_1[42] = 5'd3;
        assign melody_1[43] = 5'd0;
        assign melody_1[44] = 5'd2;
        assign melody_1[45] = 5'd0;
        assign melody_1[46] = 5'd0;
        assign melody_1[47] = 5'd0;
        assign melody_1[48] = 5'd5;
        assign melody_1[49] = 5'd0;
        assign melody_1[50] = 5'd5;
        assign melody_1[51] = 5'd0;
        assign melody_1[52] = 5'd4;
        assign melody_1[53] = 5'd0;
        assign melody_1[54] = 5'd4;
        assign melody_1[55] = 5'd0;
        assign melody_1[56] = 5'd3;
        assign melody_1[57] = 5'd0;
        assign melody_1[58] = 5'd3;
        assign melody_1[59] = 5'd0;
        assign melody_1[60] = 5'd2;
        assign melody_1[61] = 5'd0;
        assign melody_1[62] = 5'd0;
        assign melody_1[63] = 5'd0;
        assign melody_1[64] = 5'd1;
        assign melody_1[65] = 5'd0;
        assign melody_1[66] = 5'd1;
        assign melody_1[67] = 5'd0;
        assign melody_1[68] = 5'd5;
        assign melody_1[69] = 5'd0;
        assign melody_1[70] = 5'd5;
        assign melody_1[71] = 5'd0;
        assign melody_1[72] = 5'd6;
        assign melody_1[73] = 5'd0;
        assign melody_1[74] = 5'd6;
        assign melody_1[75] = 5'd0;
        assign melody_1[76] = 5'd5;
        assign melody_1[77] = 5'd0;
        assign melody_1[78] = 5'd0;
        assign melody_1[79] = 5'd0;
        assign melody_1[80] = 5'd4;
        assign melody_1[81] = 5'd0;
        assign melody_1[82] = 5'd4;
        assign melody_1[83] = 5'd0;
        assign melody_1[84] = 5'd3;
        assign melody_1[85] = 5'd0;
        assign melody_1[86] = 5'd3;
        assign melody_1[87] = 5'd0;
        assign melody_1[88] = 5'd2;
        assign melody_1[89] = 5'd0;
        assign melody_1[90] = 5'd2;
        assign melody_1[91] = 5'd0;
        assign melody_1[92] = 5'd1;
        assign melody_1[93] = 5'd0;
        assign melody_1[94] = 5'd0;
        assign melody_1[95] = 5'd0;

        assign melody_2[0] = 5'd1;
        assign melody_2[1] = 5'd0;
        assign melody_2[2] = 5'd2;
        assign melody_2[3] = 5'd0;
        assign melody_2[4] = 5'd3;
        assign melody_2[5] = 5'd0;
        assign melody_2[6] = 5'd1;
        assign melody_2[7] = 5'd0;
        assign melody_2[8] = 5'd0;
        assign melody_2[9] = 5'd0;
        assign melody_2[10] = 5'd1;
        assign melody_2[11] = 5'd0;
        assign melody_2[12] = 5'd2;
        assign melody_2[13] = 5'd0;
        assign melody_2[14] = 5'd3;
        assign melody_2[15] = 5'd0;
        assign melody_2[16] = 5'd1;
        assign melody_2[17] = 5'd0;
        assign melody_2[18] = 5'd0;
        assign melody_2[19] = 5'd0;
        assign melody_2[20] = 5'd3;
        assign melody_2[21] = 5'd0;
        assign melody_2[22] = 5'd4;
        assign melody_2[23] = 5'd0;
        assign melody_2[24] = 5'd5;
        assign melody_2[25] = 5'd0;
        assign melody_2[26] = 5'd0;
        assign melody_2[27] = 5'd0;
        assign melody_2[28] = 5'd3;
        assign melody_2[29] = 5'd0;
        assign melody_2[30] = 5'd4;
        assign melody_2[31] = 5'd0;
        assign melody_2[32] = 5'd5;
        assign melody_2[33] = 5'd0;
        assign melody_2[34] = 5'd0;
        assign melody_2[35] = 5'd0;
        assign melody_2[36] = 5'd5;
        assign melody_2[37] = 5'd0;
        assign melody_2[38] = 5'd6;
        assign melody_2[39] = 5'd0;
        assign melody_2[40] = 5'd5;
        assign melody_2[41] = 5'd0;
        assign melody_2[42] = 5'd4;
        assign melody_2[43] = 5'd0;
        assign melody_2[44] = 5'd3;
        assign melody_2[45] = 5'd0;
        assign melody_2[46] = 5'd1;
        assign melody_2[47] = 5'd0;
        assign melody_2[48] = 5'd0;
        assign melody_2[49] = 5'd0;
        assign melody_2[50] = 5'd5;
        assign melody_2[51] = 5'd0;
        assign melody_2[52] = 5'd6;
        assign melody_2[53] = 5'd0;
        assign melody_2[54] = 5'd5;
        assign melody_2[55] = 5'd0;
        assign melody_2[56] = 5'd4;
        assign melody_2[57] = 5'd0;
        assign melody_2[58] = 5'd3;
        assign melody_2[59] = 5'd0;
        assign melody_2[60] = 5'd1;
        assign melody_2[61] = 5'd0;
        assign melody_2[62] = 5'd0;
        assign melody_2[63] = 5'd0;
        assign melody_2[64] = 5'd1;
        assign melody_2[65] = 5'd0;
        assign melody_2[66] = 5'd5;
        assign melody_2[67] = 5'd0;
        assign melody_2[68] = 5'd1;
        assign melody_2[69] = 5'd0;
        assign melody_2[70] = 5'd0;
        assign melody_2[71] = 5'd0;
        assign melody_2[72] = 5'd1;
        assign melody_2[73] = 5'd0;
        assign melody_2[74] = 5'd5;
        assign melody_2[75] = 5'd0;
        assign melody_2[76] = 5'd1;
        assign melody_2[77] = 5'd0;
        
            assign melody_3[0] = 5'd1;
            assign melody_3[1] = 5'd0;
            assign melody_3[2] = 5'd2;
            assign melody_3[3] = 5'd0;
            assign melody_3[4] = 5'd3;
            assign melody_3[5] = 5'd0;
            assign melody_3[6] = 5'd4;
            assign melody_3[7] = 5'd0;
            assign melody_3[8] = 5'd0;
            assign melody_3[9] = 5'd0;
            assign melody_3[10] = 5'd5;
            assign melody_3[11] = 5'd0;
            assign melody_3[12] = 5'd5;
            assign melody_3[13] = 5'd0;
            assign melody_3[14] = 5'd5;
            assign melody_3[15] = 5'd0;
            assign melody_3[16] = 5'd4;
            assign melody_3[17] = 5'd0;
            assign melody_3[18] = 5'd3;
            assign melody_3[19] = 5'd0;
            assign melody_3[20] = 5'd0;
            assign melody_3[21] = 5'd0;
            assign melody_3[22] = 5'd4;
            assign melody_3[23] = 5'd0;
            assign melody_3[24] = 5'd4;
            assign melody_3[25] = 5'd0;
            assign melody_3[26] = 5'd4;
            assign melody_3[27] = 5'd0;
            assign melody_3[28] = 5'd3;
            assign melody_3[29] = 5'd0;
            assign melody_3[30] = 5'd2;
            assign melody_3[31] = 5'd0;
            assign melody_3[32] = 5'd0;
            assign melody_3[33] = 5'd0;
            assign melody_3[34] = 5'd1;
            assign melody_3[35] = 5'd0;
            assign melody_3[36] = 5'd3;
            assign melody_3[37] = 5'd0;
            assign melody_3[38] = 5'd5;
            assign melody_3[39] = 5'd0;
            assign melody_3[40] = 5'd0;
            assign melody_3[41] = 5'd0;
            assign melody_3[42] = 5'd1;
            assign melody_3[43] = 5'd0;
            assign melody_3[44] = 5'd2;
            assign melody_3[45] = 5'd0;
            assign melody_3[46] = 5'd3;
            assign melody_3[47] = 5'd0;
            assign melody_3[48] = 5'd4;
            assign melody_3[49] = 5'd0;
            assign melody_3[50] = 5'd0;
            assign melody_3[51] = 5'd0;
            assign melody_3[52] = 5'd5;
            assign melody_3[53] = 5'd0;
            assign melody_3[54] = 5'd5;
            assign melody_3[55] = 5'd0;
            assign melody_3[56] = 5'd5;
            assign melody_3[57] = 5'd0;
            assign melody_3[58] = 5'd4;
            assign melody_3[59] = 5'd0;
            assign melody_3[60] = 5'd3;
            assign melody_3[61] = 5'd0;
            assign melody_3[62] = 5'd0;
            assign melody_3[63] = 5'd0;
            assign melody_3[64] = 5'd4;
            assign melody_3[65] = 5'd0;
            assign melody_3[66] = 5'd4;
            assign melody_3[67] = 5'd0;
            assign melody_3[68] = 5'd4;
            assign melody_3[69] = 5'd0;
            assign melody_3[70] = 5'd3;
            assign melody_3[71] = 5'd0;
            assign melody_3[72] = 5'd2;
            assign melody_3[73] = 5'd0;
            assign melody_3[74] = 5'd0;
            assign melody_3[75] = 5'd0;
            assign melody_3[76] = 5'd1;
            assign melody_3[77] = 5'd0;
            assign melody_3[78] = 5'd3;
            assign melody_3[79] = 5'd0;
            assign melody_3[80] = 5'd1;
            assign melody_3[81] = 5'd0;

    //once flag change reset the song    
    always  @(posedge clk_out)begin
        if(flag == 2'b01||flag == 2'b10)begin
                    cnt_flag0 <= 8'd95;
                    cnt_fault0 <= 0;
                end
                else if(~rst_n)begin
                    cnt_flag0 <= 8'd95;
                    cnt_fault0 <= 0;
                end
        else if(cnt_flag0 == 8'd95 )begin
                                cnt_flag0 <= 0;
                                cnt_fault0 <= 0;
                            end
        else if(key_pitch  == 7'b1000000 && melody_1[cnt_flag0] == 5'd1)begin
            cnt_flag0 <= cnt_flag0+1;
        end
        else if(key_pitch  == 7'b0100000 && melody_1[cnt_flag0] == 5'd2)begin
            cnt_flag0 <= cnt_flag0+1;
        end
        else if(key_pitch  == 7'b0010000 && melody_1[cnt_flag0] == 5'd3)begin
            cnt_flag0 <= cnt_flag0+1;
        end
        else if(key_pitch  == 7'b0001000 && melody_1[cnt_flag0] == 5'd4)begin
            cnt_flag0 <= cnt_flag0+1;      
        end
        else if(key_pitch  == 7'b0000100 && melody_1[cnt_flag0] == 5'd5)begin
            cnt_flag0 <= cnt_flag0+1;  
        end
        else if(key_pitch  == 7'b0000010 && melody_1[cnt_flag0] == 5'd6)begin
            cnt_flag0 <= cnt_flag0+1;
        end
        else if(key_pitch  == 7'b0000001 && melody_1[cnt_flag0] == 5'd7)begin
            cnt_flag0 <= cnt_flag0+1;
        end
         else if(melody_1[cnt_flag0] == 5'd0)begin
             cnt_flag0 <= cnt_flag0+1;
         end
        else if(key_pitch!=7'b0000000)begin 
                     cnt_fault0<=cnt_fault0+1;
                 end
    end
    


    always @(posedge clk_out) begin
        if(flag == 2'b00||flag == 2'b10)begin
                        cnt_flag1 <= 8'd77;
                        cnt_fault1 <= 0;
                    end
                    else if(~rst_n)begin
                        cnt_flag1 <= 8'd77;
                        cnt_fault1 <= 0;
                    end
        else if(cnt_flag1==8'd77)begin
                                    cnt_flag1<=0;
                                    cnt_fault1<=0;
                                end
        else if(key_pitch  == 7'b1000000 && melody_2[cnt_flag1] == 5'd1)begin
            cnt_flag1 <= cnt_flag1+1;
        end
        else if(key_pitch  == 7'b0100000 && melody_2[cnt_flag1] == 5'd2)begin
            cnt_flag1 <= cnt_flag1+1;
        end
        else if(key_pitch  == 7'b0010000 && melody_2[cnt_flag1] == 5'd3)begin
            cnt_flag1 <= cnt_flag1+1;
        end
        else if(key_pitch  == 7'b0001000 && melody_2[cnt_flag1] == 5'd4)begin
            cnt_flag1 <= cnt_flag1+1;
        end
        else if(key_pitch  == 7'b0000100 && melody_2[cnt_flag1] == 5'd5)begin
            cnt_flag1 <= cnt_flag1+1;
        end
        else if(key_pitch  == 7'b0000010 && melody_2[cnt_flag1] == 5'd6)begin
            cnt_flag1 <= cnt_flag1+1;
        end
        else if(key_pitch  == 7'b0000001 && melody_2[cnt_flag1] == 5'd7)begin
            cnt_flag1 <= cnt_flag1+1;
        end
        else if(melody_2[cnt_flag1] == 5'd0)begin
            cnt_flag1 <= cnt_flag1+1;
        end
        else if(key_pitch!=7'b0000000)begin 
                    cnt_fault1<=cnt_fault1+1;
                end
    end
    


    always @(posedge clk_out) begin
        if(flag == 2'b00||flag == 2'b01) begin
                    cnt_flag2 <= 8'd81;
                    cnt_fault2<=0;
                end
                else if(~rst_n)begin
                    cnt_flag2 <= 8'd81;
                    cnt_fault2<=0;
                end
                else if(cnt_flag2==8'd81) begin
                    cnt_flag2 <= 0;
                    cnt_fault2<=0;
                end
        else if(key_pitch  == 7'b1000000 && melody_3[cnt_flag2] == 5'd1) begin
            cnt_flag2 <= cnt_flag2+1;
        end
        else if(key_pitch  == 7'b0100000 && melody_3[cnt_flag2] == 5'd2) begin
            cnt_flag2 <= cnt_flag2+1;
        end
        else if(key_pitch  == 7'b0010000 && melody_3[cnt_flag2] == 5'd3) begin
            cnt_flag2 <= cnt_flag2+1;
        end
        else if(key_pitch  == 7'b0001000 && melody_3[cnt_flag2] == 5'd4) begin
            cnt_flag2 <= cnt_flag2+1;
        end
        else if(key_pitch  == 7'b0000100 && melody_3[cnt_flag2] == 5'd5) begin
            cnt_flag2 <= cnt_flag2+1;
        end
        else if(key_pitch  == 7'b0000010 && melody_3[cnt_flag2] == 5'd6) begin
            cnt_flag2 <= cnt_flag2+1;
        end
        else if(key_pitch  == 7'b0000001 && melody_3[cnt_flag2] == 5'd7) begin
            cnt_flag2 <= cnt_flag2+1;
        end
        else if(melody_3[cnt_flag2] == 5'd0)begin
            cnt_flag2 <= cnt_flag2+1;
        end
        else if(key_pitch!=7'b0000000)begin 
            cnt_fault2<=cnt_fault2+1;
        end
    end
    
    always @(*) begin
        if(flag==2'b00) begin
        cnt = cnt_flag0;
        end
        else if(flag == 2'b01) begin
        cnt = cnt_flag1;
        end
        else if(flag == 2'b10) begin
        cnt = cnt_flag2;
        end
    end
    
    
    
    

        always @(*) begin
                if(flag == 2'b00)begin
                    grades = 100 - 200*cnt_fault0/cnt_flag0;
                    song = 5'd30;
                end
                else if(flag==2'b01)begin
                    grades = 100 - 200*cnt_fault1/cnt_flag1;
                    song = 5'd28;
                end
                else if(flag == 2'b10)begin
                    grades = 100 - 200*cnt_fault2/cnt_flag2;
                    song = 5'd29;
                end
            end

     reg [`grades_WIDTH - 1:0] final_grades;
    always @(*) begin
        if (grades <= 0 || grades > 100) 
            final_grades = 0;
        else
            final_grades = grades;
    end
    reg [`grades_WIDTH - 1:0] final_rank;
    always @(*) begin
        if (rank <= 0 || rank > 100) 
            final_rank = 0;
        else
            final_rank = rank;
    end

            
    
    //decimal to binary
    reg [`binary_WIDTH - 1:0] grades_lsb;
    reg [`binary_WIDTH - 1:0] grades_mid;
    reg [`binary_WIDTH - 1:0] grades_msb;
    always @(*) begin
        grades_lsb = final_grades % 10;
        grades_mid = (final_grades / 10) % 10;
        grades_msb = final_grades / 100;
    end
    
        reg [`binary_WIDTH - 1:0] rank_lsb;
        reg [`binary_WIDTH - 1:0] rank_mid;
        reg [`binary_WIDTH - 1:0] rank_msb;
        always @(*) begin
            rank_lsb = final_rank % 10;
            rank_mid = (final_rank / 10) % 10;
            rank_msb = final_rank / 100;
        end
   
        assign seg_in = { song ,
                                    1'b0, grades_msb, 
                                    1'b0, grades_mid, 
                                    1'b0, grades_lsb, 
                                    user, 
                                    1'b0, rank_msb, 
                                    1'b0, rank_mid, 
                                    1'b0, rank_lsb   };
                                    
    Library lib(flag,cnt,pause_start,next_note);
            
endmodule
