package com.fheebiy.java.classloader;

import java.util.Date;

public class ClassLoaderTest {

    //https://juejin.im/post/5a27604d51882503dc53938d

    //虽然对于classDate和classDate2来说，我们手动指定了她的类加载是我们自定义的myclassloader，
    // 但是根据类加载器的规则，我们能用父亲的loadclass就肯定不会用自己的，而我们系统类加载器，
    // AppClassLoader要想loadclass成功是需要传入完整的包名的。所以classDate的构造还是传入
    // 了完整的包名，这就是为啥classDate的加载器还是AppClassLoader，但是classDate2并没有
    // 传入完整的包名，所以AppClassLoader也是找不到这个CustomDate类的，最后只能
    // 交给MyClassLoader这个最底层的，我们自定义的classloader来load
    //


    public static void main(String[] args) {
        try {
            Class cls1 = new MyClassLoader("/Users/bob/Documents/jproject/JavaSt/src/main/java/com/fheebiy/java/classloader").loadClass("com.fheebiy.java.classloader.MyClsData");
            Class cls2 = new MyClassLoader("/Users/bob/Documents/jproject/JavaSt/src/main/java/com/fheebiy/java/classloader").loadClass("MyClsData");

            try {
                Date data1 = (MyClsData) cls1.newInstance();
                System.out.println("data1 classloader  = " + data1.getClass().getClassLoader().getClass().getName());
                System.out.println("data1  = " + data1);

                Date data2 = (Date) cls2.newInstance();
                System.out.println("data2 classloader  = " + data2.getClass().getClassLoader().getClass().getName());
                System.out.println("data2  = " + data2);


            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
