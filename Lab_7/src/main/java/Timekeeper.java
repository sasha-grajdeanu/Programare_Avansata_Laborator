/**
 * Daemon
 * with the responsibility to time the program,
 * after the expiration of the given time,
 * is responsible for stopping the program,
 */
public class Timekeeper extends Thread {
    private long timeLimit;
    private Exploration exploration;

    public Timekeeper(long timeLimit, Exploration exploration) {
        this.timeLimit = timeLimit;
        this.exploration = exploration;
        setDaemon(true);
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        while (true) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            System.out.println("Timp de rulare : " + elapsedTime / 1000.0 + " secunde");
            if (elapsedTime >= timeLimit) {
                System.out.println("Timp depasit. Oprire program.");
                System.exit(0);
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (exploration.getMap().isComplet()) break;
        }
    }

}