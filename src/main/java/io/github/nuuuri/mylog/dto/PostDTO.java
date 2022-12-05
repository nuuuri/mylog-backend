package io.github.nuuuri.mylog.dto;

import io.github.nuuuri.mylog.data.entity.Category;
import io.github.nuuuri.mylog.data.entity.Post;
import io.github.nuuuri.mylog.data.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class PostDTO {

    @Data
    @AllArgsConstructor
    @ApiModel(value = "postRequestDTO")
    public static class Request {
        private String userId;
        private Long categoryId;
        private String title;
    }

    @Data
    @ApiModel(value = "postResponseDTO")
    public static class Response {
        private String userId;
        private String category;
        private String title;

        public Response(Post entity) {
            this.userId = entity.getUser().getUserId();
            this.category = entity.getCategory().getName();
            this.title = entity.getTitle();
        }
    }
}
