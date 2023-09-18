package ua.stokipna.hw10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PhoneNumbers {
    public static void main(String[] args) throws FileNotFoundException {
        IPhoneNumberFormatChecker fch = new FormatCheckers.DelegatedFormatChecker(
                new IPhoneNumberFormatChecker[] {
                        new FormatCheckers.FormatChecker1(), new FormatCheckers.FormatChecker2()
                }
        );
        File file = new File("file.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (fch.checkNumberFormat(line)) {
                System.out.println(line);
            }
        }
        scanner.close();
    }
}
