package io.github.nuuuri.mylog.service;

import io.github.nuuuri.mylog.data.entity.Block;
import io.github.nuuuri.mylog.data.entity.Post;
import io.github.nuuuri.mylog.data.repository.BlockRepository;
import io.github.nuuuri.mylog.data.repository.PostRepository;
import io.github.nuuuri.mylog.dto.BlockDTO;
import io.github.nuuuri.mylog.dto.BlockListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class BlockService {
    private final BlockRepository blockRepository;
    private final PostRepository postRepository;

    @Transactional
    public void createBlocks(BlockListDTO.Request request) {
        List<BlockDTO.Request> blockDTOList = request.getBlocks();
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));

        List<Block> blocks = IntStream.range(0, blockDTOList.size())
                .mapToObj(i -> blockDTOList.get(i).toEntity(post, i))
                .collect(Collectors.toList());

        blockRepository.saveAll(blocks);
    }
}
