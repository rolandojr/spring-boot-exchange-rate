package com.example.mibanco.exchangerate.repository;

import com.example.mibanco.exchangerate.models.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);

}
