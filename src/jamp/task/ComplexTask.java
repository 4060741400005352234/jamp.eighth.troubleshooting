package jamp.task;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;

public class ComplexTask implements Runnable {

    private Logger logger = Logger.getLogger(ComplexTask.class);

    private final Lock firstLock;
    private final Lock secondLock;

    public ComplexTask(final Lock firstLock, final Lock secondLock) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
    }

    @Override
    public void run() {
        firstLock.lock();
        try {
            logger.log(Level.INFO, "Thread '" + Thread.currentThread().getName() + "' acquired first lock.");
            try {
                Thread.sleep(5000);
                secondLock.lock();
                logger.log(Level.INFO, "Thread '" + Thread.currentThread().getName() + "' acquired second lock.");
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Thread '" + Thread.currentThread().getName() + "' interrupted.");
            } finally {
                secondLock.unlock();
                logger.log(Level.INFO, "Thread '" + Thread.currentThread().getName() + "' released second lock.");
            }
        } finally {
            firstLock.unlock();
            logger.log(Level.INFO, "Thread '" + Thread.currentThread().getName() + "' released first lock.");
        }
    }
}
