package com.StudyHub.StudyHub.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MaterialDTO {
    private Long id;
    private String title;
    private String description;
    private String author;
    private String fileUrl;
    private Long categoryId;  // ID категории
}
