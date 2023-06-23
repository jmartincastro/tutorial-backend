package com.ccsw.tutorial.loan.model;

import java.util.Date;

import com.ccsw.tutorial.client.model.ClientDto;
import com.ccsw.tutorial.game.model.GameDto;

/**
 * @author jmartinc
 *
 */
public class LoanDto {

    private Long id;

    private Date startDate;

    private Date endDate;

    private String date;

    private ClientDto client;

    private GameDto game;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id new value of {@link #getId()}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 
     * @param startDate new value of {@link #getStartDate()}
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 
     * @return endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 
     * @param endDate new value of {@link #getEndDate()}
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 
     * @return clientDto
     */
    public ClientDto getClient() {
        return client;
    }

    /**
     * 
     * @param clientDto new value of {@link #getClientDto()}
     */
    public void setClient(ClientDto clientDto) {
        this.client = clientDto;
    }

    /**
     * 
     * @return gameDto
     */
    public GameDto getGame() {
        return game;
    }

    /**
     * 
     * @param gameDto new value of {@link #getGameDto()}
     */
    public void setGame(GameDto gameDto) {
        this.game = gameDto;
    }

    /**
     * 
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * 
     * @param gameDto new value of {@link #getDate()}
     */
    public void setDate(String date) {
        this.date = date;
    }

}
