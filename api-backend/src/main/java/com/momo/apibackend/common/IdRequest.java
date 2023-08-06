package com.momo.apibackend.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 通用删除请求（根据id），即使只有一个id属性，也要封装成对象
 */
@Data
public class IdRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}

