package com.aashdit.setup.common.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class FinancialYearMasterDto {

	private Long finyearId;

	private String finYear;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date startDate;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date endDate;

	private Boolean currFinYear;

	private String fyStartDate;

	private String fyEndDate;
}
