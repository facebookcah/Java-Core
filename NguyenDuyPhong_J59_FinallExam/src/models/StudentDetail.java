package models;

import java.io.Serializable;
import java.util.Comparator;

public class StudentDetail implements Serializable{
	private int StudentCode;
	private String sureName;
	private String name;
	private int subjectCode;
	private String subjectName;
	private float coefficient;
	private float mark;

	public int getStudentCode() {
		return StudentCode;
	}

	public void setStudentCode(int studentCode) {
		StudentCode = studentCode;
	}

	public String getSureName() {
		return sureName;
	}

	public void setSureName(String sureName) {
		this.sureName = sureName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public float getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(float coefficient) {
		this.coefficient = coefficient;
	}

	public float getMark() {
		return mark;
	}

	public void setMark(float mark) {
		this.mark = mark;
	}

	public StudentDetail() {
		super();
	}

	public StudentDetail(int studentCode, String sureName, String name, int subjectCode, String subjectName,
			float coefficient, float mark) {
		super();
		StudentCode = studentCode;
		this.sureName = sureName;
		this.name = name;
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.coefficient = coefficient;
		this.mark = mark;
	}

	public void display() {

		System.out.printf("\t\t\t\t\t\t\t\t\t%15d		%-20s	%-12s	%5.2f%n", subjectCode, subjectName, coefficient, mark);
	}
	public void displayStudent() {

		System.out.printf("\t\t\t\t\t\t\t\t\t%-10d	%-20s	%-12s	%5.2f%n", StudentCode,sureName, name, mark);
	}

}
