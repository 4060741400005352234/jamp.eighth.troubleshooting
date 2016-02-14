package jamp.task;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;

public class VerySlowTask implements Runnable {

    private static Logger logger = Logger.getLogger(VerySlowTask.class);

    private final Lock lock;

    public VerySlowTask(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Very slow thread starts work.");
        lock.lock();
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Very slow thread is interrupted.");
        } finally {
            lock.unlock();
            logger.log(Level.INFO, "Very slow thread finish work.");
        }
    }
}
