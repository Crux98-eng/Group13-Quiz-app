import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.util.Duration;


public class CountdownTimer {
    private int timeLeft = 30; // start from 30 seconds
    private Timeline timeline;
    private Label timerLabel;
    private Runnable onTimeout; // this runs when time runs out
    
    public CountdownTimer(Label timerLabel, Runnable onTimeout) {
        this.timerLabel = timerLabel;
        this.onTimeout = onTimeout;
    }

    public void start() {
        timeLeft = 30;
        timerLabel.setText("Time: " + timeLeft + "s");

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            timeLeft--;
            timerLabel.setText("Time: " + timeLeft + "s");

            if (timeLeft <= 0) {
                timeline.stop();
                onTimeout.run(); // call the end-of-quiz action
            }
        }));

        timeline.setCycleCount(30); // 30 seconds
        timeline.play();
    }

    public void stop() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    public void reset() {
        stop();
        start();
    }
}
    
    
}





    
    