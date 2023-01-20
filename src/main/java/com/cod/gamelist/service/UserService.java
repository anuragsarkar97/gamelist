package com.cod.gamelist.service;

import com.cod.gamelist.exception.SQLException;
import com.cod.gamelist.exception.UserPresentException;
import com.cod.gamelist.model.User;
import com.cod.gamelist.model.Wallet;
import com.cod.gamelist.repository.UserRepository;
import com.cod.gamelist.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Transactional
    public User createUser(User user, Float balance) throws SQLException {
        try {
            if (userRepository.findByEmail(user.getEmail()) == null) {
                log.info("Creating new User with email {}", user.getEmail());
                user.setActive(true);
                User userCreated = userRepository.save(user);
                Wallet w = new Wallet();
                w.setUserId(userCreated.getId());
                w.setActive(true);
                w.setBalance(balance);
                log.info("Creating new wallet with id {}", userCreated.getId());
                Wallet walletCreated = walletRepository.save(w);
                userCreated.setWalletId(walletCreated.getId());
                log.info("Updating user with id {}", userCreated.getId());
                return userRepository.save(userCreated);
            } else {
                throw new UserPresentException(user.getEmail());
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }

    @Transactional
    public Boolean deleteUser(String email) throws SQLException {
        try {
            if (userRepository.findByEmail(email) != null) {
                User user = userRepository.findByEmail(email);
                log.info("deleting user with email {}", user.getEmail());
                userRepository.deleteByEmail(email);
                walletRepository.deleteById(user.getWalletId());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new SQLException(e);
        }
    }
}
