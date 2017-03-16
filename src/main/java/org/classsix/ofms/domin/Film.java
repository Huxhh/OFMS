package org.classsix.ofms.domin;

/**
 * Created by Jiang on 2017/3/15.
 *
 * @author Jiang
 */

import org.classsix.ofms.domin.common.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;


/**
 * 电影类
 */
@Entity
@Table(name = "FILM")
public class Film extends BaseEntity {
    private static final long serialVersionUID = -5740919825288439368L;

    /** 电影表主键*/
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "PK_FILM_ID")
    private Integer id;

    /** 导演外键 */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_DIRECTOR_ID")
    private Director director;


    /** 评分*/
    @Column(name = "RANK", length = 2)
    private Double rank;


    /** 类型,每个类型用逗号隔开*/
    @Column(name = "TYPE", length = 100)
    private String type;


    /** 语言*/
    @Column(name = "LANGUAGE", length = 20)
    private String language;

    /** 片长*/
    @Column(name = "LENGTH", length = 4)
    private Integer length;


    /** 电影截图,每个截图用逗号隔开*/
    @Column(name = "PIC", length = 255)
    private String pic;

    /** 地区*/
    @Column(name = "AREA", length = 5)
    private String area;

    /**上映日期*/
    @Column(name = "OUT_DATE")
    private Date outDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public Double getRank() {
        return rank;
    }

    public void setRank(Double rank) {
        this.rank = rank;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Date getOutDate() {
        return outDate;
    }

    public void setOutDate(Date outDate) {
        this.outDate = outDate;
    }
}
