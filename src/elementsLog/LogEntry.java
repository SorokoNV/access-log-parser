package elementsLog;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LogEntry {
    private final String ipAddr;
    private final LocalDateTime time;
    private final HttpMethod method;
    private final String path;
    private final int responseCode;
    private final int responseSize;
    private final String referer;
    private final UserAgent userAgent;

    public LogEntry(String ipAddr, LocalDateTime time, HttpMethod method, String path, int responseCode, int responseSize, String referer, UserAgent userAgent) {
        this.ipAddr = ipAddr;
        this.time = time;
        this.method = method;
        this.path = path;
        this.responseCode = responseCode;
        this.responseSize = responseSize;
        this.referer = referer;
        this.userAgent = userAgent;
    }

    public LogEntry(String line) {

        String[] parts = line.split("\\s(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        this.ipAddr = parts[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMM/yyyy:HH:mm:ssZ",Locale.ENGLISH);
        String dataTime =parts[3] + parts[4];
        dataTime = dataTime.replace("[", "").replace("]", "");
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(dataTime, formatter);
        this.time = offsetDateTime.toLocalDateTime();
        String[] partsMethodPath= parts[5].split(" ");
        this.method = HttpMethod.valueOf(partsMethodPath[0].replaceAll("^\"", ""));
        this.path = partsMethodPath[1];
        this.responseCode = new Integer(parts[6]);
        this.responseSize = new Integer(parts[7]);
        this.referer = parts[8].replaceAll("^\"|\"$", "");
        this.userAgent = new UserAgent(parts[9].replaceAll("^\"|\"$", ""));
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public int getResponseSize() {
        return responseSize;
    }

    public String getReferer() {
        return referer;
    }

    public UserAgent getUserAgent() {
        return userAgent;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "ipAddr='" + ipAddr + '\'' +
                ", time=" + time +
                ", method=" + method +
                ", path='" + path + '\'' +
                ", responseCode=" + responseCode +
                ", responseSize=" + responseSize +
                ", referer='" + referer + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
enum HttpMethod {
    GET,
    POST,
    PUT,
    DELETE,
    HEAD,
    OPTIONS,
    PATCH;}
