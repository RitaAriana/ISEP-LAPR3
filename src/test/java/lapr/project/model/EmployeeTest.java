package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void getName() {
        Employee emp = new Employee("Joao", "Traffic Manager");
        assertEquals("Joao", emp.getName());
    }

    @Test
    void getEmployeeRole() {
        OrganizationRole role = new OrganizationRole("Traffic Manager");
        Employee emp = new Employee("Joao", "Traffic Manager");
        boolean result = role.equals(emp.getEmployeeRole());
        assertTrue(result);
    }
}