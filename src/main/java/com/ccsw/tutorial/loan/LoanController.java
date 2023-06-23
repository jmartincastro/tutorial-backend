package com.ccsw.tutorial.loan;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 
 * @author jmartinc
 *
 */

@Tag(name = "Loan", description = "API of Loan")
@RequestMapping(value = "/loan")
@RestController
@CrossOrigin(origins = "*")
public class LoanController {

	private static final Logger log = LoggerFactory.getLogger(LoanController.class);

	@Autowired
	LoanService loanService;

	@Autowired
	DozerBeanMapper mapper;

	/**
	 * Método para recuperar un listado paginado de {@link Loan}
	 *
	 * @param dto dto de búsqueda
	 * @return {@link Page} de {@link LoanDto}
	 */
	@Operation(summary = "Find Page", description = "Method that return a page of Loans")
	@RequestMapping(path = "", method = RequestMethod.POST)
	public Page<LoanDto> findAll(@RequestParam(value = "idClient", required = false) Long idCliente,
			@RequestParam(value = "idGame", required = false) Long idGame,
			@RequestParam(value = "date", required = false) String date, @RequestBody LoanSearchDto dto) {

		Date d = null;
		if (date != null && !date.isEmpty()) {
			try {
				d = new SimpleDateFormat("yyyy-MM-dd").parse(date.replace("'", ""));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		Page<Loan> page = this.loanService.findPage(idCliente, idGame, d, dto);

		return new PageImpl<>(
				page.getContent().stream().map(e -> mapper.map(e, LoanDto.class)).collect(Collectors.toList()),
				page.getPageable(), page.getTotalElements());
	}

	/**
	 * Método para crear o actualizar un {@link Loan}
	 *
	 * @param id  PK de la entidad
	 * @param dto datos de la entidad
	 */
	@Operation(summary = "Save or Update", description = "Method that saves or updates a Loan")
	@RequestMapping(path = { "", "/{id}" }, method = RequestMethod.PUT)
	public ResponseEntity<?> save(@PathVariable(name = "id", required = false) Long id, @RequestBody LoanDto dto) {

		Map<String, Object> response = new HashMap<>();

		Boolean validLoanGame = validateLoanGame(dto.getGame().getId(), dto.getStartDate());

		if (!validLoanGame) {

			String msg = "Error: El juego no puede tener más de un préstamo para el periodo establecido";
			response.put("mensaje", msg);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		Boolean validLoanClient = validateLoanClient(dto.getClient().getId(), dto.getStartDate());

		if (!validLoanClient) {
			String msg = "Error: El cliente no puede tener más de un préstamo para el periodo establecido";
			response.put("mensaje", msg);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		this.loanService.save(id, dto);

		response.put("mensaje", "El préstamo se ha creado/actualizado con éxito!");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	/**
	 * Método para crear o actualizar un {@link Loan}
	 *
	 * @param id PK de la entidad
	 */
	@Operation(summary = "Delete", description = "Method that deletes a Loan")
	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) throws Exception {

		this.loanService.delete(id);
	}

	private Boolean validateLoanGame(Long idGame, Date startDate) {

		// obtener los préstamos realizados por juego
		List<Loan> loansByGame = this.loanService.findByGame(idGame);

		for (Loan loan : loansByGame) {

			Boolean isBetweenDates = compareBetweenDate(loan, startDate);

			if (isBetweenDates)
				return false;
		}

		return true;
	}

	private Boolean validateLoanClient(Long idClient, Date startDate) {

		// obtener los préstamos realizados por cliente
		List<Loan> loansByClient = this.loanService.findByClient(idClient);

		for (Loan loan : loansByClient) {

			Boolean isBetweenDates = compareBetweenDate(loan, startDate);

			if (isBetweenDates)
				return false;
		}

		return true;
	}

	private Boolean compareBetweenDate(Loan loan, Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String strStartDate = sdf.format(loan.getStartDate());
		String strEndDate = sdf.format(loan.getEndDate());

		Date startDate = null;
		Date endDate = null;

		try {
			startDate = sdf.parse(strStartDate);
			endDate = sdf.parse(strEndDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		log.info("Fecha inicio préstamo " + startDate.toString());
		log.info("Fecha fin préstamo " + endDate.toString());
		log.info("Fecha comiento nuevo préstamo " + date.toString());
		log.info(
				String.format("1ra comprobación - %s < %s ? %s", startDate, date, loan.getStartDate().compareTo(date)));
		log.info(String.format("2da comprobación - %s < %s ? %s", date, endDate, date.compareTo(endDate)));

		return startDate.compareTo(date) * date.compareTo(endDate) >= 0;
	}

}
