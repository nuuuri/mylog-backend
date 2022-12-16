package io.github.nuuuri.mylog.service;

import io.github.nuuuri.mylog.data.entity.Block;
import io.github.nuuuri.mylog.data.entity.Post;
import io.github.nuuuri.mylog.data.repository.BlockRepository;
import io.github.nuuuri.mylog.data.repository.PostRepository;
import io.github.nuuuri.mylog.dto.BlockDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BlockService {
    private final BlockRepository blockRepository;
    private final PostRepository postRepository;


    @Transactional
    public void createBlocks(Long postId, List<BlockDTO.Request> blockDTOList) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));

        StringBuffer previewBuffer = new StringBuffer();

        List<Block> blocks = new ArrayList<>();

        for (int i = 0; i < blockDTOList.size(); i++) {
            Block block = blockDTOList.get(i).toEntity(post, i);
            blocks.add(block);

            if (previewBuffer.length() < 50) {
                String contents = block.getHtml()
                        .replaceAll("\n", " ")
                        .replaceAll("\\<[^>]*>", " ")
                        .replaceAll("&lt;", " <")
                        .replaceAll("&gt;", "> ");
                previewBuffer.append(contents);
            }
        }

        //List<Block> blocks = IntStream.range(0, blockDTOList.size()).mapToObj(i -> blockDTOList.get(i).toEntity(post, i)).collect(Collectors.toList());

        blockRepository.saveAll(blocks);
        post.updatePreview(previewBuffer.toString());
    }

    @Transactional
    public List<BlockDTO.Response> getBlocks(Long postId) {
        return blockRepository.findAllByPostId(postId)
                .stream()
                .map(BlockDTO.Response::new)
                .collect(Collectors.toList());
    }
}
