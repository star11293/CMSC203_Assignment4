/*
 * Class: CMSC203 Computer Science I
 * Instructor: Prof. Thai
 * Description: JUnit 5 tests for the ManagementCompany class.
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
 * Tests adding properties, total rent, the full-array case and the copy constructor.
 */
public class ManagementCompanyJUnitTestStudent {

    private ManagementCompany company;

    @BeforeEach
    public void setUp() {
        company = new ManagementCompany("Baltimore Homes", "987-65-4321");
        company.addProperty(new Property("Unit A", "Baltimore", 1100.0, "Lee"));
        company.addProperty(new Property("Unit B", "Towson", 1350.25, "Ana"));
    }

    @AfterEach
    public void tearDown() {
        company = null;
    }

    /** addProperty returns the next index and the count goes up. */
    @Test
    public void testAddProperty() {
        assertEquals(2, company.getPropertyCount());
        int index = company.addProperty(new Property("Unit C", "Columbia", 900.0, "Sam"));
        assertEquals(2, index);
        assertEquals(3, company.getPropertyCount());
        assertEquals("Unit C", company.getProperty(2).getPropertyName());
    }

    /** totalRent adds up every property's rent. */
    @Test
    public void testTotalRent() {
        assertEquals(2450.25, company.totalRent(), 0.001);
        company.addProperty(new Property("Unit C", "Columbia", 900.0, "Sam"));
        assertEquals(3350.25, company.totalRent(), 0.001);
        assertEquals(0.0, new ManagementCompany("Empty Co", "000").totalRent(), 0.001);
    }

    /** After MAX_PROPERTIES, addProperty returns -1 and nothing is added. */
    @Test
    public void testAddPropertyWhenFull() {
        for (int i = company.getPropertyCount(); i < ManagementCompany.MAX_PROPERTIES; i++) {
            assertEquals(i, company.addProperty(new Property("Unit " + i, "City", 1000.0, "Owner")));
        }
        assertEquals(-1, company.addProperty(new Property("Too Many", "City", 500.0, "Owner")));
        assertEquals(ManagementCompany.MAX_PROPERTIES, company.getPropertyCount());
    }

    /** Copy constructor makes its own copies of the properties (deep copy). */
    @Test
    public void testCopyConstructor() {
        ManagementCompany copy = new ManagementCompany(company);
        assertEquals(company.getName(), copy.getName());
        assertEquals(company.getTaxId(), copy.getTaxId());
        assertEquals(company.getPropertyCount(), copy.getPropertyCount());
        assertEquals(company.totalRent(), copy.totalRent(), 0.001);

        // adding to the copy doesn't change the original
        copy.addProperty(new Property("Unit Z", "City", 500.0, "Owner"));
        assertEquals(2, company.getPropertyCount());
    }

    /** addProperty stores a copy, so changing the original object later doesn't change the company. */
    @Test
    public void testAddStoresCopy() {
        Property p = new Property("Unit C", "Columbia", 900.0, "Sam");
        company.addProperty(p);
        p.setRentAmount(5000.0);
        assertEquals(3350.25, company.totalRent(), 0.001);
    }

    /** toString lists the company, each property and the total. */
    @Test
    public void testToString() {
        String expected = "Management Company: Baltimore Homes (Tax ID: 987-65-4321)\n"
                + "Properties:\n"
                + "  0: Unit A, Baltimore, Rent: $1100.00, Owner: Lee\n"
                + "  1: Unit B, Towson, Rent: $1350.25, Owner: Ana\n"
                + "Total Rent: $2450.25";
        assertEquals(expected, company.toString());
    }
}
