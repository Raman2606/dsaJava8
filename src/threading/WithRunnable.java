package threading;

import java.util.concurrent.atomic.AtomicInteger;

public class WithRunnable {

     static class MyRunnable implements Runnable {

         private AtomicInteger count= new AtomicInteger(0);
         @Override
         public void run() {
             for(int i=0;i<1000;i++) {
                 this.count.incrementAndGet();
             }
             System.out.println(Thread.currentThread().getName() + " printed count as : " + this.count);
         }
     }

    public static void main(String[] args) {
        MyRunnable runnable1 = new MyRunnable();
        MyRunnable runnable2 = new MyRunnable();
        Thread thread1 = new Thread(runnable1, "Thread-1");
        Thread thread2 = new Thread(runnable1, "Thread-2");
        thread1.start();
        thread2.start();
    }
}
