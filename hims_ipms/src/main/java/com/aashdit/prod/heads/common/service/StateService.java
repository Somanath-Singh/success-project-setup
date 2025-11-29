package com.aashdit.prod.heads.common.service;


import java.util.List;

import com.aashdit.prod.heads.common.model.State;
import com.aashdit.prod.heads.framework.core.ServiceOutcome;

public interface StateService {

    ServiceOutcome<List<State>> getAllState(Boolean includeDeleted);

    ServiceOutcome<State> getById(Long stateId);

    ServiceOutcome<List<State>> search(State searchParams);

    ServiceOutcome<State> saveState(State state);

    ServiceOutcome<State> updateState(State state);

    ServiceOutcome<State> deleteState(Long stateId);
}
