package com.futurebrands.finalbiblequiz.util;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class QuizLoader {

    public static List<Questions> loadQuestions(String filename) {
        try {
            Gson gson = new Gson();
            Type questionListType = new TypeToken<List<Questions>>() {}.getType();
            FileReader reader = new FileReader(filename);
            return gson.fromJson(reader, questionListType);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
