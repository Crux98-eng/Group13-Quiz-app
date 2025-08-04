package com.futurebrands.finalbiblequiz.controllers;

import com.futurebrands.finalbiblequiz.util.CountdownTimer;
import com.futurebrands.finalbiblequiz.util.Questions;
import com.futurebrands.finalbiblequiz.util.QuizLoader;
import com.futurebrands.finalbiblequiz.util.screenView;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class quizScreen {

    public StackPane mainbg;
    @FXML
    private Label questionLabel;
    @FXML
    private RadioButton btn1;
    @FXML
    private RadioButton btn2;
    @FXML
    private RadioButton btn3;
    @FXML
    private RadioButton btn4;
    @FXML
    private ProgressBar progress;
    @FXML
    private Label timelabel;     // (optional, if used elsewhere)
    @FXML
    private Label count;
    @FXML
    private Label timelapse;

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
        Random rand = new Random(12);

        Collections.shuffle(questionList);
        Collections.shuffle(questionList);

        questionList = questionList.subList(0, 15);
    }


    private void displayQuestion(int index) {
        if (timer != null) {
            timer.stop(); // stop any previous timer
        }

        if (index < questionList.size()) {
            Questions q = questionList.get(index);

            questionLabel.setText(q.question);
            btn1.setText("A. " + q.options.get("A"));
            btn2.setText("B. " + q.options.get("B"));
            btn3.setText("C. " + q.options.get("C"));
            btn4.setText("D. " + q.options.get("D"));

            // Set user data to help identify which is correct later
            btn1.setUserData("A");
            btn2.setUserData("B");
            btn3.setUserData("C");
            btn4.setUserData("D");

            // Reset styles and re-enable
            for (Toggle toggle : optionsGroup.getToggles()) {
                RadioButton rb = (RadioButton) toggle;
                rb.setStyle("");
                rb.setDisable(false);
            }

            count.setText((index + 1) + " / " + questionList.size());
            double progressValue = (double) index / questionList.size();
            progress.setProgress(progressValue);

            optionsGroup.selectToggle(null); // clear previous selection
            timer.startCountdown(timelapse, dulation, this::endQuiz);

            //System.out.println(index);

        } else {
            endQuiz();
        }
    }

    @FXML
    private void handleNext() {
        if (optionsGroup.getSelectedToggle() != null) {
            RadioButton selected = (RadioButton) optionsGroup.getSelectedToggle();
            String selectedText = selected.getText();
            String selectedKey = selectedText.substring(0, 1); // "A", "B", etc.

            String correctAnswer = questionList.get(currentIndex).answer;

            // Loop through all radio buttons to find and style the correct one
            for (Toggle toggle : optionsGroup.getToggles()) {
                RadioButton btn = (RadioButton) toggle;
                String key = btn.getText().substring(0, 1); // "A", "B", etc.

                if (key.equalsIgnoreCase(correctAnswer)) {
                    btn.setStyle("-fx-text-fill: green;");
                    // Correct answer
                    score++;
                } else if (key.equalsIgnoreCase(selectedKey)) {
                    btn.setStyle("-fx-text-fill: red;");   // Wrong answer
                } else {
                    btn.setStyle(""); // Reset others if necessary
                }
            }

            // Optional: Add a delay before switching to the next question
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(e -> {
                score += selectedKey.equalsIgnoreCase(correctAnswer) ? 1 : 0;
                currentIndex++;
                displayQuestion(currentIndex);
            });
            pause.play();

        } else {
            System.out.println("Please select an answer.");
        }
    }


    private void endQuiz() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/futurebrands/finalbiblequiz/views/resultsScreen.fxml"));

            // Load the root and get the controller
            Parent root = loader.load();
            ResultsScreen controller = loader.getController();

            // Set score BEFORE showing the stage to ensure UI is ready
            System.out.println(score);
            controller.setScore(score);

            // Use a new stage to display the result screen
           // Stage stage = new Stage();
            Stage stage = (Stage) mainbg.getScene().getWindow();

            screenView.load2(stage, root);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
