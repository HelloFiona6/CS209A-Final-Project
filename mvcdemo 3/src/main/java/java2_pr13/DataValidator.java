package java2_pr13;
import java.lang.reflect.Field;
import java.util.Scanner;

public class DataValidator {
    public static boolean validate(Object obj) {
        // TODO
        boolean isValid = true;
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            MinLength minLenAnno = field.getAnnotation(MinLength.class);
            if (minLenAnno != null) {
                int minLength = minLenAnno.min();
                try {
                    String fieldValue = (String) field.get(obj);
                    if (fieldValue == null || fieldValue.length() < minLength) {
                        System.out.println("Validation failed for field " + field.getName() + ": should have a minimum length of " + minLength);
                        isValid = false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

            CustomValidation[] customValidations = field.getAnnotationsByType(CustomValidation.class);
            for (CustomValidation customValidation : customValidations) {
                Rule rule = customValidation.rule();
                try {
                    String fieldValue = (String) field.get(obj);
                    switch (rule) {
                        case ALL_LOWERCASE:
                            if (!fieldValue.equals(fieldValue.toLowerCase())) {
                                System.out.println("Validation failed for field " + field.getName() + " should be all lowercase.");
                                isValid = false;
                            }
                            break;
                        case NO_USERNAME:
                            if (fieldValue != null) {
                                String username = (String) fields[0].get(obj);
                                if (fieldValue.contains(username.toLowerCase())) {
                                    System.out.println("Validation failed for field " + field.getName() + ": should not contain username.");
                                    isValid = false;
                                }
                            }
                            break;
                        case HAS_BOTH_DIGITS_AND_LETTERS:
                            if (!fieldValue.matches(".*[A-Za-z].*") || !fieldValue.matches(".*[0-9].*")) {
                                System.out.println("Validation failed for field " + field.getName() + ": should be both digits and letters.");
                                isValid = false;
                            }
                            break;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        return isValid;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Username: ");
            String username = sc.next();
            System.out.print("Password: ");
            String pwd = sc.next();
            User user = new User(username, pwd);
            if (validate(user)) {
                System.out.println("Success!");
                break;
            }
        }
    }
}
