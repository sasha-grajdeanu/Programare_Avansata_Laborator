package org.example;

import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class Homework {

    public List<Class<?>> classList;

    private int nrDeClase = 0;
    private int nrDeTeste = 0;
    private int nrDeSucces = 0;
    private int nrDeEsec = 0;


    /**
     * method: load the classes from the path
     *
     * @param path
     * @throws ClassNotFoundException
     */
    private void loadListClasses(String path) throws ClassNotFoundException {

        classList = new ArrayList<>();
        File director = new File(path);

        if (director.exists() && director.isDirectory()) {
            exploreDirector(director);
        } else {
            System.err.println("CALE INVALIDA");
        }
    }

    /**
     * extract class from the path
     *
     * @param director
     * @throws ClassNotFoundException
     */
    private void exploreDirector(File director) throws ClassNotFoundException {
        File[] directoare = director.listFiles();
        if (directoare != null) {
            for (File file : directoare) {
                if (file.isDirectory()) {
                    exploreDirector(file);
                } else if (file.getName().endsWith(".class")) {
                    String className = file.getPath().replace(File.separator, ".").replace(".class", "");
                    Class<?> clasa = Class.forName(className);
                    classList.add(clasa);
                }
            }
        }
    }

    /**
     * testing the classes
     */
    private void testingClase() {
        for (Class<?> clasa : classList) {
            if (Modifier.isPublic(clasa.getModifiers()) && clasa.isAnnotationPresent(Test.class)) {
                System.out.println("CLASA = " + clasa.getName());
                nrDeClase++;
                Method[] methods = clasa.getDeclaredMethods();
                for (Method method : methods) {
                    if (Modifier.isPublic(method.getModifiers()) && method.isAnnotationPresent(Test.class)) {
                        System.out.println("RULARE METODA: " + method.getName());
                        nrDeTeste++;
                        try {
                            Object instanta = null;
                            if (!Modifier.isStatic(method.getModifiers())) {
                                Constructor<?> constructor = clasa.getDeclaredConstructor();
                                constructor.setAccessible(true);
                                instanta = constructor.newInstance();
                            }
                            Class<?>[] parametrii = method.getParameterTypes();
                            Object[] argumente = new Object[parametrii.length];
                            for (int i = 0; i < parametrii.length; i++) {
                                argumente[i] = generare(parametrii[i]);
                            }
                            method.invoke(instanta, argumente);
                            System.out.println("TRECUT");
                            nrDeSucces++;
                        } catch (Exception e) {
                            System.out.println("PICAT " + e.getMessage());
                            nrDeEsec++;
                        }
                    }
                }
            }

        }
    }

    /**
     * execute all homework
     *
     * @param path
     */
    public void execute(String path) {
        try {
            this.loadListClasses(path);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.testingClase();
        this.statistici();
    }

    /**
     * print the statistics
     */
    private void statistici() {
        System.out.println("NR DE CLASE " + nrDeClase);
        System.out.println("NR DE TESTE " + nrDeTeste);
        System.out.println("SUCCES " + nrDeSucces);
        System.out.println("ESEC " + nrDeEsec);
    }

    /**
     * generate mock parameter
     *
     * @param tip
     * @return
     */
    private Object generare(Class<?> tip) {
        if (tip == int.class || tip == Integer.class) {
            return 0;
        } else if (tip == String.class) {
            return "test";
        } else {
            return null;
        }
    }
}
