package com.chris.pss.dto;

/**
 * Created by noonecode on 2017/5/12.
 */
public class StudentOnlineCountDTO {
    private long count;

    public StudentOnlineCountDTO(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
