package com.aashdit.prod.heads.hims.umt.utils;


import com.aashdit.prod.heads.hims.umt.dto.CurrentUserVo;
import com.aashdit.prod.heads.hims.umt.model.LoggedInUser;
import com.aashdit.prod.heads.hims.umt.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.Table;

public class SecurityHelper {

	
	public static User getCurrentUser() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			LoggedInUser currentUser = (LoggedInUser) auth.getPrincipal();
			CurrentUserVo currUserVo = currentUser.getCurrentUserVo();
			User user = currentUser.getDbUser();
			user.setCurrentUserVo(currUserVo);
			return user;
		}
		catch(Exception e) {
			return null;
		}
	}

	public static Long getCurrentUserEntityId() {
		User user = getCurrentUser();
		if(user != null) {
			CurrentUserVo currentUserVo = user.getCurrentUserVo();
			if(currentUserVo != null) {
				return currentUserVo.getEntityId();
			}
		}
		return null;
	}

	public static String getCurrentUserEntityLevel() {
		User user = getCurrentUser();
		if(user != null) {
			CurrentUserVo currentUserVo = user.getCurrentUserVo();
			if(currentUserVo != null) {
				return currentUserVo.getUserLevel();
			}
		}
		return null;
	}

	public static String getCurrentUserEntityIdAndLevel() {
		return getCurrentUserEntityId() + "##" + getCurrentUserEntityLevel();
	}


	public static String getTableNameWithSchema(Class<?> entityClass) {
		Table tableAnnotation = (Table)entityClass.getAnnotation(Table.class);
		String schemaName;
		if (tableAnnotation != null) {
			schemaName = tableAnnotation.schema();
			if (schemaName == null || schemaName.isEmpty()) {
				schemaName = "public";
			}
		} else {
			schemaName = "public";
		}

		return schemaName + "." + getTableName(entityClass);
	}

	public static String getTableName(Class<?> entityClass) {
		Table tableAnnotation = (Table)entityClass.getAnnotation(Table.class);
		return tableAnnotation != null ? tableAnnotation.name() : entityClass.getSimpleName();
	}



}
