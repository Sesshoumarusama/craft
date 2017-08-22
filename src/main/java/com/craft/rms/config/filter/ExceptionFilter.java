package com.craft.rms.config.filter;

import com.alibaba.fastjson.JSON;
import com.craft.rms.Base.AjaxReturnInfo;
import com.craft.rms.Base.ResultStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 异常过滤器，当异常发生时会捕获控制层抛出的异常，并且打印到控制台
 *
 */
public class ExceptionFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {

            // In console print Exception Message!!!
            logger.error("【捕获到控制层抛出的异常(该异常信息也将反馈到前端):】", e);

            /* send Exception Message to browser!!! */
            String causeMsg = getFinalCauseMsg(e);
            String failMsg = ResultStatus.EX_MSG + "\r\n | " + causeMsg;

            AjaxReturnInfo expInfo = AjaxReturnInfo.failed(failMsg);
            expInfo.setException(e);
            // write to HttpServletResponse
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setContentType("application/json");
            PrintWriter writer = httpResponse.getWriter();
            writer.write(JSON.toJSONString(expInfo));
        }
    }

    /**
     * 获取Caused by message
     *
     * @param cause
     * @return
     */
    private String getFinalCauseMsg(Throwable cause) {
        Throwable finalCause = cause;
        while (true) {
            Throwable tmpCause = finalCause.getCause();
            if (tmpCause == null) {
                break;
            }
            finalCause = tmpCause;
        }
        return finalCause.getClass().getName() + " : " + finalCause.getMessage();
    }

    public void destroy() {
    }
}
