package com.example.mibanco.exchangerate.models.thirdparty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExchangeRateResponse {

    private String from;
    private String to;
    private String exchangeRate;
}
