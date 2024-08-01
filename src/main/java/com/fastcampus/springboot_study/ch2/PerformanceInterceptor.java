package com.fastcampus.springboot_study.ch2;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class PerformanceInterceptor implements HandlerInterceptor { // 단일 책임의 원칙(SRP) - 하나의 메서드는 하나의 책임만 갖는다.
    //    long startTime; // iv - 인스턴스 변수. 싱글톤(하나의 객체)이라서 여러 쓰레드가 하나의 객체를 공유.
    // 싱글톤에서 iv는 동기화처리를 해줘야함
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 전처리 작업
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime",startTime); // request객체에 startTime을 저장

        // handler -  요청하고 연결된 컨트롤러의 메서드
//        HandlerMethod method = (HandlerMethod) handler;
        // handler가 HandlerMethod인지 확인
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            System.out.println("method.getMethod() =" + handlerMethod.getMethod()); // URL 하고 연결된 메서드
            System.out.println("method.getBean() =" + handlerMethod.getBean()); // 메서드가 포함된 컨트롤러
        }

        // return true; -> 다음 인터셉터나 컨트롤러를 호출 false면 호출안함
        return HandlerInterceptor.super.preHandle(request, response, handler);

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 2. 후처리 작업
        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        System.out.print("["+ ((HttpServletRequest)request).getRequestURI()+"]");
        System.out.println(" time="+(endTime-startTime));

        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }


}
