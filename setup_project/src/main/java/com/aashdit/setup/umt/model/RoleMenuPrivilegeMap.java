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
@Table(name = "t_umt_role_menu_privilege_map", schema="public")
public class RoleMenuPrivilegeMap extends Auditable<Long> implements Serializable {

	private static final long serialVersionUID = -4605255365242118589L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_menu_privilege_map_id")
	private Long roleMenuPrivilegeId;
	
	@Column(name="role_menu_id")
	private Long roleMenuId;
	
	@Column(name="privilege_id")
	private Long privilegeId;
	
	@Column(name = "is_active")
	private Boolean isActive;

}
