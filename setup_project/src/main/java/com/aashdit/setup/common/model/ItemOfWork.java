package com.aashdit.setup.common.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aashdit.setup.umt.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_of_work",schema = "public")
public class ItemOfWork {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "work_item_id")
	private Long id;

	@Column(name = "work_item_name")
	private String name;

	@Column(name = "work_item_description")
	private String description;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_on")
	private Date createdOn;

	@ManyToOne
	@JoinColumn(name = "created_by")
	private User createdBy;

	@Column(name = "last_updated_on")
	private Date lastUpdatedOn;

	@ManyToOne
	@JoinColumn(name = "last_updated_by")
	private User lastUpdatedBy;

}
