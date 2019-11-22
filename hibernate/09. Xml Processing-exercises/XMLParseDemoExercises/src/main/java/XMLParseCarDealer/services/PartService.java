package XMLParseCarDealer.services;


import XMLParseCarDealer.entities.Part;


public interface PartService {

	public void savePart(Part part);
	public Part getRandomPart();
	
}
