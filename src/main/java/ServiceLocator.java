package templater;

import dao.DataSource;
import dao.jdbc.JdbcUserDao;
import dao.UserDao;
import service.DefaultUserService;
import service.UserService;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;


public class ServiceLocator {
    private static final Map<Class<?>, Object> LOCATOR = initDefaultDependencies();

    public static void register(Class<?> clazz, Object service) {
        LOCATOR.put(clazz, service);

    }
    public static Map<Class<?>, Object> initDefaultDependencies() {
       Map<Class<?>, Object> map = new HashMap<>();

        //config dao
        DataSource dataSource = DataSource.getInstance();
        Connection connection = dataSource.getConnection();;

        UserDao userDao = new JdbcUserDao(connection);

        // config services
        UserService userService = new DefaultUserService(userDao);
        map.put(UserService.class, userService);

        return map;

    }
    public static <T> T get(Class<?> clazz){
        return (T) clazz.cast(LOCATOR.get(clazz));
    }

}
