package com.batch.demo.repositories;

import com.batch.demo.domain.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserDomain, Long> {
}
