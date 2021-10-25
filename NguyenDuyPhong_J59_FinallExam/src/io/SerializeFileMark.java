package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import models.Marks;
import models.Student;
import models.Subjects;

public class SerializeFileMark {
	static String path;
	public static boolean saveFile(ArrayList<Marks> list)
	{
		String folderPath="D:\\DataOfFinallExam";
		File file=new File(folderPath);
		if(file.exists()) 
		{
			path="D:\\DataOfFinallExam\\MarkDB.txt";
		}
		else 
		{
			file.mkdir();
			path="D:\\DataOfFinallExam\\MarkDB.txt";
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
	public static ArrayList<Marks>readFile()
	{
		path="D:\\DataOfFinallExam\\MarkDB.txt";
		ArrayList<Marks>list=new ArrayList<Marks>();
		try {
			FileInputStream fis=new FileInputStream(path);
			ObjectInputStream ois=new ObjectInputStream(fis);
			Object data=ois.readObject();
			list=(ArrayList<Marks>) data;
			ois.close();
			fis.close();
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		return list; 
	}
}
