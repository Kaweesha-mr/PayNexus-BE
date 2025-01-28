package org.spring.authenticationservice.cache;

import org.jetbrains.annotations.NotNull;
import org.spring.authenticationservice.Utils.SecurityUtils;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

@Component("usernameKeyGenerator")
public class cacheKeyGenerator implements KeyGenerator {

    @NotNull
    @Override
    public Object generate(@NotNull Object target, @NotNull Method method, @NotNull Object... params) {
        return Objects.requireNonNull(SecurityUtils.getUsername());
    }
}
