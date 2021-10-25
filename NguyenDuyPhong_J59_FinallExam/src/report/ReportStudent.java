package report;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import io.SerializeFileReport;
import manager.MarkManager;
import manager.StudentManager;
import manager.SubjectManager;
import models.Marks;
import models.Student;
import models.Subjects;
import models.StudentDetail;

public class ReportStudent {
	public static ArrayList<Student> listStudent = new ArrayList<Student>();;
	public static ArrayList<Subjects> listSubject = new ArrayList<Subjects>();;
	public static ArrayList<Marks> listMark = new ArrayList<Marks>();

	static Scanner sc = new Scanner(System.in);
	public static ArrayList<StudentDetail> listStudentDetail = new ArrayList<StudentDetail>();

	public static ArrayList<StudentDetail> getData() {
		listStudent = StudentManager.readFile();
		listSubject = SubjectManager.readFile();
		listMark = MarkManager.readFile();
		int m = listMark.size();
		int n = listStudent.size();
		int p = listSubject.size();
		StudentDetail s;
		sortList(true);
		for (int i = 0; i < n; i++) {

			int studentCode = listStudent.get(i).getStudentCode();
			String sureName = listStudent.get(i).getSurnameBuffer();
			String name = listStudent.get(i).getName();

			for (int j = 0; j < m; j++) {

				if (listMark.get(j).getStudentCode() == listStudent.get(i).getStudentCode()) {

					for (int k = 0; k < p; k++) {
						if (listSubject.get(k).getSubjectCode() == listMark.get(j).getSubjectCode()) {
							int subjectCode = listSubject.get(k).getSubjectCode();
							String subjectName = listSubject.get(k).getSubjectName();
							float coefficient = listSubject.get(k).getCoefficient();
							float mark = listMark.get(j).getMark();
							s = new StudentDetail(studentCode, sureName, name, subjectCode, subjectName, coefficient,
									mark);
							listStudentDetail.add(s);
							saveFile();

						}
					}

				}

			}

		}
		return listStudentDetail;

	}

	public static boolean saveFile() {
		return SerializeFileReport.saveFile(listStudentDetail);
	}

	public static ArrayList<StudentDetail> readFile() {
		listStudentDetail = SerializeFileReport.readFile();
		return listStudentDetail;
	}

	public static void sortList(boolean asc) {
		int oriented = asc ? 1 : -1;
		Collections.sort(listStudent, new Comparator<Student>() {

			@Override
			public int compare(Student o1, Student o2) {

				return oriented * (o1.getStudentCode() - o2.getStudentCode());
			}

		});

	}

	public static void studentDetail() {
		
		listStudent = StudentManager.readFile();
		listStudentDetail = getData();

		int n = listStudent.size();

		int h = listStudentDetail.size();
		for (int i = 0; i < n; i++) {
			System.out.println("\t\t\t\t\t\t\t\t========================== MARK TABLE OF " + listStudent.get(i).getSurnameBuffer()+listStudent.get(i).getName()
					+ "==========================");
			System.out.println("\t\t\t\t\t\t\t\t\tStudent's code : " + listStudent.get(i).getStudentCode());
			System.out.println("\t\t\t\t\t\t\t\t\tFull name : " + listStudent.get(i).getSurnameBuffer() + " "
					+ listStudent.get(i).getName());
			System.out.println();
			System.out.printf("\t\t\t\t\t\t\t\t\t%15s		%-20s	%-12s	%-5s%n", "[Subject code]", "[Subject name]",
					"[Coefficient]", "[Mark]");

			int count = 0;
			int sumCoeff = 0;
			float sum = 0;
			float avg = 0;

			for (int j = 0; j < h; j++) {
				if (listStudentDetail.get(j).getStudentCode() == listStudent.get(i).getStudentCode()) {
					listStudentDetail.get(j).display();
					sum += listStudentDetail.get(j).getCoefficient() * listStudentDetail.get(j).getMark();
					sumCoeff += listStudentDetail.get(j).getCoefficient();
					avg = sum / sumCoeff;
					count++;
				}

			}

			if (count == 0) {
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[Student has no score yet]");
				System.out.println();
			} else {
				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t[FINAL GRADE : %5.2f]%n", avg);
				System.out.println();
			}

		}
		listStudentDetail.clear();
	}

