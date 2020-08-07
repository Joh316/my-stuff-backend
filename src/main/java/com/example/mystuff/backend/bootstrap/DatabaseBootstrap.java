package com.example.mystuff.backend.bootstrap;

import com.example.mystuff.backend.entity.Item;
import com.example.mystuff.backend.repo.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseBootstrap implements InitializingBean {

    @Autowired
    private ItemRepository itemRepository;

    private static Logger log = LoggerFactory.getLogger(DatabaseBootstrap.class);


    @Override
    public void afterPropertiesSet() throws Exception {

        log.info("DatabaseBootstrap#afterPropertiesSet()");

        Item item = new Item(null, "Zange", 2, "Werkzeugkasten", "Werkzeug", null);

        if (itemRepository.findByNameAndDescription("Zange", "Werkzeug") == null) {
            itemRepository.save(item);
            log.info("Zange hinzugefuegt");
        }

        if (itemRepository.findByNameAndDescription("Hammer", "Werkzeug") == null) {
            item.setId(null);
            item.setName("Hammer");
            item.setAmount(1);
            item.setLocation("Werkzeugkasten");
            item.setDescription("Werkzeug");
            itemRepository.save(item);
            log.info("Hammer hinzugefuegt");
        }

        if (itemRepository.findByNameAndDescription("Steckschluessel", "Werkzeug") == null) {
            item.setId(null);
            item.setName("Steckschluessel");
            item.setAmount(1);
            item.setLocation("Werkzeugkasten");
            item.setDescription("Werkzeug");
            itemRepository.save(item);
            log.info("Steckschluessel hinzugefuegt");
        }

        if (itemRepository.findByNameAndDescription("Knarre", "Werkzeug") == null) {
            item.setId(null);
            item.setName("Knarre");
            item.setAmount(1);
            item.setLocation("Werkzeugkasten");
            item.setDescription("Werkzeug");
            itemRepository.save(item);
            log.info("Knarre hinzugefuegt");
        }

    }
}
