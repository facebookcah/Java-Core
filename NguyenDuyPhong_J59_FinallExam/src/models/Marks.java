package models;

import java.io.Serializable;

public class Marks implements Serializable{
	private int studentCode;
	private int subjectCode;
	private float mark;
	
	public void display() {
		
		System.out.printf("\t\t\t\t\t\t\t\t\t%-15d	%-15d	%-5.2f%n",studentCode,subjectCode,mark);
	}
	public int getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(int studentCode) {
		this.studentCode = studentCode;
	}
	public int getSubjectCode() {
		return subjectCode;
	}
	public void setSubjectCode(int subjectCode) {
		this.subjectCode = subjectCode;
	}
	public float getMark() {
		return mark;
	}
	public void setMark(float mark) {
		this.mark = mark;
	}
	public Marks() {
		super();
	}
	public Marks(int studentCode, int subjectCode, float mark) {
		super();
		this.studentCode = studentCode;
		this.subjectCode = subjectCode;
		this.mark = mark;
	}
}
