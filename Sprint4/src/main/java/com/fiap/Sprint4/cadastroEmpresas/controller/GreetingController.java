package com.fiap.Sprint4.cadastroEmpresas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class GreetingController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/greeting")
    public String greeting(@RequestHeader(name = "Accept-Language", required = false) String language) {
        Locale locale = Locale.getDefault();

        if (language != null && !language.isEmpty()) {
            // Extrai o primeiro valor do cabeçalho Accept-Language
            String[] locales = language.split(",");
            locale = Locale.forLanguageTag(locales[0]);
        }

        return messageSource.getMessage("greeting", null, locale);
    }
}
