package shampooCompany.ingredients;

import java.math.BigDecimal;

public class Mint extends BasicIngredient {
	
	private final static String DEFAULT_NAME = "Mint";
	private final static BigDecimal DEFAULT_PRICE = new BigDecimal(3.54);
	
	
	public Mint() {
		super(DEFAULT_NAME, DEFAULT_PRICE);
	}
}
