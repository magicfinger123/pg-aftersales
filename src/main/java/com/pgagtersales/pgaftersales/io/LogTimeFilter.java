/*
 * Copyright (c) 2020. Magicfinger
 * Email: mikeossaiofficial@gmail.com
 * Tel: 07086737758
 */

package com.pgagtersales.pgaftersales.io;

import com.pgagtersales.pgaftersales.model.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Component
public class LogTimeFilter implements Filter {

    @Autowired
    ApiResponse apiResponse;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest,servletResponse);
        long duration = System.currentTimeMillis() - startTime;
        apiResponse.executionTime = Double.valueOf(duration);
        System.out.println("Request take "+duration+ " ms");
    }
}
