package com.ccsw.tutorial.loan;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ccsw.tutorial.loan.model.Loan;

public interface LoanRepository extends CrudRepository<Loan, Long> {

	/**
	 * Método para recuperar un listado paginado de {@link Loan}
	 *
	 * @param pageable pageable
	 * @return {@link Page} de {@link Loan}
	 */
	@EntityGraph(attributePaths = { "game", "client" })
	@Query("select l from Loan l where (:game is null or l.game.id = :game) and (:client is null or l.client.id = :client) "
			+ "and (:date is null or (l.startDate <= :date and :date <= l.endDate))")
	public Page<Loan> findAll(@Param("game") Long game, @Param("client") Long client, @Param("date") Date date,
			Pageable pageable);

	@EntityGraph(attributePaths = { "game" })
	@Query("select l from Loan l where l.game.id = :game")
	public List<Loan> findByGame(@Param("game") Long game);

	@EntityGraph(attributePaths = { "client" })
	@Query("select l from Loan l where l.client.id = :client")
	public List<Loan> findByClient(@Param("client") Long client);
}
