package edu.phystech.hw5;

import edu.phystech.hw5.annotation.validation.NotBlank;
import edu.phystech.hw5.annotation.validation.Size;
import edu.phystech.hw5.exception.ValidationException;
import edu.phystech.hw5.service.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * @author kzlv4natoly
 */
public class ValidatorTest {
    private Validator validator = object -> {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(object);
                if (field.isAnnotationPresent(NotBlank.class) && ((String) value).trim().isEmpty()) {
                    String message = field.getAnnotation(NotBlank.class).message();
                    throw new ValidationException(message);
                }
                if (field.isAnnotationPresent(Size.class) && (((String) value).length() < field.getAnnotation(Size.class).min() || ((String) value).length() > field.getAnnotation(Size.class).max())) {
                    String message = field.getAnnotation(Size.class).message();
                    throw new ValidationException(message);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    };

    @Test
    void notBlankWorks() {
        class Example {

            @NotBlank
            private final String x;

            @NotBlank(message = "This is a very important field and it can't be empty!")
            private final String y;

            Example(String x, String y) {
                this.x = x;
                this.y = y;
            }
        }

        Assertions.assertDoesNotThrow(() -> validator.validate(new Example("123", "567")));
        ValidationException exception =
                Assertions.assertThrows(ValidationException.class, () -> validator.validate(new Example("11", "")));
        Assertions.assertEquals("This is a very important field and it can't be empty!", exception.getMessage());
    }

    @Test
    void sizeWorks() {
        class Example {
            @Size(max = 52, message = "Long live Saint Petersburg!")
            private final String x;

            @Size(min = 5, max = 11)
            private final String y;

            Example(String x, String y) {
                this.x = x;
                this.y = y;
            }
        }

        Assertions.assertDoesNotThrow(() -> validator.validate(new Example("123", "567765")));
        ValidationException exception =
                Assertions.assertThrows(ValidationException.class, () -> validator.validate(new Example("", "")));
        Assertions.assertEquals("Long live Saint Petersburg!", exception.getMessage());
        Assertions.assertThrows(ValidationException.class, () -> validator.validate(new Example("", "0000000000000")));
    }

}
