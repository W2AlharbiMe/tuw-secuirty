package com.example.week06d1security.Repository;

import com.example.week06d1security.Model.Todo;
import com.example.week06d1security.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {

    Todo findTodoById(Integer id);

    List<Todo> findAllByUser(User user);

}
