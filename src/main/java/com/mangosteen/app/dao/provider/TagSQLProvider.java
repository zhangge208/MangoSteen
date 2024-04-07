package com.mangosteen.app.dao.provider;

import com.mangosteen.app.model.dao.Tag;
import org.apache.ibatis.jdbc.SQL;

/**
 * SQL Provider for dynamic tag related SQL generation.
 */
public class TagSQLProvider {

    public String updateTag(Tag tagToUpdate) {
        // Dynamic SQL generating
        return new SQL() {
            {
                UPDATE("ms_tag");
                if (tagToUpdate.getName() != null) {
                    SET("name = #{name}");
                }

                if (tagToUpdate.getDescription() != null) {
                    SET("description = #{description}");
                }

                if (tagToUpdate.getIcon() != null) {
                    SET("icon = #{icon}");
                }
                WHERE("id = #{id}");
            }
        }.toString();
    }
}
