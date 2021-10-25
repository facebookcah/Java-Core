package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import io.SerializeFileMark;
import io.SerializeFileSubject;
import models.Marks;
import models.Student;
import models.Subjects;

public class MarkManager {

	static Scanner sc = new Scanner(System.in);
	public static ArrayList<Marks> listMarks = new ArrayList<Marks>();
	public static ArrayList<Subjects> listSubject = new ArrayList<Subjects>();
	public static ArrayList<Student> listStudent = new ArrayList<Student>();

	public static boolean studentIsExist(int code) {
		listStudent = StudentManager.readFile();

		int count = 0;
		for (Student student : listStudent) {
			if (student.getStudentCode() == code)
				count++;
		}
		if (count != 0)
			return true;
		else
			return false;
	}

	public static boolean subjectIsExist(int code) {
		listSubject = SubjectManager.readFile();
		int count = 0;
		for (Subjects subject : listSubject) {
			if (subject.getSubjectCode() == code)
				count++;
		}
		if (count != 0)
			return true;
		else
			return false;
	}

	public static boolean existMark(int studentCode, int subjectCode) {
		int count = 0;

		for (Marks mark : listMarks) {
			if (mark.getStudentCode() == studentCode && mark.getSubjectCode() == subjectCode)
				count++;
		}
		if (count != 0)
			return true;
		else
			return false;
	}

	public static void addMark() {

		int count = 0;
		System.out.println("\t\t\t\t\t\t\t======================= ADD A NEW MARK =======================");

		System.out.print("\t\t\t\t\t\t\t\tEnter student's code : ");
		int studentCode = Integer.parseInt(sc.nextLine());
		System.out.println();
		if (studentIsExist(studentCode)) {
			System.out.print("\t\t\t\t\t\t\t\tEnter subject's code : ");
			int subjectCode = Integer.parseInt(sc.nextLine());
			System.out.println();
			if (subjectIsExist(subjectCode)) {

				if (!existMark(studentCode, subjectCode)) {
					float sMark;
					do {
						System.out.print("\t\t\t\t\t\t\t\tEnter mark (10 score scale) : ");
						sMark = Float.parseFloat(sc.nextLine());
						System.out.println();
					} while (sMark < 0 || sMark > 10);
					Marks mark = new Marks(studentCode, subjectCode, sMark);
					listMarks.add(mark);
					saveFile();
					count++;
					System.out.println("\t\t\t\t\t\t\t\t" + count + " records have been added");
				} else {
					System.out.println("\t\t\t\t\t\t\t\t! !Mark of student has been entered ");
				}
			} else {
				System.out.println("\t\t\t\t\t\t\t\t\t! ! Subject dose not exist");
			}
		} else
			System.out.println("\t\t\t\t\t\t\t\t\t! ! Student dose not exist");
	}

	public static boolean saveFile() {
		return SerializeFileMark.saveFile(listMarks);
	}

	public static ArrayList<Marks> readFile() {
		listMarks = SerializeFileMark.readFile();
		return listMarks;
	}

	public static void printList() {
		if (listMarks.isEmpty())
			System.out.println("\t\t\t\t\t\t\t\t\t\t[LIST NULL]");
		else {
			Collections.sort(listMarks,new Comparator<Marks>() {

				@Override
				public int compare(Marks o1, Marks o2) {
					
					return o1.getStudentCode()-o2.getStudentCode();
				}
				
			});
			System.out.println("\t\t\t\t\t\t\t============================ LIST OF MARK ============================");
			System.out.printf("\t\t\t\t\t\t\t\t\t%-15s	%-15s	%-5s%n", "[STUDENT CODE]", "[SUBJECT CODE]", "[MARK]");
			for (Marks m : listMarks) {
				m.display();
			}
		}

	}

	public static void deleteMark() {
		int index = -1;
		System.out.print("\t\t\t\t\t\t\t\tEnter student's code : ");
		int studentCode = Integer.parseInt(sc.nextLine());
		System.out.println();
		if (studentIsExist(studentCode)) {
			System.out.print("\t\t\t\t\t\t\t\tEnter subject's code : ");
			int subjectCode = Integer.parseInt(sc.nextLine());
			System.out.println();
			if (subjectIsExist(subjectCode)) {
				for (int i = 0; i < listMarks.size(); i++) {
					if (listMarks.get(i).getStudentCode() == studentCode
							&& listMarks.get(i).getSubjectCode() == subjectCode) {
						index = i;
					}

				}
				if (index != -1) {
					System.out.println("\t\t\t\t\t\t\t\t\t[Are you sure you want to delete?]");
					System.out.println("\t\t\t\t\t\t\t\t\t\t1.[YES]\t\t2.[NO]");
					int select = Integer.parseInt(sc.nextLine());
					switch (select) {
					case 1: {
						listMarks.remove(index);
						saveFile();
						System.out.println("\t\t\t\t\t\t\t\t\tDelete success :)");
						break;
					}
					case 2:
						return;

					default:
						return;
					}
				} else
					System.out.println("\t\t\t\t\t\t\t\t" + "! ! This student hasn't been enter mark");
			} else
				System.out.println("\t\t\t\t\t\t\t\t" + "! ! This subject dose not exist");
		}

		else
			System.out.println("\t\t\t\t\t\t\t\t" + "! ! This student does not exist");
	}

	public static void updateMark() {
		int index = -1;
		System.out.print("\t\t\t\t\t\t\t\tEnter student's code : ");
		int studentCode = Integer.parseInt(sc.nextLine());
		System.out.println();
		if (studentIsExist(studentCode)) {
			System.out.print("\t\t\t\t\t\t\t\tEnter subject's code : ");
			int subjectCode = Integer.parseInt(sc.nextLine());
			System.out.println();
			if (subjectIsExist(subjectCode)) {
				for (int i = 0; i < listMarks.size(); i++) {
					if (listMarks.get(i).getStudentCode() == studentCode
							&& listMarks.get(i).getSubjectCode() == subjectCode) {
						index = i;
					}

				}
				if (index != -1) {
					System.out.print("\t\t\t\t\t\t\t\t\t\tEnter new mark : ");
					float mark = Float.parseFloat(sc.nextLine());
					System.out.println();
					listMarks.get(index).setMark(mark);
					saveFile();
					if (saveFile())
						System.out.println("\t\t\t\t\t\t\t\t\t\tUpdate success :)");
					else
						System.out.println("\t\t\t\t\t\t\t\t\t\tUpdate faild :)");

				}

				else
					System.out.println("\t\t\t\t\t\t\t\t" + "! ! This student hasn't been enter mark");
			} else
				System.out.println("\t\t\t\t\t\t\t\t" + "! ! This subject does not exist");
		} else
			System.out.println("\t\t\t\t\t\t\t\t" + "! ! This student does not exist");

	}

	public static void manager() {

		do {
			readFile();
			System.out.println("\t\t\t\t\t\t\t============================ MENU ============================");
			System.out.println("\t\t\t\t\t\t\t\t\t\t1.ADD MARK.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t2.UPDATE MARK.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t3.DELETE MARK.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t4.SHOW MARK TABLE.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t0.EXIT");
			System.out.print("\t\t\t\t\t\t\t\t\t>>> [YOU CHOOSE FUNCTION] : ");
			int select = Integer.parseInt(sc.nextLine());
			System.out.println();
			switch (select) {
			case 1:
				addMark();

				break;
			case 2:
				updateMark();

				break;
			case 3:
				deleteMark();

				break;
			case 4:
				printList();

				break;
			case 0:
				return;
			default:
				System.out.println("! ! Please select the available functions");

			}
		} while (true);
	}

}
