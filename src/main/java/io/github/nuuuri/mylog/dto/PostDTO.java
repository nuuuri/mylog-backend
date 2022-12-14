package io.github.nuuuri.mylog.dto;

import io.github.nuuuri.mylog.data.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String preview;
    private String writer;
    private String category;
    private LocalDateTime modified;
    private int look;

    public PostDTO(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.preview = entity.getPreview();
        this.writer = entity.getUser().getUserId();
        this.category = entity.getCategory().getLabel();
        this.modified = entity.getModified();
        this.look = entity.getLook();
    }
}
