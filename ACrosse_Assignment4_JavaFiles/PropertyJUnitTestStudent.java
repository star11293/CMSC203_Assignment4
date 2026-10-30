/*
 * Class: CMSC203 Computer Science I
 * Instructor: Prof. Thai
 * Description: JUnit 5 tests for the Property class.
 * Due: 10/30/2026
 * Platform/compiler: Eclipse, Java (JDK 25)
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Akene Crosse
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the Property constructors, copy constructor, getters/setters and toString.
 */
public class PropertyJUnitTestStudent {

    private Property house;

    @BeforeEach
    public void setUp() {
        house = new Property("Maple House", "Owings Mills", 1850.50, "Star");
    }

    @AfterEach
    public void tearDown() {
        house = null;
    }

    /** Default constructor starts empty with 0 rent. */
    @Test
    public void testDefaultConstructor() {
        Property p = new Property();
        assertEquals("", p.getPropertyName());
        assertEquals("", p.getCity());
        assertEquals(0.0, p.getRentAmount(), 0.001);
        assertEquals("", p.getOwner());
    }

    /** Parameterized constructor sets every field. */
    @Test
    public void testParameterizedConstructor() {
        assertEquals("Maple House", house.getPropertyName());
        assertEquals("Owings Mills", house.getCity());
        assertEquals(1850.50, house.getRentAmount(), 0.001);
        assertEquals("Star", house.getOwner());
    }

    /** Copy has the same info but is a different object. */
    @Test
    public void testCopyConstructor() {
        Property copy = new Property(house);
        assertNotSame(house, copy);
        assertEquals(house.toString(), copy.toString());

        // changing the copy shouldn't change the original
        copy.setRentAmount(999.0);
        assertEquals(1850.50, house.getRentAmount(), 0.001);
    }

    /** Setters change the fields. */
    @Test
    public void testSetters() {
        house.setPropertyName("Oak Cottage");
        house.setCity("Towson");
        house.setRentAmount(2100.0);
        house.setOwner("Kim");
        assertEquals("Oak Cottage, Towson, Rent: $2100.00, Owner: Kim", house.toString());
    }

    /** toString matches the format from the assignment. */
    @Test
    public void testToString() {
        assertEquals("Maple House, Owings Mills, Rent: $1850.50, Owner: Star", house.toString());
    }
}
