package org.example.tasklist.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.tasklist.domain.user.Role;
import org.example.tasklist.domain.user.User;

import java.util.Optional;

@Mapper
public interface UserRepository {

    Optional<User> findById(@Param("id") Long id);

    Optional<User> findByUsername(@Param("username") String username);

    void update(User user);

    void create(User user);

    void insertUserRole(@Param("userId") Long userId, @Param("role") Role role);

    boolean isTaskOwner(@Param("userId") Long userId, @Param("taskId") Long taskId);

    void delete(@Param("id") Long id);
}
