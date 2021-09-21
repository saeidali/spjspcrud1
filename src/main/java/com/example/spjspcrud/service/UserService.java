package com.example.spjspcrud.service;

import com.example.spjspcrud.model.UserEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity add(UserEntity userEntity);

    Page<UserEntity> getAll(int page, int size, String field);

    List<UserEntity> getbyName(String name);

    void deleteUser(long id);

    UserEntity editUser(UserEntity userEntity);

    Optional<UserEntity> getById(Long id);

    List<UserEntity> saveAll(List<UserEntity> userEntities);

    Page<UserEntity> getSearchResults(String title, int page, int size, String field);
}
