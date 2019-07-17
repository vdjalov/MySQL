package annotations;

import java.io.Console;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;


@Component
public class PasswordValidator implements ConstraintValidator<Password, CharSequence>{

	public static final Pattern checkLowerCasePattern = Pattern.compile("[a-z]+");
	public static final Pattern checkUpperCasePattern = Pattern.compile("[A-Z]+");
	public static final Pattern checkDigtsPattern = Pattern.compile("[0-9]+");
	public static final Pattern checkSpecialSymbols = Pattern.compile("[!@#$%^&*()_+<>?]");

    private int minLength;
    private int maxLength;
    private boolean hasUpper;
    private boolean hasLower;
    private boolean hasDigit;
    private boolean hasSpecialSymbol;
	
    
    public void initialize(Password password) {
		this.minLength = password.minLength();
		this.maxLength = password.maxLengh();
		this.hasUpper = password.containsUpperCase();
		this.hasLower = password.containsLowerCase();
		this.hasDigit = password.containsDigits();
		this.hasSpecialSymbol = password.containsSpecialSymbols();
	}
    
    
    
	public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
			System.out.println(value);
			if(value == null) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Password cant be empty");
				return false;
			}
			
			if(value.length() > this.maxLength) {
				return false;
			}
			
			if(value.length() < minLength) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Min length should be at least fur digits");
				return false;
			}
			String password = value.toString();
			if(!checkLowerCasePattern.matcher(password).find()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Password should contain lower case");
				return false;
			}
			
			if(!checkUpperCasePattern.matcher(password).find()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Password should contain upper case");
				return false;
			}
			
			if(!checkDigtsPattern.matcher(password).find()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Password should contain upper case");
				return false;
			}
			
			if(!checkSpecialSymbols.matcher(password).find()) {
				context.disableDefaultConstraintViolation();
				context.buildConstraintViolationWithTemplate("Password should contain special symbols");
				return false;
			}
			
		return true;
	}
}




















