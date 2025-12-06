package com.aashdit.setup.umt.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.aashdit.setup.umt.dto.CurrentUserVo;

import lombok.Getter;

@Getter
public class LoggedInUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 7767108758059803455L;

    private final Role primaryRole;

    private final User dbUser;

    private CurrentUserVo currentUserVo;

    public LoggedInUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Role primaryRole, User dbUser) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.primaryRole = primaryRole;
        this.dbUser = dbUser;
    }

    public LoggedInUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, Role primaryRole, User dbUser, CurrentUserVo currentUserVo) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.primaryRole = primaryRole;
        this.dbUser = dbUser;
        this.currentUserVo = currentUserVo;
    }


    public void setCurrentUserVo(CurrentUserVo currentUserVo) {
        this.currentUserVo = currentUserVo;
    }

}

