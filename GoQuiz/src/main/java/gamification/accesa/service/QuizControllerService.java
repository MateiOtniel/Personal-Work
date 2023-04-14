package gamification.accesa.service;

import gamification.accesa.domain.Player;
import gamification.accesa.domain.Quiz;
import gamification.accesa.domain.SolvedQuiz;
import gamification.accesa.repository.irepository.IPlayerRepository;
import gamification.accesa.repository.irepository.ISolvedQuizRepository;

public class QuizControllerService implements IService{
    private final ISolvedQuizRepository solvedQuizRepository;

    private final IPlayerRepository playerRepository;
    public QuizControllerService(ISolvedQuizRepository solvedQuizRepository,
                                 IPlayerRepository playerRepository){
        this.solvedQuizRepository = solvedQuizRepository;
        this.playerRepository = playerRepository;
    }

    public boolean checkAnswer(Quiz quiz, String answer){
        return quiz.getAnswer().equals(answer.strip());
    }

    public void updatePlayer(Player player, int tokens){
        player.setTokens(player.getTokens() + tokens);
        playerRepository.update(player);
    }

    public void addSolvedQuiz(Long idPlayer, Long idQuiz){
        solvedQuizRepository.add(new SolvedQuiz(idPlayer, idQuiz));
    }
}
