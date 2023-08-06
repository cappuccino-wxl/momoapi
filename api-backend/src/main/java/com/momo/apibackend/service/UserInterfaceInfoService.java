package com.momo.apibackend.service;

import com.momo.apicommon.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author asus
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
 * @description 用户接口信息服务
* @createDate 2023-05-28 10:29:40
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    /**
     * @description: 接口信息校验
     * @param userInterfaceInfo
     * @param add
     * @return void
     * @date: 2023/5/28 11:14
     */
    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);
    /**
     * @description: 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return boolean
     * @date: 2023/5/28 11:14
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
