package com.aashdit.setup.umt.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.aashdit.setup.core.ServiceOutcome;
import com.aashdit.setup.umt.dto.AccesslevelConfigMetaInfo;
import com.aashdit.setup.umt.model.Role;
import com.aashdit.setup.umt.model.RoleLevelMap;
import com.aashdit.setup.umt.model.RoleRightLevelMaster;
import com.aashdit.setup.umt.model.UmtNativeQuery;
import com.aashdit.setup.umt.model.User;
import com.aashdit.setup.umt.model.UserRoleMap;
import com.aashdit.setup.umt.model.UserRoleRightLevel;
import com.aashdit.setup.umt.repository.RoleLevelMapRepository;
import com.aashdit.setup.umt.repository.RoleRepository;
import com.aashdit.setup.umt.repository.RoleRightLevelMasterRepository;
import com.aashdit.setup.umt.repository.UmtNativeQueryRepository;
import com.aashdit.setup.umt.repository.UserRoleMapRepository;
import com.aashdit.setup.umt.repository.UserRoleRightLevelRepository;
import com.aashdit.setup.umt.specs.AccessLevelType;
import com.aashdit.setup.umt.utils.SecurityHelper;

@Service
public class AccessServiceImpl implements AccessService, MessageSourceAware {

	private final Logger log = Logger.getLogger(this.getClass());

	private MessageSource messageSource;

	@Autowired
	private RoleRightLevelMasterRepository levelMasterRepository;

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private RoleLevelMapRepository roleLevelMapRepository;

	@Autowired
	private UserRoleMapRepository userRoleMapRepository;

	@Autowired
	private UmtNativeQueryRepository umtNativeQueryRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRoleRightLevelRepository userRoleRightLevelRepository;

