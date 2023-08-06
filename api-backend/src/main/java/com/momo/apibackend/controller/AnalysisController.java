package com.momo.apibackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.momo.apibackend.annotation.AuthCheck;
import com.momo.apibackend.common.BaseResponse;
import com.momo.apibackend.common.ErrorCode;
import com.momo.apibackend.common.ResultUtils;
import com.momo.apibackend.exception.BusinessException;
import com.momo.apibackend.mapper.UserInterfaceInfoMapper;
import com.momo.apibackend.model.vo.InterfaceInfoVO;
import com.momo.apibackend.service.InterfaceInfoService;
import com.momo.apicommon.model.entity.InterfaceInfo;
import com.momo.apicommon.model.entity.UserInterfaceInfo;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分析控制器
 */
@RestController
@RequestMapping("/analysis")
@Slf4j
public class AnalysisController {

    @Resource
    private UserInterfaceInfoMapper userInterfaceInfoMapper;

    @Resource
    private InterfaceInfoService interfaceInfoService;

    /**
     * @description: 查看调用次数TOP的接口，仅管理员
     */
    @GetMapping("/top/interface/invoke")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<List<InterfaceInfoVO>> listTopInvokeInterfaceInfo() {
        // 统计调用次数TOP3的接口信息
        // UserInterfaceInfoMapper.xml 文件中已写好了SQL语句，在user_interface_info表中降序查询调用最多的接口id和其次数
        List<UserInterfaceInfo> userInterfaceInfoList = userInterfaceInfoMapper.listTopInvokeInterfaceInfo(3);
        // 将接口id和用户-接口列表映射成map集合
        Map<Long, List<UserInterfaceInfo>> interfaceInfoIdObjMap = userInterfaceInfoList.stream()
                .collect(Collectors.groupingBy(UserInterfaceInfo::getInterfaceInfoId));
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        // 在接口信息表中查找TOP3的接口id
        queryWrapper.in("id", interfaceInfoIdObjMap.keySet());
        // 根据接口id获取接口列表信息
        List<InterfaceInfo> list = interfaceInfoService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        // 仅返回TOP3接口的总调用次数
        // 遍历TOP3接口
        List<InterfaceInfoVO> interfaceInfoVOList = list.stream().map(interfaceInfo -> {
            InterfaceInfoVO interfaceInfoVO = new InterfaceInfoVO();
            // 把interfaceInfo的属性复制到interfaceInfoVO
            BeanUtils.copyProperties(interfaceInfo, interfaceInfoVO);
            int totalNum = interfaceInfoIdObjMap.get(interfaceInfo.getId()).get(0).getTotalNum();
            // 设置总数
            interfaceInfoVO.setTotalNum(totalNum);
            return interfaceInfoVO;
        }).collect(Collectors.toList());
        return ResultUtils.success(interfaceInfoVOList);
    }
}

