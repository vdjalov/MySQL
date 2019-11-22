package XMLParseCarDealer.services;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import XMLParseCarDealer.entities.Car;
import XMLParseCarDealer.repositories.CarRepository;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private Random random;
	
	@Override
	public void saveCar(Car car) {
		this.carRepository.saveAndFlush(car);
		
	}

	@Override
	public Optional<Car> selectRandomCar() {
		int  size = this.carRepository.findAll().size();
		int id = this.random.nextInt(size);
		return this.carRepository.findById((long) id);
	}

	@Override
	public List<Car> getAllCarsMakeToyota() {
		return this.carRepository.getAllCarsMakeToyota();
	}

	@Override
	public List<Object[]> getAllCarsWithTheirParts() {
		return this.carRepository.getAllCarsWithTheirParts();
	}

	@Override
	public List<Object[]> getAllCarSalesInfo() {
		return this.carRepository.getAllSalesInfo();
	}

}
