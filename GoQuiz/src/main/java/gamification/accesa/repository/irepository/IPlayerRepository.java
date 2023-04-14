package gamification.accesa.repository.irepository;

import gamification.accesa.domain.Player;
import gamification.accesa.repository.IRepository;

public interface IPlayerRepository extends IRepository<Player>{
    Player findByUsernameAndPassword(String username, String password);

    void update(Player player);

    int size();
}
