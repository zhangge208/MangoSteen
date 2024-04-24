package com.mangosteen.app.model.vo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ItemQueryParam {

    private Long userId;

    private String status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    List<Long> tagIds;

    private ItemType itemType;

    private Integer pageNum;

    private Integer pageSize;

}
