package com.aashdit.prod.heads.common.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.aashdit.prod.heads.hims.umt.utils.Auditable;

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
@Table(name = "t_asset_mst_vendor_bank_map", schema = "asset_mgmt")
public class VendorBankDetails extends Auditable<Long> implements Serializable  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vendor_bank_map_id")
    private Long vendorBankMapId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_branch")
    private BankBranchMaster bankBranchMaster;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "account_holder_name")
    private String accountHolderName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private AssetVendor vendorId;

    @Column(name = "is_primary")
    private Boolean isPrimary;

    @Transient
    private Long bankBranchId;
}
