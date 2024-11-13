package elementsLog;

import java.time.Duration;
import java.time.LocalDateTime;

public class Statistics {
    private long totalTraffic;
    private LocalDateTime minTime;
    private LocalDateTime maxTime;

    public Statistics() {
        this.totalTraffic = 0;
        this.minTime = LocalDateTime.MAX;
        this.maxTime = LocalDateTime.MIN;
    }

    public void addEntry(LogEntry logEntry){
        totalTraffic+=logEntry.getResponseSize();
        if (logEntry.getTime().isBefore(minTime)) {
            this.minTime = logEntry.getTime();
        }
        if (logEntry.getTime().isAfter(maxTime)) {
            this.maxTime = logEntry.getTime();
        }
    }
    public int getTrafficRate() {
        Duration duration = Duration.between(minTime, maxTime);
        double hours = (double) duration.toMinutes()/60;
        int trafficForHour = (int) (totalTraffic / hours);
        return trafficForHour;
    }
}
