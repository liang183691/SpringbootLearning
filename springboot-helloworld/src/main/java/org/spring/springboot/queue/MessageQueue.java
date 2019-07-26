package org.spring.springboot.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;

public class MessageQueue {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //创建队列
        //ArrayBlockingQueue
        BlockingQueue<String> queue = new LinkedBlockingDeque<String>();//new ArrayBlockingQueue<String>(5);

        //创建生产者
        new Thread(new Producer(queue)).start();
        //创建消费者
        new Thread(new Consumer(queue)).start();


    }
}
