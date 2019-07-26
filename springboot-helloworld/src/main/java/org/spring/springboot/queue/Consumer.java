package org.spring.springboot.queue;

import java.util.concurrent.*;

public class Consumer implements Runnable{

    private BlockingQueue<String> queue;
    public Consumer(BlockingQueue<String> queue){
        this.queue = queue;
    }
    @Override
    public void run() {

        int nThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newCachedThreadPool();//newFixedThreadPool(nThreads);

        //创建12个消费者
        for(int i = 0; i < 12; i ++){
            Future<String> future = service.submit(new Callable<String>(){
                @Override
                public String call() throws Exception {
                    String element = queue.poll();
                    if(element == null){
                        return "队列中没有元素了";

                    }
                    return "消费者消费："+element;
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
