package org.classsix.ofms.status;

import org.classsix.ofms.status.inter.StatusBase;

/**
 * Created by huxh on 2017/5/1.
 */
public enum BuyFilmStatus implements StatusBase {
    SUCCESS(0), ERROR(1), ARGUMENTS_ERROR(3), SHOWALLPAIDFILM_ERROR(4), SCOREFILM_ERROR(5);

    private int status;

    BuyFilmStatus(int status) {
        this.status = status;
    }

    @Override
    public int getStatus() {
        return status;
    }
}
