package com.aashdit.prod.heads.hims.ipms.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_util_mail_queue")
public class MailQueue implements Serializable {

	private static final long serialVersionUID = -356023932807147L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mail_queue_id")
	private Long mailQueueId;

	@Column(name = "mail_to")
	private String mailTo;

	@Column(name = "body_type")
	private String bodyType;

	@NotNull
	@Column(name = "is_active")
	private Boolean isActive=true;

	@Column(name = "subject")
	private String subject;

	@Column(name = "mail_from")
	private String mailFrom;

	@Column(name = "body")
	private String body;

	@Column(name = "status")
	private String status;

	@Column(name = "failure_reason")
	private String failureReason;
	
	@Column(name = "created_by")
	private Long createdBy;
	
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "last_updated_on")
	private Date lastUpdatedOn;

	public Long getMailQueueId() {
		return mailQueueId;
	}

	

	public Long getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}



	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	public void setMailQueueId(Long mailQueueId) {
		this.mailQueueId = mailQueueId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	public String getMailTo() {
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailFrom() {
		return mailFrom;
	}

	public void setMailFrom(String mailFrom) {
		this.mailFrom = mailFrom;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

}