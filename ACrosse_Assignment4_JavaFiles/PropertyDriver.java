/*
 * Class: CMSC203 Computer Science I
 * Instructor: Prof. Thai
 * Description: Console menu for the Property Management app. Lets the user
 *     add properties to a ManagementCompany, list them and see total rent.
 * Due: 10/30/2026
 * Platform/compiler: Eclipse, Java (JDK 25)
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Akene Crosse
 */

import java.util.Scanner;

/**
 * Driver for the Property Management application.
 */
public class PropertyDriver {

    /**
     * Shows the menu until the user picks Exit.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ManagementCompany company = new ManagementCompany("Campus Realty", "123-45-6789");
        int choice = 0;

        while (choice != 4) {
            printMenu();
            choice = readInt(input, "Enter choice (1-4): ");

            switch (choice) {
                case 1:
                    addProperty(input, company);
                    break;
                case 2:
                    System.out.println(company);
                    break;
                case 3:
                    System.out.printf("Total rent: $%.2f%n", company.totalRent());
                    break;
                case 4:
                    showCopyDemo(company);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1-4.");
            }
            System.out.println();
        }

        System.out.println("Goodbye!");
        System.out.println("Programmer: Akene Crosse");
        input.close();
    }

    /**
     * Prints the menu.
     */
    public static void printMenu() {
        System.out.println("====================================");
        System.out.println("   Assignment 4: Property Manager   ");
        System.out.println("====================================");
        System.out.println("1 - Add Property");
        System.out.println("2 - Display All Properties");
        System.out.println("3 - Show Total Rent");
        System.out.println("4 - Exit");
    }

    /**
     * Asks for a property's info and adds it to the company.
     *
     * @param input   the Scanner to read from
     * @param company the company to add to
     */
    public static void addProperty(Scanner input, ManagementCompany company) {
        if (company.getPropertyCount() >= ManagementCompany.MAX_PROPERTIES) {
            System.out.println("Sorry, the company is full (max "
                    + ManagementCompany.MAX_PROPERTIES + " properties).");
            return;
        }
        System.out.print("Enter property name: ");
        String name = input.nextLine();
        System.out.print("Enter city: ");
        String city = input.nextLine();
        double rent = readRent(input);
        System.out.print("Enter owner name: ");
        String owner = input.nextLine();

        int index = company.addProperty(new Property(name, city, rent, owner));
        System.out.println("Property added at index " + index + ".");
    }

    /**
     * Reads a whole number, asking again until the user types one.
     *
     * @param input  the Scanner to read from
     * @param prompt what to ask
     * @return the number entered
     */
    public static int readInt(Scanner input, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = input.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    /**
     * Reads the monthly rent, asking again if it's not a number or is negative.
     *
     * @param input the Scanner to read from
     * @return the rent entered
     */
    public static double readRent(Scanner input) {
        while (true) {
            System.out.print("Enter monthly rent: ");
            String line = input.nextLine().trim();
            try {
                double rent = Double.parseDouble(line);
                if (rent >= 0) {
                    return rent;
                }
                System.out.println("Rent can't be negative.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number for the rent.");
            }
        }
    }

    /**
     * Before exiting, makes a backup of the company with the copy
     * constructor and shows it has the same properties and total rent.
     *
     * @param company the company to copy
     */
    public static void showCopyDemo(ManagementCompany company) {
        ManagementCompany backup = new ManagementCompany(company);
        System.out.println("Backup made with the copy constructor:");
        System.out.println("  Properties: " + backup.getPropertyCount()
                + ", Total rent: " + String.format("$%.2f", backup.totalRent()));
    }
}
