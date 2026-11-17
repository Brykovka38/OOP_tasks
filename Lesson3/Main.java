import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IntegerOperations[] operations = {
            new DivOperation (),
            new ModOperation(),
            new NodOperation(),
            new NokOperation()
        };

        System.out.print("Введите число: ");
        int a = scanner.nextInt();
        System.out.print("Введите число: ");
        int b = scanner.nextInt();

        for (IntegerOperations operation : operations) {
            System.out.println("Название операции: " + operation.getName());
            System.out.println(a + " " + operation.getSign() + " " + b + " = " + operation.estimate(a, b));
            System.out.println();
        }

        scanner.close();
    }
}
