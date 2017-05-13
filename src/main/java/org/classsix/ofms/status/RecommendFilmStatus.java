package org.classsix.ofms.status;

import org.classsix.ofms.status.inter.StatusBase;

/**
 * Created by huxh on 2017/5/3.
 */
public enum RecommendFilmStatus implements StatusBase {
    SUCCESS(0), ERROR(1), CALC_SIMILARITY_ERROR(2);

    private int status;

    RecommendFilmStatus(int status) {
        this.status = status;
    }

    @Override
    public int getStatus() {
        return status;
    }
}
