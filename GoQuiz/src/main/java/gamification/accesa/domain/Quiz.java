package gamification.accesa.domain;

public class Quiz extends Entity<Long>{
    private final Long idPlayer;

    private final int tokens;

    private final String title;

    private final String description;

    private final String answer;

    public Quiz(Long idPlayer, int tokens, String title, String description, String answer){
        this.idPlayer = idPlayer;
        this.tokens = tokens;
        this.title = title;
        this.description = description;
        this.answer = answer;
    }

    public Long getIdPlayer(){
        return idPlayer;
    }

    public int getTokens(){
        return tokens;
    }

    public String getDescription(){
        return description;
    }

    public String getAnswer(){
        return answer;
    }

    public String getTitle(){
        return title;
    }

}
