package com.tx.myself;

/**
 * Created by zhyy on 2017/1/7.
 */
public class Action {
    private Service service;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void execute(){
        service.save();
    }
}
