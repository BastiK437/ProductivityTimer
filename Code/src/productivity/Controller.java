package productivity;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {

    private Thread timerThread;
    private StopwatchTime workTime;
    private StopwatchTime pauseTime;
    private StopwatchTime startTime;
    private DataManager dataManager;
    private boolean pauseTimer;

    @FXML
    Button pauseButton;

    @FXML
    Button playButton;

    @FXML
    Text timeText;

    @FXML
    Text captureText;

    @FXML
    public void initialize() {
        dataManager = new DataManager();

        String restoredTime = dataManager.restoreTime();
        workTime = new StopwatchTime(restoredTime);
        startTime = new StopwatchTime(restoredTime);

        timeText.setText(workTime.toString());

        pauseTimer = false;
    }


    @FXML
    public void playButtonPressed() {
        if(pauseTimer) {
            stopTimerThread();
            pauseTimer = false;
        }

        captureText.setText("Productivity Timer");
        if( timerThread==null || !timerThread.isAlive() ) {
            timerThread = new Thread(new Timer(workTime, timeText));
            timerThread.start();
        }
    }

    @FXML
    public void pauseButtonPressed() {
        if(!pauseTimer) {
            stopTimerThread();
            pauseTimer = true;
        }

        captureText.setText("Pause Timer");
        if( timerThread==null || !timerThread.isAlive() ) {
            timerThread = new Thread(new Timer(new StopwatchTime(), timeText));
            timerThread.start();
        }
    }

    public void exitApplication() {
        stopTimerThread();
        dataManager.storeTime(workTime.toString());
        dataManager.storeDiff(workTime.diff(startTime));
    }

    private void stopTimerThread() {
        if( timerThread != null && timerThread.isAlive() ) {
            timerThread.interrupt();
            try {
                timerThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
