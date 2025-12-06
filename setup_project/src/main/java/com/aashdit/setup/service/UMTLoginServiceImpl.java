package com.aashdit.setup.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import com.aashdit.setup.core.ServiceOutcome;
import com.aashdit.setup.core.util.ClientInfo;
import com.aashdit.setup.umt.model.LoggedInUser;
import com.aashdit.setup.umt.model.Role;
import com.aashdit.setup.umt.model.User;
import com.aashdit.setup.umt.model.UserLoginHistory;
import com.aashdit.setup.umt.model.UserRoleMap;
import com.aashdit.setup.umt.repository.UserLoginHistoryRepository;
import com.aashdit.setup.umt.repository.UserRepository;
import com.aashdit.setup.umt.service.RoleService;
import com.aashdit.setup.umt.service.UserService;
import com.aashdit.setup.utils.AESEncryptionDecryption;
import com.aashdit.setup.utils.SecurityHelper;
import com.aashdit.setup.utils.SmsServiceUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.icu.impl.locale.XCldrStub.Predicate;

@Service
public class UMTLoginServiceImpl implements UMTLoginService {

	final static Logger logger = Logger.getLogger(UMTLoginServiceImpl.class);

	@Autowired
	private UserLoginHistoryRepository ulHistoryReporsitory;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Value("${OTP.BASED.LOGIN:flase}")
	private String isOtpBasedLogin;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private MailService mailService;

	private final Predicate<String> nonnullEmpty = a -> Optional.ofNullable(a).isPresent() ? a.length() > 0 : false;

	public ServiceOutcome<Boolean> createFailedLoginHistory(User user, HttpServletRequest request,
			HttpServletResponse response) {
		ServiceOutcome<Boolean> svcOutcome = new ServiceOutcome<>();
		try {
			Boolean result = false;
			UserLoginHistory ulHistory = new UserLoginHistory();

			ulHistory.setBrowserDetails(ClientInfo.getClientBrowser(request));
			ulHistory.setEmail(user.getEmail());
			ulHistory.setFirstName(user.getFirstName());
			ulHistory.setLastName(user.getLastName());
			ulHistory.setLoggedInDate(new Date());
			ulHistory.setLoggedOutDate(null);
			ulHistory.setLoginStatus("LOGIN");
			ulHistory.setLoginType(user.getPrimaryRole().getRoleCode());
			ulHistory.setMobile(user.getMobile());
			ulHistory.setOsDetails(ClientInfo.getClientOS(request));
			ulHistory.setUserName(user.getUserName());
			ulHistory.setUserId(user.getUserId());
			ulHistory.setIpAddress(ClientInfo.getClientIpAddr(request));
			ulHistory.setCreatedBy(user.getUserId());
			ulHistory.setCreatedOn(new Date());

			if (SecurityHelper.getCurrentUser() == null) {
				ServiceOutcome<User> user1 = userService.findByUsername("system");
				authorizedUser(user1.getData());
				result = true;
			}

			ulHistoryReporsitory.save(ulHistory);

			if (result) {
				Cookie[] cookies = request.getCookies();
				String[] cookieNames = new String[cookies.length];

				for (int i = 0; i < cookies.length; i++) {
					cookieNames[i] = cookies[i].getName();
				}

				CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(cookieNames);
				SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
				cookieClearingLogoutHandler.logout(request, response, null);
				securityContextLogoutHandler.logout(request, response, null);
			}

			svcOutcome.setData(true);
		} catch (Exception ex) {
			svcOutcome.setOutcome(false);
			svcOutcome.setData(false);
			svcOutcome.setMessage(ex.getMessage());
			logger.error("Exception occured in createFailedLoginHistory in LoginServiceImpl-->", ex);
		}

		return svcOutcome;
	}

