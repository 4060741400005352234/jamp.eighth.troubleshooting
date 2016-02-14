package jamp.task;

import jamp.model.Spoon;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Random;

public class Philosopher implements Runnable {

    private static Logger logger = Logger.getLogger(Philosopher.class);
    private static Random rand = new Random();
    private static int counter = 0;

    private int number = counter++;

    private final Spoon leftSpoon;
    private final Spoon rightSpoon;

    public Philosopher(final Spoon left, final Spoon right) {
        leftSpoon = left;
        rightSpoon = right;
    }

    public void think() {
        logger.log(Level.INFO, this + " thinking.");
        try {
            Thread.sleep(rand.nextInt(2000));
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, this + " interrupted.");
            throw new RuntimeException(e);
        }
    }

    public void eat() {
        synchronized (leftSpoon) {
            logger.log(Level.INFO, this + " has " + this.leftSpoon + " Waiting for " + this.rightSpoon);
            try {
                Thread.sleep(rand.nextInt(1000));
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, this + " interrupted.");
            }
            synchronized (rightSpoon) {
                logger.log(Level.INFO, this + " eating");
            }
        }
    }

    public String toString() {
        return "Philosopher " + number;
    }

    @Override
    public void run() {
        while (true) {
            think();
            eat();
        }
    }
}
