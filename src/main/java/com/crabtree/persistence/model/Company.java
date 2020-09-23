package com.crabtree.persistence.model;

import javax.persistence.*;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable=false, unique=true)
    private String name;

    @Column(nullable=false)
    private String ticker;

    @Column(nullable=false)
    private int numEmployees;

    public Company() {
        super();
    }

    public Company(String ticker) {
        super();
        this.ticker = ticker;
        // use finance api to get info
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTicker() {
        return this.ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getNumEmployees() {
        return numEmployees;
    }

    public void setNumEmployees(int numEmployees) {
        this.numEmployees = numEmployees;
    }
}
