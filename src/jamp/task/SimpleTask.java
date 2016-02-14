package jamp.task;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.concurrent.locks.Lock;

public class SimpleTask implements Runnable {

    private Logger logger = Logger.getLogger(SimpleTask.class);

    private final Lock lock;

    public SimpleTask(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        logger.log(Level.INFO, "Thread '" + Thread.currentThread().getName() + "' start execution.");
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Thread interrupted.");
        }
        lock.lock();
        try {
            logger.log(Level.INFO, "Thread '" + Thread.currentThread().getName() + "' is executing.");
        } finally {
            lock.unlock();
            logger.log(Level.INFO, "Thread '" + Thread.currentThread().getName() + "' finish execution.");
        }
    }
}
