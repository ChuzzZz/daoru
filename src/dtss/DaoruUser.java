package dtss;
import java.io.*;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import model.User;

public class DaoruUser {
	
	public void getExcelData(){
		File f = new File("dc_user.xls");
		DBAccess db = new DBAccess();
		
		try {
			Workbook book = Workbook.getWorkbook(f);
			Sheet[] sheets = book.getSheets();
			User u = new User();
			
			int row = sheets[0].getRows();
			int column = sheets[0].getColumns();
			for(int i = 1; i < row; i++){
				for(int j = 0;j < column;j++){
					if(j == 0){
						u.setItcode(Integer.parseInt(sheets[0].getCell(j, i).getContents()));
					}else{
						u.setName(sheets[0].getCell(j, i).getContents());
					}
				}
				u.setId(0);
				u.setOnsite(1);
				if(db.insertUser(u)) {
					System.out.println(u);
				}else {
					System.out.println("insert onsite failed!");
				}
			}
			
			row = sheets[1].getRows();
			column = sheets[1].getColumns();
			System.out.println(row);
			System.out.println(column);
			for(int i = 1; i < row; i++){
				for(int j = 0;j<column;j++){
					if(j == 0){
//						System.out.println(sheets[1].getCell(j, i).getContents());
						u.setItcode(Integer.parseInt(sheets[1].getCell(j, i).getContents()));
					}else{
//						System.out.println(sheets[1].getCell(j, i).getContents());
						u.setName(sheets[1].getCell(j, i).getContents());
					}
				}
				u.setId(0);
				u.setOnsite(0);
				if(db.insertUser(u)) {
					System.out.println(u);
				}else {
					System.out.println("insert not onsite failed!");
				}
			}
			
			db.close();
			
		}catch (BiffException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DaoruUser du = new DaoruUser();
		du.getExcelData();
	}

}
