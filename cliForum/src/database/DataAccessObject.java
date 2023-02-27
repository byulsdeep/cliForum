package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class DataAccessObject {
	File file;
	FileReader reader;
	FileWriter writer;
	BufferedReader bReader;
	BufferedWriter bWriter;
	
	public String getUsersId() {
		StringBuffer sb = new StringBuffer();
		String record = null;
		try {
			//admin,admin,19940602,1,양화초등학교 ->
			//admin,id2,id3,id4.....
			for (int i = 0; (record = bReader.readLine()) != null; i++) {
				sb.append((i > 0) ? "," : "");
				sb.append(record.substring(0, record.indexOf(",")));
			}
		} catch (Exception e) {
		}
		return sb.toString();
	}

	public boolean fileConnected(boolean readOrWrite, String fileName, boolean append) {
		boolean result;	
		String ap = new File("").getAbsolutePath();
		file = new File(ap + fileName);
		try {
			if (readOrWrite) {
				bReader = new BufferedReader(new FileReader(file));
			} else {
				if (append) {
					writer = new FileWriter(file, true); // 파일 내용 추가
				} else {
					writer = new FileWriter(file); // 파일 내용 덮어쓰기
				}
				bWriter = new BufferedWriter(writer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		result = true;
		return result;
	}

	public void fileClose(boolean readOrWrite) {
		if (readOrWrite) {
			try {
				if (reader != null)
					reader.close();
			} catch (Exception e) {
			}
			try {
				if (bReader != null)
					bReader.close();
			} catch (Exception e2) {
			}

		} else {
			try {
				if (writer != null)
					writer.close();
			} catch (Exception e) {
			}
			try {
				if (bWriter != null)
					bWriter.close();
			} catch (Exception e2) {
			}

		}
	}
}
