package com.momo.apibackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.momo.apibackend.common.ErrorCode;
import com.momo.apibackend.exception.BusinessException;
import com.momo.apibackend.mapper.InterfaceInfoMapper;
import com.momo.apibackend.service.InterfaceInfoService;
import com.momo.apicommon.model.entity.InterfaceInfo;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

/**
* @author asus
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2023-05-28 10:29:40
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {
    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add){
        // 可补充更多校验
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        // 创建时，所有参数必须非空
        if (add) {
            if (StringUtils.isBlank(name)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }
}




