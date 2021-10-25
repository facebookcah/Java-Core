package models;

import java.io.Serializable;
import java.util.Comparator;

public class Student implements Serializable,Comparable<Student>{
	private int studentCode;
	private String surnameBuffer;
	private String name;
	private int yearOfBirth;
	private String gender;
	public Student(int studentCode, String surnameBuffer, String name, int yearOfBirth, String gender) {
		super();
		this.studentCode = studentCode;
		this.surnameBuffer = surnameBuffer;
		this.name = name;
		this.yearOfBirth = yearOfBirth;
		this.gender = gender;
	}
	public Student() {
		super();
	}
	public int getStudentCode() {
		return studentCode;
	}
	public void setStudentCode(int studentCode) {
		this.studentCode = studentCode;
	}
	public String getSurnameBuffer() {
		return surnameBuffer;
	}
	public void setSurnameBuffer(String surnameBuffer) {
		this.surnameBuffer = surnameBuffer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYearOfBirth() {
		return yearOfBirth;
	}
	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void display() {
		
		System.out.printf("%15d		%-20s %-12s %15d 	%-10s%n",studentCode,surnameBuffer,name,yearOfBirth,gender);
	}
	@Override
	public int compareTo(Student o) {
		
		return this.name.compareToIgnoreCase(o.name);
	}
	
}
