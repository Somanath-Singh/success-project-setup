package com.aashdit.prod.heads.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.prod.heads.common.model.MetaData;

public interface MetaDataRepository extends JpaRepository<MetaData, Long> {
	
  @Query("select m from MetaData m where m.metaDataKey = :metaDataKey and m.isActive = true")
  Optional<MetaData> getByKey(@Param("metaDataKey") String paramString);
  
  @Query("select m from MetaData m where m.isActive = true order by m.metaDataId")
  List<MetaData> getAllMetaData();
}
