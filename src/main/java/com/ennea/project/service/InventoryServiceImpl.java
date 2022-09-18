package com.ennea.project.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ennea.project.dao.InventoryDao;
import com.ennea.project.entity.Inventory;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryDao inventoryDao;
	private final static Logger log = LoggerFactory.getLogger(InventoryServiceImpl.class);
	Workbook workbook;

	public List<Inventory> convertToList(String filePath) {
		// TODO Auto-generated method stub
		try {
			workbook = WorkbookFactory.create(new File(filePath));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		Sheet sheet = workbook.getSheetAt(0);
		int noOfColums = sheet.getLastRowNum()+1;
		//System.out.print("-----------------------\n"+sheet.getRow(0).getLastCellNum());
		Inventory inventory = new Inventory();
		ArrayList<Inventory> list = new ArrayList<>();
		for(int i=1;i<=noOfColums;i++) {
			Row row = sheet.getRow(i);

			if(row==null) {
				continue;
			}
			inventory = new Inventory();
			for(int j=0;j<=row.getLastCellNum();j++) {
				//System.out.print(i+"-->"+j+"  ");
				if(row.getCell(j)==null ||  row.getCell(j).toString().trim().equals("")) {
					switch (j) {
					case 0:inventory.setCode(null);   break;
					case 1: inventory.setName(null);  break;
					case 2: inventory.setBatch(null); break;
					case 3: inventory.setStock(null); break;
					case 4: inventory.setDeal(null); break;
					case 5:  inventory.setFree(null); break;
					case 6:  inventory.setMrp(null); break;
					case 7: inventory.setRate(null); break;
					case 8: inventory.setExp(null); break;
					case 9: inventory.setCompany(null); break;
					case 10: inventory.setSupplier(null); break;

					}
				}
				else {
					switch (j) {
					case 0:

						switch (row.getCell(j).getCellType()) {
						case NUMERIC:
							inventory.setCode(String.valueOf(row.getCell(j).getNumericCellValue()));
							break;
						case STRING : inventory.setCode(row.getCell(j).getStringCellValue());   break;
						default:break;
						};break;
					case 1:	
						//System.out.print("i"+i+"j"+j+"-->"+row.getCell(j));
						switch (row.getCell(j).getCellType()) {
						case NUMERIC:
							inventory.setName(String.valueOf(row.getCell(j).getNumericCellValue()));
							break;
						case STRING : inventory.setName(row.getCell(j).getStringCellValue());   break;
						default:break;
						};  break;
					case 2: switch (row.getCell(j).getCellType()) {
					case NUMERIC:
						inventory.setBatch(String.valueOf(row.getCell(j).getNumericCellValue()));
						break;
					case STRING : inventory.setBatch(row.getCell(j).getStringCellValue());   break;
					default:break;
					};  break;
					case 3: switch (row.getCell(j).getCellType()) {
					case NUMERIC:
						inventory.setStock((int)row.getCell(j).getNumericCellValue());
						break;
					case STRING : inventory.setStock(Integer.parseInt(String.valueOf(row.getCell(j).getStringCellValue())));   break;
					default:break;
					};  break;
					case 4: switch (row.getCell(j).getCellType()) {
					case NUMERIC:
						inventory.setDeal((int)row.getCell(j).getNumericCellValue());
						break;
					case STRING : inventory.setDeal(Integer.parseInt(String.valueOf(row.getCell(j).getStringCellValue())));   break;
					default:break;
					};  break;
					case 5:  switch (row.getCell(j).getCellType()) {
					case NUMERIC:
						inventory.setFree((int)row.getCell(j).getNumericCellValue());
						break;
					case STRING : inventory.setFree(Integer.parseInt(String.valueOf(row.getCell(j).getStringCellValue())));   break;
					default:break;
					};  break;
					case 6:  switch (row.getCell(j).getCellType()) {
					case NUMERIC:
						inventory.setMrp((double)row.getCell(j).getNumericCellValue());
						break;
					case STRING : inventory.setMrp(Double.parseDouble(String.valueOf(row.getCell(j).getStringCellValue())));   break;
					default:break;
					};  break;
					case 7: switch (row.getCell(j).getCellType()) {
					case NUMERIC:
						inventory.setRate((double)row.getCell(j).getNumericCellValue());
						break;
					case STRING : inventory.setRate(Double.parseDouble(String.valueOf(row.getCell(j).getStringCellValue())));   break;
					default:break;
					};  break;
					case 8: switch (row.getCell(j).getCellType()) {
					case NUMERIC:
						inventory.setExp(row.getCell(j).getLocalDateTimeCellValue().toString());
						break;
					case STRING : inventory.setExp(row.getCell(j).getStringCellValue());   break;
				
					default:break;
					};  break;
					case 9: switch (row.getCell(j).getCellType()) {
					case NUMERIC:
						inventory.setCompany(String.valueOf(row.getCell(j).getNumericCellValue()));
						break;
					case STRING : inventory.setCompany(row.getCell(j).getStringCellValue());   break;
					default:break;
					};  break;
					case 10: switch (row.getCell(j).getCellType()) {
					case NUMERIC:
						inventory.setSupplier(String.valueOf(row.getCell(j).getNumericCellValue()));
						break;
					case STRING : inventory.setSupplier(row.getCell(j).getStringCellValue());   break;
					default:break;
					};  break;

					}    
				}

			}
			list.add(inventory);
		}
		return list;

	}
	@Override
	public boolean saveToDB(String filePath) {
		// TODO Auto-generated method stub
		List<Inventory> list = convertToList(filePath);
		if(list.isEmpty()) {
			return false;
		}
		inventoryDao.saveAll(list);
		return true;

	}
	@Override
	public List<Inventory> getDataById(String id,int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		if(id==null) {
			return null;
		}
	
		return inventoryDao.findByName(id,PageRequest.of(pageNo, pageSize));
	}
	@Override
	public List<Inventory> getProducts(int pageNo,int pageSize) {
		// TODO Auto-generated method stub
		return inventoryDao.findAllByExpiryGreater(PageRequest.of(pageNo, pageSize));
		
	}



}
