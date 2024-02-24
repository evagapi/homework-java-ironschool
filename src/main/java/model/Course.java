package model;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class Course {

    @Setter(AccessLevel.NONE)
    private final String courseId;
    private String name;
    private double price;
    private double money_earned;
    private Teacher teacher;

    public Course(String name, double price) {
        courseId = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
    }

}
