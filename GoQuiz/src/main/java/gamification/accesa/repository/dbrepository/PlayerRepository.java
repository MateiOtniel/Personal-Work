package gamification.accesa.repository.dbrepository;

import gamification.accesa.domain.Player;
import gamification.accesa.repository.irepository.IPlayerRepository;
import gamification.accesa.repository.utils.JDBCUtils;
import gamification.accesa.utils.PropertiesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

public class PlayerRepository implements IPlayerRepository{

    private final JDBCUtils jdbcUtils;

    private static IPlayerRepository instance = null;

    private static final Logger logger = LogManager.getLogger();

    private PlayerRepository(Properties properties){
        jdbcUtils = new JDBCUtils(properties);
        logger.info("PlayerRepository created");
    }

    public static IPlayerRepository getInstance(){
        if(instance == null){
            try{
                instance = new PlayerRepository(PropertiesUtils.getProperties());
            } catch(FileNotFoundException e){
                throw new RuntimeException(e);
            }
        }
        return instance;
    }

    @Override
    public Player findOne(Long id){
        return null;
    }

    @Override
    public Iterable<Player> findAll(){
        return null;
    }

    @Override
    public Player findByUsernameAndPassword(String username, String password){
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        Player player = null;
        try(PreparedStatement statement = connection.prepareStatement("select * from Players where username = ?" +
                "and password = ?")){
            statement.setString(1, username);
            statement.setString(2, password);
            var resultSet = statement.executeQuery();
            if(resultSet.next()){
                player = new Player(resultSet.getString("username"), resultSet.getString("password"),
                        resultSet.getInt("tokens"));
                player.setId(resultSet.getLong("id"));
            }
        } catch(Exception e){
            logger.error(e);
            System.out.println("Error DB " + e);
        }

        logger.traceExit();
        return player;
    }

    @Override
    public void update(Player player){
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        try(PreparedStatement statement = connection.prepareStatement("update Players set tokens = ? where id = ?")){
            statement.setInt(1, player.getTokens());
            statement.setLong(2, player.getId());
            statement.executeUpdate();
        } catch(Exception e){
            logger.error(e);
            System.out.println("Error DB " + e);
        }
    }

    @Override
    public int size(){
        logger.traceEntry();
        Connection connection = jdbcUtils.getConnection();
        try(PreparedStatement statement = connection.prepareStatement("select count(*) as [SIZE] from Players")){
            var resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("SIZE");
            }
        } catch(Exception e){
            logger.error(e);
            System.out.println("Error DB " + e);
        }
        return 0;
    }
}
