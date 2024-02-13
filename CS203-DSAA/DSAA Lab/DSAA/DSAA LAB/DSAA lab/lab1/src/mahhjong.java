import java.util.Arrays;
import java.util.Scanner;

public class mahhjong {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            String s = input.next();
            int[] tran = new int[14];
            int[] bar = new int[37];
            tran = trans(s);
            bar = into_bar(tran);
            //for (int j = 0; j < bar.length; j++) System.out.println(bar[j]);
            if (process(bar)) System.out.println("Blessing of Heaven");
            else System.out.println("Bad luck");
        }
    }

    protected static int[] trans(String s) {
        int[] tran = new int[14];
        char[] transf = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 1) {
                if ('b' == transf[i]) {
                    if ('1' == transf[i - 1]) tran[(i - 1) / 2] = 0;
                    if ('2' == transf[i - 1]) tran[(i - 1) / 2] = 1;
                    if ('3' == transf[i - 1]) tran[(i - 1) / 2] = 2;
                    if ('4' == transf[i - 1]) tran[(i - 1) / 2] = 3;
                    if ('5' == transf[i - 1]) tran[(i - 1) / 2] = 4;
                    if ('6' == transf[i - 1]) tran[(i - 1) / 2] = 5;
                    if ('7' == transf[i - 1]) tran[(i - 1) / 2] = 6;
                    if ('8' == transf[i - 1]) tran[(i - 1) / 2] = 7;
                    if ('9' == transf[i - 1]) tran[(i - 1) / 2] = 8;
                }
                if ('s' == transf[i]) {
                    if ('1' == transf[i - 1]) tran[(i - 1) / 2] = 10;
                    if ('2' == transf[i - 1]) tran[(i - 1) / 2] = 11;
                    if ('3' == transf[i - 1]) tran[(i - 1) / 2] = 12;
                    if ('4' == transf[i - 1]) tran[(i - 1) / 2] = 13;
                    if ('5' == transf[i - 1]) tran[(i - 1) / 2] = 14;
                    if ('6' == transf[i - 1]) tran[(i - 1) / 2] = 15;
                    if ('7' == transf[i - 1]) tran[(i - 1) / 2] = 16;
                    if ('8' == transf[i - 1]) tran[(i - 1) / 2] = 17;
                    if ('9' == transf[i - 1]) tran[(i - 1) / 2] = 18;
                }
                if ('w' == transf[i]) {
                    if ('1' == transf[i - 1]) tran[(i - 1) / 2] = 20;
                    if ('2' == transf[i - 1]) tran[(i - 1) / 2] = 21;
                    if ('3' == transf[i - 1]) tran[(i - 1) / 2] = 22;
                    if ('4' == transf[i - 1]) tran[(i - 1) / 2] = 23;
                    if ('5' == transf[i - 1]) tran[(i - 1) / 2] = 24;
                    if ('6' == transf[i - 1]) tran[(i - 1) / 2] = 25;
                    if ('7' == transf[i - 1]) tran[(i - 1) / 2] = 26;
                    if ('8' == transf[i - 1]) tran[(i - 1) / 2] = 27;
                    if ('9' == transf[i - 1]) tran[(i - 1) / 2] = 28;
                }
                if ('z' == transf[i]) {
                    if ('1' == transf[i - 1]) tran[(i - 1) / 2] = 30;
                    if ('2' == transf[i - 1]) tran[(i - 1) / 2] = 31;
                    if ('3' == transf[i - 1]) tran[(i - 1) / 2] = 32;
                    if ('4' == transf[i - 1]) tran[(i - 1) / 2] = 33;
                    if ('5' == transf[i - 1]) tran[(i - 1) / 2] = 34;
                    if ('6' == transf[i - 1]) tran[(i - 1) / 2] = 35;
                    if ('7' == transf[i - 1]) tran[(i - 1) / 2] = 36;
                }
            }
        }
        Arrays.sort(tran);
        return tran;
    }

    protected static int[] into_bar(int[] trans) {
        int[] bar = new int[37];
        for (int i = 0; i < trans.length; i++)
            bar[trans[i]]++;
        return bar;
    }

    protected static boolean process(int[] bar) {
        boolean[] flag = new boolean[bar.length];
        for (int i = 0; i < bar.length; i++) {
            if (bar[i] >= 2) {
                flag[i] = true;
            }
        }
        int cnt = 0;
        for (int i = 0; i < bar.length; i++) {
            if (flag[i]) {
                bar[i] -= 2;
                int[] tmp = new int[bar.length];

                //shunshunshunke
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length - 2; j++) {
                    if (tmp[j] >= 1 && tmp[j + 1] >= 1 && tmp[j + 2] >= 1) {
                        if (j >= 30) break;
                        cnt++;
                        tmp[j] -= 1;
                        tmp[j + 1] -= 1;
                        tmp[j + 2] -= 1;
                        for (int k = j; k < bar.length - 2; k++) {
                            if (tmp[k] >= 1 && tmp[k + 1] >= 1 && tmp[k + 2] >= 1) {
                                if (k >= 30) break;
                                cnt++;
                                tmp[k] -= 1;
                                tmp[k + 1] -= 1;
                                tmp[k + 2] -= 1;
                                for (int l = k; l < bar.length - 2; l++) {
                                    if (tmp[l] >= 1 && tmp[l + 1] >= 1 && tmp[l + 2] >= 1) {
                                        if (l >= 30) break;
                                        cnt++;
                                        tmp[l] -= 1;
                                        tmp[l + 1] -= 1;
                                        tmp[l + 2] -= 1;
                                        for (int m = l; m < bar.length; m++) {
                                            if (tmp[m] >= 3) {
                                                cnt++;
                                                tmp[m] -= 3;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("sssk");
                    return true;
                }


                //shunshunkeshun
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length - 2; j++) {
                    if (tmp[j] >= 1 && tmp[j + 1] >= 1 && tmp[j + 2] >= 1) {
                        if (j >= 30) break;
                        cnt++;
                        tmp[j] -= 1;
                        tmp[j + 1] -= 1;
                        tmp[j + 2] -= 1;
                        for (int k = j; k < bar.length - 2; k++) {
                            if (tmp[k] >= 1 && tmp[k + 1] >= 1 && tmp[k + 2] >= 1) {
                                if (k >= 30) break;
                                cnt++;
                                tmp[k] -= 1;
                                tmp[k + 1] -= 1;
                                tmp[k + 2] -= 1;
                                for (int l = k; l < bar.length - 2; l++) {
                                    if (tmp[l] >= 3) {
                                        cnt++;
                                        tmp[l] -= 3;
                                        for (int m = l; m < bar.length - 2; m++) {
                                            if (tmp[m] >= 1 && tmp[m + 1] >= 1 && tmp[m + 2] >= 1) {
                                                if (m >= 30) break;
                                                cnt++;
                                                tmp[m] -= 1;
                                                tmp[m + 1] -= 1;
                                                tmp[m + 2] -= 1;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("ssks");
                    return true;
                }


                //shunkeshunshun
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length - 2; j++) {
                    if (tmp[j] >= 1 && tmp[j + 1] >= 1 && tmp[j + 2] >= 1) {
                        if (j >= 30) break;
                        cnt++;
                        tmp[j] -= 1;
                        tmp[j + 1] -= 1;
                        tmp[j + 2] -= 1;
                        for (int k = j; k < bar.length - 2; k++) {
                            if (tmp[k] >= 3) {
                                cnt++;
                                tmp[k] -= 3;
                                for (int l = k; l < bar.length - 2; l++) {
                                    if (tmp[l] >= 1 && tmp[l + 1] >= 1 && tmp[l + 2] >= 1) {
                                        if (l >= 30) break;
                                        cnt++;
                                        tmp[l] -= 1;
                                        tmp[l + 1] -= 1;
                                        tmp[l + 2] -= 1;
                                        for (int m = l; m < bar.length - 2; m++) {
                                            if (tmp[m] >= 1 && tmp[m + 1] >= 1 && tmp[m + 2] >= 1) {
                                                if (m >= 30) break;
                                                cnt++;
                                                tmp[m] -= 1;
                                                tmp[m + 1] -= 1;
                                                tmp[m + 2] -= 1;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("skss");
                    return true;
                }


                //keshunshunshun
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length - 2; j++) {
                    if (tmp[j] >= 3) {
                        cnt++;
                        tmp[j] -= 3;
                        for (int k = j; k < bar.length - 2; k++) {
                            if (tmp[k] >= 1 && tmp[k + 1] >= 1 && tmp[k + 2] >= 1) {
                                if (k >= 30) break;
                                cnt++;
                                tmp[k] -= 1;
                                tmp[k + 1] -= 1;
                                tmp[k + 2] -= 1;
                                for (int l = k; l < bar.length - 2; l++) {
                                    if (tmp[l] >= 1 && tmp[l + 1] >= 1 && tmp[l + 2] >= 1) {
                                        if (l >= 30) break;
                                        cnt++;
                                        tmp[l] -= 1;
                                        tmp[l + 1] -= 1;
                                        tmp[l + 2] -= 1;
                                        for (int m = l; m < bar.length - 2; m++) {
                                            if (tmp[m] >= 1 && tmp[m + 1] >= 1 && tmp[m + 2] >= 1) {
                                                if (m >= 30) break;
                                                cnt++;
                                                tmp[m] -= 1;
                                                tmp[m + 1] -= 1;
                                                tmp[m + 2] -= 1;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("ksss");
                    return true;
                }


                //shunshunshunshun
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length - 2; j++) {
                    if (tmp[j] >= 1 && tmp[j + 1] >= 1 && tmp[j + 2] >= 1) {
                        if (j >= 30) break;
                        cnt++;
                        tmp[j] -= 1;
                        tmp[j + 1] -= 1;
                        tmp[j + 2] -= 1;
                        for (int k = j; k < bar.length - 2; k++) {
                            if (tmp[k] >= 1 && tmp[k + 1] >= 1 && tmp[k + 2] >= 1) {
                                if (k >= 30) break;
                                cnt++;
                                tmp[k] -= 1;
                                tmp[k + 1] -= 1;
                                tmp[k + 2] -= 1;
                                for (int l = k; l < bar.length - 2; l++) {
                                    if (tmp[l] >= 1 && tmp[l + 1] >= 1 && tmp[l + 2] >= 1) {
                                        if (l >= 30) break;
                                        cnt++;
                                        tmp[l] -= 1;
                                        tmp[l + 1] -= 1;
                                        tmp[l + 2] -= 1;
                                        for (int m = l; m < bar.length - 2; m++) {
                                            if (tmp[m] >= 1 && tmp[m + 1] >= 1 && tmp[m + 2] >= 1) {
                                                if (m >= 30) break;
                                                cnt++;
                                                tmp[m] -= 1;
                                                tmp[m + 1] -= 1;
                                                tmp[m + 2] -= 1;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("ssss");
                    return true;
                }


                //kekekeke
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length; j++) {
                    if (tmp[j] >= 3) {
                        cnt++;
                        tmp[j] -= 3;
                        for (int k = j; k < bar.length; k++) {
                            if (tmp[k] >= 3) {
                                cnt++;
                                tmp[k] -= 3;
                                for (int l = k; l < bar.length; l++) {
                                    if (tmp[l] >= 3) {
                                        cnt++;
                                        tmp[l] -= 3;
                                        for (int m = l; m < bar.length; m++) {
                                            if (tmp[m] >= 3) {
                                                cnt++;
                                                tmp[m] -= 3;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("kkkk");
                    return true;
                }


                //kekekeshun
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length; j++) {
                    if (tmp[j] >= 3) {
                        cnt++;
                        tmp[j] -= 3;
                        for (int k = j; k < bar.length; k++) {
                            if (tmp[k] >= 3) {
                                cnt++;
                                tmp[k] -= 3;
                                for (int l = k; l < bar.length; l++) {
                                    if (tmp[l] >= 3) {
                                        cnt++;
                                        tmp[l] -= 3;
                                        for (int m = l; m < bar.length - 2; m++) {
                                            if (tmp[m] >= 1 && tmp[m + 1] >= 1 && tmp[m + 2] >= 1) {
                                                if (m >= 30) break;
                                                cnt++;
                                                tmp[m] -= 1;
                                                tmp[m + 1] -= 1;
                                                tmp[m + 2] -= 1;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("kkks");
                    return true;
                }


                //kekeshunke
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length; j++) {
                    if (tmp[j] >= 3) {
                        cnt++;
                        tmp[j] -= 3;
                        for (int k = j; k < bar.length; k++) {
                            if (tmp[k] >= 3) {
                                cnt++;
                                tmp[k] -= 3;
                                for (int l = k; l < bar.length - 2; l++) {
                                    if (tmp[l] >= 1 && tmp[l + 1] >= 1 && tmp[l + 2] >= 1) {
                                        if (l >= 30) break;
                                        cnt++;
                                        tmp[l] -= 1;
                                        tmp[l + 1] -= 1;
                                        tmp[l + 2] -= 1;
                                        for (int m = l; m < bar.length; m++) {
                                            if (tmp[m] >= 3) {
                                                cnt++;
                                                tmp[m] -= 3;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("kksk");
                    return true;
                }


                //keshunkeke
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length; j++) {
                    if (tmp[j] >= 3) {
                        cnt++;
                        tmp[j] -= 3;
                        for (int k = j; k < bar.length - 2; k++) {
                            if (tmp[k] >= 1 && tmp[k + 1] >= 1 && tmp[k + 2] >= 1) {
                                if (k >= 30) break;
                                cnt++;
                                tmp[k] -= 1;
                                tmp[k + 1] -= 1;
                                tmp[k + 2] -= 1;
                                for (int l = k; l < bar.length; l++) {
                                    if (tmp[l] >= 3) {
                                        cnt++;
                                        tmp[l] -= 3;
                                        for (int m = l; m < bar.length; m++) {
                                            if (tmp[m] >= 3) {
                                                cnt++;
                                                tmp[m] -= 3;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("kskk");
                    return true;
                }


                //shunkekeke
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length - 2; j++) {
                    if (tmp[j] >= 1 && tmp[j + 1] >= 1 && tmp[j + 2] >= 1) {
                        if (j >= 30) break;
                        cnt++;
                        tmp[j] -= 1;
                        tmp[j + 1] -= 1;
                        tmp[j + 2] -= 1;
                        for (int k = j; k < bar.length; k++) {
                            if (tmp[k] >= 3) {
                                cnt++;
                                tmp[k] -= 3;
                                for (int l = k; l < bar.length; l++) {
                                    if (tmp[l] >= 3) {
                                        cnt++;
                                        tmp[l] -= 3;
                                        for (int m = l; m < bar.length; m++) {
                                            if (tmp[m] >= 3) {
                                                cnt++;
                                                tmp[m] -= 3;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("skkk");
                    return true;
                }


                //shunkeshunke
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length - 2; j++) {
                    if (tmp[j] >= 1 && tmp[j + 1] >= 1 && tmp[j + 2] >= 1) {
                        if (j >= 30) break;
                        cnt++;
                        tmp[j] -= 1;
                        tmp[j + 1] -= 1;
                        tmp[j + 2] -= 1;
                        for (int k = j; k < bar.length; k++) {
                            if (tmp[k] >= 3) {
                                cnt++;
                                tmp[k] -= 3;
                                for (int l = k; l < bar.length - 2; l++) {
                                    if (tmp[l] >= 1 && tmp[l + 1] >= 1 && tmp[l + 2] >= 1) {
                                        if (l >= 30) break;
                                        cnt++;
                                        tmp[l] -= 1;
                                        tmp[l + 1] -= 1;
                                        tmp[l + 2] -= 1;
                                        for (int m = l; m < bar.length; m++) {
                                            if (tmp[m] >= 3) {
                                                cnt++;
                                                tmp[m] -= 3;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("sksk");
                    return true;
                }


                //shunshunkeke
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length - 2; j++) {
                    if (tmp[j] >= 1 && tmp[j + 1] >= 1 && tmp[j + 2] >= 1) {
                        if (j >= 30) break;
                        cnt++;
                        tmp[j] -= 1;
                        tmp[j + 1] -= 1;
                        tmp[j + 2] -= 1;
                        for (int k = j; k < bar.length - 2; k++) {
                            if (tmp[k] >= 1 && tmp[k + 1] >= 1 && tmp[k + 2] >= 1) {
                                if (k >= 30) break;
                                cnt++;
                                tmp[k] -= 1;
                                tmp[k + 1] -= 1;
                                tmp[k + 2] -= 1;
                                for (int l = k; l < bar.length; l++) {
                                    if (tmp[l] >= 3) {
                                        cnt++;
                                        tmp[l] -= 3;
                                        for (int m = l; m < bar.length; m++) {
                                            if (tmp[m] >= 3) {
                                                cnt++;
                                                tmp[m] -= 3;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("sskk");
                    return true;
                }


                //kekeshunshun
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length; j++) {
                    if (tmp[j] >= 3) {
                        cnt++;
                        tmp[j] -= 3;
                        for (int k = j; k < bar.length; k++) {
                            if (tmp[k] >= 3) {
                                cnt++;
                                tmp[k] -= 3;
                                for (int l = k; l < bar.length - 2; l++) {
                                    if (tmp[l] >= 1 && tmp[l + 1] >= 1 && tmp[l + 2] >= 1) {
                                        if (l >= 30) break;
                                        cnt++;
                                        tmp[l] -= 1;
                                        tmp[l + 1] -= 1;
                                        tmp[l + 2] -= 1;
                                        for (int m = l; m < bar.length - 2; m++) {
                                            if (tmp[m] >= 1 && tmp[m + 1] >= 1 && tmp[m + 2] >= 1) {
                                                if (m >= 30) break;
                                                cnt++;
                                                tmp[m] -= 1;
                                                tmp[m + 1] -= 1;
                                                tmp[m + 2] -= 1;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("kkss");
                    return true;
                }


                //keshunkeshun
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length; j++) {
                    if (tmp[j] >= 3) {
                        cnt++;
                        tmp[j] -= 3;
                        for (int k = j; k < bar.length - 2; k++) {
                            if (tmp[k] >= 1 && tmp[k + 1] >= 1 && tmp[k + 2] >= 1) {
                                if (k >= 30) break;
                                cnt++;
                                tmp[k] -= 1;
                                tmp[k + 1] -= 1;
                                tmp[k + 2] -= 1;
                                for (int l = k; l < bar.length; l++) {
                                    if (tmp[l] >= 3) {
                                        cnt++;
                                        tmp[l] -= 3;
                                        for (int m = l; m < bar.length - 2; m++) {
                                            if (tmp[m] >= 1 && tmp[m + 1] >= 1 && tmp[m + 2] >= 1) {
                                                if (m >= 30) break;
                                                cnt++;
                                                tmp[m] -= 1;
                                                tmp[m + 1] -= 1;
                                                tmp[m + 2] -= 1;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("ksks");
                    return true;
                }


                //keshunshunke
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length; j++) {
                    if (tmp[j] >= 3) {
                        cnt++;
                        tmp[j] -= 3;
                        for (int k = j; k < bar.length - 2; k++) {
                            if (tmp[k] >= 1 && tmp[k + 1] >= 1 && tmp[k + 2] >= 1) {
                                if (k >= 30) break;
                                cnt++;
                                tmp[k] -= 1;
                                tmp[k + 1] -= 1;
                                tmp[k + 2] -= 1;
                                for (int l = k; l < bar.length - 2; l++) {
                                    if (tmp[l] >= 1 && tmp[l + 1] >= 1 && tmp[l + 2] >= 1) {
                                        if (l >= 30) break;
                                        cnt++;
                                        tmp[l] -= 1;
                                        tmp[l + 1] -= 1;
                                        tmp[l + 2] -= 1;
                                        for (int m = l; m < bar.length; m++) {
                                            if (tmp[m] >= 3) {
                                                cnt++;
                                                tmp[m] -= 3;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("kssk");
                    return true;
                }


                //shunkekeshun
                cnt = 0;
                for (int h = 0; h < bar.length; h++) tmp[h] = bar[h];
                for (int j = 0; j < bar.length - 2; j++) {
                    if (tmp[j] >= 1 && tmp[j + 1] >= 1 && tmp[j + 2] >= 1) {
                        if (j >= 30) break;
                        cnt++;
                        tmp[j] -= 1;
                        tmp[j + 1] -= 1;
                        tmp[j + 2] -= 1;
                        for (int k = j; k < bar.length; k++) {
                            if (tmp[k] >= 3) {
                                cnt++;
                                tmp[k] -= 3;
                                for (int l = k; l < bar.length; l++) {
                                    if (tmp[l] >= 3) {
                                        cnt++;
                                        tmp[l] -= 3;
                                        for (int m = l; m < bar.length - 2; m++) {
                                            if (tmp[m] >= 1 && tmp[m + 1] >= 1 && tmp[m + 2] >= 1) {
                                                if (m >= 30) break;
                                                cnt++;
                                                tmp[m] -= 1;
                                                tmp[m + 1] -= 1;
                                                tmp[m + 2] -= 1;
                                                break;
                                            }
                                        }
                                        break;
                                    }
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                if (cnt == 4) {
                    //System.out.println("skks");
                    return true;
                }
                bar[i] += 2;
            }
        }
        return false;
    }
}
