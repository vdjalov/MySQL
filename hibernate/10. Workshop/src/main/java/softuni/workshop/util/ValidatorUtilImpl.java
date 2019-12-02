package softuni.workshop.util;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;



public class ValidatorUtilImpl  implements ValidatorUtil {

	
//	ValidatorFactory validationFactory = Validation.buildDefaultValidatorFactory();
//	Validator gameValidator = validationFactory.getValidator();
//	Set<ConstraintViolation<Game>> gameViolations = gameValidator.validate(game);
	
	@Override
	public <E> boolean validateObject(E object) {
		ValidatorFactory validationFactory = new Validation().buildDefaultValidatorFactory();
		Validator validator = validationFactory.getValidator();
		Set<ConstraintViolation<E>> violations = validator.validate(object); 
		
			if(violations.size() > 0) {
				violations.forEach(violation -> {
					System.out.println(violation.getMessage());
				});
				return false;
			}
		return true;
	}

}
