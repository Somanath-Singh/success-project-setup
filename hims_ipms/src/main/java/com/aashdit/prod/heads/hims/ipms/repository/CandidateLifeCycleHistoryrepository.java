package com.aashdit.prod.heads.hims.ipms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aashdit.prod.heads.hims.ipms.model.CandidateLifeCycleHistory;

import java.util.List;
import java.util.Optional;

@Repository
public interface CandidateLifeCycleHistoryrepository extends JpaRepository<CandidateLifeCycleHistory, Long> {


    Optional<List<CandidateLifeCycleHistory>> findByApplyId(Long candidateId);

    //Optional<List<CandidateLifeCycleHistory>> findByCandidateIdAndActionTaken(Long candidateId, String status);
}
