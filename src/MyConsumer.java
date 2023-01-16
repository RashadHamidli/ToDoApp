public class MyConsumer extends Thread{
    private String name;
    public MyConsumer(String name){
        this.name=name;
    }
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            System.out.println(name + " is processed");
        }
    }
}
