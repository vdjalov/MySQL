package services;

import java.util.List;
import java.util.Optional;

import entities.Game;

public interface GameService {

	public void registerGame(Game game);
	public Game checkIfGameExistsInTheDatabase(String title);
	public Optional<Game> findGameById(long id);
	public void deleteGameById(long id);
	List<Game> getAllGames();
}
