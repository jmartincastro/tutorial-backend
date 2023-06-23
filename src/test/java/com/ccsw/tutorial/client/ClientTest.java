package com.ccsw.tutorial.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

@ExtendWith(MockitoExtension.class)
public class ClientTest {

    public static final String CATEGORY_NAME = "Pepe";
    public static final Long EXISTS_CATEGORY_ID = 1L;
    public static final Long NOT_EXISTS_CATEGORY_ID = 0L;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    @Test
    public void findAllShouldReturnAllCategories() {

        List<Client> list = new ArrayList<>();
        list.add(mock(Client.class));

        when(clientRepository.findAll()).thenReturn(list);

        List<Client> categories = clientService.findAll();

        assertNotNull(categories);
        assertEquals(1, categories.size());
    }

    @Test
    public void saveNotExistsClientIdShouldInsert() {

        ClientDto clientDto = new ClientDto();
        clientDto.setName(CATEGORY_NAME);

        ArgumentCaptor<Client> client = ArgumentCaptor.forClass(Client.class);

        clientService.save(null, clientDto);

        verify(clientRepository).save(client.capture());

        assertEquals(CATEGORY_NAME, client.getValue().getName());
    }

    @Test
    public void saveExistsClientIdShouldUpdate() {

        ClientDto clientDto = new ClientDto();
        clientDto.setName(CATEGORY_NAME);

        Client client = mock(Client.class);
        when(clientRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(client));

        clientService.save(EXISTS_CATEGORY_ID, clientDto);

        verify(clientRepository).save(client);
    }

    @Test
    public void deleteExistsClientIdShouldDelete() throws Exception {

        Client client = mock(Client.class);
        when(clientRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(client));

        clientService.delete(EXISTS_CATEGORY_ID);

        verify(clientRepository).deleteById(EXISTS_CATEGORY_ID);
    }

    @Test
    public void getExistsClientIdShouldReturnClient() {

        Client client = mock(Client.class);
        when(client.getId()).thenReturn(EXISTS_CATEGORY_ID);
        when(clientRepository.findById(EXISTS_CATEGORY_ID)).thenReturn(Optional.of(client));

        Client clientResponse = clientService.get(EXISTS_CATEGORY_ID);

        assertNotNull(clientResponse);
        assertEquals(EXISTS_CATEGORY_ID, client.getId());
    }

    @Test
    public void getNotExistsClientIdShouldReturnNull() {

          when(clientRepository.findById(NOT_EXISTS_CATEGORY_ID)).thenReturn(Optional.empty());

          Client client = clientService.get(NOT_EXISTS_CATEGORY_ID);

          assertNull(client);
    }

}