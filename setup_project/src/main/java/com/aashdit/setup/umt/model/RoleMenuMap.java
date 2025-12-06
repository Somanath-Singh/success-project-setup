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
@Table(name = "t_umt_role_menu_map", schema="public")
public class RoleMenuMap extends Auditable<Long> implements Serializable {

	private static final long serialVersionUID = -2468774430138794505L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_menu_map_id")
	private Long roleMenuId;
	
	@Column(name="role_id")
	private Long roleId;
	
	@Column(name="menu_id")
	private Long menuId;

	@Column(name="is_active" , columnDefinition = "boolean default true", nullable = false)
	private Boolean isActive = true;

	@Column(name="is_active_for_mobile")
	private Boolean isActiveForMobile = true;

}
