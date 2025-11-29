package com.aashdit.prod.heads.hims.umt.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_umt_native_query", schema = "public")
public class UmtNativeQuery implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "query_code", nullable = false, unique = true)
    private String queryCode;

    @Column(name = "query_name")
    private String queryName;

    @Column(name = "query", length = 100000)
    private String query;

    @Column(name = "is_active", columnDefinition = "boolean default true")
    private Boolean isActive = true;


}
