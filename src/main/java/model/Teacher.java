package model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class Teacher {

    @Setter(AccessLevel.NONE)
    private final String teacherId;
    private String name;
    private double salary;

    public Teacher(String name, double salary) {
        teacherId = UUID.randomUUID().toString();
        this.name = name;
        this.salary = salary;
    }

}