package com.mangosteen.app.model.dao;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tag {
    private Long id;
    private String name;

    private String description;

    private Long userId;

    private String icon;

    private int status;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
