import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {

	//Identify TestCase Column  by identifying  the entire 1st row
	//Once column is identified then scan entire testcase  column to identify purchase test case row

	//after you grab purchase test case row, pull all the data of that row and feed into test

	@SuppressWarnings("deprecation")
	public ArrayList<String> getData(String testcaseName) throws IOException {
		ArrayList<String> a = new ArrayList<String>();


		//Use file input stream argument
		FileInputStream fis = new FileInputStream("E:\\Sahil\\Engineering\\Testing_Courses\\Rest_API\\DataTest.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++) 
		{
			if(workbook.getSheetName(i).equalsIgnoreCase("TestData"))
			{
				XSSFSheet sheet = workbook.getSheetAt(i);	

				//Identify TestCase Column  by identifying  the entire 1st row
				Iterator<Row> rows =  sheet.iterator(); //sheet is collection of rows
				Row firstRow = rows.next();
				Iterator <Cell> cell = firstRow.cellIterator();  // row is collection of cells
				int k =0;
				int column = 0;
				while(cell.hasNext())
				{
					Cell value = cell.next();
					if(value.getStringCellValue().equalsIgnoreCase("TestCase"))
					{
						column=k;
					}
					k++;	
				}

				//Once column is identified then scan entire testcase  column to identify purchase test case row
				while(rows.hasNext()) 
				{
					Row r = rows.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName))
					{
						//after you grab purchase test case row, pull all the data of that row and feed into test
						Iterator<Cell> cv = r.cellIterator();
						while(cv.hasNext()) {

							Cell c = cv.next();
							if(c.getCellTypeEnum() == CellType.STRING) {
								a.add(cv.next().getStringCellValue());
							}else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));

							}

						}
					}
				}

			}

		}
		return a;
	}


	public static void main(String[] args) throws IOException {

	}
}
