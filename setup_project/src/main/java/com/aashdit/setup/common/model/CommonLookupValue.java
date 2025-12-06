package com.aashdit.setup.common.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aashdit.setup.umt.utils.Auditable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_mst_common_lookup_value", schema = "public")
public class CommonLookupValue extends Auditable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lookup_value_id")
    private Long lookupValueId;

    @Column(name = "lookup_value_code", nullable = false, length = 64, unique = true)
    private String lookupValueCode;

    @Column(name = "lookup_code", nullable = false, length = 64)
    private String lookupCode;

    @Column(name = "lookup_value_en", nullable = false, length = 128)
    private String lookupValueEn;

    @Column(name = "lookup_value_hi", nullable = false, length = 128)
    private String lookupValueHi;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lookup_code", referencedColumnName = "lookup_code", insertable = false, updatable = false)
    private CommonLookup commonLookup;
    
}