package org.spring.authenticationservice.controller;

import org.spring.authenticationservice.DTO.Currency.ReloadDto;
import org.spring.authenticationservice.Service.CurrencyService.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/reload")
    public ResponseEntity<String> reload(@RequestBody ReloadDto reloadDto){
        try{
            String CurrentBalance =  currencyService.ReloadUser(reloadDto);
            return ResponseEntity.status(200).body(CurrentBalance);
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }

    }

    @GetMapping("/currentBalance")
    public ResponseEntity<String> getCurrentBalance(){
        try{
            String CurrentBalance =  currencyService.GetBalance();
            return ResponseEntity.status(200).body(CurrentBalance.toString());
        }
        catch (Exception e){
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
