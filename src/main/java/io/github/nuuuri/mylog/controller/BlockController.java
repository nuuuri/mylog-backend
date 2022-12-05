package io.github.nuuuri.mylog.controller;

import io.github.nuuuri.mylog.dto.BlockListDTO;
import io.github.nuuuri.mylog.service.BlockService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/block")
@RequiredArgsConstructor
public class BlockController {
    private final BlockService blockService;

    @PostMapping()
    public void createBlocks(@RequestBody BlockListDTO.Request request) {
        blockService.createBlocks(request);
    }
}
