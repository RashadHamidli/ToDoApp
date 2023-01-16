public class Main {
    public static void main(String[] args) {

        MyWorker m=new MyWorker();
        MyWorker m1=new MyWorker();
        MyWorker m2=new MyWorker();
        MyWorker m3=new MyWorker();

        m.start();
        m1.start();
        m2.start();
        m3.start();

    }
}