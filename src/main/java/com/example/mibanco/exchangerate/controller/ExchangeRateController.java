package com.example.mibanco.exchangerate.controller;

import com.example.mibanco.exchangerate.configuration.ApplicationProperties;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateConvertResponse;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateRequest;
import com.example.mibanco.exchangerate.models.thirdparty.ExchangeRateResponse;
import com.example.mibanco.exchangerate.services.ExchangeRateService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/exchange")
@AllArgsConstructor
public class ExchangeRateController {

    private ExchangeRateService service;

    private ApplicationProperties applicationProperties;

    @GetMapping("/properties")
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("component", applicationProperties.getComponent());
        properties.put("description", applicationProperties.getDescription());
        properties.put("authorName", applicationProperties.getAuthorName());
        properties.put("authorEmail", applicationProperties.getAuthorEmail());
        return properties;
    }

    @GetMapping
    public Maybe<ResponseEntity<List<ExchangeRateResponse>>> findAllExchangeRate() {
        return service.findAllExchanges()
                .toMaybe()
                .doOnSuccess(exchangeRateResponses -> log.info("Thread Name " + Thread.currentThread().getName()))
                .map(ResponseEntity::ok)
                .switchIfEmpty(Maybe.fromCallable(() -> ResponseEntity.noContent().build()))
                .doFinally(() -> log.info("finnaly method from findAllExchangeRate"))
                .subscribeOn(Schedulers.io());
    }

    @GetMapping("/convert")
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
