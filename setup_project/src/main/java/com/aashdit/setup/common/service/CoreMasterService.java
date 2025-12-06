package com.aashdit.setup.common.service;

import java.util.List;

import com.aashdit.setup.common.dto.BankBranchDTO;
import com.aashdit.setup.common.dto.FinancialYearMasterDto;
import com.aashdit.setup.common.model.BankBranchMaster;
import com.aashdit.setup.common.model.BankMaster;
import com.aashdit.setup.common.model.FinancialYear;
import com.aashdit.setup.core.ServiceOutcome;

public interface CoreMasterService {

	ServiceOutcome<FinancialYear> saveFinancialYear(FinancialYearMasterDto financialYear);

	ServiceOutcome<List<FinancialYear>> getAllFinancialYear();

	ServiceOutcome<FinancialYear> editFinancialYear(Long finYrId);

	List<FinancialYear> findByFynYear(String finYear);

	List<BankMaster> getAllBankList();

	ServiceOutcome<BankBranchMaster> saveBankBranch(BankBranchDTO bankBranchDto);

	ServiceOutcome<BankBranchMaster> editBanBranch(Long bankBranchId);

	List<BankBranchMaster> getAllBankBranchList();

	List<BankBranchMaster> findByIfscCode(String ifscCode);

	BankBranchMaster findBranchByIfscCode(String ifscCode);


}