	@Override
	public ServiceOutcome<Boolean> createNoUserLoginHistory(String userName, HttpServletRequest request,
			HttpServletResponse response) {
		ServiceOutcome<Boolean> svcOutcome = new ServiceOutcome<>();
		try {
			ServiceOutcome<User> user = userService.findByUsername("system");

			UserLoginHistory ulHistory = new UserLoginHistory();
			ulHistory.setBrowserDetails(ClientInfo.getClientBrowser(request));
			ulHistory.setEmail("failedLogin@ormas.in");
			ulHistory.setFirstName("Failed");
			ulHistory.setLastName("Login");
			ulHistory.setLoggedInDate(new Date());
			ulHistory.setLoggedOutDate(null);
			ulHistory.setLoginStatus("FAILED LOGIN");
			ulHistory.setLoginType("NO ROLE");
			ulHistory.setMobile("9876543210");
			ulHistory.setOsDetails(ClientInfo.getClientOS(request));
			ulHistory.setUserName(userName);
			ulHistory.setIpAddress(ClientInfo.getClientIpAddr(request));
			authorizedUser(user.getData());

			if (ulHistory.getMobile().length() > 10) {
				ulHistory.setMobile(ulHistory.getMobile().substring(0, 10));
				logger.debug("Mobile truncated to: " + ulHistory.getMobile());
			}

			ulHistoryReporsitory.save(ulHistory);

			Cookie[] cookies = request.getCookies();
			String[] cookieNames = new String[cookies.length];

			for (int i = 0; i < cookies.length; i++) {
				cookieNames[i] = cookies[i].getName();
			}

			CookieClearingLogoutHandler cookieClearingLogoutHandler = new CookieClearingLogoutHandler(cookieNames);
			SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
			cookieClearingLogoutHandler.logout(request, response, null);
			securityContextLogoutHandler.logout(request, response, null);
			svcOutcome.setData(true);
		} catch (Exception ex) {
			svcOutcome.setOutcome(false);
			svcOutcome.setData(false);
			svcOutcome.setMessage(ex.getMessage());
			logger.error("Exception occured in createNoUserLoginHistory in LoginServiceImpl-->", ex);
		}

		return svcOutcome;
	}

