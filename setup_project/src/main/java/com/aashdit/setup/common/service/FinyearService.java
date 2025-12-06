package com.aashdit.setup.common.service;

import java.util.List;

import com.aashdit.setup.common.model.FinancialYear;

public interface FinyearService {

	Object getAllFinyear(boolean b);

	List<FinancialYear> getAllFinalyear(boolean includeDeleted);

}
