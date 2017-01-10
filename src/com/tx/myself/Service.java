package com.tx.myself;

/**
 * Created by zhyy on 2017/1/7.
 */
public class Service {
    private Dao dao;

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    public void save(){
        dao.save1();
        dao.save2();
    }

}