	public static void subjectDetail() {
		listSubject = SubjectManager.readFile();
		listStudentDetail = getData();
		int h = listStudentDetail.size();
		int p = listSubject.size();
		for (int i = 0; i < p; i++) {
			System.out.println("\t\t\t\t\t\t\t\t========================== SUBJECT TABLE OF SUBJECT'S CODE " + listSubject.get(i).getSubjectName()
					+ "==========================");
			System.out.println("\t\t\t\t\t\t\t\t\tSubject's code : " + listSubject.get(i).getSubjectCode());
			System.out.println("\t\t\t\t\t\t\t\t\tSubject name : " + listSubject.get(i).getSubjectName());
			System.out.println();
			System.out.printf("\t\t\t\t\t\t\t\t%15s		%-20s	%-12s	%-5s%n", "[Student code]", "[SureName]",
					"[Name]", "[Mark]");

			int count = 0;

			float sumMark = 0;
			float avgMarks = 0;

			for (int j = 0; j < h; j++) {
				if (listStudentDetail.get(j).getSubjectCode() == listSubject.get(i).getSubjectCode()) {
					listStudentDetail.get(j).displayStudent();
					sumMark += listStudentDetail.get(j).getMark();
					count++;
					avgMarks = sumMark / count;
				}

			}

			if (count == 0) {
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[Student has no score yet]");
				System.out.println();
			} else {
				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t[AVERAGE GRADE : %5.2f]%n", avgMarks);
				System.out.println();
			}

		}
		listStudentDetail.clear();
	}

	public static void searchStudentDetail() {

		listStudent = StudentManager.readFile();
		listStudentDetail = getData();
		int h = listStudentDetail.size();
		int m = listMark.size();
		int n = listStudent.size();
		int p = listSubject.size();
		System.out.print("Enter student's code you want to search : ");
		int searchCode = Integer.parseInt(sc.nextLine());
		int count = 0;
		int index = -1;
		for (int i = 0; i < n; i++) {
			if (listStudent.get(i).getStudentCode() == searchCode) {
				index = i;
				count++;
			}

		}
		if (count != 0) {
			System.out.println(
					"\t\t\t\t\t\t\t\t========================== MARK TABLE OF STUDENT ==========================");
			System.out.println("\t\t\t\t\t\t\t\t\tStudent's code : " + listStudent.get(index).getStudentCode());
			System.out.println("\t\t\t\t\t\t\t\t\tFull name : " + listStudent.get(index).getSurnameBuffer() + " "
					+ listStudent.get(index).getName());
			System.out.println();
			System.out.printf("\t\t\t\t\t\t\t\t\t%15s		%-20s	%-12s	%-5s%n", "[Subject code]", "[Subject name]",
					"[Coefficient]", "[Mark]");

			int countSubject = 0;
			int sumCoeff = 0;
			float sum = 0;
			float avg = 0;

			for (int j = 0; j < h; j++) {
				if (listStudentDetail.get(j).getStudentCode() == listStudent.get(index).getStudentCode()) {
					listStudentDetail.get(j).display();
					sum += listStudentDetail.get(j).getCoefficient() * listStudentDetail.get(j).getMark();
					sumCoeff += listStudentDetail.get(j).getCoefficient();
					avg = sum / sumCoeff;
					countSubject++;
				}

			}

			if (countSubject == 0) {
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t\t[This student has not taken any subjects yet]");
				System.out.println();
			} else {
				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t[FINAL GRADE : %5.2f]%n", avg);
				System.out.println();
			}
			listStudentDetail.clear();
		} else
			System.out.println("! ! This student dose not exist");
	}

	public static void searchSubjectDetail() {

		listSubject = SubjectManager.readFile();
		listStudentDetail = getData();
		int h = listStudentDetail.size();
		int m = listMark.size();
		int n = listStudent.size();
		int p = listSubject.size();
		System.out.print("Enter subject's code you want to search : ");
		int searchCode = Integer.parseInt(sc.nextLine());
		int count = 0;
		int index = -1;
		for (int i = 0; i < p; i++) {
			if (listSubject.get(i).getSubjectCode() == searchCode) {
				index = i;
				count++;
			}

		}
		if (count != 0) {
			System.out.println(
					"\t\t\t\t\t\t\t\t========================== SUBJECT TABLE OF STUDENT ==========================");
			System.out.println("\t\t\t\t\t\t\t\tSubject's code : " + listSubject.get(index).getSubjectCode());
			System.out.println("\t\t\t\t\t\t\t\tSubject's name : " + listSubject.get(index).getSubjectName());
			System.out.println();
			System.out.printf("\t\t\t\t\t\t\t\t%15s		%-20s	%-12s	%-5s%n", "[Student code]", "[SureName]",
					"[Name]", "[Mark]");

			int countStudent = 0;
			float sumMarks = 0;
			float avgMarks = 0;

			for (int j = 0; j < h; j++) {
				if (listStudentDetail.get(j).getSubjectCode() == listSubject.get(index).getSubjectCode()) {
					listStudentDetail.get(j).displayStudent();
					sumMarks += listStudentDetail.get(j).getMark();
					countStudent++;
					avgMarks = sumMarks / countStudent;
				}

			}

			if (countStudent == 0) {
				System.out.println();
				System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t[No students have taken this course yet]");
				System.out.println();
			} else {
				System.out.println();
				System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t[AVERAGE GRADE : %5.2f]%n", avgMarks);
				System.out.println();
			}
		} else {
			System.out.println("This subject dose not exist :(");
		}
		listStudentDetail.clear();
	}

}
