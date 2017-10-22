package CanProduce;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Admin on 2017/10/22.
 */
public class OperateTxt {
	public  static void readSpecify(File file) throws Exception {
		ArrayList<String> columnList = new ArrayList<String>();
		Workbook readwb = null;
		InputStream io = new FileInputStream(file.getAbsoluteFile());
		readwb = Workbook.getWorkbook(io);
		Sheet readsheet = readwb.getSheet(0);
		int rsRows = readsheet.getRows();
		int rsColumns = readsheet.getColumns();
		for (int i = 1; i < rsRows; i++) {
			Cell cell = readsheet.getCell(1, i);
			columnList.add(cell.getContents());
			System.out.println(columnList);
		}
		String[] ageString = new String[columnList.size()];
		int[] age = new int[ageString.length];
		for (int i = 0; i < columnList.size(); i++) {
			ageString[i] = columnList.get(i);
			age[i] = Integer.parseInt(ageString[i]);
			System.out.println(age[i]);
		}
	}
	public static void copy_excel(File file) throws Exception {
		FileWriter fWriter = null;
		PrintWriter out = null;
		String fliename = file.getName().replace(".xls", "");
		fWriter = new FileWriter(file.getParent() + "/" + fliename + "1.txt");//输出格式为.txt
//		fWriter = new FileWriter(file.getParent()+ "/agetwo.xls");//输出格式为.xls
		out = new PrintWriter(fWriter);
		InputStream is = new FileInputStream(file.getAbsoluteFile());
		Workbook wb = null;
		wb = Workbook.getWorkbook(is);
		int sheet_size = wb.getNumberOfSheets();
		Sheet sheet = wb.getSheet(0);
		for (int j = 1; j < sheet.getRows(); j++) {
			String cellinfo = sheet.getCell(1, j).getContents();//读取的是第二列数据，没有标题，标题起始位置在for循环中定义
			out.println(cellinfo);
		}
		out.close();//关闭流
		fWriter.close();
		out.flush();//刷新缓存
		System.out.println("输出完成！");
	}
	public static void main(String[] args)throws Exception {
//		Workbook book;
//		book = Workbook.getWorkbook(new File("D://1.xls"));
		File file1 = new File("D://1.xls");
		File file2 = new File("D://1");
		OperateTxt.readSpecify(file1);
		OperateTxt.copy_excel(file2);
	}

}



