package com.example.mibanco.exchangerate.controller;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/exchange")
@AllArgsConstructor
public class ExchangeRateController {

    private ExchangeRateService service;

    @GetMapping
    public Maybe<ResponseEntity<List<ExchangeRateResponse>>> findAllExchangeRate() {
        return service.findAllExchanges()
                .toMaybe()
                .map(ResponseEntity::ok)
                .switchIfEmpty(Maybe.fromCallable(() -> ResponseEntity.noContent().build()))
                .subscribeOn(Schedulers.io());
    }

    @GetMapping("/convert")
    public Single<ResponseEntity<ExchangeRateConvertResponse>> convertExchange(@RequestParam String from, @RequestParam String to, @RequestParam String amount) {
        ExchangeRateRequest request = ExchangeRateRequest.builder()
                .from(from)
                .to(to)
                .amount(amount).build();

        return service.convertExchange(request)
                .map(ResponseEntity::ok)
                .subscribeOn(Schedulers.io());
    }
}
