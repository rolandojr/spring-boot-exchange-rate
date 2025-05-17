package com.example.mibanco.exchangerate.services;

import com.example.mibanco.exchangerate.builder.ResponseBuilder;
import com.example.mibanco.exchangerate.models.entity.ExchangeRate;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateConvertResponse;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateRequest;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateResponse;
import com.example.mibanco.exchangerate.repository.ExchangeRateRepository;
import com.example.mibanco.exchangerate.utils.Utils;
import io.reactivex.Flowable;
import io.reactivex.Single;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    ExchangeRateRepository repository;

    ResponseBuilder responseBuilder;


    @Override
    public Mono<List<ExchangeRateResponse>> findAllExchanges() {
        return Flux.fromIterable(repository.findAll())
                .map(responseBuilder::buildExchangeRateResponse)
                .collectList();

    }

    @Override
    public Mono<ExchangeRateConvertResponse> convertExchange(ExchangeRateRequest exchangeRateRequest) {
        return Mono.fromCallable(() -> repository.findByFromCurrencyAndToCurrency(
                        exchangeRateRequest.getFrom(), exchangeRateRequest.getTo()))
                .map(exchangeRate -> getExchangeRateConvertResponse(exchangeRateRequest, exchangeRate));

    }

    private ExchangeRateConvertResponse getExchangeRateConvertResponse(ExchangeRateRequest exchangeRateRequest, ExchangeRate exchangeRate) {
        String result = Utils.exchageResult(exchangeRate.getExchangeRate(), exchangeRateRequest.getAmount());
        return responseBuilder.buildExchangeRateConvertResponse(exchangeRate, exchangeRateRequest, result);
    }
}
