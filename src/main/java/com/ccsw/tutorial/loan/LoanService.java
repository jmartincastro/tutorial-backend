package com.ccsw.tutorial.loan;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

public interface LoanService {

    /**
     * Recupera un {@link Loan} a través de su ID
     *
     * @param id PK de la entidad
     * @return {@link Loan}
     */
    Loan get(Long id);

    /**
     * Método para recuperar un listado paginado de {@link Loan}
     *
     * @param dto dto de búsqueda
     * @return {@link Page} de {@link Loan}
     */
    Page<Loan> findPage(Long idCliente, Long idGame, Date date, LoanSearchDto dto);

    /**
     * Método para crear o actualizar un {@link Loan}
     *
     * @param id  PK de la entidad
     * @param dto datos de la entidad
     */
    void save(Long id, LoanDto dto);

    /**
     * Método para crear o actualizar un {@link Loan}
     *
     * @param id PK de la entidad
     */
    void delete(Long id) throws Exception;
    
    /**
     * Método que devuelve un listado de {@link Loan} por juego
     * @param idGame
     * @return {@link List} of {@link Loan}
     */
    List<Loan> findByGame(Long idGame);
    
    /**
     * Método que devuelve un listado de {@link Loan} por cliente
     * @param idClient
     * @return {@link List} of {@link Loan}
     */
    List<Loan> findByClient(Long idClient);

}
