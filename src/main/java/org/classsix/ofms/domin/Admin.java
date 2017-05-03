package org.classsix.ofms.domin;

import javax.persistence.*;

/**
 * Created by clxy on 2017/5/1.
 */
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_ADMIN_ID")
    private int id;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String passWord;


}
