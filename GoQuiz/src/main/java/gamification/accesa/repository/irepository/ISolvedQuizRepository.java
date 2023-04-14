package gamification.accesa.repository.irepository;

import gamification.accesa.domain.Player;
import gamification.accesa.domain.SolvedQuiz;
import gamification.accesa.repository.IRepository;

import java.util.ArrayList;

public interface ISolvedQuizRepository extends IRepository<SolvedQuiz>{
    void add(SolvedQuiz solvedQuiz);

    ArrayList<SolvedQuiz> getSolvedQuizzesByPlayer(Player player);
}
