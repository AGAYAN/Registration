package com.example.focus_group.repositories;

import com.example.focus_group.models.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories extends JpaRepository<user, Integer> {
}
