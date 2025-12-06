package com.aashdit.setup.umt.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@Table(name = "t_ws_mail_templates", schema = "public")
public class MailTemplatesMaster implements Serializable {

	private static final long serialVersionUID = -6974103749030920423L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long messageId;

	@Column(name = "template_name")
	private String templateName;

	@Column(name = "template_id")
	private String templateId;

	@Column(name = "template_type")
	private String templateType;
	
	@Column(name = "template_code")
	private String templateCode;

	@Column(name = "template_body", columnDefinition = "TEXT")
	private String templateBody;

	@Column(name = "template_subject")
	private String templateSubject;

	@Column(name = "is_active", columnDefinition = "boolean default true")
	private Boolean isActive = true;
	
}
