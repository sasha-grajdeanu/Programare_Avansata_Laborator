package org.example;

import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestClass {
    /**
     * load the class and print info. about the class and runt the test
     *
     * @param nume
     * @throws ClassNotFoundException
     */
    public void execute(String nume) throws ClassNotFoundException {
        Class clasa = Class.forName(nume);
        Package pachet = clasa.getPackage();
        System.out.println(pachet.getName());
        Method[] metode = clasa.getMethods();
        for (int i = 0; i < metode.length; i++) {
            System.out.println(metode[i].getName());
        }
        for (int i = 0; i < metode.length; i++) {
            if (metode[i].isAnnotationPresent(Test.class) && metode[i].getParameterCount() == 0 && Modifier.isStatic(metode[i].getModifiers())) {
                System.out.println("TEST: " + metode[i].getName());
                try {
                    metode[i].invoke(null);
                } catch (Exception e) {
                    System.out.println("TEST PICAT " + metode[i].getName());
                    e.printStackTrace();
                }
            }
        }

    }
}
