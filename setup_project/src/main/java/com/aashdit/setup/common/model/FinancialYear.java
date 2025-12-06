package com.aashdit.setup.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.aashdit.setup.umt.utils.Auditable;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_mst_finyear" , schema = "public")
public class FinancialYear extends Auditable<Long> implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 6923428498245121788L;

	@Id
	@GeneratedValue(strategy  =  GenerationType.AUTO)
	@Column(name = "finyear_id")
	private Long finyearId;
	
	@NotNull
	@Column(name = "fin_year")
	private String finYear;

	@Column(name = "start_date")
	private Date startDate;
	
	@Column(name = "end_date")
	private Date endDate;

	
	@Column(name = "curr_fin_year")
	private Boolean currFinYear;
	
	@ManyToOne
	@JoinColumn(name = "prv_fin_year")
    private FinancialYear prvFinYr;
    
    @Column(name="fy_short") 
    private String fyShort;
	
	@Transient
	private String previousFinYear;

	
}