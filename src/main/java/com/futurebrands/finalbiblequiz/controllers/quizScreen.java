package com.futurebrands.finalbiblequiz.controllers;

import com.futurebrands.finalbiblequiz.util.CountdownTimer;
import com.futurebrands.finalbiblequiz.util.Questions;
import com.futurebrands.finalbiblequiz.util.QuizLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.Collections;
import java.util.List;

public class quizScreen {

    @FXML private Label questionLabel;
    @FXML private RadioButton btn1;
    @FXML private RadioButton btn2;
    @FXML private RadioButton btn3;
    @FXML private RadioButton btn4;
    @FXML private ProgressBar progress;
    @FXML private Label timelabel;     // (optional, if used elsewhere)
    @FXML private Label count;
    @FXML private Label timelapse;
    
    private ToggleGroup optionsGroup;
    private List<Questions> questionList;
    private int currentIndex = 0;
    private int score = 0;
    private int dulation = 20;
    private CountdownTimer timer;

    public void initialize() {
        setupToggleGroup();
        loadQuestions();
        timer = new CountdownTimer(); // initialize once
        displayQuestion(currentIndex);
    }

    private void setupToggleGroup() {
        optionsGroup = new ToggleGroup();
        btn1.setToggleGroup(optionsGroup);
        btn2.setToggleGroup(optionsGroup);
        btn3.setToggleGroup(optionsGroup);
        btn4.setToggleGroup(optionsGroup);
    }

    private void loadQuestions() {
        questionList = QuizLoader.loadQuestions("src/main/resources/com/futurebrands/finalbiblequiz/assets/data/bibleQuestions.json");

        if (questionList == null || questionList.size() < 5) {
            System.out.println("Failed to load enough questions.");
            return;
        }

        Collections.shuffle(questionList);
        questionList = questionList.subList(0, 5);
    }

    private void displayQuestion(int index) {
        if (timer != null) {
            timer.stop(); // stop any previous timer
        }

//        timer.startCountdown(timelapse,dulation,()->{
//
//            endQuiz();
//        });
        if (index < questionList.size()) {
            Questions q = questionList.get(index);
            questionLabel.setText(q.question);
            btn1.setText("A. " + q.options.get("A"));
            btn2.setText("B. " + q.options.get("B"));
            btn3.setText("C. " + q.options.get("C"));
            btn4.setText("D. " + q.options.get("D"));
            count.setText((index + 1) + " / " + questionList.size());

            double progressValue = (double) index / questionList.size();
            progress.setProgress(progressValue);

            optionsGroup.selectToggle(null); // clear previous selection
            timer.startCountdown(timelapse, dulation,  this::endQuiz); // call handleNext() automatically when time is up

        }
    }

    @FXML
    private void handleNext() {

        if (optionsGroup.getSelectedToggle() != null) {
            RadioButton selected = (RadioButton) optionsGroup.getSelectedToggle();
            String selectedText = selected.getText();
            String selectedKey = selectedText.substring(0, 1); // "A", "B", etc.

            String correctAnswer = questionList.get(currentIndex).answer;

            if (selectedKey.equalsIgnoreCase(correctAnswer)) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong!");
            }

            currentIndex++;
            displayQuestion(currentIndex);
        } else {
            System.out.println("Please select an answer.");
        }
    }

    private void endQuiz() {
        questionLabel.setText("Quiz finished!");
        btn1.setVisible(false);
        btn2.setVisible(false);
        btn3.setVisible(false);
        btn4.setVisible(false);
        count.setText("Score: " + score + " / " + questionList.size());
        progress.setProgress(1.0);
    }
}
