package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;


@DisplayName("Test suite")
public class ClientTest {
    private Client client;
    @BeforeEach
    public void setUp() {
        client = new Client(1, "John Doe");
//        mockAccounts = new ArrayList<>();
//        mockAccounts.add(mock(Account.class));
//        mockAccounts.add(mock(Account.class));
//        client.setAccounts(mockAccounts);
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
    @DisplayName("Test case 3 - checkConstructor_NegativeId_Throw()")
    public void checkConstructor_NegativeId_Throw() {
        assertThrows(IllegalArgumentException.class, () -> new Client(-1, "Invalid Name"));
    }

    @Test
    @DisplayName("Test case 4 - checkConstructor_wNullName_Throw()")
    public void checkConstructor_wNullName_Throw() {
        assertThrows(IllegalArgumentException.class, () -> new Client(1, null));
    }

    @Test
    @DisplayName("Test case 5 - checkConstructor_wEmptyName_Throw()")
    public void checkConstructor_wEmptyName_Throw() {
        assertThrows(IllegalArgumentException.class, () -> new Client(1, ""));
    }
}


