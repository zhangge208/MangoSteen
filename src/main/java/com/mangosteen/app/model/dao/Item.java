package com.mangosteen.app.model.dao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    private Long id;

    private Long userId;

    private BigDecimal amount;

    private String description;

    private Integer type;

    private Integer status;

    private List<Long> tagIds;

    private LocalDateTime happenAt;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}

