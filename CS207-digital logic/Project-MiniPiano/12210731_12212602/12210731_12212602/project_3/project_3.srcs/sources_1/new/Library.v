`timescale 1ns / 1ps
//////////////////////////////////////////////////////////////////////////////////
// Company: 
// Engineer: 
// 
// Create Date: 2023/12/15 16:52:43
// Design Name: 
// Module Name: Library
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


module Library(
    input [`flag_WIDTH - 1:0] flag,
    input [`cntsong_WIDTH - 1:0] cnt,
    input pause,
    output reg [`notes_WIDTH - 1:0]notes
    );
    
    wire [`notes_WIDTH - 1:0] melody_1 [`melody_WIDTH - 1:0];
    wire [`notes_WIDTH - 1:0] melody_2 [`melody_WIDTH - 1:0];
    wire [`notes_WIDTH - 1:0] melody_3 [`melody_WIDTH - 1:0];
   
    
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
    
 
    
    always @(*)begin
        if(pause) begin
            notes=5'd0;
        end
        else if(flag == 2'b00) begin
            case(cnt)
                8'd0:notes=melody_1[cnt];
                8'd1:notes=melody_1[cnt];
                8'd2:notes=melody_1[cnt];
                8'd3:notes=melody_1[cnt];
                8'd4:notes=melody_1[cnt];
                8'd5:notes=melody_1[cnt];
                8'd6:notes=melody_1[cnt];
                8'd7:notes=melody_1[cnt];
                8'd8:notes=melody_1[cnt];
                8'd9:notes=melody_1[cnt];
                8'd10:notes=melody_1[cnt];
                8'd11:notes=melody_1[cnt];
                8'd12:notes=melody_1[cnt];
                8'd13:notes=melody_1[cnt];
                8'd14:notes=melody_1[cnt];
                8'd15:notes=melody_1[cnt];
                8'd16:notes=melody_1[cnt];
                8'd17:notes=melody_1[cnt];
                8'd18:notes=melody_1[cnt];
                8'd19:notes=melody_1[cnt];
                8'd20:notes=melody_1[cnt];
                8'd21:notes=melody_1[cnt];
                8'd22:notes=melody_1[cnt];
                8'd23:notes=melody_1[cnt];
                8'd24:notes=melody_1[cnt];
                8'd25:notes=melody_1[cnt];
                8'd26:notes=melody_1[cnt];
                8'd27:notes=melody_1[cnt];
                8'd28:notes=melody_1[cnt];
                8'd29:notes=melody_1[cnt];
                8'd30:notes=melody_1[cnt];
                8'd31:notes=melody_1[cnt];
                8'd32:notes=melody_1[cnt];
                8'd33:notes=melody_1[cnt];
                8'd34:notes=melody_1[cnt];
                8'd35:notes=melody_1[cnt];
                8'd36:notes=melody_1[cnt];
                8'd37:notes=melody_1[cnt];
                8'd38:notes=melody_1[cnt];
                8'd39:notes=melody_1[cnt];
                8'd40:notes=melody_1[cnt];
                8'd41:notes=melody_1[cnt];
                8'd42:notes=melody_1[cnt];
                8'd43:notes=melody_1[cnt];
                8'd44:notes=melody_1[cnt];
                8'd45:notes=melody_1[cnt];
                8'd46:notes=melody_1[cnt];
                8'd47:notes=melody_1[cnt];
                8'd48:notes=melody_1[cnt];
                8'd49:notes=melody_1[cnt];
                8'd50:notes=melody_1[cnt];
                8'd51:notes=melody_1[cnt];
                8'd52:notes=melody_1[cnt];
                8'd53:notes=melody_1[cnt];
                8'd54:notes=melody_1[cnt];
                8'd55:notes=melody_1[cnt];
                8'd56:notes=melody_1[cnt];
                8'd57:notes=melody_1[cnt];
                8'd58:notes=melody_1[cnt];
                8'd59:notes=melody_1[cnt];
                8'd60:notes=melody_1[cnt];
                8'd61:notes=melody_1[cnt];
                8'd62:notes=melody_1[cnt];
                8'd63:notes=melody_1[cnt];
                8'd64:notes=melody_1[cnt];
                8'd65:notes=melody_1[cnt];
                8'd66:notes=melody_1[cnt];
                8'd67:notes=melody_1[cnt];
                8'd68:notes=melody_1[cnt];
                8'd69:notes=melody_1[cnt];
                8'd70:notes=melody_1[cnt];
                8'd71:notes=melody_1[cnt];
                8'd72:notes=melody_1[cnt];
                8'd73:notes=melody_1[cnt];
                8'd74:notes=melody_1[cnt];
                8'd75:notes=melody_1[cnt];
                8'd76:notes=melody_1[cnt];
                8'd77:notes=melody_1[cnt];
                8'd78:notes=melody_1[cnt];
                8'd79:notes=melody_1[cnt];
                8'd80:notes=melody_1[cnt];
                8'd81:notes=melody_1[cnt];
                8'd82:notes=melody_1[cnt];
                8'd83:notes=melody_1[cnt];
                8'd84:notes=melody_1[cnt];
                8'd85:notes=melody_1[cnt];
                8'd86:notes=melody_1[cnt];
                8'd87:notes=melody_1[cnt];
                8'd88:notes=melody_1[cnt];
                8'd89:notes=melody_1[cnt];
                8'd90:notes=melody_1[cnt];
                8'd91:notes=melody_1[cnt];
                8'd92:notes=melody_1[cnt];
                8'd93:notes=melody_1[cnt];
                8'd94:notes=melody_1[cnt];
                8'd95:notes=melody_1[cnt];
             endcase
        end
        else if (flag == 2'b01)begin
            case(cnt)
            8'd0:notes=melody_2[cnt];
            8'd1:notes=melody_2[cnt];
            8'd2:notes=melody_2[cnt];
            8'd3:notes=melody_2[cnt];
            8'd4:notes=melody_2[cnt];
            8'd5:notes=melody_2[cnt];
            8'd6:notes=melody_2[cnt];
            8'd7:notes=melody_2[cnt];
            8'd8:notes=melody_2[cnt];
            8'd9:notes=melody_2[cnt];
            8'd10:notes=melody_2[cnt];
            8'd11:notes=melody_2[cnt];
            8'd12:notes=melody_2[cnt];
            8'd13:notes=melody_2[cnt];
            8'd14:notes=melody_2[cnt];
            8'd15:notes=melody_2[cnt];
            8'd16:notes=melody_2[cnt];
            8'd17:notes=melody_2[cnt];
            8'd18:notes=melody_2[cnt];
            8'd19:notes=melody_2[cnt];
            8'd20:notes=melody_2[cnt];
            8'd21:notes=melody_2[cnt];
            8'd22:notes=melody_2[cnt];
            8'd23:notes=melody_2[cnt];
            8'd24:notes=melody_2[cnt];
            8'd25:notes=melody_2[cnt];
            8'd26:notes=melody_2[cnt];
            8'd27:notes=melody_2[cnt];
            8'd28:notes=melody_2[cnt];
            8'd29:notes=melody_2[cnt];
            8'd30:notes=melody_2[cnt];
            8'd31:notes=melody_2[cnt];
            8'd32:notes=melody_2[cnt];
            8'd33:notes=melody_2[cnt];
            8'd34:notes=melody_2[cnt];
            8'd35:notes=melody_2[cnt];
            8'd36:notes=melody_2[cnt];
            8'd37:notes=melody_2[cnt];
            8'd38:notes=melody_2[cnt];
            8'd39:notes=melody_2[cnt];
            8'd40:notes=melody_2[cnt];
            8'd41:notes=melody_2[cnt];
            8'd42:notes=melody_2[cnt];
            8'd43:notes=melody_2[cnt];
            8'd44:notes=melody_2[cnt];
            8'd45:notes=melody_2[cnt];
            8'd46:notes=melody_2[cnt];
            8'd47:notes=melody_2[cnt];
            8'd48:notes=melody_2[cnt];
            8'd49:notes=melody_2[cnt];
            8'd50:notes=melody_2[cnt];
            8'd51:notes=melody_2[cnt];
            8'd52:notes=melody_2[cnt];
            8'd53:notes=melody_2[cnt];
            8'd54:notes=melody_2[cnt];
            8'd55:notes=melody_2[cnt];
            8'd56:notes=melody_2[cnt];
            8'd57:notes=melody_2[cnt];
            8'd58:notes=melody_2[cnt];
            8'd59:notes=melody_2[cnt];
            8'd60:notes=melody_2[cnt];
            8'd61:notes=melody_2[cnt];
            8'd62:notes=melody_2[cnt];
            8'd63:notes=melody_2[cnt];
            8'd64:notes=melody_2[cnt];
            8'd65:notes=melody_2[cnt];
            8'd66:notes=melody_2[cnt];
            8'd67:notes=melody_2[cnt];
            8'd68:notes=melody_2[cnt];
            8'd69:notes=melody_2[cnt];
            8'd70:notes=melody_2[cnt];
            8'd71:notes=melody_2[cnt];
            8'd72:notes=melody_2[cnt];
            8'd73:notes=melody_2[cnt];
            8'd74:notes=melody_2[cnt];
            8'd75:notes=melody_2[cnt];
            8'd76:notes=melody_2[cnt];
            8'd77:notes=melody_2[cnt];
            endcase
        end
        else if (flag == 2'b10)begin
            case(cnt)
                    8'd0:notes=melody_3[cnt];
                    8'd1:notes=melody_3[cnt];
                    8'd2:notes=melody_3[cnt];
                    8'd3:notes=melody_3[cnt];
                    8'd4:notes=melody_3[cnt];
                    8'd5:notes=melody_3[cnt];
                    8'd6:notes=melody_3[cnt];
                    8'd7:notes=melody_3[cnt];
                    8'd8:notes=melody_3[cnt];
                    8'd9:notes=melody_3[cnt];
                    8'd10:notes=melody_3[cnt];
                    8'd11:notes=melody_3[cnt];
                    8'd12:notes=melody_3[cnt];
                    8'd13:notes=melody_3[cnt];
                    8'd14:notes=melody_3[cnt];
                    8'd15:notes=melody_3[cnt];
                    8'd16:notes=melody_3[cnt];
                    8'd17:notes=melody_3[cnt];
                    8'd18:notes=melody_3[cnt];
                    8'd19:notes=melody_3[cnt];
                    8'd20:notes=melody_3[cnt];
                    8'd21:notes=melody_3[cnt];
                    8'd22:notes=melody_3[cnt];
                    8'd23:notes=melody_3[cnt];
                    8'd24:notes=melody_3[cnt];
                    8'd25:notes=melody_3[cnt];
                    8'd26:notes=melody_3[cnt];
                    8'd27:notes=melody_3[cnt];
                    8'd28:notes=melody_3[cnt];
                    8'd29:notes=melody_3[cnt];
                    8'd30:notes=melody_3[cnt];
                    8'd31:notes=melody_3[cnt];
                    8'd32:notes=melody_3[cnt];
                    8'd33:notes=melody_3[cnt];
                    8'd34:notes=melody_3[cnt];
                    8'd35:notes=melody_3[cnt];
                    8'd36:notes=melody_3[cnt];
                    8'd37:notes=melody_3[cnt];
                    8'd38:notes=melody_3[cnt];
                    8'd39:notes=melody_3[cnt];
                    8'd40:notes=melody_3[cnt];
                    8'd41:notes=melody_3[cnt];
                    8'd42:notes=melody_3[cnt];
                    8'd43:notes=melody_3[cnt];
                    8'd44:notes=melody_3[cnt];
                    8'd45:notes=melody_3[cnt];
                    8'd46:notes=melody_3[cnt];
                    8'd47:notes=melody_3[cnt];
                    8'd48:notes=melody_3[cnt];
                    8'd49:notes=melody_3[cnt];
                    8'd50:notes=melody_3[cnt];
                    8'd51:notes=melody_3[cnt];
                    8'd52:notes=melody_3[cnt];
                    8'd53:notes=melody_3[cnt];
                    8'd54:notes=melody_3[cnt];
                    8'd55:notes=melody_3[cnt];
                    8'd56:notes=melody_3[cnt];
                    8'd57:notes=melody_3[cnt];
                    8'd58:notes=melody_3[cnt];
                    8'd59:notes=melody_3[cnt];
                    8'd60:notes=melody_3[cnt];
                    8'd61:notes=melody_3[cnt];
                    8'd62:notes=melody_3[cnt];
                    8'd63:notes=melody_3[cnt];
                    8'd64:notes=melody_3[cnt];
                    8'd65:notes=melody_3[cnt];
                    8'd66:notes=melody_3[cnt];
                    8'd67:notes=melody_3[cnt];
                    8'd68:notes=melody_3[cnt];
                    8'd69:notes=melody_3[cnt];
                    8'd70:notes=melody_3[cnt];
                    8'd71:notes=melody_3[cnt];
                    8'd72:notes=melody_3[cnt];
                    8'd73:notes=melody_3[cnt];
                    8'd74:notes=melody_3[cnt];
                    8'd75:notes=melody_3[cnt];
                    8'd76:notes=melody_3[cnt];
                    8'd77:notes=melody_3[cnt];
                    8'd78:notes=melody_3[cnt];
                    8'd79:notes=melody_3[cnt];
                    8'd80:notes=melody_3[cnt];
                    8'd81:notes=melody_3[cnt];
                    endcase
        end        
    end
    
    
    
endmodule
