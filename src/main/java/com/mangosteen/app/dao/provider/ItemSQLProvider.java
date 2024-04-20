package com.mangosteen.app.dao.provider;

import com.mangosteen.app.model.dao.Item;
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
}
