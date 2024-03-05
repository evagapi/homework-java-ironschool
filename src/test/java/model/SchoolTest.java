package model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SchoolTest {

    static School DummySchool;
    static Teacher dummyAnne;

    static Course dummyCourse;
    static Student dummyStudent;
    @BeforeAll
    static void CreateMockSchool() {
        DummySchool = new School("Marinade");
        dummyAnne = new Teacher("Anne", 1300.00);
        dummyCourse = new Course("Introducción a la programación con Java", 7500);
        dummyStudent = new Student("Pedro", "Avenida portal del angel,36", "Pedro@ejemplo.com");
        DummySchool.addTeacher(dummyAnne);
        DummySchool.addCourse(dummyCourse);
        DummySchool.addStudent(dummyStudent);
        DummySchool.assignTeacherToCourse(dummyAnne.getTeacherId(), dummyCourse.getCourseId());

    }

    @Test
    @DisplayName("Two different schools should have two different ids")
    void School_multipleInstancesOfSchoolDifferentIds() {
        School maristes = new School("Maristes");
        School balmes = new School("Jaume Balmes");
        assertNotEquals(maristes.getId(), balmes.getId());
    }

    @Test
    @DisplayName("Subtract teachers salaries from total earned from courses")
    void School_subtractSalariesFromTotalEarnedFromCourses() {
        School balmes = new School("Jaume Balmes");

        Teacher anne = new Teacher("Anne", 1200.00);
        Teacher john = new Teacher("John", 1200.00);
        Course englishCourseA1 = new Course("English A1", 300.00);
        Course englishCourseA2 = new Course("English A2", 300.00);
        Course englishCourseB1 = new Course("English B1", 300.00);
        Course englishCourseB2 = new Course("English B2", 300.00);

        balmes.addTeacher(anne);
        balmes.addTeacher(john);

        englishCourseA1.setMoney_earned(1200);
        englishCourseA2.setMoney_earned(900);
        englishCourseB1.setMoney_earned(1500);
        englishCourseB2.setMoney_earned(1200);

        balmes.addCourse(englishCourseA1);
        balmes.addCourse(englishCourseA2);
        balmes.addCourse(englishCourseB1);
        balmes.addCourse(englishCourseB2);

        assertEquals(balmes.getTotalProfit(), 2400.00);

    }

    @Test
    @DisplayName("Teachers can be added")
    void School_addSomeDummyTeachers(){
        assertEquals(1, DummySchool.getTeachers().size());
    }

    @Test
    @DisplayName("Should return Teacher name correctly from course")
    void School_assignTeacherToCourse(){
        Teacher resultTeacher = dummyCourse.getTeacher();
        assertEquals("Anne", resultTeacher.getName());
        assertEquals(dummyAnne.getTeacherId(), dummyCourse.getTeacher().getTeacherId());
    }

    @Test
    @DisplayName("Should be the same showTeachersMethod table result")
    void School_showTeachersMethodReturnASCIITable() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String uuidDummyAnne = dummyAnne.getTeacherId();
        String nameDummyAnne = dummyAnne.getName();
        DummySchool.showTeachersMethod();
        String expectedOutput = String.format(
                "╔══════════════════════════════════════╤══════════╗%n" +
                        "║ ID                                   │ Teachers ║%n" +
                        "╠══════════════════════════════════════╪══════════╣%n" +
                        "║ %-36s │ %-8s ║%n" +
                        "╚══════════════════════════════════════╧══════════╝%n%n", uuidDummyAnne, nameDummyAnne);


        String expectedNormalized = expectedOutput.replaceAll("\\s", "").replaceAll("\\\\", "");
        String actualNormalized = outputStreamCaptor.toString().replaceAll("\\s", "").replaceAll("\\\\", "");

        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    @DisplayName("Should be the same showCoursesMethod table result")
    void School_showCoursesMethodReturnASCIITable() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String uuidDummyCourse = dummyCourse.getCourseId();
        String uuidDummyNameCourse = dummyCourse.getName();
        DummySchool.showCoursesMethod();

        String expectedOutput = String.format(
                "╔══════════════════════════════════════╤═════════════════════════════════════════╗%n" +
                        "║ ID                                   │ Courses                                 ║%n" +
                        "╠══════════════════════════════════════╪═════════════════════════════════════════╣%n" +
                        "║ %-36s │ %-36s ║%n" +
                        "╚══════════════════════════════════════╧═════════════════════════════════════════╝%n%n", uuidDummyCourse, uuidDummyNameCourse);


        String expectedNormalized = expectedOutput.replaceAll("\\s", "").replaceAll("\\\\", "");
        String actualNormalized = outputStreamCaptor.toString().replaceAll("\\s", "").replaceAll("\\\\", "");
        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    @DisplayName("Should be the same showStudentsMethod table result")
    void School_showStudentsMethodReturnASCIITable() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        String uuidDummyStudent = dummyStudent.getStudentId();
        String dummyStudentName = dummyStudent.getName();
        String dummyStudentEmail = dummyStudent.getEmail();
        DummySchool.showStudentsMethod();

        String expectedOutput = String.format(
                "╔══════════════════════════════════════╤══════════╤═══════════════════╗%n" +
                        "║ ID                                   │ Students │ Email-Students    ║%n" +
                        "╠══════════════════════════════════════╪══════════╪═══════════════════╣%n" +
                        "║ %-36s │ %-8s │ %-16s ║%n" +
                        "╚══════════════════════════════════════╧══════════╧═══════════════════╝", uuidDummyStudent, dummyStudentName, dummyStudentEmail);


        String expectedNormalized = expectedOutput.replaceAll("\\s", "").replaceAll("\\\\", "");
        String actualNormalized = outputStreamCaptor.toString().replaceAll("\\s", "").replaceAll("\\\\", "");
        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    @DisplayName("Check Assign Method when IDs are valid")
    void School_enrollOneStudentAtCourse_HappyPath(){

        School balmes = new School("Jaume Balmes");
        Teacher john = new Teacher("John", 1200.00);
        Course englishCourseA1 = new Course("English A1", 300.00);
        Student peter = new Student("Peter", "False Street 123", "testing@testcase.es");

        balmes.addTeacher(john);
        balmes.addStudent(peter);
        balmes.addCourse(englishCourseA1);

        balmes.enrollStudent(peter.getStudentId(), englishCourseA1.getCourseId());

        assertEquals(300, englishCourseA1.getMoney_earned());
        assertEquals(englishCourseA1, peter.getCourse());

        Student bruce = new Student("Bruce", "False Street 123", "testing@testcase.es");
        balmes.addStudent(bruce);
        balmes.enrollStudent(bruce.getStudentId(), englishCourseA1.getCourseId());

        assertEquals(600, englishCourseA1.getMoney_earned());
        assertEquals(englishCourseA1, bruce.getCourse());
    }

    @Test
    @DisplayName("Should be the same lookUp table result")
    void School_lookupCoursesMethodReturnASCIITable() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        dummyCourse.setTeacher(dummyAnne);
        DummySchool.enrollStudent(dummyStudent.getStudentId(), dummyCourse.getCourseId());

        String uuidDummyCourse = dummyCourse.getCourseId();
        String uuidDummyNameCourse = dummyCourse.getName();
        String uuidDummyTeacherName = dummyCourse.getTeacher().getName();
        String uuidDummyCoursePrice = String.valueOf(dummyCourse.getPrice());
        String uuidDummyCourseMoneyEarned = String.valueOf(dummyCourse.getMoney_earned());

        DummySchool.lookupCourse(dummyCourse.getCourseId());

        String expectedOutput = String.format(
                "╔══════════════════════════════════════╤═════════════════════════════════════════╤═════════╤════════╤═════════════╗%n" +
                        "║ ID                                   │ Courses                                 │ Teacher │ Price  │ MoneyEarned ║%n" +
                        "╠══════════════════════════════════════╪═════════════════════════════════════════╪═════════╪════════╪═════════════╣%n" +
                        "║ %-36s │ %-36s │ %-4s │ %-6s │ %-6s ║%n" +
                        "╚══════════════════════════════════════╧═════════════════════════════════════════╧═════════╧════════╧═════════════╝%n%n",
                uuidDummyCourse, uuidDummyNameCourse,uuidDummyTeacherName, uuidDummyCoursePrice, uuidDummyCourseMoneyEarned);


        String expectedNormalized = expectedOutput.replaceAll("\\s", "").replaceAll("\\\\", "");
        String actualNormalized = outputStreamCaptor.toString().replaceAll("\\s", "").replaceAll("\\\\", "");
        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    @DisplayName("Should be the same lookUp table result")
    void School_lookupStudentMethodReturnASCIITable() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        DummySchool.enrollStudent(dummyStudent.getStudentId(), dummyCourse.getCourseId());

        String uuidDummyStudent = dummyStudent.getStudentId();
        String dummyStudentName = dummyStudent.getName();
        String dummyStudentEmail = dummyStudent.getEmail();
        String uuidDummyEnrolledCourse = dummyStudent.getCourse().getName();
        String dummyStudentAddress = dummyStudent.getAddress();

        DummySchool.lookupStudent(dummyStudent.getStudentId());

        String expectedOutput = String.format(
                "╔══════════════════════════════════════╤═════════╤═════════════════════════════════════════╤═══════════════════╤═════════════════════════════╗%n" +
                        "║ ID                                   │ Student │ Enrolled Course                        │ Email-Student     │ Student Address             ║%n" +
                        "╠══════════════════════════════════════╪═════════╪═════════════════════════════════════════╪═══════════════════╪═════════════════════════════╣%n" +
                        "║ %-36s │ %-7s │ %-36s │ %-17s │ %-27s ║%n" +
                        "╚══════════════════════════════════════╧═════════╧═════════════════════════════════════════╧═══════════════════╧═════════════════════════════╝%n%n",
                uuidDummyStudent, dummyStudentName,uuidDummyEnrolledCourse, dummyStudentEmail, dummyStudentAddress);


        String expectedNormalized = expectedOutput.replaceAll("\\s", "").replaceAll("\\\\", "");
        String actualNormalized = outputStreamCaptor.toString().replaceAll("\\s", "").replaceAll("\\\\", "");
        assertEquals(expectedNormalized, actualNormalized);
    }

    @Test
    @DisplayName("Should be the same lookUp table result")
    void School_lookupTeacherMethodReturnASCIITable() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        String uuidDummyTeacher = dummyAnne.getTeacherId();
        String uuiddummyTeacherName = dummyAnne.getName();
        String uuiddummyTeacherSalary = String.valueOf(dummyAnne.getSalary());

        DummySchool.lookupTeacher(dummyAnne.getTeacherId());

        String expectedOutput = String.format(
                "╔══════════════════════════════════════╤═════════╤════════╗%n" +
                        "║ ID                                   │ Teacher │ Salary ║%n" +
                        "╠══════════════════════════════════════╪═════════╪════════╣%n" +
                        "║ %-36s │ %-7s │ %-6s ║%n" +
                        "╚══════════════════════════════════════╧═════════╧════════╝%n%n",
                uuidDummyTeacher, uuiddummyTeacherName,uuiddummyTeacherSalary);


        String expectedNormalized = expectedOutput.replaceAll("\\s", "").replaceAll("\\\\", "");
        String actualNormalized = outputStreamCaptor.toString().replaceAll("\\s", "").replaceAll("\\\\", "");
        assertEquals(expectedNormalized, actualNormalized);
    }

























}