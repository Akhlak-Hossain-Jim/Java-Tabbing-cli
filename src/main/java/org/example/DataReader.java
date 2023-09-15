package org.example;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class DataReader {

	public static void TournamentFilesLoader() {
	}

	public static ArrayList<String> ReadBiStr(String s) {
		ArrayList<String> data = new ArrayList<String>();
		try {
			if (new File(s).exists()) {
				FileInputStream f = new FileInputStream(s);
				DataInputStream file = new DataInputStream(f);

				while (file.available() > 0) {
					String a = file.readUTF();
					data.add(a);
				}
				file.close();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return data;
	}

	public static ArrayList<String> ReadTxtStr(String s) {
		ArrayList<String> data = new ArrayList<String>();
		File file = new File(s);
		try {
			if (file.exists()) {
				Scanner re = new Scanner(file);
				while (re.hasNext()) {
					String a = re.nextLine();
					data.add(a);
				}
				re.close();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return data;
	}

	public static void PrintBiStr(String s) {
		try {
			if (new File(s).exists()) {
				FileInputStream f = new FileInputStream(s);
				DataInputStream file = new DataInputStream(f);

				while (file.available() > 0) {
					String a = file.readUTF();
					System.out.println(a);
				}
				file.close();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void PrintTxtStr(String s) {
		File file = new File(s);
		try {
			if (file.exists()) {
				Scanner re = new Scanner(file);
				while (re.hasNext()) {
					String a = re.nextLine();
					System.out.println(a);
				}
				re.close();
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

}
