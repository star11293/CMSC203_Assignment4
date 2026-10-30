/*
 * Class: CMSC203 Computer Science I
 * Instructor: Prof. Thai
 * Description: JUnit 5 tests for the PropertyDriver menu (feeds in keyboard input and checks the output).
 * Due: 10/30/2026
 * Platform/compiler: Eclipse, Java (JDK 25)
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Akene Crosse
 */

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Runs PropertyDriver with typed-in input and checks what it prints.
 */
public class PropertyDriverJUnitTestStudent {

    private final InputStream realIn = System.in;
    private final PrintStream realOut = System.out;
    private ByteArrayOutputStream output;

    @BeforeEach
    public void setUp() {
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    public void tearDown() {
        System.setIn(realIn);
        System.setOut(realOut);
    }

    /** Helper: runs the driver with the given input and returns everything it printed. */
    private String run(String keys) {
        System.setIn(new ByteArrayInputStream(keys.getBytes()));
        PropertyDriver.main(new String[0]);
        return output.toString();
    }

    /** Adding two properties gives index 0 and 1, and the total rent is right. */
    @Test
    public void testAddAndTotal() {
        String out = run("1\nLoft\nRockville\n2000\nJo\n1\nStudio\nLaurel\n850.5\nTy\n3\n4\n");
        assertTrue(out.contains("Property added at index 0."));
        assertTrue(out.contains("Property added at index 1."));
        assertTrue(out.contains("Total rent: $2850.50"));
    }

    /** Display shows the company and its properties. */
    @Test
    public void testDisplay() {
        String out = run("1\nLoft\nRockville\n2000\nJo\n2\n4\n");
        assertTrue(out.contains("Management Company: Campus Realty (Tax ID: 123-45-6789)"));
        assertTrue(out.contains("0: Loft, Rockville, Rent: $2000.00, Owner: Jo"));
    }

    /** A bad menu choice and bad rent get re-asked instead of crashing. */
    @Test
    public void testBadInput() {
        String out = run("9\nabc\n1\nLoft\nRockville\nlots\n-5\n1200\nJo\n4\n");
        assertTrue(out.contains("Invalid choice. Please enter 1-4."));
        assertTrue(out.contains("Please enter a number."));
        assertTrue(out.contains("Please enter a number for the rent."));
        assertTrue(out.contains("Rent can't be negative."));
        assertTrue(out.contains("Property added at index 0."));
    }

    /** Can't add more than MAX_PROPERTIES. */
    @Test
    public void testFull() {
        String keys = "";
        for (int i = 0; i < ManagementCompany.MAX_PROPERTIES; i++) {
            keys += "1\nP" + i + "\nCity\n100\nOwner\n";
        }
        String out = run(keys + "1\n4\n");
        assertTrue(out.contains("Sorry, the company is full (max 5 properties)."));
    }

    /** Exit shows the copy constructor backup and the programmer's name. */
    @Test
    public void testExit() {
        String out = run("1\nLoft\nRockville\n2000\nJo\n4\n");
        assertTrue(out.contains("Backup made with the copy constructor:"));
        assertTrue(out.contains("Properties: 1, Total rent: $2000.00"));
        assertTrue(out.contains("Programmer: Akene Crosse"));
    }
}
