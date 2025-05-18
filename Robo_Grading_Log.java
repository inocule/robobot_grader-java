package robo_grader;
/* 
>> BEEP BOOP << 
This GR4D-BOT v2.0 program simulates a robo-pedagogical grading system. Users can 
input student (or “unit”) information, including name, course, year, section, and 
grades. The bot evaluates each unit’s average grade and assigns a performance 
standing such as "With Highest Honors" or "Failed," using futuristic robot language 
and error-checking for invalid inputs. The interface mimics a robotic console, 
complete with command prompts, error messages, and data analysis reports, 
offering both a fun and educational experience with robust input validation.
*/ 

import java.util.*;

class Student {
    String firstName, lastName, course, section;
    int year;
    double midtermGrade, finalGrade;

    public Student(String firstName, String lastName, int year, String course, String section, double midtermGrade, double finalGrade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.year = year;
        this.course = course;
        this.section = section;
        this.midtermGrade = midtermGrade;
        this.finalGrade = finalGrade;
    }

    public void introduceSelf() {
        System.out.println(">> [IDENTIFICATION SUCCESSFUL]");
        System.out.printf(">> Name Protocol: %s %s\n", firstName.toUpperCase(), lastName.toUpperCase());
        System.out.printf(">> Course Module: %s\n", course);
        System.out.printf(">> Year & Sector: YEAR %d - SECTION %s\n", year, section);
    }

    public void evaluateGrade() {
        double average = (midtermGrade + finalGrade) / 2;
        System.out.println("\n>> [ANALYZING ACADEMIC DATA...]");
        System.out.printf(">> Midterm Report: %.2f\n", midtermGrade);
        System.out.printf(">> Final Evaluation: %.2f\n", finalGrade);
        System.out.printf(">> Cumulative Average: %.2f\n", average);
        System.out.println(">> Academic Standing: " + getStanding(average));
    }

    private String getStanding(double average) {
        if (average > 100) return "!! SYSTEM ERROR: INVALID GRADE DETECTED";
        if (average >= 98) return "★ ELITE UNIT: WITH HIGHEST HONORS";
        if (average >= 95) return "★ HIGH-RANK UNIT: WITH HIGH HONORS";
        if (average >= 90) return "★ RECOGNIZED UNIT: WITH HONORS";
        if (average >= 75) return "✓ FUNCTIONAL UNIT: PASSED";
        return "✗ SYSTEM FAILURE: FAILED";
    }
}

public class Robo_Grading_Log {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> studentList = new ArrayList<>();
        String choice;

        System.out.println("===========================================");
        System.out.println("🤖 GR4D-BOT v2.0 :: Academic Analysis System");
        System.out.println("===========================================");

        do {
            System.out.println("\n--- COMMAND MENU ---");
            System.out.println("1) Input New Unit");
            System.out.println("2) Display All Units");
            System.out.println("3) Analyze Specific Unit");
            System.out.println("4) Power Down GR4D-BOT");
            System.out.print(">> Initiate Command [1-4]: ");
            choice = scanner.nextLine().trim();
            System.out.println();

            switch (choice) {
                case "1":
                    studentList.add(createStudent(scanner));
                    System.out.println(">> [DATA INGESTED SUCCESSFULLY]");
                    break;

                case "2":
                    if (studentList.isEmpty()) {
                        System.out.println(">> No data units found in memory.");
                    } else {
                        System.out.println("-- REGISTERED UNITS --");
                        for (int i = 0; i < studentList.size(); i++) {
                            System.out.printf("%d. UNIT: %s %s\n", i + 1,
                                    studentList.get(i).firstName, studentList.get(i).lastName);
                        }
                    }
                    break;

                case "3":
                    if (studentList.isEmpty()) {
                        System.out.println(">> MEMORY SLOT EMPTY. No data available.");
                    } else {
                        System.out.print(">> Enter unit number (1 to " + studentList.size() + "): ");
                        try {
                            int index = Integer.parseInt(scanner.nextLine()) - 1;
                            if (index >= 0 && index < studentList.size()) {
                                Student s = studentList.get(index);
                                System.out.println("\n>> [UNIT PROFILE]");
                                s.introduceSelf();
                                s.evaluateGrade();
                            } else {
                                System.out.println(">> ERROR: Invalid unit number.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println(">> ERROR: Non-numeric input detected.");
                        }
                    }
                    break;

                case "4":
                    System.out.println(">> GR4D-BOT entering standby mode... Powering down.");
                    break;

                default:
                    System.out.println(">> UNKNOWN COMMAND. Reattempt input.");
            }

        } while (!choice.equals("4"));
    }

    private static Student createStudent(Scanner scanner) {
        System.out.println(">> [DATA ENTRY INITIATED]");

        System.out.print(">> Input Unit's First Name: ");
        String first = scanner.nextLine();

        System.out.print(">> Input Unit's Last Name: ");
        String last = scanner.nextLine();

        System.out.print(">> Input Course Designation: ");
        String course = scanner.nextLine();

        int year = 0;
        while (true) {
            System.out.print(">> Input Year Cycle [1-4]: ");
            try {
                year = Integer.parseInt(scanner.nextLine());
                if (year >= 1 && year <= 4) break;
                else System.out.println(">> ALERT: Year out of valid bounds.");
            } catch (NumberFormatException e) {
                System.out.println(">> MALFUNCTION: Year must be numeric.");
            }
        }

        System.out.print(">> Input Section Code: ");
        String section = scanner.nextLine();

        double midterm = 0;
        while (true) {
            System.out.print(">> Input Midterm Score: ");
            try {
                midterm = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println(">> ERROR: Numeric format required.");
            }
        }

        double fin = 0;
        while (true) {
            System.out.print(">> Input Final Score: ");
            try {
                fin = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println(">> ERROR: Numeric format required.");
            }
        }

        return new Student(first, last, year, course, section, midterm, fin);
    }
}
