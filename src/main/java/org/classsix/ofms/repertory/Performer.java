package org.classsix.ofms.repertory;

/**
 * Created by Jiang on 2017/3/15.
 *
 * @author Jiang
 */

import org.classsix.ofms.repertory.common.BaseEntity;
import org.classsix.ofms.repertory.common.BasePerson;

import javax.persistence.*;

/**
 * 演员表，别问我为什么不用actor 难道我还要创个actress
 */
@Entity
@Table(name = "PERFORMER")
public class Performer extends BasePerson {
    private static final long serialVersionUID = -6502161847872999997L;
    /** 演员表主键*/
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "PK_PERFORMER_ID")
    private Integer id;


    /** 主演电影外键 */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_FILM_ID")
    private Film film;




}
