package ProducerConsumer;

import java.util.Random;

public record Producer(Drop drop, int size) implements Runnable {

    public void run() {
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            drop.put(i);
            try {
                Thread.sleep(random.nextInt(160));
            } catch (InterruptedException ignored) {
            }
        }
    }
}

