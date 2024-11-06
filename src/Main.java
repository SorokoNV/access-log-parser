import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int fileNumber = 0;
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
            int maxLength =0;
            int minLength =0;
            try {
                FileReader fileReader = new FileReader(path);
                BufferedReader reader =
                        new BufferedReader(fileReader);
                String line;
                while ((line = reader.readLine()) != null) {
                    int length = line.length();
                    cuntLine = cuntLine +1;
                    if (length > 1024)
                        throw new RuntimeException("\n" +
                                "The number of characters in a line №"+cuntLine+" exceeds 1024");
                    if (minLength==0)
                        minLength=length;
                    if (minLength>length)
                        minLength=length;
                    if (length>maxLength)
                        maxLength=length;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("Общее количество строк в файле "+cuntLine);
            System.out.println("Длина самой длинной строки в файле "+maxLength);
            System.out.println("Длина самой короткой строки в файле "+minLength);
        }
        while (true);

    }

}