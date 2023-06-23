package com.ccsw.tutorial.loan.model;

import java.util.Date;

import com.ccsw.tutorial.client.model.Client;
import com.ccsw.tutorial.game.model.Game;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * 
 * @author jmartinc
 *
 */

@Entity
@Table(name = "loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Column(name = "end_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

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
    public Client getClient() {
        return client;
    }

    /**
     * 
     * @param clientDto new value of {@link #getClient()}
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * 
     * @return game
     */
    public Game getGame() {
        return game;
    }

    /**
     * 
     * @param gameDto new value of {@link #getGame()}
     */
    public void setGame(Game game) {
        this.game = game;
    }

}
