import java.util.Scanner;

public class GradeManagementSystem {

    private static String[] studentNames;
    private static double[][] studentMarks; // students × subjects
    private static final int MAX_STUDENTS = 100;
    private static final int SUBJECT_COUNT = 5;
    private static int studentCount = 0;

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        initializeArrays();
        boolean running = true;

        while (running) {
            System.out.println("\n=== GRADE MANAGEMENT SYSTEM ===");
            System.out.println("1. Add Student Marks");
            System.out.println("2. View All Students");
            System.out.println("3. Calculate Averages");
            System.out.println("4. Find Top Performer");
            System.out.println("5. Generate Report");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = getValidInt(1, 6);

            switch (choice) {
                case 1:
                    addStudentMarks();
                    break;
                case 2:
                    viewAllStudents();
                    break;
                case 3:
                    calculateAverages();
                    break;
                case 4:
                    findTopPerformer();
                    break;
                case 5:
                    generateReport();
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using Grade Management System!");
                    break;
            }
        }
    }

    // Initialize arrays
    private static void initializeArrays() {
        studentNames = new String[MAX_STUDENTS];
        studentMarks = new double[MAX_STUDENTS][SUBJECT_COUNT];
    }

    // Add student
    private static void addStudentMarks() {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Maximum student limit reached!");
            return;
        }

        System.out.println("\n=== ADD STUDENT MARKS ===");
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        studentNames[studentCount] = name;

        String[] subjects = {"Mathematics", "Science", "English", "History", "Computer"};

        System.out.println("Enter marks (0-100):");
        for (int i = 0; i < SUBJECT_COUNT; i++) {
            System.out.print(subjects[i] + ": ");
            studentMarks[studentCount][i] = getValidMark();
        }

        studentCount++;
        System.out.println("✅ Student added successfully!");
    }

    // View all students
    private static void viewAllStudents() {
        System.out.println("\n=== ALL STUDENTS ===");

        if (studentCount == 0) {
            System.out.println("No students found!");
            return;
        }

        for (int i = 0; i < studentCount; i++) {
            System.out.println("\nName: " + studentNames[i]);
            for (int j = 0; j < SUBJECT_COUNT; j++) {
                System.out.println("Subject " + (j + 1) + ": " + studentMarks[i][j]);
            }
            System.out.printf("Average: %.2f\n", calculateAverage(i));
        }
    }

    // Calculate average for one student
    private static double calculateAverage(int index) {
        double sum = 0;
        for (int i = 0; i < SUBJECT_COUNT; i++) {
            sum += studentMarks[index][i];
        }
        return sum / SUBJECT_COUNT;
    }

    // Show averages and grades
    private static void calculateAverages() {
        System.out.println("\n=== STUDENT AVERAGES ===");

        for (int i = 0; i < studentCount; i++) {
            double avg = calculateAverage(i);
            System.out.println(studentNames[i] + " → Average: " + avg + " Grade: " + getGrade(avg));
        }
    }

    // Find topper
    private static void findTopPerformer() {
        if (studentCount == 0) {
            System.out.println("No students available!");
            return;
        }

        int topIndex = 0;
        double max = calculateAverage(0);

        for (int i = 1; i < studentCount; i++) {
            double avg = calculateAverage(i);
            if (avg > max) {
                max = avg;
                topIndex = i;
            }
        }

        System.out.println("\n🏆 Top Performer:");
        System.out.println("Name: " + studentNames[topIndex]);
        System.out.println("Average: " + max);
    }

    // Generate full report
    private static void generateReport() {
        System.out.println("\n=== PERFORMANCE REPORT ===");

        if (studentCount == 0) {
            System.out.println("No data available!");
            return;
        }

        int a = 0, b = 0, c = 0, d = 0, f = 0;

        for (int i = 0; i < studentCount; i++) {
            double avg = calculateAverage(i);
            String grade = getGrade(avg);

            switch (grade) {
                case "A": a++; break;
                case "B": b++; break;
                case "C": c++; break;
                case "D": d++; break;
                default: f++;
            }
        }

        System.out.println("Total Students: " + studentCount);
        System.out.println("A Grade: " + a);
        System.out.println("B Grade: " + b);
        System.out.println("C Grade: " + c);
        System.out.println("D Grade: " + d);
        System.out.println("F Grade: " + f);
    }

    // Grade logic
    private static String getGrade(double avg) {
        if (avg >= 80) return "A";
        else if (avg >= 70) return "B";
        else if (avg >= 60) return "C";
        else if (avg >= 50) return "D";
        else return "F";
    }

    // Input validation (int)
    private static int getValidInt(int min, int max) {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value >= min && value <= max) return value;
                else System.out.print("Enter between " + min + " and " + max + ": ");
            } catch (Exception e) {
                System.out.print("Invalid input! Enter number: ");
                scanner.nextLine();
            }
        }
    }

    // Input validation (marks)
    private static double getValidMark() {
        while (true) {
            try {
                double mark = scanner.nextDouble();
                scanner.nextLine();
                if (mark >= 0 && mark <= 100) return mark;
                else System.out.print("Enter marks (0-100): ");
            } catch (Exception e) {
                System.out.print("Invalid input! Enter number: ");
                scanner.nextLine();
            }
        }
    }
}