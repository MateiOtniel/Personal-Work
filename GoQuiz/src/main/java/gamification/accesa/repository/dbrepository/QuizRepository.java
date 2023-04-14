package gamification.accesa.repository.dbrepository;

import gamification.accesa.domain.Player;
import gamification.accesa.domain.Quiz;
import gamification.accesa.repository.irepository.IQuizRepository;
import gamification.accesa.repository.utils.JDBCUtils;
import gamification.accesa.utils.PropertiesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Properties;

public class QuizRepository implements IQuizRepository{
    private final JDBCUtils jdbcUtils;

    private static IQuizRepository instance = null;

    private static final Logger logger = LogManager.getLogger();

    private QuizRepository(Properties properties){
        jdbcUtils = new JDBCUtils(properties);
        logger.info("QuestRepository created");
    }

    public static IQuizRepository getInstance(){
        if(instance == null){
            try{
                instance = new QuizRepository(PropertiesUtils.getProperties());
            } catch(FileNotFoundException e){
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    @Override
    public Quiz findOne(Long id){
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        Quiz quiz = null;
        try(var statement = connection
                .prepareStatement("select * from Quizzes where id = ?")){
            statement.setLong(1, id);
            var resultSet = statement.executeQuery();
            if(resultSet.next()){
                quiz = new Quiz(resultSet.getLong("idPlayer"),
                        resultSet.getInt( "tokens"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("answer"));
                quiz.setId(resultSet.getLong("id"));
            }
        } catch(Exception e){
            logger.error(e);
            System.out.println("Error DB " + e);
        }
        return quiz;
    }

    @Override
    public Iterable<Quiz> findAll(){
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        ArrayList<Quiz> quizzes = new ArrayList<>();
        try(var statement = connection
                .prepareStatement("select * from Quizzes")){
            var resultSet = statement.executeQuery();
            while(resultSet.next()){
                var quiz = new Quiz(resultSet.getLong("idPlayer"),
                        resultSet.getInt( "tokens"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("answer"));
                quiz.setId(resultSet.getLong("id"));
                quizzes.add(quiz);
            }
        } catch(Exception e){
            logger.error(e);
            System.out.println("Error DB " + e);
        }
        return quizzes;
    }

    @Override
    public void save(Quiz quiz){
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        try(var statement = connection
                .prepareStatement("insert into Quizzes (idPlayer, tokens, title," +
                        "description, answer) values (?, ?, ?, ?, ?)")){
            statement.setLong(1, quiz.getIdPlayer());
            statement.setInt(2, quiz.getTokens());
            statement.setString(3, quiz.getTitle());
            statement.setString(4, quiz.getDescription());
            statement.setString(5, quiz.getAnswer());
            statement.executeUpdate();
        } catch(Exception e){
            logger.error(e);
            System.out.println("Error DB " + e);
        }
    }

    @Override
    public ArrayList<Quiz> getQuizzesByPlayer(Player player){
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        ArrayList<Quiz> quizzes = new ArrayList<>();
        try(var statement = connection
                .prepareStatement("select * from Quizzes where idPlayer = ?")){
            statement.setLong(1, player.getId());
            var resultSet = statement.executeQuery();
            while(resultSet.next()){
                var quiz = new Quiz(resultSet.getLong("idPlayer"),
                        resultSet.getInt( "tokens"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("answer"));
                quiz.setId(resultSet.getLong("id"));
                quizzes.add(quiz);
            }
        } catch(Exception e){
            logger.error(e);
            System.out.println("Error DB " + e);
        }
        return quizzes;
    }
}
