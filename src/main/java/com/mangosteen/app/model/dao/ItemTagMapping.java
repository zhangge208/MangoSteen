package com.mangosteen.app.model.dao;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemTagMapping {
    private Long id;
    private Long itemId;

    private Long tagId;

    private Integer status;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
