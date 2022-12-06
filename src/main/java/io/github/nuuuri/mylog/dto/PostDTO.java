package io.github.nuuuri.mylog.dto;

import io.github.nuuuri.mylog.data.entity.Post;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class PostDTO {

    @Getter
    @AllArgsConstructor
    @ApiModel(value = "PostRequestDTO")
    public static class Request {
        private String userId;
        private Long categoryId;
        private String title;
        private List<BlockDTO.Request> blocks;
    }

    @Getter
    @ApiModel(value = "PostResponseDTO")
    public static class Response {
        private Long id;
        private String userId;
        private String category;
        private String title;
        private List<BlockDTO.Response> blocks;

        public Response(Post entity) {
            this.id = entity.getId();
            this.userId = entity.getUser().getUserId();
            this.category = entity.getCategory().getName();
            this.title = entity.getTitle();
            this.blocks = entity.getBlocks()
                    .stream()
                    .map(BlockDTO.Response::new)
                    .collect(Collectors.toList());
        }
    }
}
