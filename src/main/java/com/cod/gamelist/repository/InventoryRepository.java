package com.cod.gamelist.repository;

import com.cod.gamelist.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, String> {

//    @Query(" where externalId = :externalId and type = :type")
    List<Inventory> findByExternalIdAndType(@Param("externalId") String externalId,
                                      @Param("type") String type);

}
