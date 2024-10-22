package com.programming.configure_multiple_databases.user.repository;

import com.programming.configure_multiple_databases.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
