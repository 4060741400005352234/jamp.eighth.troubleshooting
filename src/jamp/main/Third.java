package jamp.main;

import jamp.model.Spoon;
import jamp.task.Philosopher;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Third {

    private static Logger logger = Logger.getLogger(Third.class);

    public static final boolean isDeadlock = true;
    public static final int PHILOSOPHER_COUNT = 4;

    public static void main(String[] args) throws Exception {
        logger.log(Level.INFO, "Start.");

        ExecutorService executorService = Executors.newCachedThreadPool();

        Spoon left = new Spoon();
        Spoon right = new Spoon();
        Spoon first = left;

        Philosopher lastPhilosopher = null;
        for (int i = 1; i <= PHILOSOPHER_COUNT; ++i) {
            lastPhilosopher = new Philosopher(left, right);
            left = right;
            right = new Spoon();
            executorService.submit(lastPhilosopher);
        }

        if(isDeadlock) {
            lastPhilosopher = new Philosopher(left, first);
            executorService.submit(lastPhilosopher);
        } else {
            lastPhilosopher = new Philosopher(first, left);
            executorService.submit(lastPhilosopher);
        }

        executorService.shutdown();
        executorService.awaitTermination(60000, TimeUnit.SECONDS);

        logger.log(Level.INFO, "Finish.");
    }
}
