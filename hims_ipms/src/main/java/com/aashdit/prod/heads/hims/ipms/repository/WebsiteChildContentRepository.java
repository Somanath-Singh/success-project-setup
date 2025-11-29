package com.aashdit.prod.heads.hims.ipms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aashdit.prod.heads.hims.ipms.model.WebsiteChildContent;
@Repository
public interface WebsiteChildContentRepository extends JpaRepository<WebsiteChildContent, Long> {

	List<WebsiteChildContent> findAllByIsActiveTrue();

	WebsiteChildContent findByContentId(Long contentId);

	@Query(value= "SELECT * \n" +
			"FROM t_website_mst_child_content cc\n" +
			"WHERE \n" +
			"  (cc.object_id = :unvId OR :unvId = 0) \n" +
			"  and cc.is_active =true",nativeQuery = true)
	List<WebsiteChildContent> findAllByIsActiveTrueAndObjectId1(@Param("unvId")Long unvId);

//	@Query(value= "SELECT * \n" +
//			"FROM t_website_mst_child_content cc\n" +
//			"WHERE \n" +
//			"  (cc.object_id = :unvId OR :unvId = 0) \n" +
//			"  AND cc.publish_date > CURRENT_DATE;",nativeQuery = true)
//	List<WebsiteChildContent> findAllByIsActiveTrueAndObjectIdPublishDateGreaterThanCurrentDate(@Param("unvId")Long unvId);


//	@Query(value= "   SELECT * \r\n"
//			+ "FROM t_website_mst_child_content cc\r\n"
//			+ "JOIN t_website_menu wm ON wm.web_menu_id = cc.menu_id \r\n"
//			+ "WHERE \r\n"
//			+ " (wm.menu_code = 'FAQ')\r\n"
//			+ " or\r\n"
//			+ " (\r\n"
//			+ " cc.publish_date <= CURRENT_DATE\r\n"
//			+ " AND cc.expire_date >= CURRENT_DATE)\r\n"
//			+ " and( cc.object_id = :unvId OR :unvId = 0)\r\n"
//			+ " AND cc.is_active = TRUE;",nativeQuery = true)
	
	@Query(value="SELECT * \r\n"
			+ "FROM t_website_mst_child_content cc\r\n"
			+ "JOIN t_website_menu wm ON wm.web_menu_id = cc.menu_id \r\n"
			+ "WHERE \r\n"
			+ " ((wm.menu_code = 'FAQ')\r\n"
			+ "or(wm.menu_code = 'RESULT')\r\n"
			+ "and(cc.publish_date <= CURRENT_DATE)or\r\n"
			+ " (\r\n"
			+ " cc.publish_date <= CURRENT_DATE\r\n"
			+ "\r\n"
			+ " AND cc.expire_date >= CURRENT_DATE))\r\n"
			+ "\r\n"
			+ " and( cc.object_id = :unvId OR :unvId = 0)\r\n"
			+ "\r\n"
			+ " AND cc.is_active = TRUE;",nativeQuery = true)
	List<WebsiteChildContent> findAllByIsActiveTrueAndObjectIdPublishDateAndExpiryDateLessThanCurrentDate(@Param("unvId")Long unvId);
}
