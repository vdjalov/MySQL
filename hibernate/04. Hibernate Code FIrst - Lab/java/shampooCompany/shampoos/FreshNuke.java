package shampooCompany.shampoos;

import java.math.BigDecimal;
import javax.persistence.Entity;
import shampooCompany.labels.BasicLabel;
import shampooCompany.enums.Size;


@Entity
public class FreshNuke extends BasicShampoo {
	

	private static final String DEFAULT_BRAND ="Fresh Nuke";
	private static final BigDecimal DEFAULT_PRICE = new BigDecimal(9.33);
	private static final Size DEFAULT_ENUM = Size.BIG;

	
	public FreshNuke(BasicLabel label) {
		super(DEFAULT_BRAND, DEFAULT_PRICE, DEFAULT_ENUM, label);
	}

}
