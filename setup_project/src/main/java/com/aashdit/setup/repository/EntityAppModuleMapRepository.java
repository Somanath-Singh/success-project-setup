package com.aashdit.setup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aashdit.setup.model.EntityAppModuleMap;

@Repository
public interface EntityAppModuleMapRepository extends JpaRepository<EntityAppModuleMap, Long> {

	List<EntityAppModuleMap> findAllByIsActiveTrueAndObjectIdAndObjectType(Long governingBodyId, String valueOf);

	Optional<EntityAppModuleMap> findByObjectIdAndObjectTypeAndAppModuleId_Id(Long governingBodyId, String valueOf,
                                                                              Long moduleId);

	@Query(value = "select * from public.t_mst_entity_app_module_map am inner join public.t_mst_application_module md on md.id = am.app_module_id  where am.object_id = :object_id and am.object_type = :object_type and am.is_active = true order by md.display_order ASC", nativeQuery = true)
	List<EntityAppModuleMap> findAllByObjectIdAndObjectType(Long object_id, String object_type);

	@Query("select e from EntityAppModuleMap e " +
			"where e.objectId = :objectId and e.objectType = :objectType " +
			"order by e.appModuleId.displayOrder")
	List<EntityAppModuleMap> findAllByObjectIdAndObjectTypeAll(@Param("objectId") Long objectId, @Param("objectType") String objectType);

	@Query("select e from EntityAppModuleMap e " +
			"where e.objectId = :objectId and e.objectType = :objectType and e.isActive = true " +
			"order by e.appModuleId.displayOrder")
	List<EntityAppModuleMap> findAllByObjectIdAndObjectTypeAllActive(@Param("objectId") Long objectId, @Param("objectType") String objectType);

}
