package org.classsix.ofms.domin;


import org.classsix.ofms.domin.common.BasePerson;
import javax.persistence.*;

/**
 * Created by Jiang on 2017/3/15.
 *
 * @author Jiang
 */
@Entity
@Table(name = "DIRECTOR")
public class Director extends BasePerson {
    private static final long serialVersionUID = 1846980330329980249L;
    /** 导演表主键*/
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "PK_DIRECTOR_ID")
    private Integer id;






}
