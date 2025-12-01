package com.aashdit.prod.heads.hims.ipms.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.aashdit.prod.heads.hims.ipms.dto.RoleDto;
import com.aashdit.prod.heads.hims.ipms.dto.UserVO;
import com.aashdit.prod.heads.hims.ipms.model.RegistrationForm;
import com.aashdit.prod.heads.hims.ipms.utils.LoggedInUser;
import com.aashdit.prod.heads.hims.umt.model.User;
import com.aashdit.prod.heads.hims.umt.service.AccessService;
@Component
public class SecurityHelper {

    @Autowired
    private AccessService accessService;

    private static AccessService staticAccessService;

    @PostConstruct
    private void init() {
        staticAccessService = accessService;
    }

    public static UserVO getCurrentUser() {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Object principal = auth.getPrincipal();

            if (principal instanceof com.aashdit.prod.heads.hims.umt.model.LoggedInUser) {
                return mapUserToUserVO(((com.aashdit.prod.heads.hims.umt.model.LoggedInUser) principal).getDbUser());
            } else if (principal instanceof LoggedInUser) {
                return ((LoggedInUser) principal).getDbUser();
            }
        } catch (Exception e) {
            // Log exception
           // e.printStackTrace();
        }
        return null;
    }

    private static UserVO mapUserToUserVO(User user) {
        if (user == null) return null;

        UserVO dto = new UserVO();
        dto.setUserId(user.getUserId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setMobile(user.getMobile());
        dto.setUserName(user.getUserName());

        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(user.getPrimaryRole(), roleDto);
        dto.setPrimaryRole(roleDto);

        dto.setPassword(user.getPassword());

        // Fetch additional data
        if (staticAccessService != null) {
        	com.aashdit.prod.heads.framework.core.ServiceOutcome<List<RegistrationForm>> outcome = staticAccessService.getByRoleLevel(
                user.getUserId(),
                user.getPrimaryRole().getRoleId(),
                "PUBLIC",
                RegistrationForm.class
            );

            if (outcome != null && outcome.getData() != null && !outcome.getData().isEmpty()) {
                RegistrationForm registrationForm = outcome.getData().get(0);
                if (registrationForm.getRegistrationId() != null) {
                    dto.setEntityId(registrationForm.getRegistrationId());
                }
            }
        }

        return dto;
    }
}
