package com.example.mystuff.backend.bootstrap;

import com.example.mystuff.backend.entity.Item;
import com.example.mystuff.backend.repo.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
class MyApplicationListener
        implements ApplicationListener<ApplicationReadyEvent> {

    private static Logger log = LoggerFactory.getLogger(MyApplicationListener.class);

    private final ItemRepository itemRepository;

    //Constructor Injection
    private MyApplicationListener(ItemRepository itemRepository) {
        log.info("MyApplicationListener#Constructor(ItemRepository itemRepository)");
        this.itemRepository = itemRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        log.info("ApplicationListener#onApplicationEvent()");

        Item item = new Item(null, "Gitarre", 7, "MusikKoffer", "Instrument", null);

        if (itemRepository.findByNameAndDescription("Gitarre", "Instrument") == null) {
            itemRepository.save(item);
            log.info("Gitarre hinzugefuegt");
        }

        if (itemRepository.findByNameAndDescription("Bass", "Instrument") == null) {
            item.setId(null);
            item.setName("Bass");
            item.setAmount(1);
            item.setLocation("BassKoffer");
            item.setDescription("Instrument");
            itemRepository.save(item);
            log.info("Bass hinzugefuegt");
        }
    }
}

