package com.example.mibanco.exchangerate.services;

import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateConvertResponse;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateRequest;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateResponse;
import io.reactivex.Single;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ExchangeRateService {

    Mono<List<ExchangeRateResponse>> findAllExchanges();

    Mono<ExchangeRateConvertResponse> convertExchange(ExchangeRateRequest exchangeRateRequest);
}
