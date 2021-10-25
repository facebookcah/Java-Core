package manager;

import java.util.ArrayList;
import java.util.Scanner;

import models.Marks;
import models.Student;
import models.Subjects;
import report.ReportStudent;

public class InformationManager {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		do {
			//ReportStudent.getData();
			System.out.println("\t\t\t\t\t\t\t============================ MENU ============================");
			System.out.println("\t\t\t\t\t\t\t\t\t\t1.STUDENT MANAGER.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t2.SUBJECT MANAGER.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t3.MARK MANAGER.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t4.SHOW TRANSCRIPTS OF ALL STUDENTS.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t5.SHOW TRANSCRIPTS OF ALL SUBJECT.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t6.SEARCH FOR STUDENT DETAILS.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t7.SEARCH FOR SUBJECT DETAILS.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t0.EXIT");
			System.out.print("\t\t\t\t\t\t\t\t\t\t>>> [YOU CHOOSE FUNCTION] : ");
			int select = Integer.parseInt(sc.nextLine());
			
			System.out.println();
			switch (select) {
			case 1:
				StudentManager.manager();
				break;
			case 2:
				SubjectManager.manager();;
				break;
			case 3:
				MarkManager.manager();
				break;
			case 4:
				ReportStudent.studentDetail();
				break;
			case 5:
				ReportStudent.subjectDetail();
				break;
			case 6:
				ReportStudent.searchStudentDetail();
				break;
			case 7:
				ReportStudent.searchSubjectDetail();
				break;
			case 0:
				return;
				default:
					System.out.println("! ! Please select the available functions");

			}
		} while (true);
	}

}
