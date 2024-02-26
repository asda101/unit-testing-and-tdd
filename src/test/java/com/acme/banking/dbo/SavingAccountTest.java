package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test suite for SavingAccount class")
public class SavingAccountTest {
    private Client client;
    @BeforeEach
    public void setUp() {
        client = new Client(1, "John Doe");
    }

    @Test //@Disabled("temporary disabled")
    @DisplayName("Test case 1 - testConstructor_wValidArgs()")
    public void testConstructor_wValidArgs() {
        SavingAccount account = new SavingAccount(123, client, 1000.0);
        assertEquals(123, account.getId());
        assertEquals(client, account.getClient());
        assertEquals(1000.0, account.getAmount(), 0.001);  // 0.001 - погрешность
    }

    @Test
    @DisplayName("Test case 2 - testConstructor_wInvalidArgs()")
    public void testConstructor_wInvalidId() {
      assertThrows(IllegalArgumentException.class, () -> {
          new SavingAccount(-1, client, 1000.0);
    });
    }
    @Test
    @DisplayName("Test case 3 - testConstructor_wNullClient()")
    public void testConstructor_wNullClient() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(123, null, 1000.0);
        });
    }

    @Test
    @DisplayName("Test case 4 - testConstructor_wNegativeAmount()")
    public void testConstructor_wNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> {
            new SavingAccount(123, client, -1000.0);
        });
    }
}
