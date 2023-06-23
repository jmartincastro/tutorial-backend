package com.ccsw.tutorial.loan;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.ccsw.tutorial.client.ClientService;
import com.ccsw.tutorial.game.GameService;
import com.ccsw.tutorial.loan.model.Loan;
import com.ccsw.tutorial.loan.model.LoanDto;
import com.ccsw.tutorial.loan.model.LoanSearchDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LoanServiceImpl implements LoanService {

	private static final Logger log = LoggerFactory.getLogger(LoanServiceImpl.class);

	@Autowired
	LoanRepository loanRepository;

	@Autowired
	GameService gameService;

	@Autowired
	ClientService clientService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Loan get(Long id) {
		return this.loanRepository.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<Loan> findPage(Long idCliente, Long idGame, Date date, LoanSearchDto dto) {

		return this.loanRepository.findAll(idCliente, idGame, date, dto.getPageable().getPageable());
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @throws Exception
	 */
	@Override
	public void save(Long id, LoanDto dto) {

		Loan loan;

		if (id == null) {
			loan = new Loan();
		} else {
			loan = this.get(id);
		}

		BeanUtils.copyProperties(dto, loan, "id", "client", "game", "startDate", "endDate");

		loan.setClient(clientService.get(dto.getClient().getId()));
		loan.setGame(gameService.get(dto.getGame().getId()));
		loan.setStartDate(dto.getStartDate());
		loan.setEndDate(dto.getEndDate());

		this.loanRepository.save(loan);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(Long id) throws Exception {

		if (this.get(id) == null) {
			throw new Exception("Not exists");
		}

		this.loanRepository.deleteById(id);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Loan> findByGame(Long idGame) {
		return this.loanRepository.findByGame(idGame);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Loan> findByClient(Long idClient) {
		return this.loanRepository.findByClient(idClient);
	}
}
