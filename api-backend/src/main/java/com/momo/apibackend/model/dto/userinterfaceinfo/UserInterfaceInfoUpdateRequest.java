package com.momo.apibackend.model.dto.userinterfaceinfo;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 更新请求：主键，总调用次数，剩余调用次数，接口状态
 */
@Data
public class UserInterfaceInfoUpdateRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 总调用次数
     */
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    private Integer leftNum;

    /**
     * 0-正常，1-禁用
     */
    private Integer status;

    @Serial
    private static final long serialVersionUID = 1L;
}

