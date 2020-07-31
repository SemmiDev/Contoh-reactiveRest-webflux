package com.sammidev.demo.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "students")
public class Student {


    @Id
    private String nisn;

    private String name;
    private Integer age;
    private String kelas;
    private String major;
    private String address;

}