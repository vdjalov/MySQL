package XMLParseCarDealer.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import XMLParseCarDealer.entities.Part;
import XMLParseCarDealer.repositories.PartRepository;

@Service
public class PartServiceImpl implements PartService{

	@Autowired
	private PartRepository partRepository;
	
	@Autowired
	private Random random;
	
	@Override
	public void savePart(Part part) {
		this.partRepository.saveAndFlush(part);
	}


	@Override
	public Part getRandomPart() {
		int size = this.partRepository.findAll().size();
		Part part = this.partRepository.findAll().get(this.random.nextInt(size));
		return part;
	}

}
