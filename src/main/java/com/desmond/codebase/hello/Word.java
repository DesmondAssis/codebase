package com.desmond.codebase.hello;

import java.util.Scanner;

/**
 * Created by Li.Xiaochuan on 16/10/14.
 */
public class Word {

    public static void main(String[] args) {

        System.out.println("请输入一个数:");
        Scanner a=new Scanner(System.in);
        int a1=a.nextInt();




        for(int i=0;i<=a1;i++)
            System.out.println(i+"+"+(a1-i)+"="+a1+"");
    }

}
