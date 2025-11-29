package com.aashdit.prod.heads.hims.ipms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.prod.heads.hims.ipms.model.MasterType;

public interface MasterTypeRepository extends JpaRepository<MasterType, Long>{

	@Query(value="select * from public.t_master_type_details tmtd \r\n"
			+ "where tmtd.is_active =true and tmtd.type_category=:status",nativeQuery = true)
	List<MasterType> findAllTypeNameByTypeCategory(@Param(value="status") String status);

	MasterType findByTypeId(Long gender);

	@Query(value="select * FROM public.t_master_type_details tmtd \r\n"
			+ "where tmtd.type_code =:typeCode",nativeQuery = true)
	MasterType findByTypeCode(String typeCode);

}
