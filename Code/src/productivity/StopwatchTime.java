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

    public int getSeconds() {
        return seconds;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getHours() {
        return hours;
    }

    public StopwatchTime(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
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

    public StopwatchTime diff(StopwatchTime startTime) {
        int seconds = 0;
        int minutes = 0;
        int hours = 0;

        seconds = this.seconds - startTime.seconds;
        if(seconds < 0) {
            seconds += 60;
            minutes = -1;
        }

        minutes += this.minutes - startTime.minutes;
        if(minutes < 0) {
            minutes += 60;
            hours = -1;
        }

        hours += this.hours - startTime.hours;

        return new StopwatchTime(hours, minutes, seconds);
    }

    public StopwatchTime add(StopwatchTime time) {
        int seconds = 0;
        int minutes = 0;
        int hours = 0;

        seconds = this.seconds + time.seconds;
        if(seconds > 60) {
            seconds -= 60;
            minutes = 1;
        }

        minutes += this.minutes + time.minutes;
        if(minutes > 60) {
            minutes -= 60;
            hours = 1;
        }

        hours += this.hours + time.hours;

        return new StopwatchTime(hours, minutes, seconds);
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
