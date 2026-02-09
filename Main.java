/**
 * Name: Micah Phillip
 * Course: CEN 3024 - Software Development 1
 * Date: September 06, 2025,
 * Class: Main.java
 * This is the entry point of the program. It initializes the LibraryManagementSystem
 * and starts the command-line interface. The overall goal of the program is to
 * manage library patrons by allowing users to import, add, remove, and display patron data.
 */

public class Main {
    public static void main(String[] args) {
        new LibraryManagementSystem().runMenu();
    }
}

