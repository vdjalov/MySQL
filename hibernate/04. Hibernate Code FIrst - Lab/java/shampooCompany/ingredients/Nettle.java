package shampooCompany.ingredients;

import java.math.BigDecimal;

public class Nettle extends BasicIngredient{

	private final static String DEFAULT_NAME = "Nettle";
	private final static BigDecimal DEFAULT_PRICE = new BigDecimal(6.12);
	
	
	public Nettle() {
		super(DEFAULT_NAME, DEFAULT_PRICE);
	}
}
