package datadriven;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelReadingBooking {

    /**
     * This method reads a specific cell value from an Excel sheet (.xlsx format).
     *
     * @param filePath  The full path to the Excel file
     * @param sheetName The name of the sheet to read from
     * @param rowNum    The row number (starting from 0)
     * @param colNum    The column number (starting from 0)
     * @return The value of the specified cell as a String
     * @throws IOException If the file is not found or cannot be read
     */
    public String getCellData(String filePath, String sheetName, int rowNum, int colNum) throws IOException {
        // Open the Excel file
        FileInputStream fis = new FileInputStream(filePath);

        // Create a workbook instance from the file
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // Access the required sheet by name
        XSSFSheet sheet = workbook.getSheet(sheetName);

        // Get the specific row and cell
        XSSFRow row = sheet.getRow(rowNum);
        XSSFCell cell = row.getCell(colNum);

        // Convert cell value to String
        String value = cell.toString();

        // Close the workbook to avoid memory leaks
        workbook.close();

        // Return the extracted value
        return value;
    }

    /**
     * This method returns the number of rows in a given Excel sheet.
     *
     * @param filePath  The full path to the Excel file
     * @param sheetName The name of the sheet to read from
     * @return The number of rows (index of the last row)
     * @throws IOException If the file is not found or cannot be read
     */
    public int getRowCount(String filePath, String sheetName) throws IOException {
        // Open the Excel file
        FileInputStream fis = new FileInputStream(filePath);

        // Create a workbook instance from the file
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // Access the required sheet
        XSSFSheet sheet = workbook.getSheet(sheetName);

        // Get the last row number (row count is zero-based)
        int rowCount = sheet.getLastRowNum();

        // Close the workbook to avoid memory leaks
        workbook.close();

        // Return the row count
        return rowCount;
    }
}
