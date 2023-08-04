package lapr.project.model;

/**
 *Represents a Employee in organization
 * @author Rita Ariana Sobral <1201386@isep.ipp.pt>
 */
public class Employee {

    /**
     *The name of an employee in the organization
     */
    private final String name;

    /**
     *The organization role of an employee in the organization
     */
    private final OrganizationRole organizationRole;

    /**
     * Constructs an instance of {@code Employee} receiving the name and organization role
     * @param name name of Employee
     * @param organizationRole organization Role of Employee
     */
    public Employee(String name,String organizationRole){
        this.name = name;
        this.organizationRole = new OrganizationRole(organizationRole);
    }

    /**
     * Get the name of an employee
     * @return the name of Employee
     */
    public String getName() {
        return name;
    }

    /**
     * Get the employee role of an employee
     * @return the employee role of Employee
     */
    public OrganizationRole getEmployeeRole() {
        return organizationRole;
    }
}
