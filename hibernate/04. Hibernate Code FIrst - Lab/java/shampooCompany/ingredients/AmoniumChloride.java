package shampooCompany.ingredients;

import java.math.BigDecimal;

import shampooCompany.interfaces.Chemicalngredient;

public class AmoniumChloride extends BasicChemicalIngredient{
	
	private final static String DEFAULT_NAME = "Amonium Chloride";
	private final static BigDecimal DEFAULT_PRICE = new BigDecimal(0.59);
	private static final String DEFAULT_FORMULA = "NH4CI";
	
	
	public AmoniumChloride() {
		super(DEFAULT_NAME, DEFAULT_PRICE, DEFAULT_FORMULA);
	}
	
	

}
