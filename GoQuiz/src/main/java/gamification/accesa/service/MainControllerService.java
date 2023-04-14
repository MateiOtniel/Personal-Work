package gamification.accesa.service;

import gamification.accesa.domain.Player;
import gamification.accesa.domain.Quiz;
import gamification.accesa.domain.SolvedQuiz;
import gamification.accesa.repository.irepository.IPlayerRepository;
import gamification.accesa.repository.irepository.IQuizRepository;
import gamification.accesa.repository.irepository.ISolvedQuizRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MainControllerService implements IService{

    private final IPlayerRepository playerRepository;

    private final IQuizRepository quizRepository;

    private final ISolvedQuizRepository solvedQuizRepository;

    public MainControllerService(IPlayerRepository playerRepository, IQuizRepository quizRepository, ISolvedQuizRepository solvedQuizRepository){
        this.playerRepository = playerRepository;
        this.quizRepository = quizRepository;
        this.solvedQuizRepository = solvedQuizRepository;
    }

    public Iterable<Quiz> getAllQuizzes(Long currentPlayerId){
        ArrayList<Quiz> quizzes = (ArrayList<Quiz>) quizRepository.findAll();
        ArrayList<SolvedQuiz> solvedQuizzes = (ArrayList<SolvedQuiz>) solvedQuizRepository.findAll();
        quizzes.removeIf(quiz ->
                solvedQuizzes.stream().anyMatch(solvedQuiz ->
                        solvedQuiz.getIdPlayer().equals(currentPlayerId)
                                && solvedQuiz.getIdQuiz().equals(quiz.getId())));
        System.out.println(Arrays.toString(quizzes.toArray()));
        return quizzes;
    }

    public int getRank(Player player){
        //luam toate quiz-urile rezolvate
        ArrayList<SolvedQuiz> solvedQuizzes =
                (ArrayList<SolvedQuiz>) solvedQuizRepository.findAll();
        //tinem minte cate tokenuri a castigat fiecare player
        HashMap<Long, Integer> totalPlayerTokens = new HashMap<>();
        for(SolvedQuiz solvedQuiz : solvedQuizzes){
            //daca nu exista playerul in hashmap, il adaugam
            if(!totalPlayerTokens.containsKey(solvedQuiz.getIdPlayer())){
                totalPlayerTokens.put(solvedQuiz.getIdPlayer(), 0);
            }
            totalPlayerTokens.put(solvedQuiz.getIdPlayer(),
                    totalPlayerTokens.get(solvedQuiz.getIdPlayer()) +
                            quizRepository.findOne(solvedQuiz.getIdQuiz()).getTokens());
        }
        int rank = 1;
        if(!totalPlayerTokens.containsKey(player.getId()))
            return playerRepository.size();
        for(Long idPlayer : totalPlayerTokens.keySet()){
            if(totalPlayerTokens.get(idPlayer) > totalPlayerTokens.get(player.getId())){
                rank++;
            }
        }
        return rank;
    }

    public void addQuiz(Long idPlayer, int tokens, String title, String description,
                        String answer){
        Quiz quiz = new Quiz(idPlayer, tokens, title, description, answer);
        quizRepository.save(quiz);
    }

    public void updatePlayerTokens(Player player, int tokens){
        player.setTokens(player.getTokens() + tokens);
        playerRepository.update(player);
    }
}
