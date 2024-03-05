package model;

import com.mitchtalmadge.asciidata.table.ASCIITable;
import com.mitchtalmadge.asciidata.table.formats.ASCIITableFormat;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode
public class School {
    @Setter(AccessLevel.NONE)
    private final String id;
    private String name;
    private Map<String, Teacher> teachers = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();
    private Map<String, Student> students = new HashMap<>();

    public School(String name, Map<String, Teacher> teachers, Map<String, Course> courses, Map<String, Student> students) {
        id = UUID.randomUUID().toString();
        this.name = name;
        this.teachers = teachers;
        this.courses = courses;
        this.students = students;
    }

    public School(String name) {
        id = UUID.randomUUID().toString();
        this.name = name;
    }

    public void addCourse(Course course) {
        courses.put(course.getCourseId(), course);
    }

    public void addTeacher(Teacher teacher) {
        teachers.put(teacher.getTeacherId(), teacher);
    }

    public void addStudent(Student student) {
        students.put(student.getStudentId(), student);
    }

    public double getTotalProfit() {
        return getTotalEarned() - computeSalaries();
    }

    private double computeSalaries() {
        double totalSpent = 0;
        for (Map.Entry<String, Teacher> entry : teachers.entrySet()) {
            Teacher teacher = entry.getValue();
            double spent = teacher.getSalary();
            totalSpent += spent;
        }
        return totalSpent;
    }

    private double getTotalEarned() {
        double totalEarned = 0;
        for (Map.Entry<String, Course> entry : courses.entrySet()) {
            Course course = entry.getValue();
            double earned = course.getMoney_earned();
            totalEarned += earned;
        }
        return totalEarned;
    }


    public void showTeachersMethod() {
        String[] header = {"ID", "Teachers"};
        String[][] data = teachers.values().stream().map(teacher -> new String[]{teacher.getTeacherId(), teacher.getName()}).toArray(String[][]::new);
        System.out.println(ASCIITable.fromData(header, data));
    }

    public void showCoursesMethod() {
        String[] header = {"ID", "Courses"};
        String[][] data = courses.values().stream().map(course -> new String[]{course.getCourseId(), course.getName()}).toArray(String[][]::new);
        System.out.println(ASCIITable.fromData(header, data));
    }

    public void showStudentsMethod() {
        String[] header = {"ID", "Students", "Email-Students"};
        String[][] data = students.values().stream().map(student -> new String[]{student.getStudentId(), student.getName(), student.getEmail()}).toArray(String[][]::new);
        System.out.println(ASCIITable.fromData(header, data));
    }

    public void enrollStudent(String studentID, String courseID) {

        String newStudentID = checkID(studentID, students);
        String newCourseID = checkID(courseID, courses);

        students.get(newStudentID).setCourse(courses.get(newCourseID));

        courses.get(newCourseID).setMoney_earned(courses.get(newCourseID).getMoney_earned()
                + courses.get(newCourseID).getPrice());

    }

    private String checkID(String id, Map<String, ?> map) {
        if (map == teachers) {
            while (!map.containsKey(id)) {
                showTeachersMethod();
                id = getNewID("Please insert a valid teacher ID from the list shown above: ");
            }
        } else if (map == courses) {
            while (!map.containsKey(id)) {
                showCoursesMethod();
                id = getNewID("Please insert a valid course ID from the list shown above: ");
            }
        } else if (map == students) {
            while (!map.containsKey(id)) {
                showStudentsMethod();
                id = getNewID("Please insert a valid student ID from the list shown above: ");
            }
        }
        return id;
    }

    private String getNewID(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    public void lookupCourse(String courseID) {

        String newCourseID = checkID(courseID, courses);
        String[] header = {"ID", "Courses", "Teacher", "Price", "MoneyEarned"};

        String[][] data = courses.values().stream()
                .filter(course -> course.getCourseId().equals(courseID))
                .map(course -> new String[]{course.getCourseId(), course.getName(), course.getTeacher().getName(),
                        String.valueOf(course.getPrice()), String.valueOf(course.getMoney_earned())})
                .toArray(String[][]::new);
        System.out.println(ASCIITable.fromData(header, data));

    }


}


