package com.sammidev.demo.controller;

import com.sammidev.demo.Entity.Student;
import com.sammidev.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Student> create(@RequestBody Student student) {
        log.debug("create student : {} ", student);
        return studentService.createStudent(student);
    }

    @PutMapping("/{nisn}")
    public Mono<Student> updateStudent(@RequestBody Student student, @PathVariable String nisn) {
        log.debug("succes for update student where nisn {} and student {}", nisn, student);
        return studentService.updateStudent(student,nisn);
    }

    @GetMapping()
    public Flux<Student> findAll() {
        log.debug("success to expose all student {}", studentService.findAll());
        return studentService.findAll();
    }

    @GetMapping("/{nisn}")
    Mono<Student> findOne(@PathVariable String nisn) {
        log.debug("succes for get student where nisn {}", nisn);
        return studentService.findOne(nisn);
    }

    @DeleteMapping("/{nisn}")
    public Mono<Boolean> deleteOne(@PathVariable String nisn) {
        log.debug("succes for delete stuudent where nisn {}", nisn);
        return studentService.delete(nisn);
    }
}

