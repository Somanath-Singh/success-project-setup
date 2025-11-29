package com.aashdit.prod.heads.framework.core.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class Auditable<U> {
	
	@Column(name = "is_active", columnDefinition = "boolean default true", nullable = false)
    protected Boolean isActive = true;

	@CreatedBy
	@ManyToOne
	@JoinColumn(name = "created_by")
	@JsonIgnore
	private U createdBy;

	@CreatedDate
	@Column(name = "created_on")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date createdOn;

	@LastModifiedBy
	@ManyToOne
	@JoinColumn(name = "updated_by")
	@JsonIgnore
	private U updatedBy;

	@LastModifiedDate
	@Column(name = "updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date updatedOn;

}