import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите первое число и нажмите <Enter>: ");
        int x = new Scanner(System.in).nextInt();
        System.out.println("Введите второе число и нажмите <Enter>: ");
        int y = new Scanner(System.in).nextInt();
        System.out.println("Сумма: " + (x + y));
        System.out.println("Разность: " + (x - y));
        System.out.println("Произведение: " + (x * y));
        System.out.println("Частное: " + (double) x / y);
    }
}

