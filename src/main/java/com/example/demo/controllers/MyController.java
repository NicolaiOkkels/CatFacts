package com.example.demo.controllers;

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
    public String welcome(){
        return "Welcome to cat facts";
    }

    @GetMapping("/getSingle")
    @ResponseBody
    public String randomFact() throws IOException {
        CatService fact = new CatService();
        return fact.getServiceData();
    }

    @GetMapping("/getTen")
    @ResponseBody
    public String tenFacts() throws IOException {
        ArrayList <String> facts = new ArrayList<>();

        String str = "";

        for (int i = 0; i < 10; i++) {
            CatService fact = new CatService();
            facts.add(fact.getServiceData());
            str+= facts.get(i) + " ";
        }

        return str;
    }

    @GetMapping("/getTenSortByDate")
    @ResponseBody
    public String tenSortedFacts() throws IOException {
        ArrayList <String> catFacts = new ArrayList<>();
        String str = "";

        for (int i = 0; i < 10; i++) {
            CatService fact = new CatService();
            catFacts.add(fact.getServiceData());
        }
        //Sort
        Collections.sort(catFacts);

        for (String catFact: catFacts) {
            str += catFact + " ";
        }

        return str;
    }
}
