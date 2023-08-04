package lapr.project.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrganizationRoleTest {

    @Test
    void getDesignation() {
        OrganizationRole organizationRole = new OrganizationRole("Traffic Manager");
        assertEquals("Traffic Manager", organizationRole.getDesignation());
    }



    @Test
    void testEqualsObject(){
        OrganizationRole orgRole = new OrganizationRole("Traffic Manager");
        OrganizationRole orgRole2 = orgRole;

        assertTrue(orgRole.equals(orgRole2));
    }

    @Test
    void testEqualsNull(){
        OrganizationRole orgRole = new OrganizationRole("Traffic Manager");
        OrganizationRole orgRole2 = null;

        assertFalse(orgRole.equals(orgRole2));
    }

    @Test
    void testEqualsClass(){
        OrganizationRole orgRole = new OrganizationRole("Traffic Manager");
        String testString = "abc";

        assertFalse(orgRole.equals(testString));
    }

    @Test
    void testEqualsCorrect() {
        OrganizationRole organizationRole = new OrganizationRole("Traffic Manager");
        OrganizationRole organizationRole2 = new OrganizationRole("Traffic Manager");
        assertEquals(organizationRole, organizationRole2);
    }
}