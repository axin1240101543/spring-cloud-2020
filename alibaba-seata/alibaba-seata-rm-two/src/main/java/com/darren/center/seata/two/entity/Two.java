package com.darren.center.seata.two.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * tbl_two
 * @author 
 */
@Data
public class Two implements Serializable {
    private Integer id;

    private String name;

    private static final long serialVersionUID = 1L;

    public Two(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}