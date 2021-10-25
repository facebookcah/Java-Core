package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import models.Student;

public class SerializeFile {
	static String path;
	public static boolean saveFile(ArrayList<Student> list)
	{
		
		try {
			String folderPath="D:\\DataOfFinallExam";
			File file=new File(folderPath);
			if(file.exists()) 
			{
				path="D:\\DataOfFinallExam\\StudentDB.txt";
			}
			else 
			{
				file.mkdir();
				path="D:\\DataOfFinallExam\\StudentDB.txt";
			}
			FileOutputStream fos=new FileOutputStream(path);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
			fos.close();
			return true;
		}
		catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		return false;
	}
	public static ArrayList<Student>readFile()
	{
		path="D:\\DataOfFinallExam\\StudentDB.txt";
		ArrayList<Student>list=new ArrayList<Student>();
		try {
			FileInputStream fis=new FileInputStream(path);
			ObjectInputStream ois=new ObjectInputStream(fis);
			Object data=ois.readObject();
			list=(ArrayList<Student>) data;
			ois.close();
			fis.close();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		return list; 
	}
}
