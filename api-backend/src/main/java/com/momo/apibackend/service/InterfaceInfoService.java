package com.momo.apibackend.service;

import com.momo.apicommon.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author asus
* @description 针对表【interface_info(接口信息)】的数据库操作Service
 *@description 接口信息服务
* @createDate 2023-05-28 10:29:40
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
