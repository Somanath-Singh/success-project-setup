package com.aashdit.prod.heads.hims.umt.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.aashdit.prod.heads.hims.umt.model.Role;

import lombok.Data;

@Data
public class CurrentUserVo {

	private Long userId;
	private String userName;
	private String userLevel; // COLG or UNV or DEPT
	private Long entityId; //college id or department id or university id
	private String entityIdStr;
	private String entityLevelStr;
	private String entityName;//college name or department name or university name
	private Role primaryRole;
	private List<Role> roles;
	private List<DynamicEntityDto> dynamicEntities;
	private List<List<DynamicEntityDto>> dynamicEntitiesOfEntity;
	private Long roleRightLvlId;
	private List<AllEntityIdsAndLevel> allEntityIdsAndLevel = new ArrayList<>();
	private String currentUserEntityIdAndUserLevel;
	@Data
	public static class AllEntityIdsAndLevel implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Long entityId;
		private String entityLevel;
		private String entityIdAndLevel;
	
		public AllEntityIdsAndLevel(long entityId, String entityLevel, String entityIdAndLevel) {
			this.entityId = entityId;
			this.entityLevel = entityLevel;
			this.entityIdAndLevel = entityIdAndLevel;
		}

		public AllEntityIdsAndLevel() {}
	}

	private String gbOrMunName;
	private String icon;

}
