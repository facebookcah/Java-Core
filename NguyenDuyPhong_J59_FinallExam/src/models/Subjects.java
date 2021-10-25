package models;

import java.io.Serializable;

public class Subjects implements Serializable,Comparable<Subjects>{
	private int subjectCode;
	private String subjectName;
	private float coefficient;
	public Subjects(int subjectCode, String subjectName, float coefficient) {
		super();
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.coefficient = coefficient;
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
	public Subjects() {
		super();
	}

	public void display() {
		
		System.out.printf("%15d	%-20s	%-12s",subjectCode,subjectName,coefficient);
	}
	@Override
	public int compareTo(Subjects o) {
		
		return this.subjectName.compareToIgnoreCase(o.subjectName);
	}
}
