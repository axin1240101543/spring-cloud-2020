package com.darren.center.service.common.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * tbl_order
 * @author 
 */
@Data
public class TblOrder implements Serializable {


    private static final long serialVersionUID = 4265416186441597170L;

    private Integer orderId;

    private Integer status;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", status=").append(status);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}