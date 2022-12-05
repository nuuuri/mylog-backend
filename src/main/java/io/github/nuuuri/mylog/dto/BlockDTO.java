package io.github.nuuuri.mylog.dto;

import io.github.nuuuri.mylog.data.entity.Block;
import io.github.nuuuri.mylog.data.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;


public class BlockDTO {

    @Data
    @AllArgsConstructor
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
}
