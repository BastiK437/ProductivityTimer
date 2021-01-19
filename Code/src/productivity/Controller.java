package productivity;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Controller {

    private Thread timerThread;
    private StopwatchTime time;
    private DataManager dataManager;

    @FXML
    Button pauseButton;

    @FXML
    Button playButton;

    @FXML
    Text timeText;

    @FXML
    public void initialize() {
        dataManager = new DataManager();

        String restoredTime = dataManager.restoreTime();
        time = new StopwatchTime(restoredTime);

        timeText.setText(time.toString());
    }


    @FXML
    public void playButtonPressed() {
        if( timerThread==null || !timerThread.isAlive() ) {
            timerThread = new Thread(() -> {
                long currentTime = System.currentTimeMillis()/1000;

                while( !Thread.currentThread().isInterrupted() ) {
                    try {
                        if( currentTime < (System.currentTimeMillis()/1000) ) {
                            time.incrementSecond();
                            timeText.setText(time.toString());

                            currentTime = System.currentTimeMillis()/1000;
                        }

                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });

            timerThread.start();
        }


    }

    @FXML
    public void pauseButtonPressed() {
        stopTimerThread();
    }

    public void exitApplication() {
        dataManager.storeTime(time.toString());
        stopTimerThread();
    }

    private void stopTimerThread() {
        if( timerThread != null && timerThread.isAlive() ) {
            timerThread.interrupt();
        }
    }
}
