package net.atos.springcachingexample;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.atos.springcachingexample.model.Pizza;
import net.atos.springcachingexample.service.PizzaRedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/pizza/redis/")
public class PizzaRedisController {

    private PizzaRedisService service;

    @GetMapping(value = "/{name}")
    public ResponseEntity<Pizza> getPizzaByName(@PathVariable String name) {
        log.info("Search pizza by name {}", name);
        return ResponseEntity.ok(service.findPizza(name));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Pizza pizza) {
        log.info("Create new pizza {}", pizza);
        return ResponseEntity.ok(service.addPizza(pizza));
    }

}
