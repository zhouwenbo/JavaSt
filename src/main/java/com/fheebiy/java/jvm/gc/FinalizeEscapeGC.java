package com.fheebiy.java.jvm.gc;

public class FinalizeEscapeGC {

    public static FinalizeEscapeGC sFinalizeEscapeGC;

    private void isAlive() {
        System.out.println("Yes, i am still alive:>");
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Execute finalize method!!!");
        sFinalizeEscapeGC = this;
    }

    public static void main(String[] args) throws InterruptedException {
        sFinalizeEscapeGC = new FinalizeEscapeGC();
        sFinalizeEscapeGC = null;

        //对象第一次成功拯救自己
        System.gc();
        //因为finalize()方法优先级很低，所以让暂停0.5秒，等待它执行
        Thread.sleep(500);
        if (sFinalizeEscapeGC != null) {
            sFinalizeEscapeGC.isAlive();
        } else {
            System.out.println("No, i am dead:<>");
        }

        //这端代码合上面的一样，但是却拯救失败了，是因为finalize方法以及被调用过了，就不会再次调用了
        sFinalizeEscapeGC = null;
        //因为finalize()方法优先级很低，所以让暂停0.5秒，等待它执行
        System.gc();
        Thread.sleep(500);
        if (sFinalizeEscapeGC != null) {
            sFinalizeEscapeGC.isAlive();
        } else {
            System.out.println("No, i am dead:<>");
        }

    }
}
