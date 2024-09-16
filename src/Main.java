import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int n = 0;
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
            n = n + 1;
            System.out.println("Это файл номер " + n);
        }
        while (true);

    }

}

