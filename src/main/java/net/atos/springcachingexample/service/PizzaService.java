package net.atos.springcachingexample.service;

import lombok.extern.slf4j.Slf4j;
import net.atos.springcachingexample.model.Pizza;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PizzaService {
    private static Map<String, Pizza> PIZZA = new HashMap<>();

    @PostConstruct
    public void initData() {

        Pizza pizza = new Pizza();
        pizza.setName("Margherita");
        pizza.setIngredients(Arrays.asList("sos", "ser mozzarella", "pieczarki", "oregano"));
        pizza.setSauce("sos czosnkowy");
        pizza.setPrice(22L);
        pizza.setCurrency("PLN");

        PIZZA.put(pizza.getName(), pizza);

        pizza = new Pizza();
        pizza.setName("Napoli");
        pizza.setIngredients(Arrays.asList("sos", "ser mozzarella", "oliwki", "oregano"));
        pizza.setSauce("sos czosnkowy");
        pizza.setPrice(20L);
        pizza.setCurrency("PLN");

        PIZZA.put(pizza.getName(), pizza);

        pizza = new Pizza();
        pizza.setName("Peperoni");
        pizza.setIngredients(Arrays.asList("sos", "ser mozzarella", "kiełbaska peperoni", "oregano"));
        pizza.setSauce("sos chili");
        pizza.setPrice(28L);
        pizza.setCurrency("PLN");

        PIZZA.put(pizza.getName(), pizza);

        pizza = new Pizza();
        pizza.setName("Mięsna");
        pizza.setIngredients(Arrays.asList("sos", "ser mozzarella", "szynka", "bekon", "oregano"));
        pizza.setSauce("kethup");
        pizza.setPrice(25L);
        pizza.setCurrency("PLN");

        PIZZA.put(pizza.getName(), pizza);

        pizza = new Pizza();
        pizza.setName("Hawajska");
        pizza.setIngredients(Arrays.asList("sos", "ser mozzarella", "szynka", "ananas", "oregano"));
        pizza.setSauce("sos słodko-kwaśny");
        pizza.setPrice(24L);
        pizza.setCurrency("PLN");

        PIZZA.put(pizza.getName(), pizza);
    }

    @Cacheable(value = "pizzas", key = "#name")
    public Pizza findPizza(String name) {
        Assert.notNull(name, "The Pizza'a name must bot be null");

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("Get pizza name : {}", name);
        return PIZZA.get(name);
    }

    @CacheEvict(value = "pizzas", key = "#pizza.name")
    public void cacheevict(Pizza pizza) {
        log.info("CacheEvict pizza {}", pizza);
    }

    @CacheEvict(value = "pizzas", allEntries = true)
    public void cacheevictAll() {
        log.info("CacheEvict allEntries pizza ");
    }

}