package skilltimeline.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Io {
	/**
	 * Read a text file into a String array (assumes the file size is
	 * 'reasonable').
	 * 
	 * @param name
	 *            File name
	 * @return Array of Strings representing lines in the file.
	 */
	public static String[] readFile(String name) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(name)));
		ArrayList<String> data = new ArrayList<String>();
		String s;
		while ((s = br.readLine()) != null) {
			data.add(s);
		}
		return data.toArray(new String[data.size()]);
	}

	/**
	 * Write a text file.
	 * 
	 * @param name
	 *            Filename
	 * @param contents
	 *            Data to write
	 * @throws IOException
	 */
	public static void writeFile(String name, String contents) throws IOException {
		BufferedWriter wr = new BufferedWriter(new FileWriter(name));
		try {
			wr.write(contents);
			wr.flush();
		} finally {
			wr.close();

		}
	}
}
