package com.aashdit.setup.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aashdit.setup.common.model.FinancialYear;
import com.aashdit.setup.common.repository.FinancialYearRepository;

@Service
public class FinyearServiceImpl implements FinyearService {
	
	@Autowired
	private FinancialYearRepository finyearRepository;

	@Override
	public Object getAllFinyear(boolean includeDeleted) {
		return finyearRepository.findByIsActive(includeDeleted);
	}

	@Override
	public List<FinancialYear> getAllFinalyear(boolean includeDeleted) {
		return finyearRepository.findByIsActive(includeDeleted);
	}
}
