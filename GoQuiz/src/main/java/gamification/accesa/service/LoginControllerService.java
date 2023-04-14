package gamification.accesa.service;

import gamification.accesa.domain.Player;
import gamification.accesa.repository.irepository.IPlayerRepository;

public class LoginControllerService implements IService{
    private final IPlayerRepository playerRepository;

    public LoginControllerService(IPlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    public Player getPlayerByUsernameAndPassword(String username, String password){
        return playerRepository.findByUsernameAndPassword(username, password);
    }
}
