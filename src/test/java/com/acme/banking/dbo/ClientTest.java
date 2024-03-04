package com.acme.banking.dbo;

import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.domain.SavingAccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test suite for Client class")
public class ClientTest {
    private Client client;
    private SavingAccount validAccount;

    @BeforeEach
    public void setUp() {
        client = new Client(1, "John Doe");
        validAccount = new SavingAccount(1, client, 1);
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

    // Кратко 6: проверяем invalid варианты
    // 0. SetUp фикстура для всех методов - создаем клиента client
    // 1. Пытаемся добавить клиенту client account null
    // 2. Создаем еще клиента otherClient и счет для него otherAccount и пытаемся добавить этот счет клиенту client
    // 3. Пытаемся 3 раза добавить клиенту cliet левый счет
    @Test
    @DisplayName("Test case 6 - test_when_AccountIsInvalid_Throw()")
    public void noAdd_SA_whenInvalid() {
        assertAll(
                "Adding invalid SaveAccount",
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> client.addAccount(null)
                ),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            Client otherClient = new Client(2, "Другой клиент");
                            SavingAccount otherAccount = new SavingAccount(1, otherClient, 1);
                            client.addAccount(otherAccount);
                        }
                ),
                () -> assertThrows(
                        IllegalArgumentException.class,
                        () -> {
                            client.addAccount(validAccount);
                            client.addAccount(validAccount);
                        }
                )
        );
    }

    // Кратко 7: проверяем invalid варианты
    // 0. SetUp фикстура для всех методов - создаем клиента client
    // 1. Пытаемся добавить клиенту client валидный аккаунт
    @Test
    @DisplayName("Test case 7 - test_try_add_SavingAccount_when_AccountIsValid_Throw()")
    public void Add_SA_whenValid() {
        assertDoesNotThrow(() -> client.addAccount(validAccount));
    }

}



