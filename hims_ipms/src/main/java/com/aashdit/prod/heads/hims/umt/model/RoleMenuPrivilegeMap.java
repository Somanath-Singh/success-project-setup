package com.aashdit.prod.heads.hims.umt.model;

import com.aashdit.prod.heads.hims.umt.utils.Auditable;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
