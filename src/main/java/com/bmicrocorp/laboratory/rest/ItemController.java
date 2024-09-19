package com.bmicrocorp.laboratory.rest;

import java.util.List;
import java.util.Optional;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bmicrocorp.laboratory.model.entities.Item;
import com.bmicrocorp.laboratory.repository.ItemRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ItemController {
    
    private static final Long TIMEOUT = 1000*100L;

    private final Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    ItemRepository repository;

    @GetMapping("/item/{id}")
	public Mono<Item> get(@PathVariable Long id) {
        Optional<Item> i = repository.findById(id);
        log.info("i: " + (i.isPresent() ? i.get().getTitle(): " void"));
        return Mono.justOrEmpty(repository.findById(id));
	}

    @GetMapping("/items")
    public Flux<Item> getList() {
        return Flux.fromIterable(repository.findAll())
            .timeout(Duration.ofMillis(TIMEOUT))
            .doOnError(err -> log.error("Impossible to reach items flux", err));
    }

    @GetMapping("/items/range/{min}/{max}")
    public Flux<Item> getRangedItems(@PathVariable double min, @PathVariable double max) {
        List<Item> l = repository.findByPriceRange(min, max);
        log.info("Got list : " + l.size());
        return Flux.fromStream(repository.findByPriceRange(min, max).stream())
            .timeout(Duration.ofMillis(TIMEOUT))
            .doOnError(err -> log.error("Impossible to reach items flux by range", err));
    }
}
