/*
 * Class: CMSC203 Computer Science I
 * Instructor: Prof. Thai
 * Description: Property stores one rental property's name, city,
 *     monthly rent and owner.
 * Due: 10/30/2026
 * Platform/compiler: Eclipse, Java (JDK 25)
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Akene Crosse
 */

/**
 * One rental property.
 */
public class Property {

    private String propertyName;
    private String city;
    private double rentAmount;
    private String owner;

    /**
     * Default constructor. Empty strings and 0 rent.
     */
    public Property() {
        propertyName = "";
        city = "";
        rentAmount = 0.0;
        owner = "";
    }

    /**
     * Makes a property with all of its info.
     *
     * @param propertyName name of the property
     * @param city         city it's in
     * @param rentAmount   monthly rent
     * @param owner        owner's name
     */
    public Property(String propertyName, String city, double rentAmount, String owner) {
        this.propertyName = propertyName;
        this.city = city;
        this.rentAmount = rentAmount;
        this.owner = owner;
    }

    /**
     * Copy constructor. Makes a new property with the same info as p.
     *
     * @param p the property to copy
     */
    public Property(Property p) {
        propertyName = p.propertyName;
        city = p.city;
        rentAmount = p.rentAmount;
        owner = p.owner;
    }

    /** @return the property name */
    public String getPropertyName() {
        return propertyName;
    }

    /** @return the city */
    public String getCity() {
        return city;
    }

    /** @return the monthly rent */
    public double getRentAmount() {
        return rentAmount;
    }

    /** @return the owner's name */
    public String getOwner() {
        return owner;
    }

    /** @param propertyName the new property name */
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    /** @param city the new city */
    public void setCity(String city) {
        this.city = city;
    }

    /** @param rentAmount the new monthly rent */
    public void setRentAmount(double rentAmount) {
        this.rentAmount = rentAmount;
    }

    /** @param owner the new owner's name */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Shows the property on one line, like
     * "Sunset Villa, Rockville, Rent: $1500.00, Owner: Alice Smith".
     *
     * @return the property's info
     */
    @Override
    public String toString() {
        return String.format("%s, %s, Rent: $%.2f, Owner: %s", propertyName, city, rentAmount, owner);
    }
}
