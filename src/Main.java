public class Main {
    public static void main(String[] args) {

        MyProducer p = new MyProducer();
        MyProducer p2 = new MyProducer();
        MyConsumer m = new MyConsumer("a");
        MyConsumer m1 = new MyConsumer("b");
        MyConsumer m2 = new MyConsumer("c");
        MyConsumer m3 = new MyConsumer("d");
        MyConsumer m4 = new MyConsumer("e");
        MyConsumer m5 = new MyConsumer("f");


        p.start();
        m.start();
        m1.start();
        m2.start();
        m3.start();
        m4.start();
        m5.start();

    }
}