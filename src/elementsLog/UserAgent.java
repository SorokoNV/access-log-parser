package elementsLog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgent {
    private final OperatingSystem operatingSystem;
    private final Browser browser;

    public UserAgent(OperatingSystem operatingSystem, Browser browser) {
        this.operatingSystem = operatingSystem;
        this.browser = browser;
    }

    public UserAgent(String userAgent) {
        switch (detectOperatingSystem(userAgent)){
            case "Linux": this.operatingSystem=OperatingSystem.Linux ; break;
            case "Windows": this.operatingSystem=OperatingSystem.Windows; break;
            case "Mac OS": this.operatingSystem=OperatingSystem.MacOS; break;
            default: this.operatingSystem=null;
        }

        switch (detectBrowser(userAgent)){
            case "Opera": this.browser=Browser.Opera; break;
            case "OPR/": this.browser=Browser.Opera; break;
            case "Chrome": this.browser=Browser.Chrome; break;
            case "Safari": this.browser=Browser.Safari; break;
            case "Firefox": this.browser=Browser.Firefox; break;
            case  "MSIE": this.browser=Browser.InternetExplorer; break;
            case "YaBrowser": this.browser=Browser.YaBrowser; break;
            case "Edg": this.browser=Browser.Edge;break;
            default: this.browser=null;
        }

    }

    public Browser getBrowser() {
        return browser;
    }

    public OperatingSystem getOperatingSystem() {
        return operatingSystem;
    }

    @Override
    public String toString() {
        return "UserAgent{" +
                "operatingSystem=" + operatingSystem +
                ", browser=" + browser +
                '}';
    }

    private String detectOperatingSystem(String userAgent) {
        // Регулярные выражения для определения браузеров
        String[] operatingSystems = {
                "Linux",
                "Windows",
                "Mac OS"
        };

        for (String operatingSystem : operatingSystems) {
            Pattern pattern = Pattern.compile(operatingSystem);
            Matcher matcher = pattern.matcher(userAgent);
            if (matcher.find()) {
                return operatingSystem;
            }
        }
        return "not";}

    private String detectBrowser(String userAgent) {

        String[] browsers = {
                "Opera",
                "OPR/",
                "Edg",
                "YaBrowser",
                "Chrome",
                "Safari",
                "Firefox",
                "MSIE"
        };

        for (String browser : browsers) {
            Pattern pattern = Pattern.compile(browser);
            Matcher matcher = pattern.matcher(userAgent);
            if (matcher.find()) {
                return browser;
            }
        }
        return "not";}
}

enum OperatingSystem {
    Windows,
    MacOS,
    Linux;}
enum Browser {
    Opera,
    Chrome,
    Safari,
    Firefox,
    InternetExplorer,
    YaBrowser,
    Edge;}
