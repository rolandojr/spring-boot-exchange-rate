package com.example.mibanco.exchangerate.models.entity;


import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;


@Entity
@Table(name = "EXCHANGE_RATE")
@Getter
@Setter
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "from_currency")
    private String fromCurrency;
    @Column(name = "to_currency")
    private String toCurrency;
    @Column(name = "exchange_rate")
    private String exchangeRate;
}
