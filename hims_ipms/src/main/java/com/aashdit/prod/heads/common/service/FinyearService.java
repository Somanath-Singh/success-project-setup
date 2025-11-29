package com.aashdit.prod.heads.common.service;

import java.util.List;

import com.aashdit.prod.heads.common.model.FinancialYear;

public interface FinyearService {

	Object getAllFinyear(boolean b);

	List<FinancialYear> getAllFinalyear(boolean includeDeleted);

}
