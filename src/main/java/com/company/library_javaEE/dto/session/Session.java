package com.company.library_javaEE.dto.session;

import com.company.library_javaEE.dto.auth.UserDTO;
import com.company.library_javaEE.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


public class Session {
    public static SessionUser sessionUser;

    public static void setSessionUser(UserDTO user) {
        if (Objects.isNull(user)) {
            sessionUser = null;
        } else
            sessionUser = new SessionUser(user);
    }


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SessionUser {
        private Long id;
        private String email;
        private UserStatus status;

        public SessionUser(UserDTO userDTO) {
            this.id = userDTO.getId();
            this.email = userDTO.getEmail();
            this.status = userDTO.getStatus();
        }
    }
}
