package io.github.nuuuri.mylog.dto;

import io.github.nuuuri.mylog.data.entity.Post;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

public class PostDTO {

    @Data
    @AllArgsConstructor
    @ApiModel(value = "PostRequestDTO")
    public static class Request {
        private String userId;
        private Long categoryId;
        private String title;
    }

    @Data
    @ApiModel(value = "PostResponseDTO")
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
