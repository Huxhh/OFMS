package org.classsix.ofms.status;

import org.classsix.ofms.status.inter.StatusBase;

/**
 * Created by jiang on 2017/5/3.
 * 面向运气，面向心情，面向Bug。
 */
public enum UserStatus implements StatusBase {
    SUCCESS(0), ERROR(1);

    private int status;

    UserStatus(int status){
        this.status = status;
    }

    @Override
    public int getStatus() {
        return status;
    }
}
