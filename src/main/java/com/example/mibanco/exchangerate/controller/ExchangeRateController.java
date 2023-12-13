package com.example.mibanco.exchangerate.controller;

import com.example.mibanco.exchangerate.builder.ResponseBuilder;
import com.example.mibanco.exchangerate.services.ExchangeRateService;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateConvertResponse;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateRequest;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateResponse;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ExchangeRateController {

    ExchangeRateService service;

    @GetMapping("/exchange")
    public Maybe<ResponseEntity<List<ExchangeRateResponse>>> findAllExchangeRate() {
        return service.findAllExchanges()
                .toMaybe()
                .doOnSuccess(exchangeRateResponses -> log.info("Thread Name " + Thread.currentThread().getName()))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Maybe.fromCallable(() -> ResponseEntity.noContent().build()))
                .doFinally(() -> log.info("finnaly method from findAllExchangeRate"))
                .subscribeOn(Schedulers.io());
    }

    @GetMapping("/exchange/convert")
    public Single<ResponseEntity<ExchangeRateConvertResponse>> convertExchange(@RequestParam String from, @RequestParam String to, @RequestParam String amount) throws InterruptedException {
        ExchangeRateRequest request = ExchangeRateRequest.builder()
                .from(from)
                .to(to)
                .amount(amount).build();
        return service.convertExchange(request)
                .doOnSuccess(exchangeRateResponses -> log.info("Thread Name " + Thread.currentThread().getName()))
                .map(ResponseEntity::ok)
                .subscribeOn(Schedulers.io());
    }


}
