package com.example.mibanco.exchangerate.services;

import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateConvertResponse;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateRequest;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateResponse;
import io.reactivex.Single;

import java.util.List;

public interface ExchangeRateService {

    Single<List<ExchangeRateResponse>> findAllExchanges();

    Single<ExchangeRateConvertResponse> convertExchange(ExchangeRateRequest exchangeRateRequest);
}
