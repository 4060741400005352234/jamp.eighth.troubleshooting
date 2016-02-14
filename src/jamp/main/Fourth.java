package jamp.main;

import jamp.task.SimpleTask;
import jamp.task.VerySlowTask;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fourth {

    private static Logger logger = Logger.getLogger(Fourth.class);

    public static final int SIMPLE_THREAD_COUNT = 4;

    public static void main(String[] args) throws Exception {
        logger.log(Level.INFO, "Start.");

        ExecutorService executorService = Executors.newCachedThreadPool();
        Lock lock = new ReentrantLock();

        executorService.execute(new VerySlowTask(lock));

        for (int i = 1; i <= SIMPLE_THREAD_COUNT; ++i) {
            executorService.submit(new SimpleTask(lock));
        }

        executorService.shutdown();
        executorService.awaitTermination(60000, TimeUnit.SECONDS);

        logger.log(Level.INFO, "Finish.");

    }
}
