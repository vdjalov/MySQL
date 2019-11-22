package XMLParseCarDealer.services;

import java.util.List;
import java.util.Optional;

import XMLParseCarDealer.entities.Car;

public interface CarService {

	public void saveCar(Car car);
	public Optional<Car> selectRandomCar();
	public List<Car> getAllCarsMakeToyota();
	public List<Object[]> getAllCarsWithTheirParts();
	public List<Object[]> getAllCarSalesInfo();
}
