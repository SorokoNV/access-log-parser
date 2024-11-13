import elementsLog.LogEntry;
import elementsLog.Statistics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        int fileNumber = 0;
        String program1="YandexBot";
        String program2="Googlebot";
        do {
            System.out.println("Введите путь к файлу и нажмите Enter");
            String path = new Scanner(System.in).nextLine();
            File file = new File(path);
            boolean fileExists = file.exists();
            boolean fileDirectory = file.isDirectory();
            if (!fileExists) {
                System.out.println("Указан путь к несуществующему файлу");
                continue;
            }
            if (fileDirectory) {
                System.out.println("Указан путь к папке, а не к файлу");
                continue;
            }
            System.out.println("Путь указан верно");
            fileNumber = fileNumber + 1;
            System.out.println("Это файл номер " + fileNumber);
            int cuntLine =0;
            int countRequestsYandexBot=0;
            int countRequestsGooglebot=0;
            Statistics statistics= new Statistics();
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    cuntLine = cuntLine +1;
                    String regex = "\\s(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
                    String[] parts = line.split(regex);
                    String userAgent = parts[9];
                    if (searchForProgramThatMakesQueries(userAgent,program1))
                        countRequestsYandexBot=countRequestsYandexBot+1;
                    if (searchForProgramThatMakesQueries(userAgent,program2))
                        countRequestsGooglebot=countRequestsGooglebot+1;
                    LogEntry logEntry =new LogEntry(line);
                    statistics.addEntry(logEntry);

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Общее количество строк в файле "+cuntLine);
            System.out.println("Число запросов "+ program1+" равно "+countRequestsYandexBot);
            System.out.println("Число запросов "+ program2+" равно "+countRequestsGooglebot);
            System.out.println("Доля запросов "+ program1+" равна "+shareOfRequestsProgram(cuntLine,countRequestsYandexBot));
            System.out.println("Доля запросов "+ program2+" равна "+shareOfRequestsProgram(cuntLine,countRequestsGooglebot));
            System.out.println("Объем часового трафика сайта "+statistics.getTrafficRate());
        }
        while (true);

    }
    public static boolean searchForProgramThatMakesQueries (String userAgent, String program) {
        String regex = "compatible;\\s*(\\S+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userAgent);
        if (matcher.find()) {
            String fragment = matcher.group(1);
            String[] fragments = fragment.split("/");
            if (fragments[0].equals(program))
                return true;
        }
        return false;
    }
    public static double shareOfRequestsProgram (int allRequests, int requestsProgram){
        double shareRequests =(double) requestsProgram /  allRequests;
        return shareRequests;
    }
}