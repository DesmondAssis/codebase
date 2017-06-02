package com.desmond.codebase.base;

/**
 * Created by Li.Xiaochuan on 16/11/23.
 */
public class Score {
    private int chinese;
    private int math;
    private int english;

    public Score() {
    }

    public Score(int chinese, int math, int english) {
        this.chinese = chinese;
        this.math = math;
        this.english = english;
    }

    public static void main(String[] args) {
        Score score = new Score(80, 99, 90);
        System.out.println("语数外三科总分:" + score.sum());
    }

    public int sum() {
        return chinese + math + english;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getMath() {
        return math;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public int getEnglish() {
        return english;
    }

    public void setEnglish(int english) {
        this.english = english;
    }
}
