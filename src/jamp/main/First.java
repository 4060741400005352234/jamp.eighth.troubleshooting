package jamp.main;

import jamp.task.RunnableTask;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class First {

    private static Logger logger = Logger.getLogger(RunnableTask.class);

    public static void main(String[] args) throws Exception {
        logger.log(Level.INFO, "Start.");

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Lock firstLock = new ReentrantLock();
        Lock secondLock = new ReentrantLock();

        executorService.submit(new RunnableTask(firstLock, secondLock));
        executorService.submit(new RunnableTask(secondLock, firstLock));

        executorService.shutdown();
        executorService.awaitTermination(60000, TimeUnit.SECONDS);

        logger.log(Level.INFO, "Finish.");
    }
}
