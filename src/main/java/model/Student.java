package model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class Student {

    @Setter(AccessLevel.NONE)
    private final String studentId;
    private String name;
    private String address;
    private String email;
    private Course course;

    public Student(String name, String address, String email) {
        studentId = UUID.randomUUID().toString();
        this.name = name;
        this.address = address;
        this.email = email;
    }
}