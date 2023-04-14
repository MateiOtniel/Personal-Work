package gamification.accesa.service;

import gamification.accesa.domain.Player;
import gamification.accesa.domain.Quiz;
import gamification.accesa.domain.SolvedQuiz;
import gamification.accesa.repository.irepository.IQuizRepository;
import gamification.accesa.repository.irepository.ISolvedQuizRepository;

import java.util.ArrayList;

public class BadgeControllerService implements IService{
    private final IQuizRepository quizRepository;

    private final ISolvedQuizRepository solvedQuizRepository;

    public BadgeControllerService(IQuizRepository quizRepository,
                                  ISolvedQuizRepository solvedQuizRepository){
        this.quizRepository = quizRepository;
        this.solvedQuizRepository = solvedQuizRepository;
    }

    public ArrayList<String> getBadges(Player player){
        ArrayList<String> badges = new ArrayList<>();
        ArrayList<SolvedQuiz> solvedQuizzes =
                solvedQuizRepository.getSolvedQuizzesByPlayer(player);
        ArrayList<Quiz> quizzes =
                quizRepository.getQuizzesByPlayer(player);
        if(hasBronzeBadge(solvedQuizzes))
            badges.add("bronze");
        if(hasSilverBadge(solvedQuizzes))
            badges.add("silver");
        if(hasGoldBadge(solvedQuizzes))
            badges.add("gold");
        if(hasCreatorBadge(quizzes))
            badges.add("creator");
        if(hasIntelectualBadge(solvedQuizzes))
            badges.add("intellectual");
        if(hasRoyalBadge(solvedQuizzes))
            badges.add("royal");
        return badges;
    }

    private boolean hasRoyalBadge(ArrayList<SolvedQuiz> solvedQuizzes){
        int sum = 0;
        for(SolvedQuiz solvedQuiz : solvedQuizzes)
            sum += quizRepository.findOne(solvedQuiz.getIdQuiz())
                    .getTokens();
        return sum >= 10000;
    }

    private boolean hasIntelectualBadge(ArrayList<SolvedQuiz> solvedQuizzes){
        for(SolvedQuiz solvedQuiz : solvedQuizzes)
            if(quizRepository.findOne(solvedQuiz.getIdQuiz())
                    .getTokens() >= 1000)
                return true;
        return false;
    }

    private boolean hasCreatorBadge(ArrayList<Quiz> quizzes){
        return quizzes.size() >= 5;
    }

    private boolean hasGoldBadge(ArrayList<SolvedQuiz> solvedQuizzes){
        return solvedQuizzes.size() >= 20;
    }

    private boolean hasSilverBadge(ArrayList<SolvedQuiz> solvedQuizzes){
        return solvedQuizzes.size() >= 10;
    }

    private boolean hasBronzeBadge(ArrayList<SolvedQuiz> solvedQuizzes){
        return solvedQuizzes.size() >= 5;
    }
}
