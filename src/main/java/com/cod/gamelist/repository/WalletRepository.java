package com.cod.gamelist.repository;

import com.cod.gamelist.model.Inventory;
import com.cod.gamelist.model.Wallet;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface WalletRepository extends JpaRepository<Wallet, String> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Wallet save(Wallet wallet);
}
