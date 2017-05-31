package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Random;

import org.apache.log4j.Logger;

import com.csvreader.CsvWriter;

public class POICSVWriter {
	private static final Logger log = Logger.getLogger(POICSVWriter.class);

	private String[][] content; // Ҫд�뵽 csv �е�����
	private String[][][] contents;// Ҫд�뵽CSV�е����ݣ��з�������£�ʹ�û���������д��
	private String csvName;// �ļ���
	private String csvPath;// �ļ�·��--�����ù���
	private OutputStream outputStream;
	private String charset = "GBK";
	// private boolean deleteTmpFileFlag = false;
	private char delimiter = ',';

	public POICSVWriter() {
	}

	/**
	 * 
	 * @param content
	 */
	// public POICSVWriter(String[][] content, String csvPath) {
	// this.content = content;
	// this.csvName = getRandomString(2) + ".csv";
	// }

	/**
	 * ע���ļ�����׺
	 * 
	 * @param fileName
	 * @param content
	 */
	public POICSVWriter(String fileName, String[][] content, String csvPath) {
		this.content = content;
		this.csvName = fileName + ".csv";
		this.csvPath = csvPath;
	}

	public POICSVWriter(String[][] content, String csvPath) {
		this.content = content;
		this.csvName = getRandomString(2) + ".csv";
		this.csvPath = csvPath;
	}

	public POICSVWriter(String[][][] contents, String csvPath) {
		this.contents = contents;
		this.csvName = getRandomString(2) + ".csv";
		this.csvPath = csvPath;
	}

	public POICSVWriter(String[][] content, char delimiter, String charset,
			String csvPath) {
		this.content = content;
		this.delimiter = delimiter;
		this.charset = charset;
		this.csvName = getRandomString(2) + ".csv";
		this.csvPath = csvPath;
	}

	/**
	 * �Զ����ŵ�·�����ļ���
	 * 
	 * @param content
	 * @param csvName
	 * @param csvPath
	 */
	public POICSVWriter(String[][] content, String csvName, String csvPath) {
		this.content = content;
		this.csvName = csvName;
		this.csvPath = csvPath;
	}

	/**
	 * 
	 * @param content
	 * @param outputStream
	 */
	public POICSVWriter(String[][] content, OutputStream outputStream) {
		this.content = content;
		this.outputStream = outputStream;
		this.csvName = getRandomString(2) + ".csv";
	}

	/**
	 * ����CSV�ļ��������ļ�����
	 * 
	 * @return
	 * @throws IOException
	 */
	public InputStream getCSVFile() throws IOException {
		CsvWriter csvWriter = null;
		InputStream is = null;
		File pathDir = new File(csvPath);
		if (!pathDir.exists()) {
			pathDir.mkdirs();
		}
		if (null == outputStream) {
			csvWriter = new CsvWriter(csvPath + csvName, delimiter, Charset
					.forName(charset));
			if (null == contents || contents.length == 0) {
				for (int i = 0; i < this.content.length; i++) {
					csvWriter.writeRecord(this.content[i]);
					// csvWriter.endRecord();
				}
			} else {
				for (int fl = 0; fl < this.contents.length; fl++) {
					this.content = this.contents[fl];
					for (int i = 0; i < this.content.length; i++) {
						csvWriter.writeRecord(this.content[i]);
					}
					csvWriter.endRecord();
				}
			}

			csvWriter.close();
		} else {
			csvWriter = new CsvWriter(outputStream, delimiter, Charset
					.forName(charset));
			if (null == contents || contents.length == 0) {
				for (int i = 0; i < this.content.length; i++) {
					csvWriter.writeRecord(this.content[i]);
					// csvWriter.endRecord();
				}
			} else {
				for (int fl = 0; fl < this.contents.length; fl++) {
					this.content = this.contents[fl];
					for (int i = 0; i < this.content.length; i++) {
						csvWriter.writeRecord(this.content[i]);
					}
					csvWriter.endRecord();
				}
			}
			csvWriter.close();
		}
		is = new FileInputStream(new File(csvPath + csvName));
		return is;
	}

	/**
	 * ����CSV�ļ�����������ļ���·��
	 * 
	 * @return
	 * @throws IOException
	 */
	public String saveCSVFile() throws IOException {
		CsvWriter csvWriter = null;
		File pathDir = new File(csvPath);
		if (!pathDir.exists()) {
			pathDir.mkdirs();
		}
		if (null == outputStream) {
			csvWriter = new CsvWriter(csvPath + csvName, delimiter, Charset
					.forName(charset));
			if (null == contents || contents.length == 0) {
				for (int i = 0; i < this.content.length; i++) {
					csvWriter.writeRecord(this.content[i]);
					// csvWriter.endRecord();
				}
			} else {
				for (int fl = 0; fl < this.contents.length; fl++) {
					this.content = this.contents[fl];
					for (int i = 0; i < this.content.length; i++) {
						csvWriter.writeRecord(this.content[i]);
					}
					csvWriter.endRecord();
				}
			}
			csvWriter.close();
		} else {
			csvWriter = new CsvWriter(outputStream, delimiter, Charset
					.forName(charset));
			if (null == contents || contents.length == 0) {
				for (int i = 0; i < this.content.length; i++) {
					csvWriter.writeRecord(this.content[i]);
					// csvWriter.endRecord();
				}
			} else {
				for (int fl = 0; fl < this.contents.length; fl++) {
					this.content = this.contents[fl];
					for (int i = 0; i < this.content.length; i++) {
						csvWriter.writeRecord(this.content[i]);
					}
					csvWriter.endRecord();
				}
			}
			csvWriter.close();
		}
		return csvPath + csvName;
	}

	public String[][] getContent() {
		return content;
	}

	public void setContent(String[][] content) {

		this.content = content;
	}

	public String getCsvName() {
		return csvName;
	}

	public void setCsvName(String csvName) {
		this.csvName = csvName;
	}

	public String getCsvPath() {
		return csvPath;
	}

	public void setCsvPath(String csvPath) {
		this.csvPath = csvPath;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	// public boolean isDeleteTmpFileFlag() {
	// return deleteTmpFileFlag;
	// }
	//
	// public void setDeleteTmpFileFlag(boolean deleteTmpFileFlag) {
	// this.deleteTmpFileFlag = deleteTmpFileFlag;
	// }

	public char getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(char delimiter) {
		this.delimiter = delimiter;
	}

	public String[][][] getContents() {
		return contents;
	}

	public void setContents(String[][][] contents) {
		this.contents = contents;
	}

	private String getRandomString(int length) {
		StringBuffer sb = new StringBuffer();
		sb.append(System.currentTimeMillis());
		String base = "abcdefghijklmnopqrstuvwxyz"; // ����ַ�Ӵ�������ȡ
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}

		return sb.toString();
	}

	public static void main(String[] args) {
		final String[] header = new String[] { "name", "sex", "age" };
		final String[][] data = new String[][] { header, { "Lucy", "Ů", "22" },
				{ "Tom", "��", "25" }, { "Lily", "Ů", "19" } };
		final String[][][] datas = new String[3][][];
		datas[0] = data;
		datas[1] = data;
		datas[2] = data;
		POICSVWriter writer = new POICSVWriter(datas, "F:/csvFile/temp/");
		// POICSVWriter writer = new POICSVWriter(data, "F:/csvFile/temp/");
		try {
			String pathName = writer.saveCSVFile();
			System.out.println(pathName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
