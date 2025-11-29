package com.aashdit.prod.heads.common.model;

import java.io.Serializable;
import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.context.i18n.LocaleContextHolder;

import com.aashdit.prod.heads.framework.core.annotation.Sortable;
import com.aashdit.prod.heads.hims.umt.utils.Auditable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_mst_state", schema = "public")
public class State extends Auditable<Long> implements Serializable {

	private static final long serialVersionUID = 285701719160134651L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "state_id")
	private Long stateId;

	@Column(name = "state_name_en")
	@Sortable(lang = "en")
	private String stateNameEN;

	@Column(name = "state_name_hi")
	@Sortable(lang = "hi")
	private String stateNameHI;

	@Column(name = "state_code")
	private String stateCode;

	@Column(name = "is_active")
	private Boolean isActive;
	
	@Transient
	private String stateName;

	public String getStateName() {
		Locale locale = LocaleContextHolder.getLocale();
        if (locale.getLanguage().equals("hi")) {
            return this.getStateNameHI();
        }
        return this.getStateNameEN();
    }

}
