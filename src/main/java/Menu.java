import com.github.lalyos.jfiglet.FigletFont;import model.Course;
import model.School;
import model.Student;
import model.Teacher;
import utils.Command;
import utils.MainMenuOption;
import utils.Validator;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Menu {
    private static final int OPTION_DATA_ENTRY = 1;
    private static final int OPTION_SCHOOL_MANAGEMENT = 2;
    private static School school;

    public static void main(String[] args) {

        Course newCourse = new Course("Etica", 2000);
        Teacher newTeacher = new Teacher("Pedro", 2500);
        Student newStudent = new Student("Mariano", "Calle falsa 123,", "Mariano@ejemplo.com");

        school = createSchool();

        school.addTeacher(newTeacher);
        school.addCourse(newCourse);
        school.addStudent(newStudent);
        // showPrincipalMenuAndRetrieveOption(scanner)

        // int numberOfTeachers = getNumberOfEntity("teachers", MaxValue.MAX_TEACHER_TO_CREATE.getValue());
        // registerTeachers(numberOfTeachers);

        // int numberOfStudents = getNumberOfEntity("students", MaxValue.MAX_STUDENT_TO_CREATE.getValue());
        // registerStudents(numberOfStudents);

        // int numberOfCourses = getNumberOfEntity("courses", MaxValue.MAX_COURSES_TO_CREATE.getValue());
        // registerCourses(numberOfCourses);

        menuOfCommands();

    }

    public static School createSchool() {
        String schoolName = getValidNameFor("school");
        System.out.println(FigletFont.convertOneLine(schoolName));
        return new School(schoolName);
    }

    private static void registerStudents(int numberOfStudents) {
        for (int i = 0; i < numberOfStudents; i++) {
            Student student = new Student(getValidNameFor("student"), getValidAddress(), getValidEmail());
            school.addStudent(student);
        }
    }

    private static void registerTeachers(int numberOfTeachers) {
        for (int i = 0; i < numberOfTeachers; i++) {
            Teacher teacher = new Teacher(getValidNameFor("teacher"), getSalary());
            school.addTeacher(teacher);
        }
    }

    private static void registerCourses(int numberOfCourses) {
        for (int i = 0; i < numberOfCourses; i++) {
            Course course = new Course(getValidNameFor("course"), getPrice());
            school.addCourse(course);
        }
    }

    public static double getSalary() {
        Scanner scanner = new Scanner(System.in);
        String salary;
        do {
            System.out.println("Enter the salary: ");
            salary = scanner.next();
        } while (!Validator.isPositiveDecimalNumberValid(salary));
        return Double.parseDouble(salary);
    }

    public static double getPrice() {
        Scanner scanner = new Scanner(System.in);
        String price;
        do {
            System.out.println("Enter the price: ");
            price = scanner.next();
        } while (!Validator.isPositiveDecimalNumberValid(price));
        return Double.parseDouble(price);
    }

    public static String getValidNameFor(String entityType) {
        Scanner scanner = new Scanner(System.in);
        String name;
        do {
            System.out.printf("Enter a %s name: ", entityType);
            name = scanner.nextLine();
        } while (!Validator.isNameValid(name));
        return name;
    }

    private static String getValidAddress() {
        Scanner scanner = new Scanner(System.in);
        String address;
        do {
            System.out.println("Enter student's address: ");
            address = scanner.nextLine();
        } while (!Validator.isAddressValid(address));
        return address;
    }

    private static String getValidEmail() {
        Scanner scanner = new Scanner(System.in);
        String email;
        do {
            System.out.println("Enter student's email: ");
            email = scanner.next();
        } while (!Validator.isEmailValid(email));
        return email;
    }

    private static int getNumberOfEntity(String value, int max) {
        Scanner scanner = new Scanner(System.in);
        String number;
        do {
            System.out.printf("Enter the number of %s to create (max-%d): %n", value, max);
            number = scanner.next();
        } while (!Validator.isPositiveNumberValid(number, max));
        return Integer.parseInt(number);
    }

    public static int showPrincipalMenuAndRetrieveOption(Scanner sc) {
        System.out.println("Select option");
        Stream.of(MainMenuOption.values()).forEach(opt -> System.out.println((opt.ordinal() + 1) + " - " + opt.getDescription()));

        int selectedOption = -1;
        while (selectedOption != OPTION_DATA_ENTRY && selectedOption != OPTION_SCHOOL_MANAGEMENT) {
            try {
                selectedOption = sc.nextInt();
                if (selectedOption != OPTION_DATA_ENTRY && selectedOption != OPTION_SCHOOL_MANAGEMENT) {
                    System.err.println("Invalid option. Please enter either " + OPTION_DATA_ENTRY + " or " + OPTION_SCHOOL_MANAGEMENT);
                }
            } catch (NoSuchElementException e) {
                System.err.println("Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
        return selectedOption;
    }

    public static void menuOfCommands() {
        Scanner scanner = new Scanner(System.in);
        long max = Stream.of(Command.values()).count();
        Stream.of(Command.values()).forEach(opt -> System.out.println(opt.getIndex() + " - " + opt.getDescription()));

        String commandIndex;
        do {
            System.out.println("Enter a command: ");
            commandIndex = scanner.next();
            school.executeCommand(commandIndex);
        } while (!commandIndex.equals("0") && Validator.isPositiveNumberValid(commandIndex, (int) max - 1));
    }

}


