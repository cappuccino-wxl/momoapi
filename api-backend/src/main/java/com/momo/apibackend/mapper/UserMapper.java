package com.momo.apibackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.momo.apicommon.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author asus
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-05-28 10:29:40
* @Entity com.momo.apicommon.model.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




