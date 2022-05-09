public class Main {

    static Thread threadA = new Thread(){
        public void run() {

            Boolean state = false;
            while(true) {
                try {
                    Thread.sleep(100);
                    state = !state;
                    if (state) {
                        threadB.start();
                    }
                    else {
                        threadB.interrupt();
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    static Thread threadB = new Thread(){
        public void run(){
            float timeLeft = 100f;
            long delta = 0;
            while(true){
                long now = System.nanoTime();
                timeLeft -= delta/1000f;
                System.out.print(timeLeft);
                delta = System.nanoTime() - now;
            }
        }
    };

    public static void main(String[] args) throws InterruptedException {
        threadA.start();
    }
}