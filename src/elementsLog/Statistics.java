package elementsLog;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Statistics {
    private long totalTraffic=0;
    private LocalDateTime minTime = LocalDateTime.MAX;
    private LocalDateTime maxTime = LocalDateTime.MIN;
    private HashSet<String> sitePages =new HashSet<>();
    private HashSet<String> notSitePages =new HashSet<>();
    private HashMap<String, Integer> numberOfOSOccurrences =new HashMap<>();
    private HashMap<String, Integer> numberOfBrowserOccurrences =new HashMap<>();


    public void addEntry(LogEntry logEntry){
        totalTraffic+=logEntry.getResponseSize();
        if (logEntry.getTime().isBefore(minTime)) {
            this.minTime = logEntry.getTime();
        }
        if (logEntry.getTime().isAfter(maxTime)) {
            this.maxTime = logEntry.getTime();
        }
        if (logEntry.getResponseCode()==200){
            String cleanedUrl;
            if (logEntry.getPath().indexOf('?') != -1) {
                cleanedUrl = logEntry.getPath().substring(0, logEntry.getPath().indexOf('?')-1);
            } else {
                cleanedUrl = logEntry.getPath();
            }
            sitePages.add(cleanedUrl);}
        if (logEntry.getResponseCode()==404){
            notSitePages.add(logEntry.getPath());
        }
        if (logEntry.getUserAgent().getOperatingSystem()!=null){
            String operatingSystem=logEntry.getUserAgent().getOperatingSystem().toString();
            if (numberOfOSOccurrences.containsKey(operatingSystem))
                numberOfOSOccurrences.put(operatingSystem, numberOfOSOccurrences.get(operatingSystem)+1);
            else  numberOfOSOccurrences.put(operatingSystem, 1);}
        if (logEntry.getUserAgent().getBrowser()!=null){
            String browser=logEntry.getUserAgent().getBrowser().toString();
            if (numberOfBrowserOccurrences.containsKey(browser))
                numberOfBrowserOccurrences.put(browser, numberOfBrowserOccurrences.get(browser)+1);
            else  numberOfBrowserOccurrences.put(browser, 1);}
    }
    public int getTrafficRate() {
        Duration duration = Duration.between(minTime, maxTime);
        double hours = (double) duration.toMinutes()/60;
        int trafficForHour = (int) (totalTraffic / hours);
        return trafficForHour;
    }
    public HashMap<String,Double> getStatisticsOS(){
        HashMap<String,Double> statisticsOS =new HashMap<>();
        Set<String> operatingSystems = numberOfOSOccurrences.keySet();
        ArrayList<Integer> values = new ArrayList<>(numberOfOSOccurrences.values());
        int occurrenceOperatingSystems=0;
        for (int value: values){
            occurrenceOperatingSystems=occurrenceOperatingSystems+value;
        }

        for (String operatingSystem :operatingSystems){
            Double shareOS = (double)numberOfOSOccurrences.get(operatingSystem)/occurrenceOperatingSystems;
            statisticsOS.put(operatingSystem,shareOS);
        }
        return statisticsOS;
    }
    public HashMap<String,Double> getStatisticsBrowser(){
        HashMap<String,Double> statisticsBrowser =new HashMap<>();
        Set<String> browsers = numberOfBrowserOccurrences.keySet();
        ArrayList<Integer> values = new ArrayList<>(numberOfBrowserOccurrences.values());
        int occurrenceBrowsers=0;
        for (int value: values){
            occurrenceBrowsers=occurrenceBrowsers+value;
        }

        for (String browser :browsers){
            Double shareBrowser = (double)numberOfBrowserOccurrences.get(browser)/occurrenceBrowsers;
            statisticsBrowser.put(browser,shareBrowser);
        }
        return statisticsBrowser;
    }

    public HashSet<String> sitePages() {
        return sitePages;
    }
    public HashSet<String> getNotSitePages() {
        return notSitePages;
    }
}
