package gamification.accesa.service.utils;

import gamification.accesa.repository.dbrepository.PlayerRepository;
import gamification.accesa.repository.dbrepository.QuizRepository;
import gamification.accesa.repository.dbrepository.SolvedQuizRepository;
import gamification.accesa.repository.irepository.IPlayerRepository;
import gamification.accesa.repository.irepository.IQuizRepository;
import gamification.accesa.repository.irepository.ISolvedQuizRepository;
import gamification.accesa.service.*;

public class ServiceUtils{
    public static IService getLoginControllerService() {
        IPlayerRepository playerRepository = PlayerRepository.getInstance();
        return new LoginControllerService(playerRepository);
    }

    public static IService getMainControllerService() {
        IPlayerRepository playerRepository = PlayerRepository.getInstance();
        IQuizRepository questRepository = QuizRepository.getInstance();
        ISolvedQuizRepository solvedQuizRepository =
                SolvedQuizRepository.getInstance();
        return new MainControllerService(playerRepository, questRepository,
                solvedQuizRepository);
    }

    public static IService getQuizControllerService() {
        ISolvedQuizRepository solvedQuizRepository =
                SolvedQuizRepository.getInstance();
        IPlayerRepository playerRepository = PlayerRepository.getInstance();
        return new QuizControllerService(solvedQuizRepository, playerRepository);
    }

    public static IService getBadgeControllerService(){
        IQuizRepository questRepository = QuizRepository.getInstance();
        ISolvedQuizRepository solvedQuizRepository =
                SolvedQuizRepository.getInstance();
        return new BadgeControllerService(questRepository,
                solvedQuizRepository);
    }
}
