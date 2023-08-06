package com.momo.apibackend.aop;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.momo.apibackend.annotation.AuthCheck;
import com.momo.apibackend.common.ErrorCode;
import com.momo.apibackend.exception.BusinessException;
import com.momo.apibackend.service.UserService;
import com.momo.apicommon.model.entity.User;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限校验 AOP
 */
@Aspect
@Component
public class AuthInterceptor {

    @Resource
    private UserService userService;

    /**
     * 执行拦截
     *
     * @param joinPoint
     * @param authCheck
     * @return
     */
    @Around("@annotation(authCheck)") // 环绕通知
    public Object doInterceptor(ProceedingJoinPoint joinPoint, AuthCheck authCheck) throws Throwable {
        // 获取AuthCheck注解的两个用户角色
        List<String> anyRole = Arrays.stream(authCheck.anyRole()).filter(StringUtils::isNotBlank).collect(Collectors.toList());
        // 获取AuthCheck注解的“必须有某个角色”
        String mustRole = authCheck.mustRole();
        // 它是Spring MVC框架内部工具，外界很少用到。它的作用是Model在封装Request、Session的数据时，
        // 通过RequestContextHolder.currentRequestAttributes()取得当前线程下的Request、Session的Attribute值。
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        // 从Attribute中获取request
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        // 当前登录用户
        User user = userService.getLoginUser(request);
        // 拥有任意权限即通过
        if (CollectionUtils.isNotEmpty(anyRole)) {
            String userRole = user.getUserRole();
            if (!anyRole.contains(userRole)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
        // 必须有所有权限才通过
        if (StringUtils.isNotBlank(mustRole)) {
            String userRole = user.getUserRole();
            if (!mustRole.equals(userRole)) {
                throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
            }
        }
        // 通过权限校验，放行
        return joinPoint.proceed();
    }
}


