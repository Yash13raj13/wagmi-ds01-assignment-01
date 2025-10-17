package main.java.com.wagmi.finance.strings;

public final class StringUtils {
    private StringUtils() {
    }

    public static String sanitizeDescription(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }
        // Replace unwanted characters with a space, then trim and collapse spaces.
        return input.replaceAll("[^a-zA-Z0-9\\s]", " ")
                .trim()
                .replaceAll("\\s+", " ");
    }

    public static boolean matchesDatePattern(String input) {
        if (input == null || !input.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            return false;
        }

        try {
            String[] parts = input.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);

            if (year < 1 || month < 1 || month > 12 || day < 1) {
                return false;
            }

            int maxDay;
            switch (month) {
                case 2: // February
                    boolean isLeapYear = ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
                    maxDay = isLeapYear ? 29 : 28;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    maxDay = 30; // April, June, September, November
                    break;
                default:
                    maxDay = 31; // All other months
            }
            return day <= maxDay;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}