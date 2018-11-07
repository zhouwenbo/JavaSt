package com.fheebiy.java.classloader;

import java.util.List;

public class JVMClassLoaderTest {


    public static void main(String[] args) {
        System.out.println("当前类的classloader : " + JVMClassLoaderTest.class.getClassLoader());
        System.out.println("System的classloader : " + System.class.getClassLoader());
        System.out.println("List的classloader : " + List.class.getClassLoader());
        System.out.println("String的classloader : " + String.class.getClassLoader());


        ClassLoader cl = JVMClassLoaderTest.class.getClassLoader();
        while(cl != null){
            System.out.print(cl.getClass().getName()+"->");
            cl = cl.getParent();
        }
        System.out.println(cl);



    }
}
