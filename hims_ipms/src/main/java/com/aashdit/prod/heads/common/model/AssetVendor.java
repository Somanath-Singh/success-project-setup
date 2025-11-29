package com.aashdit.prod.heads.common.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Where;

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
@Table(name = "t_asset_mst_vendor", schema = "asset_mgmt")
public class AssetVendor extends Auditable<Long> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vendor_id")
	private Long vendorId;

	@NotNull
	@Column(name = "vendor_name")
	private String vendorName;

	@Column(name = "vendor_code", unique = true)
	private String vendorCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_type_id")
    private VendorType vendorType;
	
	@Column(name = "mobile_no")
	private String mobileNo;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "vendor_address")
	private String vendorAddress;
	
	@Column(name = "point_of_contact")
	private String pointOfContact;
	
	@Column(name = "contact_number")
	private String contactNumber;

	@Column(name = "poc_email")
	private String pocEmail;
	
	@Column(name = "gst_no")
	private String gstNo;
	
    @Column(name = "entity_level")
    private String entityLevel;

    @Column(name = "entity_id")
    private Long entityId;

    @Column(name = "is_active")
    private Boolean isActive = false;

	@Column(name = "gst_doc")
	private String gstDoc;

	@Column(name = "aadhar_no")
	private String aadharNo;

	@Column(name = "pan_no")
    private String panNo;

	@Column(name = "pan_doc")
    private String panDoc;

	@Column(name = "tan_doc")
	private String tanDoc;

	@Column(name = "lior_doc")
	private String liorDoc;

	@OneToMany(mappedBy = "vendorId")
	@Where(clause = "is_active = true")
	private List<VendorBankDetails> vendorBankDetails;

	@Transient
    private Long vendorTypeId;

	@Transient
	private Long categoryId;

	@Transient
	private String objectIdAndType;

	@PostLoad
	private void postLoad() {
		if (this.entityId != null && this.entityLevel != null) {
			this.objectIdAndType = this.entityId + "##" + this.entityLevel;
		}
	}
	
}
