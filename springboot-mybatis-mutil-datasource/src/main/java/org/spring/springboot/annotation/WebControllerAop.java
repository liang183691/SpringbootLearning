package org.spring.springboot.annotation;

import com.alibaba.fastjson.JSON;
import org.apache.ibatis.binding.MapperMethod;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class WebControllerAop {
    /**
     * 指定切点
     * 匹配 包及其子包下的所有类的所有方法
     */
    @Pointcut("execution(* org.spring.springboot.controller.*.*(..)))")
    public void  optlog(){
    }

    /**
     * 前置通知，方法调用前被调用
     * @param joinPoint
     */
    @Before("optlog()")
    public void doBefore(JoinPoint joinPoint){
        System.out.println("我是前置通知，方法执行前调用...");
        //获取目标方法的参数信息
        Object[] obj = joinPoint.getArgs();
        Signature signature = joinPoint.getSignature();
        //代理的是哪一个方法
        String methodName = signature.getName();
        //AOP代理类的名字
        String className = signature.getDeclaringTypeName();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] strings = methodSignature.getParameterNames();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attributes.getRequest();
        StringBuffer content = new StringBuffer();
        content.append("url="+req.getRequestURI());
        content.append(" ,paramName=").append(Arrays.toString(strings));
        content.append(" ,paramValue=").append( Arrays.toString(joinPoint.getArgs()));
        content.append( ", clientIp=").append( req.getRemoteAddr());
        content.append(" ,HTTP_METHOD=").append(req.getMethod());
        content.append(" ,methodName=").append(methodName);
        content.append(" ,methodClassName=").append(className);
        System.out.println("AOP方式："+content);
        //设置请求开始时间
        req.setAttribute("STARTTIME2", System.currentTimeMillis());

    }



    /**
     * 处理完请求返回内容
     * @param ret
     * @throws Throwable
     */

    @AfterReturning(returning = "ret", pointcut = "optlog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
         System.out.println("方法的返回值 : " + ret);
    }


    /*
    *//**
     * 后置异常通知
     * @param jp
     *//*

    @AfterThrowing("optlog()")
    public void throwss(JoinPoint jp){
        System.out.println("方法异常时执行.....");
    }*/

    /**
     * 后置最终通知,final增强，不管是抛出异常或者正常退出都会执行
     * @param jp
     */
    @After("optlog()")
    public void after(JoinPoint jp){
          System.out.println("方法最终执行.....");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = attributes.getRequest();
        //当前时间
        long currentTime = System.currentTimeMillis();
        long  startTime = Long.valueOf(req.getAttribute("STARTTIME2").toString());
        System.out.println("AOP方式请求耗时："+(currentTime - startTime)+"ms");
    }



    /**
     * 环绕通知,环绕增强，相当于MethodInterceptor
     * @param pjp
     * @return
     */

    @Around("optlog()")
    public Object arround(ProceedingJoinPoint pjp) {
        try {
            Object o =  pjp.proceed();
            System.out.println("arround:"+JSON.toJSONString(o));
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;

        }

    }
}
