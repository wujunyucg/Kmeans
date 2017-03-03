package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadFile {

	public ArrayList<double[]> inport( String excelPath) throws IOException {
		ArrayList<double[]> dataSet=new ArrayList<double[]>();
		InputStream  fis = new FileInputStream(excelPath);
		String fileType = excelPath.substring(excelPath.lastIndexOf(".") + 1, excelPath.length());
		Workbook wb = null;
		if(fileType.equals("xlsx")){
			 wb = new XSSFWorkbook(fis);
		}
		else if(fileType.equals("xls")){
			 wb = new HSSFWorkbook(fis);
		}
		Sheet sht0 = wb.getSheetAt(0);
		for (Row r : sht0) {
			double a=0,b=0;
			if (r.getRowNum() < 1) {
				continue;// 第一行是标题
			}
			for(int i=1;i<3;i++){
				if(r.getCell(i) == null)
					return null;
				r.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
				double cellValue =Double.parseDouble( r.getCell(i).getStringCellValue()) ;
				
				switch (i){
				case 1:a=cellValue; break;
				case 2:b=cellValue; break;
				}
			}
			 dataSet.add(new double[]{a,b});
		}
		fis.close();
		return dataSet;
	}
}
