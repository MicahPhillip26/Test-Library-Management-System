/**
 * Name: Micah Phillip
 * Course: CEN 3024 - Software Development 11
 * Date: September 06, 2025,
 * Class: LibraryManagementSystem.java
 * This class provides the interface and functionality for managing patrons.
 * It allows the user to add patrons (manually or from file), remove patrons,
 * and display all current patrons. It handles validation and data integrity.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LibraryManagementSystem {

    private final List<Patron> patrons = new ArrayList<>();
    private final Scanner in = new Scanner(System.in);

    /**
     * Method: runMenu
     * Purpose: Starts the command-line interface for the system
     * Arguments: None
     * Returns: void
     */
    public void runMenu() {
        String choice;
        do {
            printMenu();
            choice = in.nextLine().trim();
            switch (choice) {
                case "1" -> addPatronsFromFile();
                case "2" -> addPatronManually();
                case "3" -> removePatronById();
                case "4" -> displayPatrons();
                case "5" -> System.out.println("Good‑bye!");
                default  -> System.out.println("❌  Invalid option. Try again.");
            }
        } while (!"5".equals(choice));
    }

    /**
     * Method: addPatronsFromFile
     * Purpose: Imports patrons from a file provided by the user
     * Arguments: None
     * Returns: void
     */
    private void addPatronsFromFile() {
        System.out.print("Enter path to patron file: ");
        String path = in.nextLine().trim();
        int imported = 0, skipped = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (parseAndAdd(line)) imported++; else skipped++;
            }
            System.out.printf("✔  Import finished (%d added, %d skipped).%n", imported, skipped);
        } catch (IOException e) {
            System.out.println("❌  Could not read file: " + e.getMessage());
        }
    }

    /**
     * Method: addPatronManually
     * Purpose: Adds a single patron from user input
     * Arguments: None
     * Returns: void
     */
    private void addPatronManually() {
        try {
            System.out.print("7‑digit ID: ");
            int id = Integer.parseInt(in.nextLine().trim());
            System.out.print("Name: ");
            String name = in.nextLine().trim();
            System.out.print("Address: ");
            String address = in.nextLine().trim();
            System.out.print("Fine (0‑250): ");
            double fine = Double.parseDouble(in.nextLine().trim());

            if (addValidated(new Patron(id, name, address, fine))) {
                System.out.println("✔  Patron added.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("❌  Invalid numeric entry.");
        }
    }

    /**
     * Method: removePatronById
     * Purpose: Removes a patron using their 7-digit ID
     * Arguments: None
     * Returns: void
     */
    private void removePatronById() {
        System.out.print("Enter 7‑digit ID to remove: ");
        try {
            int id = Integer.parseInt(in.nextLine().trim());
            if (patrons.removeIf(p -> p.getId() == id)) {
                System.out.println("✔  Patron removed.");
            } else {
                System.out.println("⚠  ID not found.");
            }
        } catch (NumberFormatException ex) {
            System.out.println("❌  Not a valid ID.");
        }
    }

    /**
     * Method: displayPatrons
     * Purpose: Displays all patrons currently stored
     * Arguments: None
     * Returns: void
     */
    private void displayPatrons() {
        if (patrons.isEmpty()) {
            System.out.println("(No patrons in system)");
        } else {
            System.out.println("ID       | Name                 | Address                       | Fine");
            System.out.println("---------+----------------------+--------------------------------+-------");
            patrons.forEach(System.out::println);
            System.out.printf("Total patrons: %d%n", patrons.size());
        }
    }

    /**
     * Method: parseAndAdd
     * Purpose: Parses a single line from a file and attempts to add a Patron
     * Arguments: String line - the raw input line from the file
     * Returns: boolean - true if added, false if invalid
     */
    private boolean parseAndAdd(String line) {
        String[] parts = line.split("\\s*-\\s*");
        if (parts.length != 4) {
            System.out.println("Skipping malformed line: " + line);
            return false;
        }
        try {
            int id       = Integer.parseInt(parts[0]);
            String name  = parts[1];
            String addr  = parts[2];
            double fine  = Double.parseDouble(parts[3]);

            return addValidated(new Patron(id, name, addr, fine));
        } catch (NumberFormatException ex) {
            System.out.println("Skipping malformed numeric data: " + line);
            return false;
        }
    }

    /**
     * Method: addValidated
     * Purpose: Validates and adds a Patron to the list
     * Arguments: Patron p - the patron to add
     * Returns: boolean - true if valid and added, false otherwise
     */
    private boolean addValidated(Patron p) {
        if (String.valueOf(p.getId()).length() != 7) {
            System.out.println("❌  ID must be exactly 7 digits.");
            return false;
        }
        if (patrons.stream().anyMatch(existing -> existing.getId() == p.getId())) {
            System.out.println("❌  Duplicate ID.");
            return false;
        }
        if (p.getFineAmount() < 0 || p.getFineAmount() > 250) {
            System.out.println("❌  Fine must be between 0 and 250.");
            return false;
        }
        patrons.add(p);
        return true;
    }

    /**
     * Method: printMenu
     * Purpose: Displays the main menu
     * Arguments: None
     * Returns: void
     */
    private void printMenu() {
        System.out.println("""
            \n===== Library Management System =====
            1) Import patrons from file
            2) Add patron manually
            3) Remove patron
            4) Display all patrons
            5) Exit
            Select an option:""");
    }
}

