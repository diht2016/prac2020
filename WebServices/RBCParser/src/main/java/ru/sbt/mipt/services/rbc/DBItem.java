package ru.sbt.mipt.services.rbc;

import javax.persistence.*;

@Entity
@Table(name = "rbc")
public class DBItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private Double currency;

    public DBItem() {
        super();
    }

    public DBItem(String date, Double currency){
        super();
        this.currency = currency;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public Double getCurrency() {
        return currency;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCurrency(Double currency) {
        this.currency = currency;
    }

}