package io.github.nuuuri.mylog.dto;

import io.github.nuuuri.mylog.data.entity.Post;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

public class PostDetailDTO {

    @Getter
    @AllArgsConstructor
    @ApiModel(value = "PostDetailRequestDTO")
    public static class Request {
        private String userId;
        private Long categoryId;
        private String title;
        private List<BlockDTO.Request> blocks;
    }

    @Getter
    @ApiModel(value = "PostDetailResponseDTO")
    public static class Response {
        private final Long id;
        private final String userId;
        private final String category;
        private final String title;
        private final List<BlockDTO.Response> blocks;

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
