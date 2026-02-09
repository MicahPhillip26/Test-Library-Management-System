/**
 * Name: Micah Phillip
 * Course: CEN 3024 - Software Development 1
 * Date: September 06, 2025,
 * Class: Patron.java
 * This class defines a single library patron with ID, name, address, and fine amount.
 * It is an immutable value object used by the LibraryManagementSystem class to track users.
 */

import java.text.DecimalFormat;

public class Patron {
    private final int id;
    private final String name;
    private final String address;
    private final double fineAmount;

    private static final DecimalFormat MONEY = new DecimalFormat("0.00");

    // Constructor
    public Patron(int id, String name, String address, double fineAmount) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.fineAmount = fineAmount;
    }

    // Getters
    public int getId()            { return id; }
    public String getName()       { return name; }
    public String getAddress()    { return address; }
    public double getFineAmount() { return fineAmount; }

    /**
     * Method: toString
     * Purpose: Returns a formatted string representation of the patron
     * Arguments: None
     * Returns: A string showing ID, name, address, and fine
     */
    @Override
    public String toString() {
        return String.format("%07d | %-20s | %-30s | $%s",
                id, name, address, MONEY.format(fineAmount));
    }
}

