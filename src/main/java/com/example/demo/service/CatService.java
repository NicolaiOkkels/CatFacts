package com.example.demo.service;

import com.example.demo.model.CatFact;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class CatService {

    public CatFact getCatFactData() throws IOException {
        //Choose what API to consume
        URL catURL = new URL("https://cat-fact.herokuapp.com/facts/random");
        //Instantiate a Buffered Reader to consume the InputStream from the URL
        BufferedReader inputFromCatURL = new BufferedReader(new InputStreamReader(catURL.openStream()));
        //Map the data from Json to an object
        CatFact data = new Gson().fromJson(inputFromCatURL, CatFact.class);
        //Close the BufferedReader
        inputFromCatURL.close();

        return data;
    }

    public ArrayList<CatFact> getTenCatFactsArrayList() throws IOException {
        ArrayList<CatFact> tenCatFacts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tenCatFacts.add(getCatFactData());
        }

        return tenCatFacts;
    }

    public ArrayList<CatFact> getSortedCatFactsArrayList() throws IOException {
        ArrayList<CatFact> sortedCatFacts = getTenCatFactsArrayList();
        Collections.sort(sortedCatFacts);

        return sortedCatFacts;
    }
    //TODO: lav en metode der ligger catFacts ind i en arrayList, istedet for at have redundant kode i myController klassen
}
