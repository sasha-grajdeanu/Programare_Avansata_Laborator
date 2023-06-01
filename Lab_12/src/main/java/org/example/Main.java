package org.example;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        TestClass testClass = new TestClass();
        try {
            testClass.execute("org.example.TestClass");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Homework homework = new Homework();
        homework.execute("D:\\B2-Company-Back-End\\target\\classes\\com\\b2\\controllers");
    }
}