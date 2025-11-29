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
import javax.validation.constraints.NotNull;

import com.aashdit.prod.heads.hims.umt.utils.Auditable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_adm_mst_bank_branch", schema = "public")
public class BankBranchMaster extends Auditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "branch_id", nullable = false)
    private Long bankBranchId;
	
	@NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private BankMaster bankId;
    
	@NotNull
    @Column(name = "branch_name")
    private String branchName;
    
	@NotNull
    @Column(name = "ifsc_code")
    private String ifscCode;
    
    @Column(name = "branch_address")
    private String branchAddress;
    
    @Column(name = "contact_no")
    private String branchMobile;
    
    @Column(name = "email_id")
    private String branchEmail;

}
