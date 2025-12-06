package com.aashdit.setup.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@Table(name = "t_asset_mst_vendor_type", schema = "asset_mgmt")
public class VendorType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendor_type_id")
	private Long vendorTypeId;

	@NotNull
	@Column(name = "vendor_type_name")
	private String vendorTypeName;

	@Column(name = "vendor_type_code", unique = true)
	private String vendorTypeCode;
	
	@Column(name = "is_active")
	private boolean isActive;
}
