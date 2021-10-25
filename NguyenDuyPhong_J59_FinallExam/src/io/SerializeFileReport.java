package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import models.Student;
import models.StudentDetail;
import models.StudentDetail;

public class SerializeFileReport {
	static String path;
	public static boolean saveFile(ArrayList<StudentDetail> list)
	{		
		try {
			String folderPath="D:\\DataOfFinallExam";
			File file=new File(folderPath);
			if(file.exists()) 
			{
				path="D:\\DataOfFinallExam\\ReportDB.txt";
			}
			else 
			{
				file.mkdir();
				path="D:\\DataOfFinallExam\\ReportDB.txt";
			}
			FileOutputStream fos=new FileOutputStream(path);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
			fos.close();
			return true;
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		return false;
	}
	public static ArrayList<StudentDetail>readFile()
	{
		path="D:\\DataOfFinallExam\\ReportDB.txt";
		ArrayList<StudentDetail>list=new ArrayList<StudentDetail>();
		try {
			FileInputStream fis=new FileInputStream(path);
			ObjectInputStream ois=new ObjectInputStream(fis);
			Object data=ois.readObject();
			list=(ArrayList<StudentDetail>) data;
			ois.close();
			fis.close();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		return list; 
	}
}
