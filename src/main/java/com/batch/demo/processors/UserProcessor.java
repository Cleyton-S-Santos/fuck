package com.batch.demo.processors;

import com.batch.demo.domain.UserDomain;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class UserProcessor implements ItemProcessor<UserDomain, UserDomain> {



    @Override
    public UserDomain process(UserDomain item) throws Exception {
        log.info("PROCESSANDO USUARIO: {}", item);
        return item;
    }
}
