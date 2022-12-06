package io.github.nuuuri.mylog.dto;

import io.github.nuuuri.mylog.data.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class UserDTO {

    @Data
    @AllArgsConstructor
    @ApiModel(value = "UserRequestDTO")
    public static class Request {
        private String userId;
        private String password;
        private String name;
        private String email;

        @Builder
        public User toEntity() {
            return User.builder()
                    .userId(userId)
                    .password(password)
                    .name(name)
                    .email(email)
                    .build();
        }
    }

    @Data
    @ApiModel(value = "UserResponseDTO")
    public static class Response {
        private String userId;
        private String name;
        private String email;

        public Response(User entity) {
            this.userId = entity.getUserId();
            this.name = entity.getName();
            this.email = entity.getEmail();
        }
    }
}
