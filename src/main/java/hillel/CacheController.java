package hillel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CacheController {
    @Autowired
    private CacheService cacheService;

    //  http://localhost:8080/create?name=appcache
    @GetMapping("/create")
    boolean create(@RequestParam("name") String cacheName){
        cacheService.create(cacheName);
        return cacheService.isCacheExist(cacheName);
    }

    //  http://localhost:8080/isexist?name=appcache
    @GetMapping("/isexist")
    boolean isCacheExist(@RequestParam("name") String cacheName) {
        return cacheService.isCacheExist(cacheName);
    }

    //  http://localhost:8080/put?name=appcache&key=data&value=1234567890
    @GetMapping("/put")
    boolean put(@RequestParam("name") String cacheName, @RequestParam String key, @RequestParam String value) {
        return cacheService.put(cacheName, key, value);
    }

    //  http://localhost:8080/get?name=appcache&key=data
    @GetMapping("/get")
    String get(@RequestParam("name") String cacheName, @RequestParam String key){
        return Optional.ofNullable(cacheService.get(cacheName, key)).orElse("null");
    }

    //  http://localhost:8080/clear
    @GetMapping("/clearall")
    String clear(){
        cacheService.clear();
        return "ok";
    }

    @GetMapping("/clear")
    String clear(@RequestParam("name") String cacheName){
        cacheService.clear(cacheName);
        return "ok";
    }





}
