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
@RequestMapping(value = "/redis/pizza")
public class PizzaRedisController {

    PizzaRedisService pizzaRedisService;

    @GetMapping(value = "/{name}")
    public ResponseEntity<Pizza> findPizzaById(@PathVariable String name) {
        log.info("Searching by ID  : {}", name);
        return ResponseEntity.ok(pizzaRedisService.findPizza(name));
    }

    @PutMapping(value = "/{name}")
    public ResponseEntity update(@PathVariable("name") String name, @RequestBody Pizza pizza) {
        log.info("Update Pizza with name {}", name);

        return ResponseEntity.ok(pizzaRedisService.updatePizza(name, pizza));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Pizza pizza) {
        log.info("Create pizza {}", pizza);
        return ResponseEntity.ok(pizzaRedisService.addPizza(pizza));
    }
}
