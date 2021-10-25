package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import models.Student;
import models.Subjects;

public class SerializeFileSubject {
	static String path;
	public static boolean saveFile(ArrayList<Subjects> list)
	{
		String folderPath="D:\\DataOfFinallExam";
		File file=new File(folderPath);
		if(file.exists()) 
		{
			path="D:\\DataOfFinallExam\\SubjectDB.txt";
		}
		else 
		{
			file.mkdir();
			path="D:\\DataOfFinallExam\\SubjectDB.txt";
		}
		try {
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
	public static ArrayList<Subjects>readFile()
	{
		path="D:\\DataOfFinallExam\\SubjectDB.txt";
		ArrayList<Subjects>list=new ArrayList<Subjects>();
		try {
			FileInputStream fis=new FileInputStream(path);
			ObjectInputStream ois=new ObjectInputStream(fis);
			Object data=ois.readObject();
			list=(ArrayList<Subjects>) data;
			ois.close();
			fis.close();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		return list; 
	}
}
