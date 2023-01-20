package com.cod.gamelist;

import com.cod.gamelist.model.Inventory;
import com.cod.gamelist.model.SupakiResult;
import com.cod.gamelist.repository.InventoryRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
@Slf4j
public class DataLoader {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private InventoryRepository inventoryRepository;


    public <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType) {
        return restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
    }

    @PostConstruct
    public void init() {
        try {
            log.info("Starting Data loader process");
            List<SupakiResult> responseObjects = exchangeAsList("https://supaki.free.beeceptor.com/items",
                    new ParameterizedTypeReference<>() {
                    });
            responseObjects.forEach(System.out::println);
            responseObjects.forEach(result -> {
                List<Inventory> presentInventory = inventoryRepository.findByExternalIdAndType(result.getId(), "SUPAKI-EXTERNAL");
                if (presentInventory == null || presentInventory.size() == 0) {
                    Inventory i = new Inventory(
                            result.getId(),
                            result.getName(),
                            result.getPrice().floatValue(),
                            "SALE",
                            "SUPAKI-EXTERNAL",
                            result.getId()
                    );
                    inventoryRepository.save(i);
                } else {
                    log.info("inventory already present ");
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}