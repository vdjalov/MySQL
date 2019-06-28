package shampooCompany.ingredients;

import java.math.BigDecimal;

public class Strawberry extends BasicIngredient{

	private final static String DEFAULT_NAME = "Strawberry";
	private final static BigDecimal DEFAULT_PRICE = new BigDecimal(4.85);
	
	
	public Strawberry() {
		super(DEFAULT_NAME, DEFAULT_PRICE);
	}
}
