Name: Micah Phillip

Course: Advanced Java Programming

CRN: COP-2805C

Program Name: Library-Management-System

Description
This Java program simulates a basic library management system focused on patron tracking. It demonstrates the use of object-oriented programming, data validation, and list operations. The program allows users to add, view, and remove patrons either manually or through a file import, all via a console-based menu.

Key Concepts and Tools Used

Object-Oriented Programming (Patron and LibraryManagementSystem classes)

File Input and Output

Input Validation

Java Collections Framework (List and Scanner)

Console-Based User Interface

Defensive Programming Practices

How It Works
The user is presented with a console menu with five options:

Import patrons from a file

Add a patron manually

Remove a patron

Display all patrons

Exit the program

When importing from a file, each patron must be formatted as:
1234567-John Doe-123 Main St-10.50

Patrons are validated before being added to ensure IDs are 7-digit and fines are between $0 and $250.

Users can view a neatly formatted list of all patrons.

Duplicate IDs are rejected to maintain unique identification.

Inputs and Outputs

Inputs:

Console input from the user (menu selection, patron details)

Text file input for batch patron import

Outputs:

Menu options and result messages are displayed on the console

Table-like list of all patrons

Validation messages for invalid or duplicate data

# Test-Library-Management-System-
