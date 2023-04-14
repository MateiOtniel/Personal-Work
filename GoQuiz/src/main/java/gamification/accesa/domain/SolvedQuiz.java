package gamification.accesa.domain;

public class SolvedQuiz extends Entity<Long>{
    private final Long idPlayer;

    private final Long idQuiz;

    public SolvedQuiz(Long idPlayer, Long idQuiz){
        this.idPlayer = idPlayer;
        this.idQuiz = idQuiz;
    }

    public Long getIdPlayer(){
        return idPlayer;
    }

    public Long getIdQuiz(){
        return idQuiz;
    }

}
