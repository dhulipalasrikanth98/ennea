package com.ennea.project.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ennea.project.entity.Inventory;
import com.ennea.project.exception.InvalidDetailsException;
import com.ennea.project.exception.RecordsNotFoundException;
import com.ennea.project.service.InventoryService;
import com.ennea.project.service.InventoryServiceImpl;

@RestController
@RequestMapping("/ennea")
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestBody String filePath){
		

		return new ResponseEntity<>( inventoryService.saveToDB(filePath),HttpStatus.ACCEPTED); 
		
	}
	@GetMapping("/id/{id}/{pageNo}/{pageSize}")
	public ResponseEntity<?> getDataById(@PathVariable("id") String id,@PathVariable int pageNo,@PathVariable int pageSize){
		

		return new ResponseEntity<>( inventoryService.getDataById(id,pageNo,pageSize),HttpStatus.ACCEPTED); 
		
	}
	
	@GetMapping("/products/notexpired/{pageNo}/{pageSize}")
	public ResponseEntity<?> getProductGreaterThanExpiry(@PathVariable int pageNo,@PathVariable int pageSize) {
		

		return new ResponseEntity<>( inventoryService.getProducts(pageNo,pageSize),HttpStatus.ACCEPTED); 
		
	}
}
