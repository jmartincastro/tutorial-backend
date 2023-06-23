package com.ccsw.tutorial.client;

import java.util.List;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.client.model.ClientDto;

/**
 * 
 * @author jmartinc
 *
 */
public interface ClientService {

    /**
     * Recupera una {@link Client} a partir de su ID
     *
     * @param id PK de la entidad
     * @return {@link Client}
     */
    Client get(Long id);

    /**
     * Método para recuperar todas las categorías
     *
     * @return {@link List} de {@link Client}
     */
    List<Client> findAll();

    /**
     * Método para crear o actualizar una categoría
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, ClientDto dto);

    /**
     * Método para borrar una categoría
     *
     * @param id PK de la entidad
     * @throws Exception
     */
    void delete(Long id) throws Exception;
}
