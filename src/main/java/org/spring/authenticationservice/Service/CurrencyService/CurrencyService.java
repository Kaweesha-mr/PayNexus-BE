package org.spring.authenticationservice.Service.CurrencyService;

import org.spring.authenticationservice.DTO.Currency.ReloadDto;
import org.spring.authenticationservice.model.User;
import org.spring.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CurrencyService {

    @Autowired
    private UserRepository userRepository;

    public Float ReloadUser(ReloadDto reloadDto) {
        User user = userRepository.findByEmail(reloadDto.getUserID()).orElseThrow(() -> new RuntimeException("User not found!"));
        float newBalance = user.getBalance() + reloadDto.getCurrency();
        user.setBalance(newBalance);
        userRepository.save(user);
        return newBalance;
    }

}
