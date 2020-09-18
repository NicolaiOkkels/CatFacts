package com.example.demo.controllers;

import com.example.demo.model.CatFact;
import com.example.demo.service.CatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//Note: Kunne evt godt gøre denne klasse static, da den ikke gemmer nogle attributter på klassen.

@Controller
public class MyController {
    private CatService fact = new CatService();

    @GetMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to cat facts";
    }

    @GetMapping("/getSingle")
    @ResponseBody
    public String randomFact() throws IOException {
        return fact.getCatFactData().toString();
    }

    @GetMapping("/getTen")
    @ResponseBody
    public String tenFacts() throws IOException {
        ArrayList<CatFact> catFacts = fact.getTenCatFactsArrayList();
        StringBuilder builder = new StringBuilder();

        for (CatFact catFact : catFacts) {
            builder.append(catFact + " ");
        }

       return builder.toString();
    }

    @GetMapping("/getTenSortByDate")
    @ResponseBody
    public String tenSortedFacts() throws IOException {
        ArrayList<CatFact> catFacts = fact.getSortedCatFactsArrayList();
        StringBuilder builder = new StringBuilder();

        for (CatFact catFact : catFacts) {
            builder.append(catFact + " ");
        }

        return builder.toString();
    }

    @GetMapping("/contain")
    @ResponseBody
    public String containChar(char enterChar, int amount) throws IOException {
        int charCounter = 0;
        char[] charArray = fact.getCatFactData().toString().toCharArray();

        for (char ch : charArray) {
            if (ch == enterChar) {
                charCounter++;
            }
        }

        if (charCounter > amount) {
            return "Sorry no luck!";
        } else {
            return fact.getCatFactData().toString();
        }
    }
}
