package org.spring.authenticationservice.Service.CurrencyService;

import org.spring.authenticationservice.DTO.Currency.ReloadDto;
import org.spring.authenticationservice.Service.JwtService;
import org.spring.authenticationservice.model.User;
import org.spring.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static org.spring.authenticationservice.Utils.SecurityUtils.getUsername;

@Service
public class CurrencyService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    // Evict cache when balance is reloaded
    //@CacheEvict(value = "userBalance", key = "#reloadDto.getUserID()")
    public Float ReloadUser(ReloadDto reloadDto) {
        User user = userRepository.findByEmail(reloadDto.getUserID()).orElseThrow(() -> new RuntimeException("User not found!"));
        float newBalance = user.getBalance() + reloadDto.getCurrency();
        user.setBalance(newBalance);
        userRepository.save(user);
        return newBalance;
    }

    // Get balance with caching
    //@Cacheable(value = "userBalance", key = "#reloadDto.getUserID()")
    public Float GetBalance() {
        User user = userRepository.findByEmail(getUsername()).orElseThrow(() -> new RuntimeException("User not found!"));
        return user.getBalance();
    }
}
