package hillel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {
    @Autowired
    private ApplicationContext applicationContext;

    //  http://localhost:8080/create?name=appcache
    @GetMapping("/create")
    boolean create(@RequestParam(name = "name") String cacheName){
        CacheService cacheService = applicationContext.getBean(CacheService.class);
        cacheService.create(cacheName);
        return cacheService.isCacheExist(cacheName);
    }

    //  http://localhost:8080/isexist?name=appcache
    @GetMapping("/isexist")
    boolean isCacheExist(@RequestParam("name") String cacheName) {
        CacheService cacheService = applicationContext.getBean(CacheService.class);
        return cacheService.isCacheExist(cacheName);
    }

    @GetMapping("/put")
    boolean put(@RequestParam(name = "name") String cacheName, @RequestParam String key, @RequestParam String value) {
        CacheService cacheService = applicationContext.getBean(CacheService.class);
        return cacheService.put(cacheName, key, value);
    }

}