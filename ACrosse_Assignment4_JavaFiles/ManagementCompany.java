/*
 * Class: CMSC203 Computer Science I
 * Instructor: Prof. Thai
 * Description: ManagementCompany has an array of Property objects
 *     (aggregation). It can add properties, count them and total the rent.
 * Due: 10/30/2026
 * Platform/compiler: Eclipse, Java (JDK 25)
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Akene Crosse
 */

/**
 * A company that manages up to MAX_PROPERTIES properties.
 */
public class ManagementCompany {

    /** Most properties a company can hold. */
    public static final int MAX_PROPERTIES = 5;

    private String name;
    private String taxId;
    private Property[] properties;
    private int propertyCount;

    /**
     * Makes a company with no properties yet.
     *
     * @param name  company name
     * @param taxId company tax ID
     */
    public ManagementCompany(String name, String taxId) {
        this.name = name;
        this.taxId = taxId;
        properties = new Property[MAX_PROPERTIES];
        propertyCount = 0;
    }

    /**
     * Copy constructor (deep copy). The new company gets its own copy of
     * every property, so changing one company doesn't change the other.
     *
     * @param other the company to copy
     */
    public ManagementCompany(ManagementCompany other) {
        name = other.name;
        taxId = other.taxId;
        properties = new Property[MAX_PROPERTIES];
        propertyCount = other.propertyCount;
        for (int i = 0; i < propertyCount; i++) {
            properties[i] = new Property(other.properties[i]);
        }
    }

    /**
     * Adds a copy of p to the next open spot in the array.
     *
     * @param p the property to add
     * @return the index it was added at, or -1 if the company is full
     */
    public int addProperty(Property p) {
        if (propertyCount >= MAX_PROPERTIES) {
            return -1;
        }
        properties[propertyCount] = new Property(p); // store a copy, not the original
        propertyCount++;
        return propertyCount - 1;
    }

    /**
     * Adds up the rent of every property.
     *
     * @return the total monthly rent
     */
    public double totalRent() {
        double total = 0;
        for (int i = 0; i < propertyCount; i++) {
            total += properties[i].getRentAmount();
        }
        return total;
    }

    /** @return how many properties have been added */
    public int getPropertyCount() {
        return propertyCount;
    }

    /** @return the company name */
    public String getName() {
        return name;
    }

    /** @return the tax ID */
    public String getTaxId() {
        return taxId;
    }

    /**
     * Gets a copy of the property at an index (a copy so the array
     * can't be changed from outside).
     *
     * @param index which property
     * @return a copy of it, or null if the index is not valid
     */
    public Property getProperty(int index) {
        if (index < 0 || index >= propertyCount) {
            return null;
        }
        return new Property(properties[index]);
    }

    /**
     * Shows the company, each property with its index, and the total rent.
     *
     * @return the company summary
     */
    @Override
    public String toString() {
        String result = "Management Company: " + name + " (Tax ID: " + taxId + ")\n";
        result += "Properties:\n";
        if (propertyCount == 0) {
            result += "  (none yet)\n";
        }
        for (int i = 0; i < propertyCount; i++) {
            result += "  " + i + ": " + properties[i] + "\n";
        }
        result += String.format("Total Rent: $%.2f", totalRent());
        return result;
    }
}
