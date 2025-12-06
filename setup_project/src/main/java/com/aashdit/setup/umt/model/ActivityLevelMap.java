package com.aashdit.setup.umt.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_umt_activity_level_map", schema = "public")
public class ActivityLevelMap implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4611086012829692345L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
    @Column(name = "id", nullable = false)
    private Long id;
    
	@NotNull
    @Column(name = "table_module_name")
    private String tableModuleName;

    @Column(name = "table_module_query_codes", length = 100000)
    private String tableModuleQueryCodes;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;

    
}
