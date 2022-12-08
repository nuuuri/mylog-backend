package io.github.nuuuri.mylog.dto;

import io.github.nuuuri.mylog.data.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostDTO {
    private String title;
    private String preview;
    private String writer;
    private String category;
    private LocalDateTime modified;
    private int look;

    public PostDTO(Post entity) {
        this.title = entity.getTitle();
        this.preview = "";
        this.writer = entity.getUser().getUserId();
        this.category = entity.getCategory().getName();
        this.modified = entity.getModified();
        this.look = entity.getLook();
    }
}
