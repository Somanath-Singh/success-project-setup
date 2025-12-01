package com.aashdit.prod.heads.hims.ipms.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aashdit.prod.heads.hims.ipms.model.RegistrationForm;


@Repository
public interface RegistrationFormRepository extends JpaRepository<RegistrationForm, Long> {

	RegistrationForm findByRegistrationId(Long registrationId);

	List<RegistrationForm> findByIsActiveTrue();

	RegistrationForm findByRegistrationIdAndIsActiveTrue(Long objectId);

	@Query(value="select \r\n"
			+ "tmr.first_name || ''||tmr.last_name  as name ,\r\n"
			+ "tmg.gender_name_en ,\r\n"
			+ "to_char(tmr.dob,'DD-MM-YYYY' ) asdate_of_birth,\r\n"
			+ "tmr.mobile_no ,\r\n"
			+ "tmr.email \r\n"
			+ "from t_guest_faculty_applicant_reg tmr \r\n"
			+ "join t_mst_gender tmg on tmg.gender_id =tmr.gender_id \r\n"
			+ "where  tmr.is_active =true and\r\n"
			+ "DATE(tmr.created_on) BETWEEN DATE(:fromDate) AND DATE(:toDate)",nativeQuery = true)
	List<Object[]>applicantRegistrationReportData(@Param("fromDate") Date fromDate , @Param ("toDate") Date toDate);

	RegistrationForm findByContact(String userName);

}
