package org.example;

import java.net.MalformedURLException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        TestClass testClass = new TestClass();
        try {
            testClass.execute("org.example.TestClass");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}