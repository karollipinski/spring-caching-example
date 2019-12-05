package net.atos.springcachingexample.service;

import lombok.extern.slf4j.Slf4j;
import net.atos.springcachingexample.cache.PizzaRedisCache;
import net.atos.springcachingexample.model.Pizza;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PizzaRedisService {
    private static Map<String, Pizza> PIZZA = new HashMap<>();

    private PizzaRedisCache pizzaRedisCache;

    public PizzaRedisService(PizzaRedisCache pizzaRedisCache) {
        this.pizzaRedisCache = pizzaRedisCache;
    }

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

    public Pizza findPizza(String name) {
        Assert.notNull(name, "The Pizza'a name must bot be null");

        // pobranie wartości z cache redis
        return pizzaRedisCache.get(name)
                              .orElseGet(() -> getPizzaFromDB(name));
    }

    private Pizza getPizzaFromDB(String name) {
        log.info("Get pizza name : {}", name);
        Pizza pizza = PIZZA.get(name);
        //dodanie wartości do cache redis
        pizzaRedisCache.put(pizza.getName(), pizza);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pizza;
    }

    public Pizza addPizza(Pizza pizza) {
        log.info("Add new Pizza {}", pizza);
        Pizza newPizza = PIZZA.putIfAbsent(pizza.name, pizza);
        //dodanie wartości do cache redis
        pizzaRedisCache.put(pizza.getName(), pizza);
        return newPizza;
    }
}