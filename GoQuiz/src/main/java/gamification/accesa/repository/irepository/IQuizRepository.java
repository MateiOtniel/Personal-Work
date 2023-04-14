package gamification.accesa.repository.irepository;

import gamification.accesa.domain.Player;
import gamification.accesa.domain.Quiz;
import gamification.accesa.repository.IRepository;

import java.util.ArrayList;

public interface IQuizRepository extends IRepository<Quiz>{
    void save(Quiz quiz);

    ArrayList<Quiz> getQuizzesByPlayer(Player player);
}
