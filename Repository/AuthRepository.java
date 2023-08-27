package com.example.week06d1security.Repository;

import com.example.week06d1security.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface AuthRepository extends JpaRepository<User, Integer> {

    User findUserByUsername(String username);

}
