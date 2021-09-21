package com.example.spjspcrud.service;

import com.example.spjspcrud.dao.UserDaoRepo;
import com.example.spjspcrud.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDaoRepo userDaoRepo;

    @Autowired
    public UserServiceImpl(UserDaoRepo userDaoRepo) {
        this.userDaoRepo = userDaoRepo;
    }

    @Override
    @Transactional
    public UserEntity add(UserEntity userEntity) {
        return userDaoRepo.save(userEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserEntity> getAll(int page, int size, String field) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, field);
        return userDaoRepo.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserEntity> getbyName(String name) {
        return userDaoRepo.findByName(name);
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDaoRepo.deleteById(id);
    }

    @Override
    @Transactional
    public UserEntity editUser(UserEntity userEntity) {
        return userDaoRepo.save(userEntity);
    }

    @Transactional(readOnly = true)
    public Optional<UserEntity> getById(Long id) {
        return userDaoRepo.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public List<UserEntity> saveAll(List<UserEntity> userEntities) {
        return userDaoRepo.saveAll(userEntities);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserEntity> getSearchResults(String name, int page, int size, String field) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, field);
        return userDaoRepo.findByTitleContaining(name, pageable);
    }
}
