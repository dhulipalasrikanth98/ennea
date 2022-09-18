package com.ennea.project.entity;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
@Table(name="inventory")
public class Inventory {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name = "code")
	private String code;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="batch")
	private String batch;
	
	@Column(name="stock")
	private Integer stock;
	
	@Column(name = "deal")
	private Integer deal;
	
	@Column(name = "free")
	private Integer free;
	
	@Column(name = "mrp")
	private Double mrp;
	
	@Column(name = "rate")
	private Double rate;
	
	@Column(name = "exp")
	private String exp;
	
	@Column(name = "company")
	private String company;
	
	@Column(name="supplier")
	private String supplier;
}
