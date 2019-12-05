package net.atos.springcachingexample;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.atos.springcachingexample.model.Pizza;
import net.atos.springcachingexample.service.PizzaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(value = "/pizza")
public class PizzaController {

    PizzaService pizzaService;

    @GetMapping(value = "/{name}")
    public ResponseEntity<Pizza> findPizzaById(@PathVariable String name) {
        log.info("Searching by ID  : {}", name);
        return ResponseEntity.ok(pizzaService.findPizza(name));
    }

    @PutMapping(value = "/{name}")
    public ResponseEntity update(@PathVariable("name") String name, @RequestBody Pizza pizza) {
        log.info("Update Pizza with name {}", name);

        return ResponseEntity.ok(pizzaService.updatePizza(name, pizza));
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Pizza pizza) {
        log.info("Create pizza {}", pizza);
        return ResponseEntity.ok(pizzaService.addPizza(pizza));
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity delete(@PathVariable("name") String name) {
        log.info("Delete Pizza with name {}", name);
        Pizza pizza = pizzaService.findPizza(name);
        pizzaService.deletePizza(pizza);
        return ResponseEntity.noContent()
                             .build();
    }

}
