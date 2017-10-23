package CanProduce;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * Created by Admin on 2017/10/22.
 */
public class OperateTxt {
	//读入excel的文件
	public static String inputFile = "D:\\2.xls";
	//写入到指定的txt中
	public static String outputFile = "D:\\tableH.1.txt";
	//开始的行号
	public static int startRowNumber = 1;
	//选取的列号
	public static int[] selectColNumber = {0,1,2,3,4};

	public static void main(String[] args) throws IOException {
		File f = new File(inputFile);
		FileWriter fw = new FileWriter(outputFile);

//写入标题
//		fw.write("Can信号生成结果\r\n\r\n");

		try {
			FileInputStream is = new FileInputStream(f);
			HSSFWorkbook wbs = new HSSFWorkbook(is);
			HSSFSheet childSheet = wbs.getSheetAt(0);
// System.out.println(childSheet.getPhysicalNumberOfRows());
			System.out.println("有行数" + childSheet.getLastRowNum());
//当需要读取最后一行时j <= childSheet.getLastRowNum();否则<
			for (int j = 0; j <= childSheet.getLastRowNum(); j++) {
				HSSFRow row = childSheet.getRow(j);
// System.out.println(row.getPhysicalNumberOfCells());
// System.out.println("有列数" + row.getLastCellNum());
				if (null != row&&j>startRowNumber) {

					for (int k = 0; k < row.getLastCellNum(); k++) {
						HSSFCell cell = row.getCell(k);
//System.out.println("******"+k);
						if (null != cell && k==selectColNumber[k]) {
							switch (cell.getCellType()) {
								case HSSFCell.CELL_TYPE_NUMERIC: // 数字
									System.out.print(cell.getNumericCellValue()
											+ " ");
									fw.write(cell.getNumericCellValue()+"\r\n");
									break;
								case HSSFCell.CELL_TYPE_STRING: // 字符串
									System.out.print(cell.getStringCellValue()
											+ " ");
									fw.write(cell.getStringCellValue()+"\r\n");
									break;
								case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
									System.out.println(cell.getBooleanCellValue()
											+ " ");
									fw.write(cell.getBooleanCellValue()+"\r\n");
									break;
								case HSSFCell.CELL_TYPE_FORMULA: // 公式
									System.out.print(cell.getCellFormula() + " ");
									fw.write(cell.getCellFormula()+"\r\n");
									break;
								case HSSFCell.CELL_TYPE_BLANK: // 空值
									System.out.println(" ");
									fw.write(" "+"\r\n");
									break;
								case HSSFCell.CELL_TYPE_ERROR: // 故障
									System.out.println(" ");
									fw.write(" "+"\r\n");
									break;
								default:
									System.out.print("未知类型 ");
									break;
							}
						} else {
							System.out.print("- ");
						}
					}
				}
				System.out.println();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			fw.flush();
			fw.close();
		}
	}
}
