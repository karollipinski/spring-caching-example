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

    private PizzaService service;

    @GetMapping(value = "/{name}")
    public ResponseEntity<Pizza> getPizzaByName(@PathVariable String name) {
        log.info("Search pizza by name {}", name);
        return ResponseEntity.ok(service.findPizza(name));
    }

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Pizza pizza) {
        log.info("Delete from cache pizza {}", pizza);
        service.cacheevict(pizza);
        return ResponseEntity.ok()
                             .build();
    }

    @DeleteMapping(value = "/all")
    public ResponseEntity deleteAll() {
        log.info("Delete from cache all pizza ");
        service.cacheevictAll();
        return ResponseEntity.ok()
                             .build();
    }

}
