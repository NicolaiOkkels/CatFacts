package com.example.demo.controllers;

import com.example.demo.model.CatFact;
import com.example.demo.service.CatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

@Controller
public class MyController {

    @GetMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome to cat facts";
    }

    @GetMapping("/getSingle")
    @ResponseBody
    public String randomFact() throws IOException {
        CatService fact = new CatService();
        return fact.getCatFactString();
    }

    @GetMapping("/getTen")
    @ResponseBody
    public String tenFacts() throws IOException {
        ArrayList<CatFact> facts = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            CatService fact = new CatService();
            facts.add(fact.getCatFactData());
            builder.append(facts.get(i) + " ");
        }

       return builder.toString();
    }

    @GetMapping("/getTenSortByDate")
    @ResponseBody
    public String tenSortedFacts() throws IOException {
        ArrayList<CatFact> catFacts = new ArrayList<>();
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            CatService fact = new CatService();
            catFacts.add(fact.getCatFactData());
        }

        //Sort after created date
        Collections.sort(catFacts);

        for (CatFact catFact : catFacts) {
            builder.append(catFact + " ");
        }

        return builder.toString();
    }

    @GetMapping("/contain")
    @ResponseBody
    public String containChar(char enterChar, int amount) throws IOException {
        CatService fact = new CatService();
        int charCounter = 0;
        char[] charArray = fact.getCatFactString().toCharArray();

        for (char ch : charArray) {
            if (ch == enterChar) {
                charCounter++;
            }
        }

        if (charCounter > amount) {
            return "Sorry no luck!";
        } else {
            return fact.getCatFactString();
        }
    }
}
