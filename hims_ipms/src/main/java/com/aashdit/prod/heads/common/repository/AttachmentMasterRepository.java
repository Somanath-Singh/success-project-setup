package com.aashdit.prod.heads.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aashdit.prod.heads.common.model.AttachmentMaster;

public interface AttachmentMasterRepository extends JpaRepository<AttachmentMaster, Long>{

	List<AttachmentMaster> findAllByAttachmentOfAndEntityIdAndIsActiveIsTrue(String string, Long itemId);

}
