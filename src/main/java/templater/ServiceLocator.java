package templater;

import java.util.HashMap;
import java.util.Map;
// place where I could register all my services
public class ServiceLocator {
    private static final Map<Class<?>, Object> LOCATOR = new HashMap<>();

    public static void register(Class<?> clazz, Object service) {
        LOCATOR.put(clazz, service);

    }
    public static <T> T get(Class<?> clazz){
        return (T) clazz.cast(LOCATOR.get(clazz));
    }

}
