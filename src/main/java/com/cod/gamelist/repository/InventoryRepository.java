package com.cod.gamelist.repository;

import com.cod.gamelist.model.Inventory;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
    List<Inventory> findByExternalIdAndType(@Param("externalId") String externalId,
                                            @Param("type") String type);

    Page<Inventory> findAllByStatusNot(String status, PageRequest pageRequest);

    /*
    * pessimistic write locks are slow
    * might as well go ahead with redis locks ?
    * or optimistic locking given scenario of conflict is low.
    * */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Inventory save(Inventory inventory);

    /*
     * Cannot update a single unit of inventory while being processed or read.
     * might have edge cases of hot inventory reads
     * */
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Inventory> findById(String id);

}
