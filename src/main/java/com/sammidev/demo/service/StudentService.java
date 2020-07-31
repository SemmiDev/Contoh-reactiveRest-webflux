package com.sammidev.demo.service;


import com.sammidev.demo.Entity.Student;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface StudentService {

   // kita bikin serive sebagai layer antara controller dan service implementnya
    Mono<Student> createStudent(Student student);
    Mono<Student> updateStudent(Student student, String nisn);
    Flux<Student> findAll();
    Mono<Student> findOne(String nisn);
    Mono<Boolean> delete(String nisn);
}