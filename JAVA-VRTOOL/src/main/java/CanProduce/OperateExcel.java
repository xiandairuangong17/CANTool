package CanProduce;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.File;
import java.io.IOException;

/**
 * Created by lenovo on 2017/10/23.
 */
public class OperateExcel {
    //	public static void main(String[] args) throws IOException, BiffException {
    public String OperateExcel() throws IOException, BiffException {
        int i;
        Sheet sheet;
        Workbook book;
        Cell cell1, cell2, cell3, cell4, cell5, cell6, cell7;
        //hello.xls为要读取的excel文件名
        book = Workbook.getWorkbook(new File("D://1.xls"));

        //获得第一个工作表对象(ecxel中sheet的编号从0开始,0,1,2,3,....)
        sheet = book.getSheet(0);
        //获取左上角的单元格
        cell1 = sheet.getCell(0, 0);
        System.out.println("标题：" + cell1.getContents());

        i = 1;
        while (true) {
            //获取每一行的单元格
            cell1 = sheet.getCell(0, i);//（列，行）
            cell2 = sheet.getCell(1, i);
            cell3 = sheet.getCell(2, i);
            cell4 = sheet.getCell(3, i);
            cell5 = sheet.getCell(4, i);
            cell6 = sheet.getCell(5, i);
            cell7 = sheet.getCell(6, i);
            if ("".equals(cell1.getContents()) == true) {    //如果读取的数据为空
                break;
            }
            System.out.println(cell1.getContents() + "\t" + cell2.getContents() + "\t" + cell3.getContents() + "\t" + cell4.getContents()
                    + "\t" + cell5.getContents() + "\t" + cell6.getContents() + "\t" + cell7.getContents());

            i++;
        }
        book.close();

        return null;
    }

}