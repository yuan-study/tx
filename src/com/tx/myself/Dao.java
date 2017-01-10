package com.tx.myself;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

/**
 * Created by zhyy on 2017/1/7.
 */
public class Dao extends JdbcDaoSupport {

    public void save1() {
        JdbcTemplate jdbc = this.getJdbcTemplate();
        jdbc.execute("INSERT demo_info VALUES ('java')");
    }

    public void save2() {
        JdbcTemplate jdbc = this.getJdbcTemplate();
        jdbc.execute("INSERT demo_info VALUES ('javascript')");
    }
}
