package com.futurebrands.finalbiblequiz.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class QuizLoader {

    public static List<Questions> loadQuestions(String resourcePath) {
        try {
            // Load from classpath
            InputStream is = QuizLoader.class.getResourceAsStream(resourcePath);
            if (is == null) {
                throw new RuntimeException("Resource not found: " + resourcePath);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            Gson gson = new Gson();
            Type questionListType = new TypeToken<List<Questions>>() {}.getType();

            return gson.fromJson(reader, questionListType);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
