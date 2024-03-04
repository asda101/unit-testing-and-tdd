package com.acme.banking.dbo;


import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.service.CashApi;
import com.acme.banking.dbo.service.ClientRepository;
import com.acme.banking.dbo.service.Processing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ProcessingTest {

    @Test
    @DisplayName("Test case 1 - shouldCreateNewClient()")
    void shouldCreateNewClient() {
        // оборачиваем класс ClientRepository в мок объект
        var mockRepository = mock(ClientRepository.class);
        var id = 2;
        // когда придет generateId() отдай id = 2
        when(mockRepository.generateId()).thenReturn(id);

        // создаем клиента с id=4 и именем Иван
        var expectedResultClient = new Client(4, "Иван");
        // когда попросят выполнить save() верни клиента с id=4 и именем Иван
        when(mockRepository.save(any())).thenReturn(expectedResultClient);

        var sut = new Processing(mockRepository, Cash::log);
        var name = "Саша";

        // ожидаем клиента с id=2 и именем Саша
        var expectedSavedClient = new Client(id, name);

        // создаем через Processing клиента с именем Саша
        var result = sut.createClient(name);
        // проверяем что в save передается клиент с именем Саша
        verify(mockRepository).save(expectedSavedClient);
        // проверяем, что в реузльтате клиент с id=2 и именем Саша
        assertEquals(expectedResultClient, result);
    }

    @Test
    @DisplayName("Test case 2 - shouldNotCreateNewClientWhenNameIsIncorrect()")
    void shouldNotCreateNewClientWhenNameIsIncorrect() {
        var mockRepository = mock(ClientRepository.class);
        var id = 2;
        when(mockRepository.generateId()).thenReturn(id);
        var sut = new Processing(mockRepository, Cash::log);
        String name = null;

        assertThrows(IllegalArgumentException.class, () -> sut.createClient(name));

        verify(mockRepository, times(0)).save(any());
    }

    @Test
    void getAccountsByClientId() {
    }

    @Test
    void transfer() {
    }

    @Test
    @DisplayName("Test case 3 - cash()")
    void cash() {
        var dummy = mock(ClientRepository.class);
        var mockLog = mock(CashApi.class);
        var sut = new Processing(dummy, mockLog);
        var amount = 1;
        var fromAccountId = 1;

        sut.cash(amount, fromAccountId);

        verify(mockLog).log(amount, fromAccountId);
    }

}
