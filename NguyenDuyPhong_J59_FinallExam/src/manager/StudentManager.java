package manager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import io.SerializeFile;
import models.Marks;
import models.Student;
import models.Subjects;

public final class StudentManager {
	static Scanner sc = new Scanner(System.in);
	

	public static ArrayList<Student> list = new ArrayList<Student>();
	public static ArrayList<Marks> listMark = new ArrayList<Marks>();
	
	public static int checkCode()
	{
		if(list.isEmpty()) return 1;
		int maxCode=list.get(0).getStudentCode();
		for(int i=0;i<list.size();i++)
		{
			if(list.get(i).getStudentCode()>maxCode)
			maxCode=list.get(i).getStudentCode();
		}
		return maxCode+1;
	}
	public static void addStudent() {
		int code=0;
		
		System.out.println("How many students do you want to enter?");
		int n = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < n; i++) {
			code=checkCode();
			System.out.println("**Enter student " + (i + 1));
			System.out.print("Enter surname buffer : ");
			String surenameBuffer = sc.nextLine();
			System.out.println();
			System.out.print("Enter name : ");
			String name = sc.nextLine();
			System.out.println();
			System.out.print("Enter year of birth : ");
			int yearOfBirth = Integer.parseInt(sc.nextLine());
			System.out.println();
			System.out.print("Enter gender : ");
			String gender = sc.nextLine();
			System.out.println();
			Student student = new Student(code, surenameBuffer, name, yearOfBirth, gender);
			list.add(student);
			saveFile();
		}

	}

	public static boolean saveFile() {
		return SerializeFile.saveFile(list);
	}

	public static ArrayList<Student> readFile() {
		list = SerializeFile.readFile();
		return list;
	}

	public static void printList() {
		for (Student student : list) {
			System.out.println(student);
		}
	}

	public static void updateStudent() {
		System.out.print("Enter student's code do you want update : ");
		int code = Integer.parseInt(sc.nextLine());
		System.out.println();
		int index = -1;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStudentCode() == code) {
				index = i;

			}
		}
		if (index == -1)
			System.out.println("! ! Student dose not exist");
		else {
			do {
				System.out.println("\t\t\t\t\t\t\t============================ UPDATE STUDENT ============================");
				System.out.println("\t\t\t\t\t\t\t\t\t\t1.EDIT SURENAME BUFFER.");
				System.out.println("\t\t\t\t\t\t\t\t\t\t2.EDIT NAME.");
				System.out.println("\t\t\t\t\t\t\t\t\t\t3.EDIT YEAR OF BIRTH.");
				System.out.println("\t\t\t\t\t\t\t\t\t\t4.EDIT GENDER.");
				System.out.println("\t\t\t\t\t\t\t\t\t\t0.EXIT");
				System.out.print("\t\t\t\t\t\t\t\t\t\t>>>[YOU CHOOSE FUNCTION] : ");
				int select = Integer.parseInt(sc.nextLine());
				System.out.println();
				switch (select) {
				case 1: {
					System.out.print(">>>Enter new surename bufer : ");
					String sb = sc.nextLine();
					System.out.println();
					list.get(index).setSurnameBuffer(sb);
					saveFile();
					break;
				}
				case 2: {
					System.out.print(">>>Enter new name : ");
					String name = sc.nextLine();
					System.out.println();
					list.get(index).setName(name);
					saveFile();
					break;
				}
				case 3: {
					System.out.print(">>>Enter new year of birth : ");
					int yob = Integer.parseInt(sc.nextLine());
					System.out.println();
					list.get(index).setYearOfBirth(yob);
					saveFile();
					break;
				}
				case 4: {
					System.out.print(">>>Enter new gender : ");
					String gender = sc.nextLine();
					System.out.println();
					list.get(index).setGender(gender);
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

	public static void deleteStudent() {
		System.out.print("Enter student's code do you want delete : ");
		int code = Integer.parseInt(sc.nextLine());
		System.out.println();
		int index = -1;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStudentCode() == code) {
				index = i;

			}
		}
		if (index == -1)
			System.out.println("! !Student dose not exist");
		else {
			listMark=MarkManager.readFile();
			int count = 0;
			for (Marks mark : listMark) {
				if (mark.getStudentCode() == list.get(index).getStudentCode()) {
					count++;
				}
			}
			if (count == 0) {
				System.out.println("\t\t\t\t\t\t\t\t\t[Are you sure you want to delete?]");
				System.out.println("\t\t\t\t\t\t\t\t\t\t1.[Yes]\t\t2.[No]");
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
				System.out.println("! ! This student already has mark ");
			}
		}
	}

	public static void sortList() {
		if(list.isEmpty()) System.out.println("\t\t\t\t\t\t\t\t\t\t[LIST NULL]");
		else
		{
			int i = 1;
			Collections.sort(list);
			System.out.println("\t\t\t\t\t\t\t======================== SORTED LIST OF STUDENT ========================");
			System.out.println();
			System.out.printf("\t\t\t\t\t\t\t%-3s %12s		%-20s %-12s %-13s	%-10s%n", "NO.", "[CODE]", "[SURENAME BUFFER]", "[NAME]",
					"[YEAR OF BIRTH]", "[GENDER]");
			for (Student student : list) {
				System.out.print("\t\t\t\t\t\t\t"+i);
				student.display();
				i++;
			}
		}

	}

	public static void manager()
	{
		do {
			readFile();
			System.out.println("\t\t\t\t\t\t\t============================ MENU ============================");
			System.out.println("\t\t\t\t\t\t\t\t\t\t1.ADD STUDENT.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t2.UPDATE STUDENT.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t3.DELETE STUDENT.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t4.SHOW SORTED OF LIST STUDENT.");
			System.out.println("\t\t\t\t\t\t\t\t\t\t0.EXIT");
			System.out.print("\t\t\t\t\t\t\t\t\t\t>>>[YOU CHOOSE FUNCTION] : ");
			int select = Integer.parseInt(sc.nextLine());
			System.out.println();
			switch (select) {
			case 1:
				addStudent();
				break;
			case 2:
				updateStudent();
				break;
			case 3:
				deleteStudent();
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
