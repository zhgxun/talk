package com.github.zhgxun.test.controller;

public class Test {

    public static void main(String args[]) {
        StringBuilder builder = new StringBuilder();
        int[] numbers = {1, 2, 3, 4, 5};
        for (int i : numbers) {
            builder.append(String.valueOf(i));
            builder.append(",");
        }
        String s = builder.toString();
        System.out.println(s.substring(0, s.length() - 1));
    }
}
