package threading;

public class BasicThreading {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            System.out.println("Printing via thread : " + Thread.currentThread().getName());
        }, "CustomeThreadName");
        Thread t2 = new Thread(() -> {
            System.out.println("Printing via thread : " + Thread.currentThread().getName());
        }, "CustomeThreadName");
        t.start();
        System.out.println("Printing via main : " + Thread.currentThread().getName());
    }
}
