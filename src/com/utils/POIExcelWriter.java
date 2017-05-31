package com.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;

public class POIExcelWriter {

	private static final Logger log = Logger.getLogger(POIExcelWriter.class);

	private String[][] content; // Ҫд�뵽 Sheet �е�����
	private int beginRow = 0; // Ҫд��� Sheet �Ŀ�ʼ�У� Ĭ��Ϊ��0��
	private int beginCol = 0; // Ҫд��� Sheet �Ŀ�ʼ�У� Ĭ��Ϊ��0��
	private String index; // Ҫд��� .xls �ļ����Ǹ� Sheet �����֣� Ĭ��Ϊ��0��(Sheet1)
	private String[] sheets; // д���.xls�ļ���Sheet�����֣������Sheet1��Sheet2....��//0, 1,
	private String[][][] contents;
	private String[][] mergeTitles; // ��һ��String��Ҫд����ַ��ڶ����Ǻϲ���Ԫ�������ַ���ʽΪxx,xx,xx,xx,xxΪ������

	// 2.....

	public String[][][] getContents() {
		return contents;
	}

	public void setContents(String[][][] contents) {
		this.contents = contents;
	}

	public String[] getSheets() {
		return sheets;
	}

	public void setSheets(String[] sheets) {
		this.sheets = sheets;
	}

	/**
	 * URL Ҫд����Ǹ� .xls �ļ��ĵ�ַ content Ҫд�뵽 Sheet �е�����
	 */
	public POIExcelWriter(String[][] content) {
		this.content = content;
	}

	/**
	 * URL Ҫд����Ǹ� x.ls �ļ��ĵ�ַ content Ҫд�뵽 Sheet �е����� index Ҫд����Ǹ� Sheet ��
	 * Ĭ��Ϊ��һ��(Sheet1)
	 */
	public POIExcelWriter(String[][] content, String index) {
		this(content);
		this.index = index;
	}

	public POIExcelWriter(String[][][] contentsin, String index,
			String[][] mergeTitles) {
		this.contents = contentsin;
		this.index = index;
		this.mergeTitles = mergeTitles;
	}

	/**
	 * URL Ҫд����Ǹ� x.ls �ļ��ĵ�ַ content Ҫд�뵽 Sheet �е����� index Ҫд����Ǹ� Sheet ��
	 * Ĭ��Ϊ��һ��(Sheet1)
	 */
	public POIExcelWriter(String[][] content, String index,
			String[][] mergeTitles) {
		this(content);
		this.index = index;
		this.mergeTitles = mergeTitles;
	}

	/**
	 * URL Ҫд����Ǹ� x.ls �ļ��ĵ�ַ contents Ҫд�뵽 Sheet �е����� index Ҫд����Ǹ� Sheet
	 * 
	 */
	public POIExcelWriter(String[][][] contentsin, String[] sheetsin) {
		this.contents = contentsin;
		this.sheets = sheetsin;
		// this.sheets = new String[sheetstt.length];
		// for (int i = 0; i < sheetstt.length; i++) {
		// this.sheets[i] = sheetstt[i];
		// }
	}

	/**
	 * URL Ҫд����Ǹ� .xls �ļ��ĵ�ַ content Ҫд�뵽 Sheet �е����� index Ҫд����Ǹ� Sheet ��
	 * Ĭ��Ϊ��һ��(Sheet1) beginRow Ҫд��� Sheet �Ŀ�ʼ�� beginCol Ҫд��� Sheet �Ŀ�ʼ��
	 */
	public POIExcelWriter(String[][] content, String index, int beginRow,
			int beginCol) {
		this(content, index);
		this.beginRow = beginRow;
		this.beginCol = beginCol;
	}

	public String[][] getMergeTitles() {
		return mergeTitles;
	}

	public void setMergeTitles(String[][] mergeTitles) {
		this.mergeTitles = mergeTitles;
	}

	public String[][] getContent() {
		return content;
	}

