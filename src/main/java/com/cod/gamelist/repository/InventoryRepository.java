package com.cod.gamelist.repository;

import com.cod.gamelist.model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
    List<Inventory> findByExternalIdAndType(@Param("externalId") String externalId,
                                            @Param("type") String type);

    Page<Inventory> findAllByQuantityGreaterThan(Integer minx, PageRequest pageRequest);

}
