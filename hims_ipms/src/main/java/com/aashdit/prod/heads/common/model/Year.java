package com.aashdit.prod.heads.common.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="t_mst_year")	
public class Year implements Serializable{
	
	private static final long serialVersionUID = 772778374754020937L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "year_id", nullable = false)
    private Long yearId;
    
    @Column(name = "year_name")
    private String yearName;
    
    @Column(name = "order_id")
    private Long orderId;
    
    @Column(name="is_active")
	private Boolean isActive;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "last_updated_on")
	private Date lastUpdatedOn;
	
	@Column(name = "last_updated_by")
	private Long lastUpdatedBy;

}
