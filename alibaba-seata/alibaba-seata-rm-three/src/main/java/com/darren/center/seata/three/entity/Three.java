package com.darren.center.seata.three.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * tbl_three
 * @author 
 */
@Data
public class Three implements Serializable {
    private Integer id;

    private String name;

    private static final long serialVersionUID = 1L;

    public Three(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}