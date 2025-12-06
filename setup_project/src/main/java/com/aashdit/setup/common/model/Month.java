package com.aashdit.setup.common.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name  =  "t_mst_month", schema = "public")
public class Month implements Serializable {
private static final long serialVersionUID = -612196930684148L;

	@Id
	@GeneratedValue(strategy  =  GenerationType.IDENTITY)
	@Column(name = "month_id")
	private Long monthId;


	@NotNull
	@Column(name = "display_order")
	private Integer displayOrder;

	
	@Column(name = "quarter")
	private String quarter;

	@NotNull
	@Column(name = "month_name_en")
	private String monthNameEn;


	@NotNull
	@Column(name = "month_code")
	private String monthCode;
	
	@Column(name = "last_updated_by")
	private Long lastUpdatedBy;
	
	@Column(name = "last_updated_on")
	private Date lastUpdatedOn;


}