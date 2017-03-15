package org.classsix.ofms.repertory.common;

/**
 * Created by Jiang on 2017/3/15.
 *
 * @author Jiang
 */

import javax.persistence.*;

/**
 * 用户类
 */
@MappedSuperclass
public class BasePerson extends BaseEntity{
    private static final long serialVersionUID = 3548821545773064641L;

    /** 姓名 */
    @Column(name = "NAME", length = 50,nullable = false)
    private String name;

    /** 性别 0为女性 1为男性*/
    @Column(name = "SEX", length = 1,nullable = false)
    private Integer sex;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
