package com.aashdit.prod.heads.hims.ipms.dto;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Data
public class CandidateVo {

	private String advertiseNo;
	private String candidateName;
	private String applicationId;
	private String staffCategory;
	private String postName;
	private String subjectName;
	private String categoryName;
	private String pWd;
	private String dob;
	private String emailId;
	private String applRcvdOn;
	private String gender;
	//private String genderName;

	private String status;
	private Long entityId; // Registration Id
	private Long postId;
	private Long subjectId;
	private MultipartFile documnet;
	private Long universityId;

	private Long candidateId;
	private String remarks;
	private String actionCode;
	private String cycleCode;

	private String PWD;

	private String interviewDate;
	private String actionType;
	private Long scheduleId;
	private String collegeName;

	private boolean disableReschedule;
	private Boolean isDaysExpired = false; // Flag to check if applRcvdOn is more than 3 days old
	private Long vacSanctionedId;
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd"); // Date format

	public CandidateVo(Object[] obj) {
		try {
			if (obj != null) {
				this.advertiseNo   = obj[0]  != null ? obj[0].toString() : "NA";           // vacancy_no
				this.candidateName = obj[1]  != null ? obj[1].toString() : "NA";           // applicant_name
				this.applicationId = obj[2]  != null ? obj[2].toString() : "NA";           // application_no
				this.categoryName  = obj[3]  != null ? obj[3].toString() : "NA";           // casteName
				this.dob           = obj[5]  != null ? obj[5].toString() : "NA";           // dob
				this.emailId       = obj[6]  != null ? obj[6].toString() : "NA";           // email_id
				this.applRcvdOn    = obj[7]  != null ? obj[7].toString() : "NA";           // rcv_on
				this.candidateId   = obj[8]  != null ? Long.valueOf(obj[8].toString()) : null; // apply_id
				this.status        = obj[9]  != null ? obj[9].toString() : "NA";           // status
				this.entityId         = obj[10] != null ? Long.valueOf(obj[10].toString()) : null; // reg_id
				this.universityId      = obj[11] != null ? Long.valueOf(obj[11].toString()) : null; // v_object_id
				this.PWD           = obj[12] != null ? obj[12].toString() : "NA";          // disability_facility
				this.interviewDate = obj[13] != null ? obj[13].toString() : "NA";          // scheduleDt
				this.scheduleId    = obj[14] != null ? Long.valueOf(obj[14].toString()) : null; // schedule_id
				this.remarks       = obj[15] != null ? obj[15].toString() : "NA";          // remark
				this.gender        = obj[16] != null ? obj[16].toString() : "NA";          // genderName
				this.vacSanctionedId = obj[17] != null ? Long.valueOf(obj[17].toString()) : null; // vac_sanctioned_id

				this.isDaysExpired = checkDaysDifference(interviewDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CandidateVo() {
	}

	/**
	 * Checks if the application received date is more than 3 days old
	 */
	private boolean checkDaysDifference(String interviewDate) {
	    if (interviewDate == null || interviewDate.equalsIgnoreCase("NA")) {
	        return false;
	    }

	    String[] patterns = {"dd/MM/yyyy", "dd-MM-yyyy"};

	    for (String pattern : patterns) {
	        try {
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
	            LocalDate interviewDt = LocalDate.parse(interviewDate, formatter);
	            LocalDate today = LocalDate.now();

	            long daysDiff = ChronoUnit.DAYS.between(today, interviewDt);

	            // If interview date is within 3 days (0,1,2,3) → expired for reschedule
	            if (daysDiff <= 3) {
	                return true;
	            }

	        } catch (DateTimeParseException e) {
	            // ignore and try next pattern
	        }
	    }
	    return false;
	}


}
