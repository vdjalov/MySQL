package shampooCompany.ingredients;

import java.math.BigDecimal;

public class Lavender extends BasicIngredient {
	
	private final static String DEFAULT_NAME = "Lavender";
	private final static BigDecimal DEFAULT_PRICE = new BigDecimal(2.00);
	
	
	public Lavender() {
		super(DEFAULT_NAME, DEFAULT_PRICE);
	}

}