	private static StringBuilder columnMetaDataQuery() {
		StringBuilder infoSQL = new StringBuilder();

		/*
		 * SELECT c.column_name, c.table_name FROM information_schema.columns c JOIN
		 * information_schema.tables t ON c.table_name = t.table_name AND c.table_schema
		 * = t.table_schema AND c.table_catalog = t.table_catalog WHERE t.table_name =
		 * CASE WHEN POSITION('.' IN :objectName) > 0 THEN SPLIT_PART(:objectName, '.',
		 * 2) ELSE :objectName END AND t.table_schema = CASE WHEN POSITION('.' IN
		 * :objectName) > 0 THEN SPLIT_PART(:objectName, '.', 1) ELSE 'public' END ORDER
		 * BY c.ordinal_position ASC;
		 */

		infoSQL.append("select c.column_name , c.table_name ");
		infoSQL.append(" from ");
		infoSQL.append(" information_schema.columns c ");
		infoSQL.append(" join information_schema.tables t ");
		infoSQL.append(" on c.table_name = t.table_name ");
		infoSQL.append(" and c.table_schema = t.table_schema ");
		infoSQL.append(" and c.table_catalog = t.table_catalog ");
		infoSQL.append(" where t.table_name = CASE ");
		infoSQL.append(" WHEN POSITION('.' IN :objectName) > 0 THEN SPLIT_PART(:objectName, '.', 2) ");
		infoSQL.append(" ELSE :objectName ");
		infoSQL.append(" END ");
		infoSQL.append(" and t.table_schema = CASE ");
		infoSQL.append(" WHEN POSITION('.' IN :objectName) > 0 THEN SPLIT_PART(:objectName, '.', 1) ");
		infoSQL.append(" ELSE 'public' ");
		infoSQL.append(" END ");
		infoSQL.append(" order by c.ordinal_position asc ");

		return infoSQL;
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;

	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> ServiceOutcome<List<T>> getByRoleLevel(Long userId, Long roleId, AccessLevelType accessLevel,
			Class<T> returnType) {
		ServiceOutcome<List<T>> svcOutcome = new ServiceOutcome<>();
		try {
			log.debug("Validating Access Level : " + accessLevel.getAccesslevel());
			RoleRightLevelMaster roleRightLevel = levelMasterRepository.findByLevelCode(accessLevel.getAccesslevel());
			if (roleRightLevel != null) {
				String sql = buildSQL(roleRightLevel);
				if (sql != null) {
					// TODO : Try to make this dynamic and refactor into a FactoryBuilder
					Query query = null;
					query = entityManager.createNativeQuery(sql, returnType);

					query.setParameter("userId", userId);
					query.setParameter("roleId", roleId);
					query.setParameter("roleLevelId", roleRightLevel.getRoleRightLevelId());

					svcOutcome.setData(query.getResultList());
				}
			} else {
				log.warn("Role Right Level Master not defined for Access level : " + accessLevel.getAccesslevel());
				svcOutcome.setOutcome(false);
				svcOutcome.setData(null);
				svcOutcome.setMessage(
						"Role Right Level Master not defined for Access level : " + accessLevel.getAccesslevel());
			}

		} catch (Exception ex) {
			log.error(ex);

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return svcOutcome;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> ServiceOutcome<List<T>> getByRoleLevel(Long userId, Long roleId, String accessLevelCode,
			Class<T> returnType) {
		ServiceOutcome<List<T>> svcOutcome = new ServiceOutcome<>();
		try {
			log.debug("Validating Access Level : " + accessLevelCode);
			RoleRightLevelMaster roleRightLevel = levelMasterRepository.findByLevelCode(accessLevelCode);
			if (roleRightLevel != null) {
				String sql = buildSQL(roleRightLevel);
				if (sql != null) {
					// TODO : Try to make this dynamic and refactor into a FactoryBuilder
					Query query = null;
					query = entityManager.createNativeQuery(sql, returnType);

					query.setParameter("userId", userId);
					query.setParameter("roleId", roleId);
					query.setParameter("roleLevelId", roleRightLevel.getRoleRightLevelId());

					svcOutcome.setData(query.getResultList());
				}
			} else {
				log.warn("Role Right Level Master not defined for Access level : " + accessLevelCode);
				svcOutcome.setOutcome(false);
				svcOutcome.setData(null);
				svcOutcome.setMessage("Role Right Level Master not defined for Access level : " + accessLevelCode);
			}

		} catch (Exception ex) {
			log.error(ex);

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return svcOutcome;
	}

	private String buildSQL(RoleRightLevelMaster rrlm) {
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("SELECT e.")
					// .append(rrlm.getDisplayColumns())
					.append("*").append(" FROM ").append(rrlm.getDisplayViewName()).append(" e ")
					.append(" JOIN t_umt_user_role_right_level urrl ").append(" ON ")
					// .append(" e.").append(rrlm.getPrimaryKeyName()).append(" = urrl.object_id")
					.append(" e.").append(rrlm.getPrimaryKeyName()).append(" = case when urrl.object_id = -1 then e.")
					.append(rrlm.getPrimaryKeyName()).append(" else urrl.object_id end")
					.append(" join t_umt_user_role_map urm ").append(" on urm.user_role_map_id = urrl.user_role_id ")
					.append(" and urm.user_id = :userId ").append(" and urm.role_id = :roleId ")
					.append(" join t_umt_role_level_map rlm ").append(" on rlm.role_id = :roleId ")
					.append(" and rlm.role_right_level_id = :roleLevelId ")
					.append(" and urrl.role_level_id = rlm.role_level_map_id  ").append(" where e.is_active = true");

		} catch (Exception ex) {
			log.error(ex);
		}

		return sb.toString();
	}

	private String buildSQLForPKs(RoleRightLevelMaster rrlm) {
		StringBuilder sb = new StringBuilder();
		try {
			/*
			 * RAW SQL for District as an example SELECT e.district_id , e.district_id as
			 * dummy FROM t_mst_district e JOIN t_umt_user_role_right_level urrl ON
			 * e.district_id = case when urrl.object_id = -1 then e.district_id else
			 * urrl.object_id end join t_umt_user_role_map urm on urm.user_role_map_id =
			 * urrl.user_role_id and urm.user_id = :userId and urm.role_id = :roleId join
			 * t_umt_role_level_map rlm on rlm.role_id = :roleId and rlm.role_right_level_id
			 * = :roleLevelId where e.is_active = true;
			 */

			sb.append("SELECT e.").append(rrlm.getPrimaryKeyName()).append(" , e.").append(rrlm.getPrimaryKeyName())
					.append(" as dummy ") // To force a RecordSet
					.append(" FROM ").append(rrlm.getDisplayViewName()).append(" e ")
					.append(" JOIN t_umt_user_role_right_level urrl ").append(" ON ")
					// .append(" e.").append(rrlm.getPrimaryKeyName()).append(" = urrl.object_id")
					.append(" e.").append(rrlm.getPrimaryKeyName()).append(" = case when urrl.object_id = -1 then e.")
					.append(rrlm.getPrimaryKeyName()).append(" else urrl.object_id end")
					.append(" join t_umt_user_role_map urm ").append(" on urm.user_role_map_id = urrl.user_role_id ")
					.append(" and urm.user_id = :userId ").append(" and urm.role_id = :roleId ")
					.append(" join t_umt_role_level_map rlm ").append(" on rlm.role_id = :roleId ")
					.append(" and rlm.role_right_level_id = :roleLevelId ")
					.append(" and urrl.role_level_id = rlm.role_level_map_id  ").append(" where e.is_active = true");

		} catch (Exception ex) {
			log.error(ex);
		}

		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceOutcome<AccesslevelConfigMetaInfo> getACLConfigData(Long userId, Long roleId, Long roleRightLevelId,
			String searchTerm, Integer page, Integer size) {
		ServiceOutcome<AccesslevelConfigMetaInfo> svcOutcome = new ServiceOutcome<>();
		try {
			log.debug("Getting Access Level with id : " + roleRightLevelId);
			RoleRightLevelMaster roleRightLevel = levelMasterRepository.getOne(roleRightLevelId);
			if (roleRightLevel != null) {
				AccesslevelConfigMetaInfo alcConfig = new AccesslevelConfigMetaInfo();
				StringBuilder sb = getPagedSQL(searchTerm, page, size, roleRightLevel);
				StringBuilder recCount = getCountSQL(searchTerm, page, size, roleRightLevel);

				String sql = sb.toString();
				if (sql != null) {
					// TODO : Try to make this dynamic and refactor into a FactoryBuilder
					Query query = null;
					query = entityManager.createNativeQuery(sql);
					List<Object[]> rows = query.getResultList();

					Query qCount = null;
					qCount = entityManager.createNativeQuery(recCount.toString());
					Integer lnRecCount = ((BigInteger) qCount.getSingleResult()).intValue();

					Integer totalPages = lnRecCount / size;
					log.debug("Total pages is " + totalPages);
					alcConfig.setTotalPages(totalPages);
					alcConfig.setCurrentPage(page);
					alcConfig.setPageSize(size);

					String sql2 = buildSQLForPKs(roleRightLevel);
					Query query2 = entityManager.createNativeQuery(sql2);
					query2.setParameter("userId", userId);
					query2.setParameter("roleId", roleId);
					query2.setParameter("roleLevelId", roleRightLevel.getRoleRightLevelId());
					List<Object[]> pks = query2.getResultList();

					log.debug("Querying exisiting PKS");
					List<Long> arw = new ArrayList<Long>();
					for (Object[] r : pks) {
						arw.add(Long.parseLong(String.valueOf(r[0])));
					}
					alcConfig.setAllotedRowIds(arw);

					log.debug("Building Column Meta Info");
					StringBuilder infoSQL = columnMetaDataQuery();

					log.debug(infoSQL.toString());

					Query infoQuery = entityManager.createNativeQuery(infoSQL.toString());
					infoQuery.setParameter("objectName", roleRightLevel.getDisplayViewName());
					List<Object[]> colRows = infoQuery.getResultList();

					log.debug("Parsing Column Meta Info");
					LinkedHashMap<Integer, String> colMetaData = new LinkedHashMap<Integer, String>();
					Integer idx = 0;
					for (Object[] r : colRows) {
						String colName = String.valueOf(r[0]);
						colMetaData.put(idx, colName);
						if (roleRightLevel.getPrimaryKeyName().equals(colName)) {
							alcConfig.setPrimaryKey(String.valueOf(r[0]));
						}
						idx++;
					}
					alcConfig.setColumnMetaData(colMetaData);

					log.debug("Building Row Data");
					JSONArray jArr = new JSONArray();
					for (Object[] r : rows) {
						// log.debug("Got a row...");
						JSONObject jobj = new JSONObject();
						for (int idx2 = 0; idx2 < colMetaData.size(); idx2++) {
							String columnName = colMetaData.get(idx2);
							jobj.put(columnName, String.valueOf(r[idx2]));
						}
						jArr.put(jobj);
					}
					alcConfig.setData(jArr.toString());
					svcOutcome.setData(alcConfig);
				}
			} else {
				log.warn("Role Right Level Master not defined for Access level : " + roleRightLevelId);
				svcOutcome.setOutcome(false);
				svcOutcome.setData(null);
				svcOutcome.setMessage("Role Right Level Master not defined for Access level : " + roleRightLevelId);
			}

		} catch (Exception ex) {
			log.error(ex);

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return svcOutcome;
	}

	private StringBuilder getPagedSQL(String searchTerm, Integer page, Integer size,
			RoleRightLevelMaster roleRightLevel) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT e.*").append(" FROM ").append(roleRightLevel.getDisplayViewName()).append(" e ");

		if (searchTerm != null || searchTerm != "") {
			sb.append(" where ");
			String[] searchColumns = roleRightLevel.getSearchColumns().split(",");
			Boolean addOr = false;
			for (String searchColumn : searchColumns) {
				if (addOr) {
					sb.append(" or lower(e.").append(searchColumn).append(") like '%").append(searchTerm.toLowerCase())
							.append("%' ");
				} else {
					sb.append(" lower(e.").append(searchColumn).append(") like '%").append(searchTerm.toLowerCase())
							.append("%' ");
					addOr = true;
				}

			}
		}

		sb.append(" order by e.").append(roleRightLevel.getPrimaryKeyName()).append(" asc ");
		sb.append(" limit ").append(size);
		sb.append(" offset ").append(size * page);
		return sb;
	}

	private StringBuilder getCountSQL(String searchTerm, Integer page, Integer size,
			RoleRightLevelMaster roleRightLevel) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT count(1)").append(" FROM ").append(roleRightLevel.getDisplayViewName()).append(" e ");

		if (searchTerm != null || searchTerm != "") {
			sb.append(" where ");
			String[] searchColumns = roleRightLevel.getSearchColumns().split(",");
			Boolean addOr = false;
			for (String searchColumn : searchColumns) {
				if (addOr) {
					sb.append(" or lower(e.").append(searchColumn).append(") like '%").append(searchTerm.toLowerCase())
							.append("%' ");
				} else {
					sb.append(" lower(e.").append(searchColumn).append(") like '%").append(searchTerm.toLowerCase())
							.append("%' ");
					addOr = true;
				}

			}
		}
		return sb;
	}

	@Override
	public ServiceOutcome<String> saveConfig(Long userId, Long roleId, Long roleRightLevelId, Long objectId) {
		ServiceOutcome<String> svcOutcome = new ServiceOutcome<String>();
		try {
			boolean skipDelete = false;
			User user = SecurityHelper.getCurrentUser();

			// Get roleLevelId
			RoleLevelMap rlm = roleLevelMapRepository.findByRoleIdAndLevelId(roleId, roleRightLevelId);

			String levelCode = null;
			if (rlm.getLevelId() != null) {
				RoleRightLevelMaster roleRightLevel = levelMasterRepository.getOne(rlm.getLevelId());
				levelCode = roleRightLevel.getLevelCode();
			}

			// Get userRoleId
			UserRoleMap urm = userRoleMapRepository.findByUserIdAndRoleId(userId, roleId);

			UserRoleRightLevel urrl = userRoleRightLevelRepository.find(urm.getUserRoleId(), rlm.getRoleLevelId(),
					objectId);
			List<UserRoleRightLevel> allocatedValues = userRoleRightLevelRepository
					.getAllocatedValues(urm.getUserRoleId(), rlm.getRoleLevelId());

			// ADDITION PART
			// If max Allocations is -1 that means there is no limit
			if (rlm.getMaxAllocations() < 0) {
				if (urrl == null) {
					urrl = new UserRoleRightLevel();
					urrl.setObjectId(objectId);
					urrl.setObjectLevel(levelCode);
					urrl.setRoleLevelId(rlm.getRoleLevelId());
					urrl.setUserRoleId(urm.getUserRoleId());
					urrl.setCreatedBy(user.getUserId());
					urrl.setCreatedOn(new Date());
					urrl.setIsActive(true);

					userRoleRightLevelRepository.save(urrl);
					svcOutcome
							.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
				}
				skipDelete = true;
			} else {
				if (allocatedValues != null && allocatedValues.size() < rlm.getMaxAllocations()) {
					if (urrl == null) {
						urrl = new UserRoleRightLevel();
						urrl.setObjectId(objectId);
						urrl.setObjectLevel(levelCode);
						urrl.setRoleLevelId(rlm.getRoleLevelId());
						urrl.setUserRoleId(urm.getUserRoleId());
						urrl.setCreatedBy(user.getUserId());
						urrl.setCreatedOn(new Date());
						urrl.setIsActive(true);

						userRoleRightLevelRepository.save(urrl);
						svcOutcome.setOutcome(true);
						svcOutcome.setMessage(
								messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
					}
					skipDelete = true;
				} else {
					// We have met or exceeded the limit. Return an error message
					svcOutcome.setData(null);
					svcOutcome.setOutcome(false);
					svcOutcome.setMessage(messageSource.getMessage("max.allocations.exceeded", null,
							LocaleContextHolder.getLocale()));
				}
			}
			// DELETETION PART
			if (urrl != null && !skipDelete) {
				userRoleRightLevelRepository.delete(urrl);
				svcOutcome.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			}

		} catch (Exception ex) {
			log.error(ex);

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}

		return svcOutcome;
	}

	@Override
	public ServiceOutcome<String> allocate(Long userId, Long roleId, Long roleRightLevelId, Long objectId) {
		return this.saveConfig(userId, roleId, roleRightLevelId, objectId);
	}

	@Override
	public ServiceOutcome<String> deAllocate(Long userId, Long roleId, Long roleRightLevelId, Long objectId) {
		return this.saveConfig(userId, roleId, roleRightLevelId, objectId);
	}

	@Override
	public ServiceOutcome<User> getUsersWithAccess(Long entityId, String roleLevelCode, Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceOutcome<List<Role>> findRolesFromUserRoleMap(Long userId) {
		ServiceOutcome<List<Role>> svcOutcome = new ServiceOutcome<>();
		try {
			List<Role> roleList = new ArrayList<>();
			List<UserRoleMap> userRoleMapList = userRoleMapRepository.findByUserIdAndIsActiveTrue(userId);
			if (userRoleMapList != null && userRoleMapList.size() > 0) {
				for (UserRoleMap userRoleMap : userRoleMapList) {
					Role role = roleRepository.findByRoleID(userRoleMap.getRoleId());
					roleList.add(role);
				}
			} else {
				log.warn("User Role Map not found against the user whose user id is--> " + userId);
				svcOutcome.setOutcome(false);
				svcOutcome.setData(roleList);
				svcOutcome.setMessage("User Role Map not found for user id-->" + userId);
			}

		} catch (Exception ex) {
			log.error("Exception occured in findRolesFromUserRoleMap method in AccessServiceImpl-->", ex);

			svcOutcome.setData(new ArrayList<>());
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return svcOutcome;
	}

//	@Override
//	public ServiceOutcome<List<CommonNameIdDto>> getAccessListToShowInDashboard(User user) {
//		ServiceOutcome<List<CommonNameIdDto>> svcOutcome = new ServiceOutcome<>();
//		try
//		{
//			List<CommonNameIdDto> accessList = new ArrayList<>();
//			List<UserRoleMap> userRoleMapList = userRoleMapRepository.findByUserIdAndIsActiveTrue(user.getUserId());
//			if (userRoleMapList != null && userRoleMapList.size()>0)
//			{
//				userRoleMapList.sort(Comparator.comparing(UserRoleMap::getUserRoleId));
//				for(UserRoleMap userRoleMap : userRoleMapList) {
//
//					try {
//						Role role = roleRepository.findByRoleID(userRoleMap.getRoleId());
//						CommonNameIdDto commonNameIdDto = new CommonNameIdDto();
//						commonNameIdDto.setId(userRoleMap.getUserRoleId());
//						if(userRoleMap.getObjectType().equals("COLG")) {
//							CollegeMaster collegeObj = collegeMasterRepository.findByCollegeId(userRoleMap.getObjectTypeId());
//							commonNameIdDto.setName(role.getDisplayName()+" ("+collegeObj.getShortName()+")");
//						}
//
//						if(userRoleMap.getObjectType().equals("UNV")) {
//							UniversityMaster universityObj = universityMasterRepository.findByUniversityId(userRoleMap.getObjectTypeId());
//							commonNameIdDto.setName(role.getDisplayName()+" ("+universityObj.getShortName()+")");
//						}
//
//						if(userRoleMap.getObjectType().equals("DEPT")) {
//							HigherEduDepartment hedObj = higherEduDepartmentRepository.findById(userRoleMap.getObjectTypeId()).get();
//							commonNameIdDto.setName(role.getDisplayName()+" ("+hedObj.getHedDptName()+")");
//						}
//						accessList.add(commonNameIdDto);
//					}catch (Exception e) {
//						log.error("Exception occurred in getAccessListToShowInDashboard method in AccessServiceImpl-->",e);
//						log.error("Error in getting access list for userRoleMapId-->"+userRoleMap.getUserRoleId());
//					}
//
//
//				}
//				svcOutcome.setData(accessList);
//				svcOutcome.setOutcome(true);
//				svcOutcome.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
//			}
//			else
//			{
//				log.error("User Role Map not found against the user whose user is--> " +user.getUserName());
//				svcOutcome.setOutcome(false);
//				svcOutcome.setData(new ArrayList<>());
//				svcOutcome.setMessage("User Role Map not found. Please check with admin.");
//			}
//			
//		}
//		catch(Exception ex)
//		{
//			log.error("Exception occured in getAccessListToShowInDashboard method in AccessServiceImpl-->",ex);
//			
//			svcOutcome.setData(new ArrayList<>());
//			svcOutcome.setOutcome(false);
//			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
//		}
//		return svcOutcome;
//	}

	@Override
	public ServiceOutcome<UserRoleMap> getUserRoleMapByMapId(Long userRoleMapId) {
		ServiceOutcome<UserRoleMap> svcOutcome = new ServiceOutcome<>();
		try {
			UserRoleMap userRoleMap = userRoleMapRepository.findById(userRoleMapId).get();
			if (userRoleMap == null) {
				svcOutcome.setData(null);
				svcOutcome.setOutcome(false);
				svcOutcome.setMessage("No roles found.");
			} else {
				svcOutcome.setData(userRoleMap);
				svcOutcome.setOutcome(true);
				svcOutcome.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
			}
		} catch (Exception ex) {
			log.error("Exception occured in getUserRoleMapByMapId method in AccessServiceImpl-->", ex);

			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage(messageSource.getMessage("msg.error", null, LocaleContextHolder.getLocale()));
		}
		return svcOutcome;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ServiceOutcome<List<Object[]>> getCoreDataByQuery(String sql) {
		ServiceOutcome<List<Object[]>> svcOutcome = new ServiceOutcome<>();
		try {
			if (sql != null && !sql.equals("")) {
				String check = sql.substring(0, 6);
				if (check.equalsIgnoreCase("select")) {
					Query query = entityManager.createNativeQuery(sql);
					List<Object[]> colRows = query.getResultList();
					svcOutcome.setData(colRows);
					svcOutcome.setOutcome(true);
					svcOutcome
							.setMessage(messageSource.getMessage("msg.success", null, LocaleContextHolder.getLocale()));
				} else {
					svcOutcome.setData(null);
					svcOutcome.setOutcome(false);
					svcOutcome.setMessage("Only select queries are allowed.");
				}
			} else {
				svcOutcome.setData(null);
				svcOutcome.setOutcome(false);
				svcOutcome.setMessage("The given sql query is null or empty.");
			}
		} catch (Exception e) {
			log.error("Exception occured in getCoreDataByQuery method in AccessServiceImpl-->", e);
			svcOutcome.setData(null);
			svcOutcome.setOutcome(false);
			svcOutcome.setMessage("Unable to execute query.");
		}

		return svcOutcome;
	}

	@Override
	public Optional<RoleRightLevelMaster> getRoleRightLevelMasterByClass(Class<?> entityClass) {
		Optional<RoleRightLevelMaster> roleRightLevelMaster = Optional.empty();
		try {
			String tableNameWithSchema = SecurityHelper.getTableNameWithSchema(entityClass);
			roleRightLevelMaster = levelMasterRepository.findByLevelEntityName(tableNameWithSchema);
		} catch (Exception e) {
			log.error("Exception occured in getRoleRightLevelMasterByClass method in AccessServiceImpl-->", e);
		}
		return roleRightLevelMaster;
	}

	@Override
	public Map<String, Object> getLevelMasterEntityNameByObjectType(Long objectId, String objectTypeCode) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			RoleRightLevelMaster roleRightLevelMaster = levelMasterRepository.findByLevelCode(objectTypeCode);
			if (roleRightLevelMaster != null) {
				String columns = roleRightLevelMaster.getSearchColumns();
				List<Object[]> resultList = getEntityResultData(objectId, roleRightLevelMaster);
				// iterate over the result list and add the data to the resultMap as column key
				// and value
				for (Object[] objects : resultList) {
					for (int i = 0; i < objects.length; i++) {
						resultMap.put(columns.split(",")[i], objects[i]);
					}
				}
			}
		} catch (Exception e) {
			log.error("Exception occured in getLevelMasterEntityNameByObjectType method in AccessServiceImpl-->", e);
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getLevelMasterEntityNameByObjectTypeWithColumns(Long objectId, String objectTypeCode,
			String... fields) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			RoleRightLevelMaster roleRightLevelMaster = levelMasterRepository.findByLevelCode(objectTypeCode);
			if (roleRightLevelMaster != null) {
				String columns = fields.length > 0 ? String.join(",", fields) : roleRightLevelMaster.getSearchColumns();
				List<Object[]> resultList = getEntityResultData(objectId, roleRightLevelMaster, columns);
				// iterate over the result list and add the data to the resultMap as column key
				// and value
				for (Object[] objects : resultList) {
					for (int i = 0; i < objects.length; i++) {
						resultMap.put(fields[i], objects[i]);
					}
				}
			}
		} catch (Exception e) {
			log.error("Exception occurred in getLevelMasterEntityNameByObjectType method in AccessServiceImpl-->", e);
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> getLevelMasterEntityNameByObjectType(Long objectId, String objectTypeCode,
			String... fields) {
		Map<String, Object> resultMap = new HashMap<>();
		try {
			RoleRightLevelMaster roleRightLevelMaster = levelMasterRepository.findByLevelCode(objectTypeCode);
			if (roleRightLevelMaster != null) {
				// String columns = fields.length > 0 ? String.join(",", fields) :
				// roleRightLevelMaster.getSearchColumns();
				List<Object[]> resultList = getEntityResultData(objectId, roleRightLevelMaster);
				// iterate over the result list and add the data to the resultMap as column key
				// and value
				for (Object[] objects : resultList) {
					for (int i = 0; i < objects.length; i++) {
						if (fields.length < i) {
							resultMap.put("field" + i, objects[i]);
						} else {
							resultMap.put(fields[i], objects[i]);
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("Exception occurred in getLevelMasterEntityNameByObjectType method in AccessServiceImpl-->", e);
		}
		return resultMap;
	}

	@SuppressWarnings("unchecked")
	private List<Object[]> getEntityResultData(Long objectId, RoleRightLevelMaster roleRightLevelMaster) {
		String tableNameWithSchema = roleRightLevelMaster.getLevelEntityName();
		String columns = roleRightLevelMaster.getSearchColumns();
		String primaryKey = roleRightLevelMaster.getPrimaryKeyName();
		String sql = "select " + columns + " from " + tableNameWithSchema + " where " + primaryKey + " = :objectId";
		Query nativeQuery = entityManager.createNativeQuery(sql);
		nativeQuery.setParameter("objectId", objectId);
		List<Object[]> resultList = nativeQuery.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	private List<Object[]> getEntityResultData(Long objectId, RoleRightLevelMaster roleRightLevelMaster,
			String columns) {
		String tableNameWithSchema = roleRightLevelMaster.getLevelEntityName();
		String primaryKey = roleRightLevelMaster.getPrimaryKeyName();
		String sql = "select " + columns + " from " + tableNameWithSchema + " where " + primaryKey + " = :objectId";
		Query nativeQuery = entityManager.createNativeQuery(sql);
		nativeQuery.setParameter("objectId", objectId);
		List<Object[]> resultList = nativeQuery.getResultList();
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> setGbOrMunNameAndIcon(List<String> allEntityIdsAndLevel) {
		Map<String, String> gbOrMunNameAndIcon = new ConcurrentHashMap<>();
		try {
			String gbOrMunName = "";
			String icon = "";
			if (allEntityIdsAndLevel != null && !allEntityIdsAndLevel.isEmpty()) {
				for (String data : allEntityIdsAndLevel) {
					if (gbOrMunName != null && !gbOrMunName.isEmpty() && icon != null && !icon.isEmpty()) {
						break;
					}
					String[] split = data.split("##");
					Long entityId = split.length > 0 ? Long.parseLong(split[0]) : 0;
					String entityLevel = split.length > 1 ? split[1] : "";

					Optional<UmtNativeQuery> byQueryCode = umtNativeQueryRepository
							.findByQueryCode(entityLevel + "_ICON");
					if (byQueryCode.isPresent()) {
						UmtNativeQuery umtNativeQuery = byQueryCode.get();
						String sql = umtNativeQuery.getQuery();
						Query nativeQuery = entityManager.createNativeQuery(sql);
						nativeQuery.setParameter("objectId", entityId);
						List<Object[]> resultList = nativeQuery.getResultList();
						if (resultList != null && !resultList.isEmpty()) {
							Object[] objects = resultList.get(0);
							gbOrMunName = objects.length > 0 && objects[0] != null ? objects[0].toString() : "";
							icon = objects.length > 1 && objects[1] != null ? objects[1].toString() : "";
						}
					}
				}
			}
			gbOrMunNameAndIcon.put("gbOrMunName", gbOrMunName);
			gbOrMunNameAndIcon.put("icon", icon);
		} catch (Exception e) {
			log.error("Error setting GB or Mun Name and Icon: " + e.getMessage());
		}
		return gbOrMunNameAndIcon;
	}

}
