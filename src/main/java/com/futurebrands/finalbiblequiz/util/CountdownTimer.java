package com.futurebrands.finalbiblequiz.util;


    import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
    import javafx.fxml.FXML;
    import javafx.scene.control.Label;
import javafx.util.Duration;

    public class CountdownTimer {

        private Timeline timeline;

        /**
         * Starts a countdown timer that updates the given Label every second.
         *
         * @param label         The Label to display the countdown.
         * @param durationSecs  The countdown duration in seconds.
         * @param onFinish      A Runnable to execute when the timer finishes.
         */


        public void startCountdown(Label label, int durationSecs, Runnable onFinish) {
            label.setText(String.valueOf(durationSecs));
            int[] timeLeft = { durationSecs };

            timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                timeLeft[0]--;
                if (timeLeft[0] <= 0) {
                    label.setText("Time's up!");
                    timeline.stop();
                    if (onFinish != null) {
                        onFinish.run();
                    }
                } else {
                    label.setText(String.valueOf(timeLeft[0]));
                }
            }));
            timeline.setCycleCount(durationSecs);
            timeline.play();
        }

        /**
         * Stops the timer if it's running.
         */
        public void stop() {
            if (timeline != null) {
                timeline.stop();
            }
        }
    }


