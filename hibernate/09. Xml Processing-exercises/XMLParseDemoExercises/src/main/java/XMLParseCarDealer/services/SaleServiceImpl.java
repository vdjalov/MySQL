package XMLParseCarDealer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import XMLParseCarDealer.entities.Sale;
import XMLParseCarDealer.repositories.SaleRepository;

@Service
public class SaleServiceImpl implements SaleService {

	@Autowired
	private SaleRepository saleRepository;
	
	@Override
	public void saveSale(Sale sale) {
		this.saleRepository.saveAndFlush(sale);
	}

}
