package airtel.airtel.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AirtelController {
	
	@RequestMapping(value="/readExcel", method=RequestMethod.GET)
	public ModelAndView readFile() throws IOException{
		
		// InputStream inputStream = new URL("http://localhost/report.xls").openStream();
		
		FileInputStream input=new FileInputStream(new File("C:\\Users\\brajendrasingh\\Desktop\\Newfolder\\airtel.xlsx"));
		Workbook workbook=new XSSFWorkbook(input);
		Sheet sheet=workbook.getSheetAt(0);
		
		Iterator iterator=sheet.iterator();
		while(iterator.hasNext()){
			Row row=(Row) iterator.next();
			
			Iterator cellIterator=row.iterator();
			while(cellIterator.hasNext()){
				Cell cell=(Cell) cellIterator.next();
				
				switch(cell.getCellType()){
					case Cell.CELL_TYPE_NUMERIC:
						System.out.print(cell.getNumericCellValue()+" ");
						break;
					case Cell.CELL_TYPE_STRING:
						System.out.print(cell.getStringCellValue()+" ");
						break;
				}
				System.out.println(" | ");
			}
			System.out.println("\n");
			
			
		}
		
		ModelAndView model=new ModelAndView();
		model.setViewName("displayData.jsp");
		return model;
	}

}
