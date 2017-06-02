package com.desmond.codebase.hello;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Li.Xiaochuan on 16/10/14.
 */
public class YangXing {
    public static void main(String[] args) {
        String[][] a=new String[10][10];
        for(int i=0;i<a.length;i++){
            for(int b=0;b<a.length;b++){
                a[i][b]="≌";
            }
        }
        int y=0, x=0; // "囚" 的当前位置
        int sx = 6, sy = 3; // "△" 的当前位置
        int fsy = 8, fsx = 9; // "☆" 的最终位置
        a[y][x]="囚";
        a[3][6]="△";
        a[8][9]="☆";
        while(true){
            if(sy != fsy || sx != fsx) {
                a[8][9]="☆"; // "☆"为固定位置
            }
            for(int i=0;i<a.length;i++){
                for (int j = 0; j < a.length; j++) {
                    System.out.print(a[i][j]);
                }
                System.out.println();
            }

            if(sy == fsy && sx == fsx) { //  "△"  到达 "☆" 的位置
                System.out.println("胜利!Game Over");
                break;
            }

            Scanner scan=new Scanner(System.in);
            System.out.println("请输入移动方向(wsad):");
            String f=scan.next();
            if (f.equals("s")) {
                if(y + 1 <= 9) {
                    if(a[y+1][x].equals("△") && y + 2 <= 9) {
                        a[y][x]="≌";
                        y++;
                        a[y][x]="囚";
                        a[y+1][x]="△";
                        sx = x; sy = y + 1;
                    } else if(!a[y +1][x].equals("△")){
                        a[y][x]="≌";
                        y++;
                        a[y][x]="囚";
                    }
                }
            }else if(f.equals("w")){
                if(y - 1 >= 0) {
                    if(a[y-1][x].equals("△") && (y - 2 >= 0)) {
                        a[y][x]="≌";
                        y--;
                        a[y][x]="囚";
                        a[y-1][x]="△";

                        sx = x; sy = y - 1;
                    } else if(!a[y - 1][x].equals("△")){
                        a[y][x]="≌";
                        y--;
                        a[y][x]="囚";
                    }
                }
            }else if(f.equals("d")){
                if(x + 1 <= 9) {
                    if(a[y][x+1].equals("△") && (x + 2) <= 9) {
                        a[y][x]="≌";
                        x++;
                        a[y][x]="囚";
                        a[y][x+1]="△";

                        sx = x + 1; sy = y;
                    } else if(!a[y][x + 1].equals("△")){
                        a[y][x]="≌";
                        x++;
                        a[y][x]="囚";
                    }
                }
            }else if(f.equals("a")){
                if(x-1 >= 0) {
                    if(a[y][x-1].equals("△") && (x - 2 >= 0)) {
                        a[y][x]="≌";
                        x--;
                        a[y][x]="囚";
                        a[y][x-1]="△";

                        sx = x - 1; sy = y;
                    } else if(!a[y][x - 1].equals("△")){
                        a[y][x]="≌";
                        x--;
                        a[y][x]="囚";
                    }
                }
            }

        }
    }
}
