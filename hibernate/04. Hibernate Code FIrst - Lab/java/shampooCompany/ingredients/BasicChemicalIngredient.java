package shampooCompany.ingredients;



import java.math.BigDecimal;

import shampooCompany.interfaces.Chemicalngredient;



public class BasicChemicalIngredient extends BasicIngredient 
									implements Chemicalngredient {
	
	private String chemicalFormula;
	
	public BasicChemicalIngredient(){};
	
	public BasicChemicalIngredient(String name, BigDecimal price, String chemicalFormula) {
		super(name, price);
		this.chemicalFormula = chemicalFormula;
	};

	
	
	public String getChemicalFormula() {
		return this.chemicalFormula;
	}
	

	public void setChemicalFormula(String chemicalFormula) {
		this.chemicalFormula = chemicalFormula;
	}

	

}
