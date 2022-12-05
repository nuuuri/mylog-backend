package io.github.nuuuri.mylog.data.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "BLOCK")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String html;

    @Column(nullable = false)
    private String tag;

    @Column(nullable = false)
    private int index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false, updatable = false)
    private Post post;

    @Builder
    public Block(Post post, String html, String tag, int index) {
        this.post = post;
        this.html = html;
        this.tag = tag;
        this.index = index;
    }
}
