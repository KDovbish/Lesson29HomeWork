package hillel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Веб-контроллер
 */
@RestController
public class CacheController {
    @Autowired
    private CacheService cacheService;

    private Logger logger = LoggerFactory.getLogger("hillel.hw29log");

    /**
     * Конечная точка /create для создания кэша<br/>
     * Пример использования:<br/>
     * {@literal http://localhost:8080/create?name=appcache}
     * @param cacheName Имя кэша. При запросе через веб отображается в параметр name
     * @return true или false в зависимости о того создан или нет соответствующий кеш
     */
    @GetMapping("/create")
    boolean create(@RequestParam("name") String cacheName){
        logger.info("Endpoint /create  Cache name: " + cacheName);
        cacheService.create(cacheName);
        return cacheService.isCacheExist(cacheName);
    }


    /**
     * Конечная точка /isexist для проверки существования кэша<br/>
     * Пример использования:<br/>
     * {@literal http://localhost:8080/isexist?name=appcache}
     * @param cacheName Имя кэша. При запросе через веб отображается в параметр name
     * @return true или false
     */
    @GetMapping("/isexist")
    boolean isCacheExist(@RequestParam("name") String cacheName) {
        logger.info("Endpoint /isexist  Cache name: " + cacheName);
        return cacheService.isCacheExist(cacheName);
    }


    /**
     * Конечная точка /put - положить данные в кэш<br/>
     * Пример использования:<br/>
     * {@literal http://localhost:8080/put?name=appcache&key=data&value=1234567890}
     * @param cacheName Имя кэша. При запросе через веб отображается в параметр name
     * @param key Ключ
     * @param value Данные
     * @return true/false - удалось или нет положить в кэш
     */
    @GetMapping("/put")
    boolean put(@RequestParam("name") String cacheName, @RequestParam String key, @RequestParam String value) {
        logger.info("Endpoint /put  " + cacheName + " " + key + " " + value);
        return cacheService.put(cacheName, key, value);
    }


    /**
     * Конечная точка /get - взять данные из кэша<br/>
     * Пример использования:<br/>
     * {@literal http://localhost:8080/get?name=appcache&key=data}
     * @param cacheName Имя кэша. При запросе через веб отображается в параметр name
     * @param key Ключ
     * @return Данные
     */
    @GetMapping("/get")
    String get(@RequestParam("name") String cacheName, @RequestParam String key){
        logger.info("Endpoint /get  " + cacheName + " " + key);
        return Optional.ofNullable(cacheService.get(cacheName, key)).orElse("null");
    }


    /**
     * Конечная точка /clearall - очистка всех существующих кэшей<br/>
     * Пример использования:<br/>
     * {@literal http://localhost:8080/clearall}<br/>
     * Примечание: кэши очищаются, но не удаляются
     * @return ok
     */
    @GetMapping("/clearall")
    String clear(){
        logger.info("Endpoint /clearall");
        cacheService.clear();
        return "ok";
    }

    /**
     * Конечная точка /clear - очистка заданного кэша<br/>
     * Пример использования:<br/>
     * {@literal http://localhost:8080/clear?name=appcache}<br/>
     * Примечание: кэш очищается, но не удаляется
     * @return ok
     */
    @GetMapping("/clear")
    String clear(@RequestParam("name") String cacheName){
        logger.info("Endpoint /clear  Cache name: " + cacheName);
        cacheService.clear(cacheName);
        return "ok";
    }

}
