package com.aashdit.setup.umt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aashdit.setup.umt.model.Menu;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
	
	@Query(value="SELECT * FROM t_umt_menu WHERE parent_menu_id IS NULL order by menu_en",nativeQuery=true)
	List<Menu> findMenu();
	
	@Query("FROM Menu WHERE id IN ?1")
	List<Menu> findByMenuIds(Long[] selectedMenu);
	
	List<Menu> findByIsActive(Boolean isActive);
	
	@Query("FROM Menu where  id=:id")
	Menu findByMenuID(@Param("id") Long id);

	@Query(value="SELECT DISTINCT m.*  " + 
			"	FROM t_umt_menu m " + 
			"	JOIN t_umt_role_menu_map rm " + 
			"		ON rm.menu_id = m.menu_id " + 
			"		AND rm.role_id = :roleId " + 
			"		AND rm.is_active = true " +
			"   WHERE m.is_active = true " +
			"   AND  m.is_sys_config = false "+
			"	ORDER BY m.parent_menu_id, m.menu_id, m.display_order;",nativeQuery=true)
	List<Menu> findMenuByRoleId(@Param("roleId") Long roleId);
	
	@Query("from Menu where isModule = true order by displayOrder, menuTextEN")
	List<Menu> findModuleMenus();
	
	@Query("from Menu where isModule = false AND isSystemConfigEntry = false order by menuURL")
	List<Menu> findAppMenus();
	
	@Query(value="SELECT DISTINCT m.*  " + 
			"	FROM public.t_umt_menu m " + 
			"	JOIN t_umt_role_menu_map rm " + 
			"		ON rm.menu_id = m.menu_id " + 
			"		AND rm.role_id = :roleId " + 
			"		AND rm.is_active = true " +
			"   WHERE m.is_active = true " +
			"   AND  m.is_sys_config = false "+
			"	ORDER BY m.parent_menu_id, m.menu_id, m.display_order;",nativeQuery=true)
	List<Menu> findUIMenusByRole(@Param("roleId") Long roleId);


	@Query(value="SELECT DISTINCT m.*  " +
			"	FROM public.t_umt_menu m " +
			"	JOIN t_umt_role_menu_map rm " +
			"		ON rm.menu_id = m.menu_id " +
			"		AND rm.role_id = :roleId " +
			"		AND rm.is_active = true " +
			"       AND m.app_code = :appCode " +
			"   WHERE m.is_active = true " +
			"   AND  m.is_sys_config = false "+
			"	ORDER BY m.parent_menu_id, m.menu_id, m.display_order;",nativeQuery=true)
	List<Menu> findUIMenusByRoleAndCode(@Param("roleId") Long roleId, @Param("appCode") String appCode);

	
	@Query("FROM Menu WHERE isSystemConfigEntry = false and isModule = false")
	List<Menu> findNonModuleNonSystemEntries();

	@Query(value="SELECT DISTINCT m.*\n" +
			"FROM public.t_umt_menu m\n" +
			"JOIN t_umt_role_menu_map rm\n" +
			"ON rm.menu_id = m.menu_id\n" +
			"AND rm.role_id = :roleId\n" +
			"AND rm.is_active_for_mobile = true\n" +
			"AND m.app_code = :appCode\n" +
			"WHERE m.is_active_for_mobile = true\n" +
			"AND  m.is_sys_config = false\n" +
			"ORDER BY m.parent_menu_id, m.menu_id, m.display_order;\n",nativeQuery=true)
	List<Menu> findMobileMenusByRoleAndCode(@Param("roleId") Long roleId, @Param("appCode") String appCode);

	@Query(value="SELECT DISTINCT m.*\n" +
			"FROM public.t_umt_menu m\n" +
			"JOIN t_umt_role_menu_map rm\n" +
			"ON rm.menu_id = m.menu_id\n" +
			"AND rm.role_id = :roleId\n" +
			"AND rm.is_active_for_mobile = true\n" +
			"WHERE m.is_active_for_mobile = true\n" +
			"AND  m.is_sys_config = false\n" +
			"ORDER BY m.parent_menu_id, m.menu_id, m.display_order;\n",nativeQuery=true)
	List<Menu> findMobileMenusByRole(@Param("roleId") Long roleId);
}
