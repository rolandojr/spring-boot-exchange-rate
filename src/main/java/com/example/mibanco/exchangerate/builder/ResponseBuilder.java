package com.example.mibanco.exchangerate.builder;

import com.example.mibanco.exchangerate.models.entity.ExchangeRate;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateConvertResponse;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateRequest;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ResponseBuilder {

    public ExchangeRateResponse buildExchangeRateResponse(ExchangeRate exchangeRate) {
        return ExchangeRateResponse.builder()
                .from(exchangeRate.getFromCurrency())
                .to(exchangeRate.getToCurrency())
                .exchangeRate(exchangeRate.getExchangeRate())
                .build();
    }

    public ExchangeRateConvertResponse buildExchangeRateConvertResponse(ExchangeRate exchangeRate, ExchangeRateRequest exchangeRateRequest, String result ) {
        return ExchangeRateConvertResponse.builder()
                .from(exchangeRateRequest.getFrom())
                .to(exchangeRateRequest.getTo())
                .exchangeRate(exchangeRate.getExchangeRate())
                .amount(exchangeRateRequest.getAmount())
                .result(result)
                .build();
    }


}
