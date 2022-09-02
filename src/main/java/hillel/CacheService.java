package hillel;

public interface CacheService {
    void create(String cacheName);
    boolean put(String cacheName, String key, String value);
    String get(String cacheName, String key);
    void clear();
    void clear(String cacheName);
    boolean isCacheExist(String cacheName);
}
