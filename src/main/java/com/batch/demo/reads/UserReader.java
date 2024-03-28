package com.batch.demo.reads;

import com.batch.demo.domain.UserDomain;
import com.batch.demo.repositories.UserRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;

@Configuration
public class UserReader implements ItemReader<UserDomain> {

    @Autowired
    private UserRepository userRepository;

    private Iterator<UserDomain> userIterator;

    @Override
    public UserDomain read() throws Exception {
        if (userIterator == null) {
            userIterator = userRepository.findAll().iterator();
        }

        return userIterator.hasNext() ? userIterator.next() : null;
    }
}
