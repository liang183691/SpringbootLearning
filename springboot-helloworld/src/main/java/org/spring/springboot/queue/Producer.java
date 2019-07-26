package org.spring.springboot.queue;

import javax.swing.*;
import java.util.concurrent.*;

public class Producer implements Runnable{

    private BlockingQueue<String> queue;
    public Producer(BlockingQueue<String> queue){
        this.queue = queue;
    }
    @Override
    public void run() {
        int nThreads = Runtime.getRuntime().availableProcessors();//获得当前系统的核数
        //创建线程池
        ExecutorService service = Executors.newCachedThreadPool();//newFixedThreadPool(nThreads);

        //创建10个生产者
        for(int i = 0; i < 10; i ++){
            Future<String> future = service.submit(new Callable<String>(){

                @Override
                public String call() throws Exception {
                    String threadName = Thread.currentThread().getName();
                    boolean flag = queue.offer(threadName+":一条消息");
                    if(flag){
                        return threadName+"生产者生产了一条消息";
                    }else{
                        return "队列已满，"+threadName+"，生产消息失败";
                    }
                }

            });

            try {
                System.out.println(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        service.shutdown();

    }

}
