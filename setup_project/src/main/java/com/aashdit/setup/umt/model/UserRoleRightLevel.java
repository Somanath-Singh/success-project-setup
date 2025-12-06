package com.aashdit.setup.umt.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.aashdit.setup.umt.utils.Auditable;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name="t_umt_user_role_right_level",  schema = "public")
public class UserRoleRightLevel extends Auditable<Long> implements Serializable {

	private static final long serialVersionUID = 2249139132003359594L;
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_role_right_level_id")
	private Long userRoleRightLevelId;
	
	@Column(name="role_level_id")
	private Long roleLevelId;
	
	@Column(name="user_role_id")
	private Long userRoleId;
	
	@Column(name="object_id")
	private Long objectId;

	@Column(name = "object_level")
	private String objectLevel;
	
	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "is_currently_active_session", columnDefinition = "boolean default false")
	private Boolean isCurrentlyActiveSession;


}
