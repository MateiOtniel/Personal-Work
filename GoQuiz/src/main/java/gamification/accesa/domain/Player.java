package gamification.accesa.domain;

public class Player extends Entity<Long>{
    private final String username;

    private final String password;

    private int tokens;

    public Player(String username, String password, int tokens){
        this.username = username;
        this.password = password;
        this.tokens = tokens;
    }

    public String getUsername(){
        return username;
    }


    public int getTokens(){
        return tokens;
    }

    public void setTokens(int tokens){
        this.tokens = tokens;
    }
}
