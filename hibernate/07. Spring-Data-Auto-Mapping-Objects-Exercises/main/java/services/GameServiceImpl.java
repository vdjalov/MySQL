package services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.Game;
import repositories.GameRepository;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private GameRepository gameRepository;

	@Override
	public void registerGame(Game game) {
		this.gameRepository.saveAndFlush(game);
	}

	@Override
	public Game checkIfGameExistsInTheDatabase(String title) {
		return this.gameRepository.findByTitle(title);
	}

	@Override
	public Optional<Game> findGameById(long id) {
		return this.gameRepository.findById(id);
	}

	@Override
	public void deleteGameById(long id) {
		this.gameRepository.deleteById(id);
		
	}

	@Override
	public List<Game> getAllGames() {
		return this.gameRepository.findAll();
	}
	
	
	
}