	public void authorizedUser(User user) {
		List<UserRoleMap> userRoleMaps = this.userService.findUserRoleMapByUserId(user.getUserId());
		userRoleMaps = (List<UserRoleMap>) userRoleMaps.stream().filter(r -> r.getIsActive().booleanValue())
				.collect(Collectors.toList());
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		List<Role> lstRoles = new ArrayList<>();
		for (UserRoleMap urm : userRoleMaps) {
			Role role = roleService.findByRoleId(urm.getRoleId()).getData();
			grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleCode()));
			if (role.getIsActive().booleanValue())
				lstRoles.add(role);
		}
		user.setRoles(lstRoles);
		LoggedInUser userDetails = new LoggedInUser(user.getUserName(), user.getPassword(), true, true, true, true,
				grantedAuthorities, user.getPrimaryRole(), user);
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, null, grantedAuthorities);
		SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(usernamePasswordAuthenticationToken);
	}

	@Override
	public ServiceOutcome<String> sendOtpByUserName(String userName, HttpSession session) {
		ServiceOutcome<String> serviceOutcome = new ServiceOutcome<>();
		try {
			User user = userRepository.findByUserName(userName);
			if (Optional.ofNullable(user).isPresent() && Boolean.parseBoolean(isOtpBasedLogin)) {
				Random random = new Random();
				String otp = String.valueOf(random.nextInt(999999));
				String msg = "Your OTP for login is {#otp#}  - Govt. of Odisha";
				String response = SmsServiceUtil.sendSms(msg.replace("{#otp#}", otp), "ODIGOV", user.getMobile(),
						"1007772737776920501", "D015003", "sendOTPSMS");
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.readTree(response);
				String status = jsonNode.get("status").asText();
				if (status.equals("1")) {
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append("your OTP has been sent to ");
					stringBuilder.append(user.getMobile().substring(0, 2));
					stringBuilder.append("******");
					stringBuilder.append(user.getMobile().substring(user.getMobile().length() - 2));
					serviceOutcome.setMessage(stringBuilder.toString());
					user.setUpdatedOn(new Date());
					// user.setMobileOtp(otp);
					session.setAttribute("otp", AESEncryptionDecryption.encryptData(otp));
					session.setAttribute("otpExpiry", System.currentTimeMillis() + 2 * 60 * 1000); // 2-minute expiry
					userRepository.save(user);
				} else {
					serviceOutcome.setOutcome(false);
					serviceOutcome.setMessage(jsonNode.get("message").asText());
				}
			} else {
				serviceOutcome.setOutcome(false);
				serviceOutcome.setMessage(Boolean.parseBoolean(isOtpBasedLogin) ? "Invalid User Name !!"
						: "Failed to access OTP-based login service. Please try again later or contact support for assistance.");
			}

		} catch (Exception ex) {
			serviceOutcome.setOutcome(false);
			serviceOutcome.setMessage(ex.getMessage());
			logger.error("Exception occured in sendOtpByUserName LoginServiceImpl-->", ex);
		}
		return serviceOutcome;
	}

	@Override
	public String resetOTP(String userName, HttpSession session) {
		try {
			Optional<User> userOptional = Optional.ofNullable(userRepository.findByUserName(userName));
			userOptional.ifPresent(user -> {
				session.removeAttribute("otp");
				session.removeAttribute("otpExpiry");
			});
			return userOptional.isPresent() ? "SUCCESS" : "ERROR";
		} catch (Exception e) {
			return "ERROR";
		}
	}

	@Override
	public ServiceOutcome<String> resetPassword(Long userId) {
		ServiceOutcome<String> serviceOutcome = new ServiceOutcome<>();
		try {
			userRepository.findById(userId).ifPresent(userDtls -> {
				userDtls.setPassword(bCryptPasswordEncoder.encode("123456"));
				userDtls.setIsActive(true);
				userDtls.setIsLocked(false);
				userDtls.setIsEnabled(true);
				userRepository.save(userDtls);
				serviceOutcome.setMessage("Password successfully reset");
			});
		} catch (Exception ex) {
			serviceOutcome.setOutcome(false);
			serviceOutcome.setMessage("Something went wrong in reset Password");
			logger.error("Exception occured in resetPassword UMTLoginServiceImpl-->", ex);
		}

		return serviceOutcome;
	}

	@Override
	public ServiceOutcome<String> sendOtp(String mobNo, HttpSession session, String emailId) {
		ServiceOutcome<String> serviceOutcome = new ServiceOutcome<>();
		try {
			Random random = new Random();
			if (nonnullEmpty.test(mobNo)) {
				String otp = String.format("%06d", random.nextInt(1000000));
				System.out.println("OTP" + "--" + otp);
				String msg = "Welcome to Odisha Guest Faculty Recruitment Portal. Your OTP for registration is XXXX. HED, Govt. of Odisha";
				// String response=SmsServiceUtil.sendSMS(msg.replace("{#otp#}", otp), "ODIGOV",
				// mobNo, "1007772737776920501","D015003","sendOTPSMS");
				String response = SmsServiceUtil.sendSms(msg.replace("XXXX", otp), "ODIGOV", mobNo,
						"1007738456673299668", "D048002", "singleSMS");
//			    ObjectMapper objectMapper = new ObjectMapper();
//		        JsonNode jsonNode = objectMapper.readTree(response);
				// String status = jsonNode.get("status").asText();
				if (response.equals("1")) {
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append("your OTP has been sent to ");
					stringBuilder.append(mobNo.substring(0, 2));
					stringBuilder.append("******");
					stringBuilder.append(mobNo.substring(mobNo.length() - 2));
					serviceOutcome.setMessage(stringBuilder.toString());
					// user.setLastUpdatedOn(new Date());
					// user.setMobileOtp(otp);
					session.setAttribute("otp", AESEncryptionDecryption.encryptData(otp));
					session.setAttribute("otpExpiry", System.currentTimeMillis() + 2 * 60 * 1000); // 2-minute expiry
					// userRepository.save(user);
				} else {
					serviceOutcome.setOutcome(false);
					serviceOutcome.setMessage("message not sent");
				}
			} else if (nonnullEmpty.test(emailId)) {
				String otp = String.format("%06d", random.nextInt(1000000));
				System.out.println("E-OTP" + "--" + otp);
				Boolean isOtpBoolean = mailService.sendOtp(otp, emailId);
				if (isOtpBoolean) {
					session.setAttribute("otp", AESEncryptionDecryption.encryptData(otp));
					session.setAttribute("otpExpiry", System.currentTimeMillis() + 2 * 60 * 1000); // 2-minute expiry
				} else {
					serviceOutcome.setOutcome(false);
					serviceOutcome.setMessage("Error in mail otp send ..");
				}
			} else {
				serviceOutcome.setOutcome(false);
				serviceOutcome.setMessage(Boolean.parseBoolean(isOtpBasedLogin) ? "Invalid Mobile Or Email !!"
						: "Failed to access OTP-based login service. Please try again later or contact support for assistance.");
			}

		} catch (Exception ex) {
			serviceOutcome.setOutcome(false);
			serviceOutcome.setMessage(ex.getMessage());
			logger.error("Exception occured in sendOtpByUserName LoginServiceImpl-->", ex);
		}
		return serviceOutcome;
	}

	@Override
	public String resetOTPByMob(String mobileNo, HttpSession session) {
		try {
			Optional<String> userOptional = Optional.ofNullable(mobileNo);
			userOptional.ifPresent(user -> {
				session.removeAttribute("otp");
				session.removeAttribute("otpExpiry");
			});
			return userOptional.isPresent() ? "SUCCESS" : "ERROR";
		} catch (Exception e) {
			return "ERROR";
		}
	}

}