	public void setContent(String[][] content) {
		this.content = content;
	}

	public int getBeginRow() {
		return beginRow;
	}

	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}

	public int getBeginCol() {
		return beginCol;
	}

	public void setBeginCol(int beginCol) {
		this.beginCol = beginCol;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	/**
	 * ����������List��pageSize�ı�׼��С���з�ҳ����
	 * */
	public InputStream getExcelFilePage(int pageSize)
			throws FileNotFoundException, IOException {
		// int pageSize = 2000;
		// �����µ� Excel ������
		HSSFWorkbook workbook = new HSSFWorkbook();
		// �� Excel �������н���һ��������,����Ϊȱʡֵ sheet1
		int count = content.length;
		int page = 0;
		int i = 0;
		int lastRow = 0;
		if (count % pageSize == 0) {
			page = count / pageSize;
		} else {
			page = (count / pageSize) + 1;
		}
		for (int p = 0; p < page; p++) {
			lastRow = lastRow + pageSize + 1;
			if (p == (page - 1)) {
				lastRow = count;
			}
			HSSFSheet sheet = workbook.createSheet("��" + p + "ҳ");
			HSSFFont font = workbook.createFont();
			font.setColor(Font.COLOR_NORMAL);
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			int beginRow = this.beginRow;
			int beginCol = this.beginCol;
			for (i = p * pageSize; i < lastRow; i++) {
				HSSFRow row = sheet.createRow(beginRow++);
				beginCol = this.beginCol;
				for (int j = 0; j < content[i].length; j++) {
					HSSFCell cell = row.createCell(beginCol++);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					if (i == 0 || i == p * pageSize) {// дXLS��ͷ
						cell.setCellStyle(cellStyle);
						cell.setCellValue(content[0][j]);
					} else {
						cell.setCellValue(content[i][j]);
					}

				}
			}
		}
		// �ֽ�����������
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		byte[] ba = baos.toByteArray();
		// �ֽ������������
		ByteArrayInputStream bais = new ByteArrayInputStream(ba);
		return bais;
	}

	public InputStream getExcelFile() throws FileNotFoundException, IOException {
		// �����µ� Excel ������
		HSSFWorkbook workbook = new HSSFWorkbook();
		// �� Excel �������н���һ��������,����Ϊȱʡֵ sheet1
		HSSFSheet sheet = workbook.createSheet(String.valueOf(index));
		// ��������
		HSSFFont font = workbook.createFont();
		// ��������ɫ����Ϊ��ɫ
		font.setColor(Font.COLOR_NORMAL);
		// ����������Ϊ����
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		// ������ʽ
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		// �Ѵ��������帶���ڸ�ʽ
		cellStyle.setFont(font);

		int beginRow = this.beginRow;
		int beginCol = this.beginCol;

		for (int i = 0; i < content.length; i++) {
			// �ڹ������д���һ��
			HSSFRow row = sheet.createRow(beginRow++);
			beginCol = this.beginCol;
			for (int j = 0; j < content[i].length; j++) {
				// ��һ���д���һ�����
				HSSFCell cell = row.createCell(beginCol++);

				if (i == 0) {
					// ������ĸ�ʽ������һ����Ԫ��
					cell.setCellStyle(cellStyle);
				}

				// ���ô˵�Ԫ���д�������ַ�
				cell.setCellType(Cell.CELL_TYPE_STRING);
				// ���ñ��� ���������������������� ���µ�JAR�����Զ�Unicode������ 10/02/25
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				// ��˵�Ԫ���з���ֵ
				cell.setCellValue(content[i][j]);
			}
		}

		// �ֽ�����������
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		byte[] ba = baos.toByteArray();
		// �ֽ������������
		ByteArrayInputStream bais = new ByteArrayInputStream(ba);

		return bais;
	}

	public InputStream getExcelFileForMoreSheets()
			throws FileNotFoundException, IOException {
		// �����µ� Excel ������
		HSSFWorkbook workbook = new HSSFWorkbook();
		for (int l = 0; l < this.sheets.length; l++) { // д���ĸ�sheet

			// �� Excel �������н���һ��������,����Ϊȱʡֵ sheet1
			HSSFSheet sheet = workbook.createSheet(String.valueOf(sheets[l]));
			// ��������
			HSSFFont font = workbook.createFont();
			// ��������ɫ����Ϊ��ɫ
			font.setColor(Font.COLOR_NORMAL);
			// ����������Ϊ����
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			// ������ʽ
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			// �Ѵ��������帶���ڸ�ʽ
			cellStyle.setFont(font);

			int beginRow = this.beginRow;
			int beginCol = this.beginCol;
			String contentArray[][] = contents[l];
			for (int i = 0; i < contentArray.length; i++) {
				// �ڹ������д���һ��
				HSSFRow row = sheet.createRow(beginRow++);
				beginCol = this.beginCol;
				for (int j = 0; j < contentArray[i].length; j++) {
					// ��һ���д���һ�����
					HSSFCell cell = row.createCell(beginCol++);

					if (i == 0) {
						// ������ĸ�ʽ������һ����Ԫ��
						cell.setCellStyle(cellStyle);
					}

					// ���ô˵�Ԫ���д�������ַ�
					cell.setCellType(Cell.CELL_TYPE_STRING);
					// ���ñ��� ���������������������� ���µ�JAR�����Զ�Unicode������ 10/02/25
					// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
					// ��˵�Ԫ���з���ֵ
					cell.setCellValue(contentArray[i][j]);
				}
			}

		}
		// �ֽ�����������
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		byte[] ba = baos.toByteArray();
		// �ֽ������������
		ByteArrayInputStream bais = new ByteArrayInputStream(ba);

		return bais;
	}

	/**
	 * ��ϲ���Ԫ�� ֻ�ʺϣ��?�����������ȷ���±��?ʹ�ã�����ʹ�ã���������п���- ������
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public InputStream getExcelFileWithMerged() throws FileNotFoundException,
			IOException {
		// �����µ� Excel ������
		HSSFWorkbook workbook = new HSSFWorkbook();
		// �� Excel �������н���һ��������,����Ϊȱʡֵ sheet1
		HSSFSheet sheet = workbook.createSheet(String.valueOf(index));

		// ��������
		HSSFFont font = workbook.createFont();
		// ��������ɫ����Ϊ��ɫ
		font.setColor(Font.COLOR_NORMAL);
		// ����������Ϊ����
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		// ������ʽ
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		// �Ѵ��������帶���ڸ�ʽ
		cellStyle.setFont(font);

		HSSFCellStyle cellStyle2 = workbook.createCellStyle();
		cellStyle2.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle2.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle2.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle2.setBorderTop(CellStyle.BORDER_THIN);

		int beginRow = this.beginRow;
		int beginCol = this.beginCol;

		if (mergeTitles != null) {
			HSSFRow row = sheet.createRow(beginRow++);
			HSSFCellStyle cellStyle1 = workbook.createCellStyle();
			// �Ѵ��������帶���ڸ�ʽ
			// ��������
			HSSFFont font1 = workbook.createFont();
			// ��������ɫ����Ϊ��ɫ
			font1.setColor(Font.COLOR_NORMAL);
			// ����������Ϊ����
			font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
			font1.setFontHeightInPoints((short) 16);
			cellStyle1.setFont(font1);
			cellStyle1.setAlignment(CellStyle.ALIGN_CENTER);
			for (String[] key : mergeTitles) {// ֻ�����һ�еı���ϲ�
				String mergeStr = key[1];
				String[] mergeArr = mergeStr.split(",");
				sheet.addMergedRegion(new CellRangeAddress(Integer
						.parseInt(mergeArr[0]), Integer.parseInt(mergeArr[1]),
						Integer.parseInt(mergeArr[2]), Integer
								.parseInt(mergeArr[3])));

				HSSFCell cell = row.createCell(Integer.parseInt(mergeArr[2]));
				cell.setCellStyle(cellStyle1);
				cell.setCellValue(key[0]);
				// beginCol += Integer.parseInt(mergeArr[3]);
			}
		}
		for (int i = 0; i < content.length; i++) {
			// �ڹ������д���һ��
			HSSFRow row = sheet.createRow(beginRow++);
			beginCol = this.beginCol;
			for (int j = 0; j < content[i].length; j++) {
				// ��һ���д���һ�����
				HSSFCell cell = row.createCell(beginCol++);

				if (i == 0) {
					// ������ĸ�ʽ������һ����Ԫ��
					cell.setCellStyle(cellStyle);
				} else {
					// ������ĸ�ʽ������һ����Ԫ��
					cell.setCellStyle(cellStyle2);
				}

				// ���ô˵�Ԫ���д�������ַ�
				cell.setCellType(Cell.CELL_TYPE_STRING);
				// ���ñ��� ���������������������� ���µ�JAR�����Զ�Unicode������ 10/02/25
				// cell.setEncoding(HSSFCell.ENCODING_UTF_16);
				// ��˵�Ԫ���з���ֵ
				cell.setCellValue(content[i][j]);
			}
		}

		// �ֽ�����������
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		byte[] ba = baos.toByteArray();
		// �ֽ������������
		ByteArrayInputStream bais = new ByteArrayInputStream(ba);

		return bais;
	}

	/**
	 * ��ϲ���Ԫ�� ֻ�ʺϣ��?������۷�������±��?ʹ�ã�����ʹ�ã���������п���
	 * ��Ҫ������ϲ���Ԫ�񣬰�contents��ά��������������ά��һ������Ǻϲ�����
	 * ����ά�ڶ���������������ݣ�����ϲ��Զ�ά�ڶ�������ĳ������ϲ�- ������
	 * 
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public InputStream getExcelFileForConsultationFees()
			throws FileNotFoundException, IOException {
		// �����µ� Excel ������
		HSSFWorkbook workbook = new HSSFWorkbook();
		// �� Excel �������н���һ��������,����Ϊȱʡֵ sheet1
		HSSFSheet sheet = workbook.createSheet(String.valueOf(index));

		// ��������
		HSSFFont font = workbook.createFont();
		// ��������ɫ����Ϊ��ɫ
		font.setColor(Font.COLOR_NORMAL);
		// ����������Ϊ����
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		// ������ʽ
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
		cellStyle.setBorderRight(CellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyle.setBorderTop(CellStyle.BORDER_THIN);
		// �Ѵ��������帶���ڸ�ʽ
		cellStyle.setFont(font);

		int beginRow = this.beginRow;
		int beginCol = this.beginCol;

		if (mergeTitles != null) {
			HSSFRow row = sheet.createRow(beginRow++);
			HSSFCellStyle cellStyle1 = workbook.createCellStyle();
			// �Ѵ��������帶���ڸ�ʽ
			// ��������
			HSSFFont font1 = workbook.createFont();
			// ��������ɫ����Ϊ��ɫ
			font1.setColor(Font.COLOR_NORMAL);
			// ����������Ϊ����
			font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
			font1.setFontHeightInPoints((short) 16);
			cellStyle1.setFont(font1);
			cellStyle1.setAlignment(CellStyle.ALIGN_CENTER);
			for (String[] key : mergeTitles) {// ֻ�����һ�еı���ϲ�
				String mergeStr = key[1];
				String[] mergeArr = mergeStr.split(",");
				sheet.addMergedRegion(new CellRangeAddress(Integer
						.parseInt(mergeArr[0]), Integer.parseInt(mergeArr[1]),
						Integer.parseInt(mergeArr[2]), Integer
								.parseInt(mergeArr[3])));

				HSSFCell cell = row.createCell(Integer.parseInt(mergeArr[2]));
				cell.setCellStyle(cellStyle1);
				cell.setCellValue(key[0]);
				// beginCol += Integer.parseInt(mergeArr[3]);
			}
		}
		for (int i = 0; i < contents.length; i++) {
			if (i == 0) {// ����ӱ�ͷ
				HSSFRow row = sheet.createRow(beginRow++);
				String titles[] = contents[0][0];
				for (int j = 0; j < titles.length; j++) {
					// ��һ���д���һ�����
					HSSFCell cell = row.createCell(beginCol++);
					// ������ĸ�ʽ������һ����Ԫ��
					cell.setCellStyle(cellStyle);
					// ���ô˵�Ԫ���д�������ַ�
					cell.setCellType(Cell.CELL_TYPE_STRING);
					// ��˵�Ԫ���з���ֵ
					cell.setCellValue(titles[j]);
				}
			} else {
				String[][] hospitals = contents[i];
				String cityName = hospitals[0][0];
				// String[][] hospitals = doubleCon[1];
				int firstRow = beginRow;
				int lastRow = beginRow + hospitals.length - 2;// ������ռ��һ�У�����0��ʼ������Ҫ��2
				int firstCol = 0;
				int lastCol = 0;

				HSSFCellStyle cellStyle2 = workbook.createCellStyle();
				// �Ѵ��������帶���ڸ�ʽ
				cellStyle2.setFont(font);
				cellStyle2.setAlignment(CellStyle.ALIGN_CENTER);
				cellStyle2.setBorderLeft(CellStyle.BORDER_THIN);
				cellStyle2.setBorderRight(CellStyle.BORDER_THIN);
				cellStyle2.setBorderBottom(CellStyle.BORDER_THIN);
				cellStyle2.setBorderTop(CellStyle.BORDER_THIN);
				cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
				cellStyle2.setWrapText(true);

				HSSFRow row = null;// sheet.createRow(firstRow);
				HSSFCell cell = null;// row.createCell(firstCol);

				HSSFCellStyle cellStyle3 = workbook.createCellStyle();
				cellStyle3.setBorderLeft(CellStyle.BORDER_THIN);
				cellStyle3.setBorderRight(CellStyle.BORDER_THIN);
				cellStyle3.setBorderBottom(CellStyle.BORDER_THIN);
				cellStyle3.setBorderTop(CellStyle.BORDER_THIN);
				for (int h = 1; h < hospitals.length; h++) {// Ҫ��ȥ���е��Ǹ�����
					String[] hospArr = hospitals[h];
					beginCol = this.beginCol + lastCol + 1;
					row = sheet.createRow(beginRow++);
					for (int j = 0; j < hospArr.length; j++) {
						// ��һ���д���һ�����
						cell = row.createCell(beginCol++);
						cell.setCellStyle(cellStyle3);
						// ���ô˵�Ԫ���д�������ַ�
						cell.setCellType(Cell.CELL_TYPE_STRING);
						// ��˵�Ԫ���з���ֵ
						cell.setCellValue(hospArr[j]);
					}
				}
				sheet.addMergedRegion(new CellRangeAddress(firstRow, lastRow,
						firstCol, lastCol));
				row = sheet.getRow(firstRow);// ��ȡ�����´����ᵼ�¸�������
				cell = row.createCell(firstCol);
				cellStyle2
						.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
				cellStyle2
						.setFillBackgroundColor(HSSFColor.GREY_50_PERCENT.index);
				cell.setCellStyle(cellStyle2);// ������ĸ�ʽ������һ����Ԫ��
				cell.setCellType(Cell.CELL_TYPE_STRING);// ���ô˵�Ԫ���д�������ַ�
				cell.setCellValue(cityName);

			}

		}

		// �ֽ�����������
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		workbook.write(baos);
		byte[] ba = baos.toByteArray();
		// �ֽ������������
		ByteArrayInputStream bais = new ByteArrayInputStream(ba);

		return bais;
	}
}
