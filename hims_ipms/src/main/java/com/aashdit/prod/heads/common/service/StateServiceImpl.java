package com.aashdit.prod.heads.common.service;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.aashdit.prod.heads.common.model.State;
import com.aashdit.prod.heads.common.repository.StateRepository;
import com.aashdit.prod.heads.framework.core.ServiceOutcome;

@Service
public class StateServiceImpl implements StateService , MessageSourceAware{

    final static Logger logger = Logger.getLogger(StateServiceImpl.class);

    private MessageSource messageSource;

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    @Autowired
    private StateRepository stateRepository;
    
    @Override
    public ServiceOutcome<List<State>> getAllState(Boolean includeDeleted) {

    	ServiceOutcome<List<State>> svcOutcome = new ServiceOutcome<>();
    		try
    		{
    			List<State> lstStates = null;
    			if(includeDeleted) {
        			lstStates = stateRepository.findAllByIsActiveTrue();
    			}else {
    				lstStates = stateRepository.findAll();
    			}
//    			lstStates = new LocaleSpecificSorter<State>(State.class).sort(lstStates);
    			svcOutcome.setData(lstStates);
    			svcOutcome.setOutcome(true);
    			svcOutcome.setMessage("SUCCESS");
    		}
    		catch(Exception ex)
    		{
    			logger.error(ex);
    			
    			svcOutcome.setData(null);
    			svcOutcome.setOutcome(false);
    			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
    		}
    		
    		return svcOutcome;
    	    }

    @Override
    public ServiceOutcome<State> getById(Long stateId) {

		ServiceOutcome<State> svcOutcome = new ServiceOutcome<>();
		try {
			State states = stateRepository.getOne(stateId);
			svcOutcome.setData(states);
		} catch (Exception ex) {
			logger.error(ex);
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return svcOutcome;
	   }

    @Override
    public ServiceOutcome<List<State>> search(State searchParams) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServiceOutcome<State> saveState(State state) {
		ServiceOutcome<State> svcOutcome = new ServiceOutcome<>();
		try {
			if (state.getStateId() != null) {
				State prvState = stateRepository.getOne(state.getStateId());
				prvState.setStateCode(state.getStateCode());
				prvState.setStateNameEN(state.getStateNameEN());
				prvState.setStateNameHI(state.getStateNameHI());
				prvState.setIsActive(state.getIsActive());
				prvState = stateRepository.save(prvState);
				svcOutcome.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			} else {
				state = stateRepository.save(state);
				svcOutcome.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			}

		} catch (Exception ex) {
			logger.error(ex);
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return svcOutcome;
	        }

    @Override
    public ServiceOutcome<State> updateState(State state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServiceOutcome<State> deleteState(Long stateId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
