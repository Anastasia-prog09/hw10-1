package ua.stokipna.hw10;

public final class FormatCheckers {

    public static boolean isDigit(char ch) {
        if (ch >= '0' && ch <= '9') {
            return true;
        }
        return false;
    }

    public static class FormatChecker1 implements IPhoneNumberFormatChecker {
        @Override
        public boolean checkNumberFormat(String number) {
            String cleanNumber = number.trim();
            if (cleanNumber.length() == 14) {
                if (cleanNumber.charAt(0) == '(' && cleanNumber.charAt(4) == ')'
                        && cleanNumber.charAt(9) == '-' && cleanNumber.charAt(5) == ' ') {
                    cleanNumber = cleanNumber.replace("(", "")
                            .replace(")", "")
                            .replace("-", "")
                            .replace(" ", "");
                    if (cleanNumber.length() != 10) {
                        return false;
                    }
                    char[] symbols = cleanNumber.toCharArray();
                    for (int i = 0; i < symbols.length; i++) {
                        if (!isDigit(symbols[i])) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public static class FormatChecker2 implements IPhoneNumberFormatChecker {

        @Override
        public boolean checkNumberFormat(String number) {
            String cleanNumber = number.trim();
            if (cleanNumber.length() == 12) {
                if (cleanNumber.charAt(3) == '-' && cleanNumber.charAt(7) == '-') {
                    cleanNumber = cleanNumber.replace("-", "");
                    if (cleanNumber.length() != 10) {
                        return false;
                    }
                    char[] symbols = cleanNumber.toCharArray();
                    for (int i = 0; i < symbols.length; i++) {
                        if (!isDigit(symbols[i])) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
    }

    public static class DelegatedFormatChecker implements IPhoneNumberFormatChecker {
        private IPhoneNumberFormatChecker[] checkers;

        public DelegatedFormatChecker(IPhoneNumberFormatChecker[] checkers) {
            this.checkers = checkers;
        }

        @Override
        public boolean checkNumberFormat(String number) {
            for (IPhoneNumberFormatChecker checker : checkers) {
                if (checker.checkNumberFormat(number)) {
                    return true;
                }
            }
            return false;
        }
    }
}
