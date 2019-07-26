package org.spring.springboot.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.istack.internal.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求路劲
        String url = request.getRequestURI();
        //获取请求参数信息
        String paramData = JSON.toJSONString(request.getParameterMap(),SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);
        //获取请求客户都IP
        String clientIp = request.getRemoteAddr();
        //请求方法
        String  methodName = request.getMethod();
        //   String  requestType = request.getHeader("X-Requested-With");
        StringBuffer content = new StringBuffer();
        content.append("url="+url);
        content.append(" ,paramData=").append(paramData);
        content.append( ", clientIp=").append(clientIp);
        content.append(" ,HTTP_METHOD=").append(methodName);
        System.out.println("拦截器方式："+content);
        //设置请求开始时间
        request.setAttribute("STARTTIME", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        //controller 方法处理完毕后，调用此方法
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

        //页面渲染完毕后调用次方法，通常用来清除某些资源，类似java中的finnally
        int status = response.getStatus();
        //当前时间
        long currentTime = System.currentTimeMillis();
        long  startTime = Long.valueOf(request.getAttribute("STARTTIME").toString());
        System.out.println("拦截器方式请求耗时："+(currentTime - startTime)+"ms");
    }

}
