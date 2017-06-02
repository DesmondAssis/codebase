package com.desmond.codebase.base;

import java.util.Scanner;

/**
 * Created by Li.Xiaochuan on 16/11/23.
 */
public class Compute {

    /**
     * 计算长方形面积: 长x宽
     * @param length 长
     * @param width 宽
     * @return
     */
    public double comuteAreaOfRectangle(int length, int width) {
        return length * width;
    }

    /**
     * 计算三角形面积: 1/2 x 底 x 高
     * @param bottomLength
     * @param heigh
     * @return
     */
    public double computeAreaOfTriangle(int bottomLength, int heigh) {
        return 0.5 * bottomLength * heigh;
    }

    /**
     * 计算圆的面积: π*半径*半径
     * @param radius
     * @return
     */
    public double computeAreaOfCircle(int radius) {
        return Math.PI * radius * radius;
    }

    public static void main(String[] args) {
        Compute compute = new Compute();

        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择: 1-计算长方形面积, 2-计算三角形面积, 3-计算圆的面积:");
        int type = scanner.nextInt();

        switch (type) {
            case 1 :
                System.out.println("请输入长度:" );
                int length = scanner.nextInt();
                System.out.println("请输入宽度:" );
                int width = scanner.nextInt();

                System.out.println("长:" + length + ", 宽: " + width + ", 长方形面积: " + compute.comuteAreaOfRectangle(length, width));

                break;

            case 2 :
                System.out.println("请输入底长:" );
                int bottonLength = scanner.nextInt();
                System.out.println("请输入高度:" );
                int height = scanner.nextInt();

                System.out.println("底长:" + bottonLength + ", 高: " + height + ", 三角形面积: " + compute.computeAreaOfTriangle(bottonLength, height));

                break;

            case 3 :
                System.out.println("请输入圆的半径:" );
                int radius = scanner.nextInt();

                System.out.println("半径:" + radius + ", 圆的面积:" +  compute.computeAreaOfCircle(radius));

                break;
        }
    }
}
