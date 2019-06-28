package shampooCompany.shampoos;

import java.math.BigDecimal;
import javax.persistence.Entity;
import shampooCompany.enums.Size;
import shampooCompany.labels.BasicLabel;

@Entity
public class FiftyShades extends BasicShampoo {

	private static final String DEFAULT_BRAND ="Fifty Shades";
	private static final BigDecimal DEFAULT_PRICE = new BigDecimal(6.69);
	private static final Size DEFAULT_ENUM = Size.SMALL;
	
	public FiftyShades(){}
	
	public FiftyShades(BasicLabel label) {
		super(DEFAULT_BRAND, DEFAULT_PRICE, DEFAULT_ENUM, label);
	}
	
}
