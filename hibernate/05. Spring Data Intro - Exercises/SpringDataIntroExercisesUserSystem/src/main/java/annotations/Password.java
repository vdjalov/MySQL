package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.springframework.stereotype.Component;

@Component
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PasswordValidator.class)
public @interface Password {

	String message() default "Invalid password format";
	
	int minLength() default 4;
	int maxLengh() default 40;
	
	boolean containsDigits() default false;
	boolean containsLowerCase() default false;
	boolean containsUpperCase() default false;
	boolean containsSpecialSymbols() default false;
	
	Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
	
}
