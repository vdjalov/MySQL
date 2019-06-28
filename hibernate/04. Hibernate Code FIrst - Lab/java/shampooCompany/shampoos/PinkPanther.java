package shampooCompany.shampoos;

import java.math.BigDecimal;
import javax.persistence.Entity;
import shampooCompany.enums.Size;
import shampooCompany.labels.BasicLabel;


@Entity
public class PinkPanther extends BasicShampoo {

	private static final String DEFAULT_BRAND ="Fresh Nuke";
	private static final BigDecimal DEFAULT_PRICE = new BigDecimal(8.50);
	private static final Size DEFAULT_ENUM = Size.MEDIUM;
	
	
	
	public PinkPanther(BasicLabel label) {
		super(DEFAULT_BRAND, DEFAULT_PRICE, DEFAULT_ENUM, label);
	}
}
