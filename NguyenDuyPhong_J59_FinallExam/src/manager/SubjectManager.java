package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import io.SerializeFile;
import io.SerializeFileSubject;
import models.Marks;
import models.Student;
import models.Subjects;

public class SubjectManager {
	static Scanner sc = new Scanner(System.in);
	public static ArrayList<Subjects> list = new ArrayList<Subjects>();
	public static ArrayList<Student> listStudent = new ArrayList<Student>();;
	public static ArrayList<Marks> listMark = new ArrayList<Marks>();

	public static int checkCode() {
		if(list.isEmpty()) return 1;
		int maxCode = list.get(0).getSubjectCode();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSubjectCode() > maxCode)
				maxCode = list.get(i).getSubjectCode();
		}
		return maxCode + 1;
	}

	public static void addSubject() {
		int code = 0;
		System.out.println("How many subject do you want to enter?");
		int n = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < n; i++) {
			code=checkCode();
			System.out.println("**Enter subject " + (i + 1));
			System.out.print("Enter subject's name : ");
			String subjectName = sc.nextLine();
			System.out.println();
			System.out.print("Enter coefficient : ");
			float coefficient = Integer.parseInt(sc.nextLine());
			System.out.println();
			Subjects subject = new Subjects(code, subjectName, coefficient);
			list.add(subject);
			saveFile();
		}

	}

	public static boolean saveFile() {
		return SerializeFileSubject.saveFile(list);
	}

	public static ArrayList<Subjects> readFile() {
		list = SerializeFileSubject.readFile();
		return list;
	}

	public static void printList() {
		for (Subjects sb : list) {
			System.out.println(sb);
		}
	}

	public static void updateSubject() {
		System.out.print("Enter subject's code do you want update : ");
		int code = Integer.parseInt(sc.nextLine());
		System.out.println();
		int index = -1;
		int find = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSubjectCode() == code) {
				index = i;
				find = 1;
			}
		}
		if (find == 0)
			System.out.println("! ! This subject dose not exist");
		else {
			do {
				System.out.println(
						"\t\t\t\t\t\t\t============================ UPDATE SUBJECT ============================");
				System.out.println("\t\t\t\t\t\t\t\t\t\t1.EDIT SUBJECT'S NAME.");
				System.out.println("\t\t\t\t\t\t\t\t\t\t2.EDIT SUBJECT'S COEFFICIENT.");
				System.out.println("\t\t\t\t\t\t\t\t\t\t0.EXIT");
				System.out.print("\t\t\t\t\t\t\t\t\t\t>>>[YOU CHOOSE FUNCTION] : ");
				int select = Integer.parseInt(sc.nextLine());
				switch (select) {
				case 1: {
					System.out.print("Enter new subject's name : ");
					String newName = sc.nextLine();
					System.out.println();
					list.get(index).setSubjectName(newName);
					saveFile();
					break;
				}
				case 2: {
					System.out.print("Enter new coefficient : ");
					float newCoff = Float.parseFloat(sc.nextLine());
					System.out.println();
					list.get(index).setCoefficient(newCoff);
					saveFile();
					break;
				}

				case 0: {
					return;
				}
				default:
					System.out.println("! ! Please select the available functions");
				}

			} while (true);
		}
	}

	public static void deleteSubject() {

		System.out.print("Enter subject's code do you want delete : ");
		int code = Integer.parseInt(sc.nextLine());
		System.out.println();
		int index = -1;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getSubjectCode() == code) {
				index = i;
			}
		}
		if (index == -1)
			System.out.println("! ! This subject dose not exist");
		else {
			listMark=MarkManager.readFile();
			int count = 0;
			for (Marks mark : listMark) {
				if (mark.getStudentCode() == list.get(index).getSubjectCode()) {
					count++;
				}
			}
			if (count == 0) {
				System.out.println("\t\t\t\t\t\t\t\t\t[Are you sure you want to delete?]");
				System.out.println("\t\t\t\t\t\t\t\t\t\t1.[YES]\t\t2.[NO]");
				int select = Integer.parseInt(sc.nextLine());
				switch (select) {
				case 1: {
					list.remove(index);
					saveFile();
					System.out.println("\t\t\t\t\t\t\t\t\tDelete success :)");
					break;
				}
				case 2:
					return;

				default:
					return;
				}
			} else {
				System.out.println("! !This subject already has mark ");
			}
		}
	}

	public static void sortList() {
		if(list.isEmpty()) System.out.println("\t\t\t\t\t\t\t\t\t\t[LIST NULL]");
		else
		{
			int i = 1;
			Collections.sort(list);
			System.out.println("\t\t\t\t\t\t\t==================== SORTED LIST OF SUBJECT ====================");
			System.out.printf("\t\t\t\t\t\t\t\t%-3s	%12s	%-20s	%-12s%n", "[NO.]", "[CODE]", "[NAME]", "[COEFFICIENT]");

			for (Subjects subjects : list) {
				System.out.print("\t\t\t\t\t\t\t\t" + i);
				subjects.display();
				System.out.println();
				i++;
			}
		}
		
	}

	public static void manager() {
		do {
			readFile();
			System.out.println("\t\t\t\t\t\t\t============================ MENU ============================");
			System.out.println("\t\t\t\t\t\t\t\t\t\t1.ADD SUBJECT.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t2.UPDATE SUBJECT.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t3.DELETE SUBJECT.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t4.SHOW SORTED LIST OF SUBJECT.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t0.EXIT");
			System.out.print("\t\t\t\t\t\t\t\t\t\t>>>[YOU CHOOSE FUNCTION] : ");
			int select = Integer.parseInt(sc.nextLine());
			System.out.println();
			switch (select) {
			case 1:
				addSubject();

				break;
			case 2:
				updateSubject();

				break;
			case 3:
				deleteSubject();
				break;
			case 4:
				sortList();
				break;
			case 0:
				return;
				default:
					System.out.println("! ! Please select the available functions");

			}
		} while (true);
	}

}
