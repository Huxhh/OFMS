package org.classsix.ofms.domin;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jiang on 2017/5/18.
 * 面向运气，面向心情，面向Bug。
 */
@Entity
public class Role {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;


    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
