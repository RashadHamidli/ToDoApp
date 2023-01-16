import java.util.UUID;

public class MyProducer implements Runnable {
    @Override
    public void run() {
        while (true) {
            Base.list.add(UUID.randomUUID().toString());
        }
    }
}
