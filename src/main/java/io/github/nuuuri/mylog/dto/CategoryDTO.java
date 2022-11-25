package io.github.nuuuri.mylog.dto;

import io.github.nuuuri.mylog.data.entity.Category;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDTO {

    @Data
    @AllArgsConstructor
    public static class Request {

    }

    @Data
    @ApiModel(value = "categoryResponseDTO")
    public static class Response {
        private Long id;
        private String name;
        private List<CategoryDTO.Response> subCategories;

        public Response(Category entity) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.subCategories = entity.getSubCategories().stream()
                    .map(CategoryDTO.Response::new)
                    .collect(Collectors.toList());
        }
    }
}
