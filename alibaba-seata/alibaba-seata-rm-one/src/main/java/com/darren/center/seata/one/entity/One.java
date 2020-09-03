package com.darren.center.seata.one.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * tbl_one
 * @author 
 */
@Data
public class One implements Serializable {
    private Integer id;

    private String name;

    private static final long serialVersionUID = 1L;

    public One(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}