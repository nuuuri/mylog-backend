package io.github.nuuuri.mylog.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

public class BlockListDTO {

    @Data
    @AllArgsConstructor
    @ApiModel(value = "BlockListRequestDTO")
    public static class Request {
        private Long postId;
        private List<BlockDTO.Request> blocks;
    }
}
