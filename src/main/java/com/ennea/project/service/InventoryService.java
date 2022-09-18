package com.ennea.project.service;

import java.util.List;

import com.ennea.project.entity.Inventory;

public interface InventoryService {


	boolean saveToDB(String filePath);

	List<Inventory> getDataById(String id,int pageNo,int pageSize);

	List<Inventory> getProducts(int pageNo,int pageSize);
	
	
	
}
