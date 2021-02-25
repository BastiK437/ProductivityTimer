package productivity;

import javafx.scene.text.Text;

public class Timer implements Runnable {
    private StopwatchTime time;
    private Text timeText;

    public Timer(StopwatchTime time, Text timeText) {
        this.time = time;
        this.timeText = timeText;
    }


    @Override
    public void run() {
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
    }
}
