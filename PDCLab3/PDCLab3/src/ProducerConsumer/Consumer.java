package ProducerConsumer;

import java.util.Random;

public record Consumer(Drop drop) implements Runnable {

    public void run() {
        Random random = new Random();
        for (int number = drop.take(); number >= 0; number = drop.take()) {
            System.out.println("MESSAGE RECEIVED:" + number);
            try {
                Thread.sleep(random.nextInt(160));
            } catch (InterruptedException ignored) {
            }
        }
    }
}
