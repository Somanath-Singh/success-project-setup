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
@Table(name="t_umt_mst_role_right_level",  schema = "public")
public class RoleRightLevelMaster extends Auditable<Long> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8680189488723686172L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mst_role_right_level_id")
	private Long roleRightLevelId;
	
	@Column(name = "level_code")
	private String levelCode;
	
	@Column(name ="level_entity_name")
	private String levelEntityName;
	
	@Column(name = "display_view_name")
	private String displayViewName;
	
	@Column(name = "primary_key_name")
	private String primaryKeyName;
	
	@Column(name ="display_columns")
	private String displayColumns;

	@Column(name = "is_active")
	private Boolean isActive;
	
	@Column(name = "display_name")
	private String displayName;
	
	@Column(name = "search_columns")
	private String searchColumns;

	@Column(name = "parent_level_id")
	private Long parentLevelId;

	@Column(name = "level_key_class")
	private String levelKeyClass;

	@Column(name = "display_on_ui", columnDefinition = "boolean default true")
	private Boolean displayOnUi = true;

	@Column(name = "display_at_config", columnDefinition = "boolean default true")
	private Boolean displayAtConfig = true;

	@Column(name = "mapping_type", columnDefinition = "varchar default 'ONE_TO_MANY'")
	private String mappingType = "ONE_TO_MANY";


	
}
