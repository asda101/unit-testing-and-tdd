package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test suite for Client class")
public class ClientTest {
    private Client client;

    @BeforeEach
    public void setUp() {
        client = new Client(1, "John Doe");
    }

    @Test //@Disabled("temporary disabled")
    @DisplayName("Test case 1 - testGetId()")
    public void testGetId() {
        assertEquals(1, client.getId());
    }

    @Test
    @DisplayName("Test case 2 - testGetName()")
    public void testGetName() {
        assertEquals("John Doe", client.getName());
    }
    @Test
    @DisplayName("Test case 3 - testConstructor_NegativeId_Throw()")
    public void testConstructor_NegativeId_Throw() {
        assertThrows(IllegalArgumentException.class, () -> new Client(-1, "Invalid Name"));
    }

    @Test
    @DisplayName("Test case 4 - testConstructor_wNullName_Throw()")
    public void testConstructor_wNullName_Throw() {
        assertThrows(IllegalArgumentException.class, () -> new Client(1, null));
    }

    @Test
    @DisplayName("Test case 5 - testConstructor_wEmptyName_Throw()")
    public void testConstructor_wEmptyName_Throw() {
        assertThrows(IllegalArgumentException.class, () -> new Client(1, ""));
    }
}



