package com.example.spjspcrud.service;

import com.example.spjspcrud.dao.StudentDaoRepo;
import com.example.spjspcrud.model.StudentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentDaoRepo studentDaoRepo;

    @Autowired
    public StudentServiceImpl(StudentDaoRepo studentDaoRepo) {
        this.studentDaoRepo = studentDaoRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentInfo> getAllStudentInfo() {
        return studentDaoRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StudentInfo> findById(Long id) {
        return studentDaoRepo.findById(id);
    }

    @Override
    @Transactional
    public StudentInfo addStudent(StudentInfo studentInfo) {
        return studentDaoRepo.save(studentInfo);
    }

    @Override
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    public StudentInfo editStudentInfo(StudentInfo studentInfo) {
        return studentDaoRepo.save(studentInfo);
    }

    @Override
    @Transactional
    public void deleteStudentById(Long id) {
        studentDaoRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StudentInfo> getByAddress(String address, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentDaoRepo.getByAddress(address, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StudentInfo> findByName(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return studentDaoRepo.findByName(name, pageable);
    }
}
