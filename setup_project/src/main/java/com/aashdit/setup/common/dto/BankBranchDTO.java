package com.aashdit.setup.common.dto;

import java.util.List;

import com.aashdit.setup.common.model.BankBranchMaster;

import lombok.Data;

@Data
public class BankBranchDTO {

	private BankBranchMaster bankBranchMaster;

	private List<BankBranchMaster> bankBranchMasterList;

}
