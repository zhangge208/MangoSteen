package com.mangosteen.app.dao.provider;

import java.util.List;
import java.util.stream.Collectors;

import com.mangosteen.app.model.dao.Item;
import com.mangosteen.app.model.vo.ItemQueryParam;
import org.apache.ibatis.jdbc.SQL;

public class ItemSQLProvider {

    public String updateItem(Item itemToUpdate) {
        // Dynamic SQL
        return new SQL() {
            {
                UPDATE("ms_item");
                if (itemToUpdate.getAmount() != null) {
                    SET("amount=#{amount}");
                }
                if (itemToUpdate.getStatus() != null) {
                    SET("status=#{status}");
                }
                if (itemToUpdate.getDescription() != null) {
                    SET("description=#{description}");
                }
                if (itemToUpdate.getType() != null) {
                    SET("type=#{type}");
                }
                if (itemToUpdate.getHappenAt() != null) {
                    SET("happen_at=#{happenAt}");
                }
                WHERE("id= #{id}");
            }
        }.toString();
    }

    public String selectItemsByFilters(ItemQueryParam queryParam) {
        return new SQL() {
            {
                SELECT_DISTINCT("item.id, item.user_id, item.amount,"
                           + " item.description, item.type, item.status, item.happen_at");
                FROM("ms_item item");
                WHERE("item.user_id = #{userId}");
                if (queryParam.getItemType() != null) {
                    WHERE("item.type = #{itemType}");
                }

                if (queryParam.getStartTime() != null && queryParam.getEndTime() != null) {
                    WHERE("item.happen_at BETWEEN #{startTime} AND #{endTime}");
                }

                if (queryParam.getTagIds() != null && !queryParam.getTagIds().isEmpty()) {
                    String tagIdsSql = formatTagIds(queryParam.getTagIds());
                    JOIN("ms_item_tag_mapping itm ON item.id = itm.item_id");
                    WHERE("itm.tag_id IN " + tagIdsSql);

                    //select * from xx_table where id in (1,2,3)
                }
                ORDER_BY("item.happen_at DESC");
            }
        }.toString();
    }

    private String formatTagIds(List<Long> tagIds) {
        return tagIds.stream()
                     .map(String::valueOf)
                     .collect(Collectors.joining(", ", "(", ")"));
    }
}
