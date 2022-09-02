package hillel;

import java.util.HashMap;
import java.util.Map;

public class CacheServiceImpl implements CacheService {

    private Map<String, Map<String, String>> cacheset;

    CacheServiceImpl(){
        cacheset = new HashMap<>();
    }

    /**
     * Создать кэш с заданным именем<br/>
     * В случае, если кэш с заданным именем уже существует, то он будет пересоздан пустым
     * @param cacheName Имя кэша
     */
    public void create(String cacheName){
        cacheset.put(cacheName, new HashMap<>());
    }

    /**
     * Положить в заданный кэш значение по ключу
     * @param cacheName Имя кэша
     * @param key Ключ
     * @param value Значение ключа
     * @return true - значение положено в кэш; false - заданный кэш отстутствует
     */
    public boolean put(String cacheName, String key, String value){
        Map<String, String> cache = cacheset.get(cacheName);
        if (cache != null) {
            cache.put(key, value);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Получить значение из заданного кэша по ключу
     * @param cacheName Имя кэша
     * @param key Ключ
     * @return Значение, если заданный ключ существует, либо null
     */
    public String get(String cacheName, String key) {
        Map<String, String> cache = cacheset.get(cacheName);
        if (cache != null) {
            return cache.get(key);
        } else {
            return null;
        }
    }

    /**
     * Очистка всего набора кашей
     */
    public void clear() {
        cacheset.values()
                .stream()
                .forEach(e->e.clear());
    }

    /**
     * Очистка заданного кэша
     * @param cacheName Имя кэша
     */
    public void clear(String cacheName) {
        Map<String, String> cache = cacheset.get(cacheName);
        if (cache != null) cache.clear();
    }

    /**
     * Проверка наличия заданного кэша
     * @param cacheName Имя кэша
     * @return true/false
     */
    public boolean isCacheExist(String cacheName) {
        return (cacheset.get(cacheName) != null) ? true : false;
    }


}
