package com.example.mystuff.backend.repo;

import com.example.mystuff.backend.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ItemRepository extends JpaRepository<Item, Long> {

    public Item findByNameAndDescription (String name, String description);

}
