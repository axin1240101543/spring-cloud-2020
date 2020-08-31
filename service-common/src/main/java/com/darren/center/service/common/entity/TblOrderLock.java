package com.darren.center.service.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * tbl_order_lock
 * @author 
 */
@Data
public class TblOrderLock implements Serializable {
    private Integer orderId;

    private Integer driverId;

    private static final long serialVersionUID = 1L;
}