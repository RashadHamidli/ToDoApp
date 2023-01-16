public class Main {
    public static void main(String[] args) {

        MyConsumer m=new MyConsumer("Rashad");
        MyConsumer m1=new MyConsumer("Hamid");
        MyConsumer m2=new MyConsumer("Farhad");
        MyConsumer m3=new MyConsumer("Valizade");
        MyConsumer m4=new MyConsumer("a");
        MyConsumer m5=new MyConsumer("b");
        MyConsumer m6=new MyConsumer("c");
        MyConsumer m7=new MyConsumer("d");
        MyConsumer m8=new MyConsumer("e");

        m.start();
        m1.start();
        m2.start();
        m3.start();
        m4.start();
        m5.start();
        m6.start();
        m7.start();
        m8.start();
    }
}