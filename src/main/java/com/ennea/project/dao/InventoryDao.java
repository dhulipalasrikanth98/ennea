package com.ennea.project.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ennea.project.entity.Inventory;
@Repository
public interface InventoryDao extends CrudRepository<Inventory,Integer> {

	
	@Query("select i from Inventory i where i.name LIKE :name%")
	List<Inventory> findByName(String name,Pageable pageable);
	@Query("select i from Inventory i where i.exp>=sysdate()")
	List<Inventory> findAllByExpiryGreater(Pageable pageable);

}
