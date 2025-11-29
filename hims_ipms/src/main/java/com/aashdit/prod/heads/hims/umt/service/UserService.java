package com.aashdit.prod.heads.hims.umt.service;


import com.aashdit.prod.heads.framework.core.ServiceOutcome;

import com.aashdit.prod.heads.hims.umt.dto.CurrentUserVo;
import com.aashdit.prod.heads.hims.umt.dto.EntityIdAndUserLevel;
import com.aashdit.prod.heads.hims.umt.model.Role;
import com.aashdit.prod.heads.hims.umt.model.User;
import com.aashdit.prod.heads.hims.umt.model.UserRoleMap;
import com.aashdit.prod.heads.hims.umt.model.UserRoleRightLevel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface UserService {

	ServiceOutcome<User> save(User user);
	
	ServiceOutcome<User> findByUsername(String userName);
	
	ServiceOutcome<User> findByUserId(Long userId);

	ServiceOutcome<Page<User>> findUserList(String searchTerm, PageRequest pageRequest);

	ServiceOutcome<User> lockNUnlockUserById(Long id, Boolean status);
	
	ServiceOutcome<List<User>> searchUser(String name);

	List<Role> findActiveRole();

	//ServiceOutcome<User> updateUser(Long userId, User user);

	ServiceOutcome<User> updateUser(Long userId, String userName, String firstName, String lastName, Date dateOfBirth,
                                    String mobile, String email, Long[] userRoleHcMapId, Long[] roleName, Long[] isPrimary, String[] status, String designation);


	List<UserRoleMap> findUserRoleMapByUserId(Long userId);

	ServiceOutcome<User> addUser(String username, String firstname, String lastname,
                                 Date dateOfbirth, String userMobile, String userEmail, Long[] roleId, Long[] isPrimary, String designation, String userType, String level, Long levelId, Long[] objectTypeId, String staffCode);

	ServiceOutcome<User> userRegistration(User user, String roleCode);
	
	ServiceOutcome<List<User>> getUsersByLevelAndId(Long roleLevelId, Long entityId);
	
	//ServiceOutcome<List<User>> getUsersByRoleAndLevelAndId(Long roleId, Long roleLevelId, Long entityId);
	
	ServiceOutcome<List<User>> getAllUsers();
	
	ServiceOutcome<User> updateProfile(User user, MultipartFile userProfileImage);

	Boolean saveResetPassword(Long userId, String txtRePass);
	
	ServiceOutcome<Boolean> createLoginHistory(User user, HttpServletRequest request);
	
	ServiceOutcome<Boolean> createLogoutHistory(User user, HttpServletRequest request);



	ServiceOutcome<Role> getRoleObjectByRoleCode(String roleCode);

	ServiceOutcome<Role> getRoleObjectByRoleId(Long roleId);


	CurrentUserVo getCurrentUserVoByUserId(Long userId);

	Optional<UserRoleMap> findUserRoleMapByUserIdAndRoleId(Long currentUserId, Long primaryRoleId);

	List<UserRoleRightLevel> findUserRoleLevelAccessByUserRoleMapId(Long userRoleMapId);


	List<EntityIdAndUserLevel> getEntityIdAndUserLevelByUserId(Class<?> entityClass);

	List<EntityIdAndUserLevel> getEntityIdAndUserLevelByUserIdAndLevel(Class<?> entityClass, String level);



	boolean reConfigureOrganizationStructure(String objectIdAndTypeEncode);

    ServiceOutcome<Boolean> forgotPasswordSendOtp(String username);


    //Optional<User> addUserWithAllDetails(String userId, String userName, String password, String email, String firstName, String lastName, String phoneNumber, Long primaryRoleId, String otherRoleIds, String level);



}
