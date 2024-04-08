package com.mangosteen.app.model.vo;

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
public class ItemVO {
    private Long id;

    private Long userId;

    private BigDecimal amount;

    private String description;

    private ItemType itemType;

    private List<Long> tagIds;

    private LocalDateTime happenAt;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}



