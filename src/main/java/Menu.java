import com.github.lalyos.jfiglet.FigletFont;
import model.Student;
import model.School;
import utils.MainMenuOption;
import utils.MaxValue;
import utils.Validator;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Stream;

public class Menu {
    private static final int OPTION_DATA_ENTRY = 1;
    private static final int OPTION_SCHOOL_MANAGEMENT = 2;

    private static final Scanner scanner = new Scanner(System.in);
    private static int numberOfCourses;
    private static int numberOfTeachers;
    private static int numberOfStudents;

    public static void main(String[] args) {
        String name = getValidNameFor("school");
        System.out.println(FigletFont.convertOneLine(name));
        School school = new School(name);
        //System.out.println("Option Selected: " + showPrincipalMenuAndRetrieveOption(scanner));
        numberOfCourses = getNumberOfEntity("courses", MaxValue.MAX_COURSES_TO_CREATE.getValue());
        numberOfTeachers = getNumberOfEntity("teachers", MaxValue.MAX_TEACHER_TO_CREATE.getValue());
        numberOfStudents = getNumberOfEntity("students", MaxValue.MAX_STUDENT_TO_CREATE.getValue());
        registerStudents(school);
    }

    private static void registerStudents(School school) {
        for (int i = 0; i < numberOfStudents; i++) {
            Student student = new Student(getValidNameFor("student"), getValidAddress(), getValidEmail());
            school.addStudent(student);
        }
    }

    private static String getValidNameFor(String entityType) {
        String name;
        do {
            System.out.printf("Enter a %s name: %n", entityType);
            name = scanner.nextLine();
        } while (!Validator.isNameValid(name));
        return name;
    }

    private static String getValidAddress() {
        String address;
        do {
            System.out.println("Enter student's address: ");
            address = scanner.nextLine();
        } while (!Validator.isAddressValid(address));
        return address;
    }

    private static String getValidEmail() {
        String email;
        do {
            System.out.println("Enter student's email: ");
            email = scanner.nextLine();
        } while (!Validator.isEmailValid(email));
        return email;
    }

    private static int getNumberOfEntity(String value, int max) {
        String number;
        do {
            System.out.printf("Enter the number of %s to create (max-%d): %n", value, max);
            number = scanner.nextLine();
        } while (!Validator.isNumberValid(number, max));
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
}


