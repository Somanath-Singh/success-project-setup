package com.aashdit.setup.common.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.aashdit.setup.common.dto.BankBranchDTO;
import com.aashdit.setup.common.dto.FinancialYearMasterDto;
import com.aashdit.setup.common.model.BankBranchMaster;
import com.aashdit.setup.common.model.BankMaster;
import com.aashdit.setup.common.model.FinancialYear;
import com.aashdit.setup.common.repository.BankBranchMasterRepository;
import com.aashdit.setup.common.repository.BankMasterRepository;
import com.aashdit.setup.common.repository.FinancialYearRepository;
import com.aashdit.setup.core.ServiceOutcome;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CoreMasterServiceImpl implements CoreMasterService{
	
	final ResourceBundle rb = ResourceBundle.getBundle("application");
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private FinancialYearRepository financialYearRepository;
	
	@Autowired
	private BankMasterRepository bankMasterRepository;
	
	@Autowired
	private BankBranchMasterRepository bankBranchMasterRepository;
	
	
	@Override
	public ServiceOutcome<List<FinancialYear>> getAllFinancialYear() {
		ServiceOutcome<List<FinancialYear>> outcome = new ServiceOutcome<>();
		List<FinancialYear> finYearList = new ArrayList<>();
		try {
			finYearList = financialYearRepository.findAllByIsActiveIsTrue();
			outcome.setData(finYearList);
		} catch (Exception e) {
			log.error(" Exception occured in CoreMasterServiceImpl at getAllFinancialYear ----->"+e);
			e.printStackTrace();
		}
		return outcome;
	}
	
	
	@Override
	public ServiceOutcome<FinancialYear> saveFinancialYear(FinancialYearMasterDto financialYear) {
		ServiceOutcome<FinancialYear> outcome = new ServiceOutcome<>();
		String msg = "";
		try {
			FinancialYear finYear = Optional.ofNullable(financialYear.getFinyearId()).isPresent()
					? financialYearRepository.findByFinyearId(financialYear.getFinyearId())
					: new FinancialYear();
			msg = Optional.ofNullable(financialYear.getFinyearId()).isPresent()
					? messageSource.getMessage("finYear.msg.update", null, LocaleContextHolder.getLocale())
					: messageSource.getMessage("finYear.msg.success", null, LocaleContextHolder.getLocale());
			finYear.setFinYear(financialYear.getFinYear());
			
			 SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
			 Date startDate = simpleDate.parse(financialYear.getFyStartDate());
			 finYear.setStartDate(startDate);
			 
			 Date endDate = simpleDate.parse(financialYear.getFyEndDate());
			 finYear.setEndDate(endDate);
			 
			 if (financialYear.getCurrFinYear()) {
		            financialYearRepository.findAll().stream()
		            .filter(fyn -> !fyn.getFinyearId().equals(financialYear.getFinyearId()) && fyn.getCurrFinYear())
		            .forEach(fyn ->{
		            	fyn.setCurrFinYear(false);
                        financialYearRepository.save(fyn);
		            });
		        }
			finYear.setCurrFinYear(financialYear.getCurrFinYear());
			financialYearRepository.save(finYear);
			
			outcome.setData(finYear);
			outcome.setMessage(msg);
			outcome.setOutcome(true);
		} catch (Exception e) {
			outcome.setOutcome(false);
			outcome.setMessage("Something went wrong..");
			log.error(" Exception occured in CoreMasterServiceImpl at saveFinancialYear ----->"+e);
			e.printStackTrace();
			
		}
		return outcome;
	}


	@Override
	public ServiceOutcome<FinancialYear> editFinancialYear(Long finYrId) {
		ServiceOutcome<FinancialYear> outcome = new ServiceOutcome<>();
		try {
			Optional<FinancialYear> finYear = financialYearRepository.findById(finYrId);
			outcome.setData(finYear.get());
		} catch (Exception e) {
			outcome.setOutcome(false);
			outcome.setMessage("Something went wrong..");
			log.error(" Exception occured in CoreMasterServiceImpl at saveFinancialYear ----->"+e);
		}
		return outcome;
	}


	@Override
	public List<FinancialYear> findByFynYear(String finYear) {
		List<FinancialYear> financialYear = new ArrayList<>();
		try {
			financialYear = financialYearRepository.findByfinYear(finYear);
		} catch (Exception e) {
			log.error(" Exception occured in CoreMasterServiceImpl at findByFynYear ----->"+e);
		}
		return financialYear;
	}


	@Override
	public List<BankMaster> getAllBankList() {
		List<BankMaster> bankList = new ArrayList<>();
		bankList = bankMasterRepository.findAll();
		bankList.sort(Comparator.comparing(BankMaster::getBankName));
		return bankList;
		
	}


	@Override
	public ServiceOutcome<BankBranchMaster> saveBankBranch(BankBranchDTO bankBranchDto) {
		ServiceOutcome<BankBranchMaster> outcome = new ServiceOutcome<>();
		String msg = null;
		try {
			BankBranchMaster bankBranch = Optional.ofNullable(bankBranchDto.getBankBranchMaster().getBankBranchId()).isPresent()
							? bankBranchMasterRepository.findByBankBranchId(bankBranchDto.getBankBranchMaster().getBankBranchId())
							: new BankBranchMaster();
			msg = Optional.ofNullable(bankBranchDto.getBankBranchMaster().getBankBranchId()).isPresent()
					? messageSource.getMessage("finYear.msg.update", null, LocaleContextHolder.getLocale())
					: messageSource.getMessage("finYear.msg.success", null, LocaleContextHolder.getLocale());
			bankBranch.setBankId(
					bankMasterRepository.findByBankId(bankBranchDto.getBankBranchMaster().getBankId().getBankId()));
			bankBranch.setBranchName(bankBranchDto.getBankBranchMaster().getBranchName());
			bankBranch.setIfscCode(bankBranchDto.getBankBranchMaster().getIfscCode());
			bankBranch.setBranchMobile(bankBranchDto.getBankBranchMaster().getBranchMobile());
			bankBranch.setBranchEmail(bankBranchDto.getBankBranchMaster().getBranchEmail());
			bankBranch.setBranchAddress(bankBranchDto.getBankBranchMaster().getBranchAddress());
			bankBranchMasterRepository.save(bankBranch);
			outcome.setData(bankBranch);
			outcome.setMessage(msg);
			outcome.setOutcome(true);
		} catch (Exception e) {
			outcome.setOutcome(false);
			outcome.setMessage("Something went wrong..");
			log.error(" Exception occured in CoreMasterServiceImpl at saveBankBranch ----->" + e);
		}
		return outcome;
	}


	@Override
	public ServiceOutcome<BankBranchMaster> editBanBranch(Long bankBranchId) {
		ServiceOutcome<BankBranchMaster> outcome = new ServiceOutcome<>();
		try {
			Optional<BankBranchMaster> bankBranch = bankBranchMasterRepository.findById(bankBranchId);
			outcome.setData(bankBranch.get());
		} catch (Exception e) {
			outcome.setOutcome(false);
			outcome.setMessage("Something went wrong..");
			log.error(" Exception occured in CoreMasterServiceImpl at editBanBranch ----->" + e);
		}
		return outcome;
	}


	@Override
	public List<BankBranchMaster> getAllBankBranchList() {
		List<BankBranchMaster> bankBranchList = new ArrayList<>();
		try {
			bankBranchList = bankBranchMasterRepository.findAllByIsActiveIsTrue();
		} catch (Exception e) {
			log.error(" Exception occured in CoreMasterServiceImpl at getAllFinancialYear ----->"+e);
		}
		return bankBranchList;
	}


	@Override
	public List<BankBranchMaster> findByIfscCode(String ifscCode) {
		List<BankBranchMaster> branchIfsc = new ArrayList<>();
		try {
			branchIfsc = bankBranchMasterRepository.findAllByifscCode(ifscCode);
		} catch (Exception e) {
			log.error(" Exception occured in CoreMasterServiceImpl at findByFynYear ----->"+e);
		}
		return branchIfsc;
	}

	@Override
	public BankBranchMaster findBranchByIfscCode(String ifscCode) {
		BankBranchMaster branchIfsc = new BankBranchMaster();
		try {
			branchIfsc = bankBranchMasterRepository.findByifscCode(ifscCode);
		} catch (Exception e) {
			log.error(" Exception occured in CoreMasterServiceImpl at findByFynYear ----->"+e);
		}
		return branchIfsc;
	}


	

	
}
