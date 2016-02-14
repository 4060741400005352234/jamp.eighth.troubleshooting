package jamp.model;

public class Spoon {

    private static int counter = 0;

    private int number = counter++;

    @Override
    public String toString() {
        return "Spoon " + number;
    }
}
