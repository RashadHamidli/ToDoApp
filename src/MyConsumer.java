public class MyConsumer extends Thread {
    private String name;

    public MyConsumer(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(2000);
        }  catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Base.incA();


//        while (true) {
//            int lastIndex = Base.list.size() - 1;
//            System.out.println("last index="+lastIndex);
//            if (lastIndex > -1) {
//                String s = Base.list.remove(lastIndex);
//                System.out.println(name+"=" + s);
//            }
//        }
    }
}


