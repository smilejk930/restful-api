package com.smilejk930.springboot.restfulapi.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.smilejk930.springboot.restfulapi.configuration.CurrencyServiceConfiguration;

@RestController
public class CurrencyConfigurationController {

    @Autowired
    private CurrencyServiceConfiguration configuration;

    @RequestMapping(value = "/currency-service", method = RequestMethod.GET)
    public CurrencyServiceConfiguration getCurrencyServiceConfiguration() {
        return configuration;
    }
}
