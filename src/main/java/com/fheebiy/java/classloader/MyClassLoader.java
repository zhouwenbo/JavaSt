package com.fheebiy.java.classloader;

import java.io.*;

public class MyClassLoader extends ClassLoader {


    String classDir;

    public MyClassLoader() {

    }

    public MyClassLoader(String classDir) {
        this.classDir = classDir;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String classFile = classDir + "/" + name + ".class";
        System.out.println("classFile path==" + classFile);

        try {
            //这个地方我们只是简单的读取文件流的方式来获取byte数组
            //其实可以尝试将class文件加密以后 这里解密 这样就可以保证
            //这种class文件 只有你写的classloader才能读取的了。
            //其他任何classloader都读取不了 包括系统的。
            byte[] classByte = toByteArray(classFile);
            return defineClass(classByte, 0, classByte.length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.findClass(name);

    }


    /**
     * the traditional io way
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filename) throws IOException, FileNotFoundException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

}
