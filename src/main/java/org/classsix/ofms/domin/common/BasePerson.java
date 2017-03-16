package org.classsix.ofms.domin.common;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by Jiang on 2017/3/15.
 *
 * @author Jiang
 */

/**
 * 用户类
 */
@MappedSuperclass
public class BasePerson extends BaseEntity{
    private static final long serialVersionUID = 3548821545773064641L;

    /** 姓名 */
    @Column(name = "NAME", length = 50,nullable = true)
    private String name;

    /** 性别 0为女性 1为男性*/
    @Column(name = "SEX", length = 1,nullable = true)
    private Integer sex;

    /** 用户的头像 演员导演的照片*/
    @Column(name = "PERSONPIC", length = 255,nullable = true)
    private Integer personPic;

    /**出生日期*/
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    /** 出生地 */
    @Column(name = "BIRTH_PLACE", length = 50,nullable = true)
    private String birthPlace;

    /** 英文名 */
    @Column(name = "ENGNAME", length = 50,nullable = true)
    private String engname;

    /** 家庭成员名,多名用逗号隔开 */
    @Column(name = "FAMILYNAME", length = 100,nullable = true)
    private String familyname;


    public Integer getPersonPic() {
        return personPic;
    }

    public void setPersonPic(Integer personPic) {
        this.personPic = personPic;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getEngname() {
        return engname;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

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
