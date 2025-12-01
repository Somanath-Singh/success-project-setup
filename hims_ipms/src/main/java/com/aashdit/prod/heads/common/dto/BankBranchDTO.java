package com.aashdit.prod.heads.common.dto;

import java.util.List;

import com.aashdit.prod.heads.common.model.BankBranchMaster;

import lombok.Data;

@Data
public class BankBranchDTO {

	private BankBranchMaster bankBranchMaster;

	private List<BankBranchMaster> bankBranchMasterList;

}
