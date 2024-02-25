package model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class School {
    @Setter(AccessLevel.NONE)
    private final String id;
    private String name;
    private HashMap<String, Teacher> teachers;
    private HashMap<String, Course> courses;
    private HashMap<String, Student> students;
    private double totalProfit;

    public School(String name) {
        id = UUID.randomUUID().toString();
        this.name = name;
    }
}