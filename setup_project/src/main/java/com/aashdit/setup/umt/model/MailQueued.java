package com.aashdit.setup.umt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "t_util_mail_queue", schema = "public")
public class MailQueued implements Serializable {

	private static final long serialVersionUID = 5034477311179983988L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entry_id")
	private Long entryId;

	@Column(name = "mail_to")
	private String mailTo;

	@Column(name = "mail_from")
	private String mailFrom;

	@Column(name = "subject")
	private String subject;

	@Column(name = "body")
	private String body;

	@Column(name = "body_type")
	private String bodyType;

	@Column(name = "status")
	private String status;

	@Column(name = "failure_reason")
	private String failureReason;

	@CreatedDate
	@Column(name = "created_on")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date createdOn;
	
	@Column(name = "updated_on")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonIgnore
	private Date updatedOn;

	@Column(name = "mail_priority")
	private String mailPriority;

}
