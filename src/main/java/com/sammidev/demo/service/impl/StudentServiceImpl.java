package com.sammidev.demo.service.impl;

import com.sammidev.demo.Entity.Student;
import com.sammidev.demo.repository.StudentRepository;
import com.sammidev.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository repository;

    @Override
    public Mono<Student> createStudent(Student student) {
        return repository.insert(student);
    }

    @Override
    public Mono<Student> updateStudent(Student student, String nisn) {
        return findOne(nisn).doOnSuccess(findStudent -> {
            findStudent.setName(student.getName());
            findStudent.setKelas(student.getKelas());
            findStudent.setAge(student.getAge());
            findStudent.setMajor(student.getMajor());
            findStudent.setAddress(student.getAddress());
            repository.save(findStudent).subscribe();
        }).switchIfEmpty(Mono.error(new Exception("student not found")));
    }

    @Override
    public Flux<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Student> findOne(String nisn) {
        return findOne(nisn).switchIfEmpty(Mono.error(new Exception("student not found")));
    }

    @Override
    public Mono<Boolean> delete(String nisn) {
        return findOne(nisn)
                .doOnSuccess(student -> repository.delete(student).subscribe())
                .flatMap(student -> Mono.just(Boolean.TRUE))
                .switchIfEmpty(Mono.error(new Exception("student not found")));
    }
}