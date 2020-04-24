package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE LOWER(u.lastName) = LOWER(:lastName)")
     List<User> findByLastName(@Param("lastName") String lastName);
}
