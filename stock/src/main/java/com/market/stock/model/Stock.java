package com.market.stock.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stock {
	@Id
	private String id;
	
	@Indexed
	@Field
	@NotNull(message = "Company code is must")
	@ApiModelProperty(dataType = "String", example = "101")
	private String companyCode;
	
	@Field
	@Indexed
	//@DateTimeFormat(iso=ISO.DATE_TIME)
	private Date startDate;
	
	@Field
	@NotNull(message = "Stock price is must")
	@ApiModelProperty(dataType = "String", example = "101.90")
	@DecimalMin(value = "0.0", inclusive = false, message = "price cannot be lower than 0.0")
    @Digits(integer = 3, fraction=2, message ="Price range allowed 000.001 to 999.99")
	private String price;
	

}
