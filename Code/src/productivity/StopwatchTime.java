package productivity;

public class StopwatchTime {
    private int seconds;
    private int minutes;
    private int hours;

    public StopwatchTime() {
        seconds = 0;
        minutes = 0;
        hours = 0;
    }

    public StopwatchTime(String time) {
        seconds = Integer.parseInt(time.substring(6,8));
        minutes = Integer.parseInt(time.substring(3,5));
        hours = Integer.parseInt(time.substring(0,2));
    }

    public void incrementSecond() {
        seconds++;
        if(seconds >= 60) {
            seconds = 0;
            minutes++;
            if(minutes >= 60) {
                minutes = 0;
                hours++;
            }
        }

    }

    public String toString() {
        String hoursString = "";
        String minuteString = "";
        String secondsString = "";

        if(hours < 10) {
            hoursString = "0";
        }
        hoursString += String.format("%d", hours);

        if(minutes < 10) {
            minuteString = "0";
        }
        minuteString += String.format("%d", minutes);

        if(seconds < 10) {
            secondsString = "0";
        }
        secondsString += String.format("%d", seconds);

        return String.format("%s:%s:%s", hoursString, minuteString, secondsString);
    }
}
