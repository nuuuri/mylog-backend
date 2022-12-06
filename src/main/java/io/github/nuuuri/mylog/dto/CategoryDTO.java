package io.github.nuuuri.mylog.dto;

import io.github.nuuuri.mylog.data.entity.Category;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryDTO {

    @Data
    @AllArgsConstructor
    @ApiModel(value = "CategoryRequestDTO")
    public static class Request {
        private String name;
        private Long parentId;
    }

    @Data
    @ApiModel(value = "CategoryResponseDTO")
    public static class Response {
        private Long id;
        private String name;
        private String label;
        private int count;
        private List<CategoryDTO.Response> subCategories;


        public Response(Category entity) {
            this.id = entity.getId();
            this.name = entity.getName();
            this.label = entity.getLabel();
            this.count = entity.getCount();
            this.subCategories = entity.getSubCategories()
                    .stream()
                    .map(CategoryDTO.Response::new)
                    .collect(Collectors.toList());
        }
    }
}
