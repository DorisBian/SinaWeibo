package com.bzy.weibo.bean;

import com.litesuits.orm.db.annotation.Column;
import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.litesuits.orm.db.enums.Relation;

import java.util.List;

/**
 * FriendsTimeLine of WeiBo,now we can see many things from this.
 */
@Table("friends") public class FriendsTimeLine {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    @Column("_id") private long id;
    @Column("since_id") private String since_id;
    @Column("max_id") private String max_id;
    @Mapping(Relation.OneToMany) private List<Status> statuses;

    public String previous_cursor;
    public String next_cursor ;
    public String total_number ;
    public List<AD> ad;
    public List<Status> getStatuses() {
        return statuses;
    }

    public String getSince_id() {
        return since_id;
    }

    public String getMax_id() {
        return max_id;
    }
public static class AD{
        public long id;
        public String mark;
}
    @Override
    public String toString() {
        return "FriendsTimeLine{" +
                "since_id='" + since_id + '\'' +
                ", max_id='" + max_id + '\'' +
                ", statuses=" + statuses +
                '}';
    }
}
