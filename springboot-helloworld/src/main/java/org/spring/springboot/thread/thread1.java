package org.spring.springboot.thread;

public class thread1 {
    public static void main(String[] args) {
       /* Account accout = new Account("act1", 5000);
        Thread t1 = new Thread(new Processor1(accout),"a");
        Thread t2 = new Thread(new Processor1(accout),"b");
        t1.start();
        t2.start();*/
        MyClass mc1=new MyClass();
        MyClass mc2=new MyClass();
        Thread t1=new Thread(new Runnable1(mc1));
        Thread t2=new Thread(new Runnable1(mc2));
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        //延迟,保证t1先执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
class Processor implements Runnable {
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + "," + i);
            if (i % 10 == 0) {
                try {
                    //睡眠 100 毫秒，主要是放弃 CPU 的使用，将 CPU 时间片交给其他线程使用
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

    class Processor1 implements Runnable{
        Account act;
        Processor1(Account act){
            this.act=act;
        }
        @Override
        public void run() {

            System.out.println(Thread.currentThread().getName());
            synchronized(Processor1.class) {
            act.setBalance(act.getBalance() - 1000);
                try {
                    if("a".equals(Thread.currentThread().getName()))
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("取款1000.0成功,余额: " + act.getBalance());
            }
        }

    }

    class Account {
        public void withdraw(int money){
            synchronized (this) {
                this.setBalance(balance - 1000);
            }
        }
        private String actno;
        private double balance;

        public Account() {
            super();
        }

        public Account(String actno, double balance) {
            super();
            this.actno = actno;
            this.balance = balance;
        }

        public String getActno() {
            return actno;
        }

        public void setActno(String actno) {
            this.actno = actno;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }

class Runnable1 implements Runnable{
    MyClass mc;
    Runnable1(MyClass mc){
        this.mc=mc;
    }
    @Override
    public void run() {

        if("t1".equals(Thread.currentThread().getName())){
            MyClass.m1();//因为是静态方法,用的还是类锁,和对象锁无关
        }
        if("t2".equals(Thread.currentThread().getName())){
            MyClass.m2();
        }

    }
}
class MyClass{
    //synchronized添加到静态方法上,线程执行此方法的时候会找类锁,类锁只有一把
    public synchronized static void m1(){
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m1()............");
    }
    /**
     *  m2()不会等m1结束,因为该方法没有被synchronized修饰
     */
//    public static void m2(){
//        System.out.println("m2()........");
//    }
    /**
     * m2方法等m1结束之后才能执行,该方法有synchronized
     * 线程执行该方法需要"类锁",而类锁只有一个.
     */
    public synchronized static void m2(){
        System.out.println("m2()........");
    }
}

