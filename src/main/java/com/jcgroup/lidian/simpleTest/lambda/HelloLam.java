package com.jcgroup.lidian.simpleTest.lambda;

public class HelloLam {
    public static void main(String args[]){
        new Thread(() -> System.out.println("hello world")).start();
    }
}
