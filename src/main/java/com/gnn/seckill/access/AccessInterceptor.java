package com.gnn.seckill.access;

import com.alibaba.fastjson.JSON;

import com.gnn.seckill.common.Result;
import com.gnn.seckill.enums.ResultStatus;
import com.gnn.seckill.model.User;
import com.gnn.seckill.redis.RedisService;
import com.gnn.seckill.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

import static com.gnn.seckill.enums.ResultStatus.ACCESS_LIMIT_REACHED;
import static com.gnn.seckill.enums.ResultStatus.SESSION_ERROR;


@Service
public class AccessInterceptor  extends HandlerInterceptorAdapter{

	private static Logger logger = LoggerFactory.getLogger(AccessInterceptor.class);

	@Autowired
    UserService userService;

	@Autowired
	RedisService redisService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/**
		 * 获取调用 获取主要方法
		 */
		if(handler instanceof HandlerMethod) {
			logger.info("打印拦截方法handler ：{} ",handler);
			HandlerMethod hm = (HandlerMethod)handler;
			//方便mybatis 测试
//			if(hm.getMethod().getName().startsWith("test")){
//				return true;
//			}
			User user = getUser(request, response);
			UserContext.setUser(user);
			AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
			if(accessLimit == null) {
				return true;
			}
			int seconds = accessLimit.seconds();
			int maxCount = accessLimit.maxCount();
			boolean needLogin = accessLimit.needLogin();
			String key = request.getRequestURI();
			if(needLogin) {
				if(user == null) {
					render(response, SESSION_ERROR);
					return false;
				}
				key += "_" + user.getUsername();
			}else {
				//do nothing
			}
            //通过redis找到登录的次数，如果超过最大次数，则返回错误信息
            AccessKey ak = AccessKey.withExpire(seconds);
			Integer count = redisService.get(ak, key, Integer.class);
	    	if(count  == null) {
	    		 redisService.set(ak, key, 1);
	    	}else if(count < maxCount) {
	    		 redisService.incr(ak, key);
	    	}else {
	    		render(response, ACCESS_LIMIT_REACHED);
	    		return false;
	    	}
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
		UserContext.removeUser();
	}

    /**
     *输出错误信息
     * @param response
     * @param cm
     * @throws Exception
     */
	private void render(HttpServletResponse response, ResultStatus cm)throws Exception {
		response.setContentType("application/json;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		String str  = JSON.toJSONString(Result.error(cm));
		out.write(str.getBytes("UTF-8"));
		out.flush();
		out.close();
	}

    /**
     * 从token里获取用户
     * @param request
     * @param response
     * @return
     */
	private User getUser(HttpServletRequest request, HttpServletResponse response) {
		String paramToken = request.getParameter(UserService.COOKIE_NAME_TOKEN);
		String cookieToken = getCookieValue(request, UserService.COOKIE_NAME_TOKEN);
		if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
			return null;
		}
		String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
		return userService.getByToken(response, token);
	}

    /**
     * 得到确定的name的cookie的值
     * @param request
     * @param cookiName
     * @return
     */
	private String getCookieValue(HttpServletRequest request, String cookiName) {
		Cookie[]  cookies = request.getCookies();
		if(cookies == null || cookies.length <= 0){
			return null;
		}
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(cookiName)) {
				return cookie.getValue();
			}
		}
		return null;
	}

}
