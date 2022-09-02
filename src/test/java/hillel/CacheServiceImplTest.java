package hillel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheServiceImplTest {

    @Test
    void create() {
        CacheService cacheService = new CacheServiceImpl();
        cacheService.create("ApplicationCache");
        cacheService.create("ResourceCache");
        cacheService.create("ApplicationCache");
    }

    @Test
    void put() {
        CacheService cacheService = new CacheServiceImpl();
        cacheService.create("ApplicationCache");
        assertTrue(cacheService.put("ApplicationCache", "serviceData", "1234567890"));
        assertFalse(cacheService.put("UnknownCache", "serviceData", "1234567890"));
    }

    @Test
    void get() {
        CacheService cacheService = new CacheServiceImpl();
        cacheService.create("ApplicationCache");
        assertNull(cacheService.get("ApplicationCache", "serviceData"));
        cacheService.put("ApplicationCache", "serviceData", "1234567890");
        assertEquals("1234567890", cacheService.get("ApplicationCache", "serviceData"));
    }

    @Test
    void clearCacheSet(){
        CacheService cacheService = new CacheServiceImpl();
        cacheService.create("ApplicationCache");
        cacheService.put("ApplicationCache", "serviceData", "1234567890");
        cacheService.create("ResourceCache");
        cacheService.put("ResourceCache", "data1", "abcd");
        cacheService.put("ResourceCache", "data2", "abcd");
        cacheService.clear();
    }


}