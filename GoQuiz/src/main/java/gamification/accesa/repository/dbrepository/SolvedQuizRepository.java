package gamification.accesa.repository.dbrepository;

import gamification.accesa.domain.Player;
import gamification.accesa.domain.SolvedQuiz;
import gamification.accesa.repository.irepository.ISolvedQuizRepository;
import gamification.accesa.repository.utils.JDBCUtils;
import gamification.accesa.utils.PropertiesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Properties;

public class SolvedQuizRepository implements ISolvedQuizRepository{
    private final JDBCUtils jdbcUtils;

    private static ISolvedQuizRepository instance = null;

    private static final Logger logger = LogManager.getLogger();

    private SolvedQuizRepository(Properties properties){
        jdbcUtils = new JDBCUtils(properties);
        logger.info("SolvedQuizRepository created");
    }

    public static ISolvedQuizRepository getInstance(){
        if(instance == null){
            try{
                instance = new SolvedQuizRepository(PropertiesUtils.getProperties());
            } catch(FileNotFoundException e){
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    @Override
    public SolvedQuiz findOne(Long id){
        return null;
    }

    @Override
    public Iterable<SolvedQuiz> findAll(){
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        ArrayList<SolvedQuiz> solvedQuizzes = new ArrayList<>();
        try(var statement = connection
                .prepareStatement("select * from SolvedQuizzes")){
            var resultSet = statement.executeQuery();
            while(resultSet.next()){
                var solvedQuiz = new SolvedQuiz(resultSet.getLong("idPlayer"),
                        resultSet.getLong("idQuiz"));
                solvedQuiz.setId(resultSet.getLong("id"));
                solvedQuizzes.add(solvedQuiz);
            }
        } catch(Exception e){
            logger.error(e);
            System.out.println("Error DB " + e);
        }
        logger.traceExit(solvedQuizzes);
        return solvedQuizzes;
    }

    @Override
    public void add(SolvedQuiz solvedQuiz){
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        try(var statement = connection.prepareStatement("insert into SolvedQuizzes (idPlayer, idQuiz) values (?, ?)")){
            statement.setLong(1, solvedQuiz.getIdPlayer());
            statement.setLong(2, solvedQuiz.getIdQuiz());
            statement.executeUpdate();
        } catch(Exception e){
            logger.error(e);
            System.out.println("Error DB " + e);
        }
    }

    @Override
    public ArrayList<SolvedQuiz> getSolvedQuizzesByPlayer(Player player){
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        ArrayList<SolvedQuiz> solvedQuizzes = new ArrayList<>();
        try(var statement = connection
                .prepareStatement("select * from SolvedQuizzes where idPlayer = ?")){
            statement.setLong(1, player.getId());
            var resultSet = statement.executeQuery();
            while(resultSet.next()){
                var solvedQuiz = new SolvedQuiz(resultSet.getLong("idPlayer"),
                        resultSet.getLong("idQuiz"));
                solvedQuiz.setId(resultSet.getLong("id"));
                solvedQuizzes.add(solvedQuiz);
            }
        } catch(Exception e){
            logger.error(e);
            System.out.println("Error DB " + e);
        }
        logger.traceExit(solvedQuizzes);
        return solvedQuizzes;
    }
}
