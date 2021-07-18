package com.market.company.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Document
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

//	@Field
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull(message = "Company code is must")
	@ApiModelProperty(dataType = "String", example = "101")
	private String code;
	
	//@Field
	@Column
	@NotNull(message = "Company name is must")
	@ApiModelProperty(dataType = "String", example = "CompanyXYZ")
	private String name;
	
	//@Field
	@Column
	@NotNull(message = "Company CEO is must")
	@ApiModelProperty(dataType = "String", example = "CEOxyz")
	private String ceo;
	
	//@Field
	@Column
	@NotNull(message = "Company turnover is must")
	@Min(value = 10, message= "Greater than 10CR")
	@ApiModelProperty(dataType = "String", example = "10",allowableValues = "range(10, infinity]")
	private String turnover;
	
	//@Field
	@Column
	@NotNull(message = "Company website is must")
	@ApiModelProperty(dataType = "String", example = "www.companyxyz.com")
	private	String website;

}
