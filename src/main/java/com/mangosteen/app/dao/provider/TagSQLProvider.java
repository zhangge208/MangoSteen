package com.mangosteen.app.dao.provider;

import com.google.common.base.Joiner;
import com.mangosteen.app.model.dao.Tag;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

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

    public String getTagListByIds(List<Long> ids) {
        return new SQL() {
            {
                SELECT("id", "user_id");
                FROM("ms_tag");
                WHERE(String.format("id in ('%s')",
                                    Joiner.on("','")
                                          .skipNulls()
                                          .join(ids)));
            }
        }.toString();
    }
}
