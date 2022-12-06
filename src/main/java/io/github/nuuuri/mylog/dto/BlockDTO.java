package io.github.nuuuri.mylog.dto;

import io.github.nuuuri.mylog.data.entity.Block;
import io.github.nuuuri.mylog.data.entity.Post;
import io.swagger.annotations.ApiModel;
import lombok.*;


public class BlockDTO {

    @Data
    @Setter(AccessLevel.NONE)
    @AllArgsConstructor
    @ApiModel(value = "BlockRequestDTO")
    public static class Request {
        private String html;
        private String tag;

        public Block toEntity(Post post, int index) {
            return Block.builder()
                    .post(post)
                    .html(html)
                    .tag(tag)
                    .index(index)
                    .build();
        }
    }

    @Getter
    @ApiModel(value = "BlockResponseDTO")
    public static class Response {
        private Long id;
        private String html;
        private String tag;

        public Response(Block entity) {
            this.id = entity.getId();
            this.html = entity.getHtml();
            this.tag = entity.getTag();
        }
    }
}
