package com.example.mystuff.backend.controller;

import com.example.mystuff.backend.entity.Item;
import com.example.mystuff.backend.repo.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/v1/items")
public class ItemController {

    private static Logger log = LoggerFactory.getLogger(ItemController.class);

    private ItemRepository itemRepository;

    // Constructor Injection
    private ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping
    public @ResponseBody
    Item addNewItem(@RequestBody Item item) {
        item.setId(null);
        return itemRepository.save(item);
    }

    /*

        //@ResponseStatus(code = HttpStatus.CREATED)
        @PostMapping
        public ResponseEntity<Object> createItem(@RequestBody Item item){
            Item savedItem = itemRepository.save(item);
            URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(savedItem.getId()).toUri();
           return ResponseEntity.created(location).build();
        }

    */
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Item> getAllItems() {
        // This returns a JSON or XML with the Items
        return itemRepository.findAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Item getItemById(@PathVariable Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (!item.isPresent()) {
            log.warn("Method getItemById: id-" + id + " not found");
            throw new ItemNotFoundExeption();
        }
        return item.get();
    }


    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (!item.isPresent()) {
            log.warn("Method deleteItem: id-" + id + " not found");
            throw new ItemNotFoundExeption();
        }
        itemRepository.deleteById(id);
    }


    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Item not found")
    private class ItemNotFoundExeption extends RuntimeException {
        public ItemNotFoundExeption() {
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateItem(@RequestBody Item item, @PathVariable Long id) {
        Optional<Item> itemOptional = itemRepository.findById(id);
        if (!itemOptional.isPresent()) {
           return ResponseEntity.notFound().build();
        }
        item.setId(id);
        itemRepository.save(item);
        return ResponseEntity.ok().build();
    }
}
