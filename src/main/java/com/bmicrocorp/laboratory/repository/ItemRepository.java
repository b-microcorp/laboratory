package com.bmicrocorp.laboratory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bmicrocorp.laboratory.model.entities.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findByTitle(String title);

    Item findById(long id);

    @Query(value = "SELECT i FROM Item i WHERE i.price >= ?1 AND i.price <= ?2")
    List<Item> findByPriceRange(double min, double max);

}
