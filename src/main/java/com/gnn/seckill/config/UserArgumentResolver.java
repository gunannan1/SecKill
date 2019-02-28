package com.gnn.seckill.config;


import com.gnn.seckill.common.UserContext;
import com.gnn.seckill.model.MiaoshaUser;
import com.gnn.seckill.service.MiaoShaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 会对Controller层方法的参数执行 HandlerMethodArgumentResolver(对参数的解析器)中的方法。
 * 用来出来把user传入controller的方法里
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private MiaoShaUserService userService;

    /**
     * 判断是否是需要解析的对象
     * @param methodParameter
     * @return
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
      Class<?> clazz =    methodParameter.getParameterType() ;
      return clazz == MiaoshaUser.class ;
    }

    /**
     * 解析参数，返回的对象会自动赋值到参数对象中
     * @param methodParameter
     * @param modelAndViewContainer
     * @param webRequest
     * @param webDataBinderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        /**
         *  threadlocal 存储线程副本 保证线程不冲突
         */
        return UserContext.getUser();
    }

}
