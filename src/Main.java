public class Main  {
    public static void main(String[] args) throws Exception {

        System.out.println("all threads are starting");
        Thread[] threads= new Thread[100];
       for(int i =0;i< threads.length;i++){
           threads[i]= new Thread(new MyConsumer("test"));
       }
       for(int i=0; i<threads.length;i++){
           threads[i].start();
       }
       Thread.sleep(5000);
        System.out.println(Base.getA());
//        Thread thread = new Thread(new MyProducer()); // implement Runnable
//        Thread thread2 = new Thread(new MyConsumer("a"));
//        Thread thread3=new Thread(new MyConsumer("b"));
//        Thread thread4=new Thread(new MyConsumer("c"));
///
////        MyProducer p = new MyProducer(); // extends Thread
////        MyConsumer m = new MyConsumer("a");
////        MyConsumer m1 = new MyConsumer("b");
//
//        thread.start();
//        thread2.start();
//        thread3.start();
//        thread4.start();
    }
}