public class Main {
    public static void main(String[] args) {

        Thread thread = new Thread(new MyProducer()); // implement Runnable
        Thread thread1 = new Thread(new MyProducer());
        Thread thread2 = new Thread(new MyConsumer("a"));
        Thread thread3=new Thread(new MyConsumer("b"));
        Thread thread4=new Thread(new MyConsumer("c"));

//        MyProducer p = new MyProducer(); // extends Thread
//        MyProducer p1 = new MyProducer();
//        MyConsumer m = new MyConsumer("a");
//        MyConsumer m1 = new MyConsumer("b");
//        MyConsumer m2 = new MyConsumer("c");

        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }
}