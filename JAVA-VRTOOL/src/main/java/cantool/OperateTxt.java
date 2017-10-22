package cantool;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import java.io.File;


/**
 * Created by lenovo on 2017/10/22.
 */
public class OperateTxt {
    public static void readSpecify(File file)throws Exception{
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






}
