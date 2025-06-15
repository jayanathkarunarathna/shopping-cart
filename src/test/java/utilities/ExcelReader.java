package utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelReader {

    private final String filePath;

    public ExcelReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String[]> getContactFormData(String sheetName, boolean isIterative) throws Exception {
        List<String[]> data = new ArrayList<>();
        FileInputStream fis = new FileInputStream(new File(filePath));
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);
        Iterator<Row> rowIterator = sheet.iterator();

        if (isIterative == true ) {
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                int cellCount = row.getLastCellNum();
                String[] rowData = new String[cellCount];
                for (int i = 0; i < cellCount; i++) {
                    Cell cell = row.getCell(i);
                    rowData[i] = (cell == null) ? "" : cell.toString();
                }
                data.add(rowData);
            }
        }
        else{
            Row row = sheet.getRow(1);
            int colCount = row.getLastCellNum();
            String[] val = new String[colCount];

            for (int j = 0; j < colCount; j++) {
                val[j] = row.getCell(j).toString();
            }
            data.add(val);
        }
        workbook.close();
        return data;
    }

    public List<String[]> getCShoppingCartData(String sheetName) throws Exception {
        return null;
    }
}
