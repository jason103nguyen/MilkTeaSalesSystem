package fa.training.service;

import fa.training.utils.ValidateUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ValidationService<T> {

    public Set<ConstraintViolation<T>> validate(T dto) {
        Validator validator = ValidateUtil.getValidator();
        return validator.validate(dto);
    }
}
