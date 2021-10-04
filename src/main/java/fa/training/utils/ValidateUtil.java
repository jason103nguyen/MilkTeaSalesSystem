package fa.training.utils;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/*
 * Built ValidatoreFactory allows to express and validate application constraints 
 */
public class ValidateUtil {

    private static ValidatorFactory factory;

    static {
        factory = Validation.buildDefaultValidatorFactory();
    }

    public static Validator getValidator() {
        return factory.getValidator();
    }
}
