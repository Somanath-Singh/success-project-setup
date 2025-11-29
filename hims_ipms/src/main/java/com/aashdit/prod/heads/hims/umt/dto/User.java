package com.aashdit.prod.heads.hims.umt.dto;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Data
public class User implements Serializable {
	private static final long serialVersionUID = 285701719160134651L;
	private Long userId;
	private String userName;
	private Role primaryRole;
	private List<Role> roles;
	private String orgLevel;
	private Long orgId;
	private String userType;
	private Long userTypeId;
	private List<Long> staffDepartmentId;
	private String staffDepartment;
	private String userFirstName;
	private String userLastName;
	private String userDesignation;
	private String jwtForCore;
	private List<AllEntityIdsAndLevel> allEntityIdsAndLevel = new ArrayList<>();
	private String currentUserEntityIdAndUserLevel;

	@Data
	public static class AllEntityIdsAndLevel implements Serializable {
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

}
