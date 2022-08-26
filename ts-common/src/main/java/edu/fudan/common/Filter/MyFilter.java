package edu.fudan.common.Filter;

import edu.fudan.common.mq.CRUDRabbitSend;
import edu.fudan.common.util.JsonUtils;
import edu.fudan.common.util.Response;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//@WebFilter(urlPatterns = {"/api/v1/*"}, filterName = "MyFilter")
@Component
public class MyFilter extends HttpFilter {
    @Autowired
    private CRUDRabbitSend sendService;
    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);
    private HashMap<String, String> uuidMap;
    /**
     * 反序列化
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            try {
                String traceId = TraceContext.traceId();
                //获取header
                String uuid = request.getHeader("Uid");
                if (uuid != null){
                    uuidMap.put(traceId, uuid);
                }
//                //输出到uuid
//                ActiveSpan.tag("uuid", uuid);
                //获取body
                String body = new String(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
                if (StringUtils.hasLength(body)) {
                    //输出到input
                    ActiveSpan.tag("input", body);
                    logger.info("input:" + body);
                } else
                    logger.info("input is empty");

                //获取返回值body
                String responseBody = new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
                //输出到output
                ActiveSpan.tag("output", responseBody);
                logger.info("output:" + responseBody);
                //读取是否成功
                String reg = "(?<=\"status\"\\:)\\s*\\d*[0-1]";
                Pattern p = Pattern.compile(reg);
                Matcher m = p.matcher(responseBody);
                String status = "unknown";
                if (m.find())
                    status = m.group();
                System.out.println(status);
                //输出到status
                ActiveSpan.tag("status", status);
                logger.info("status:" + status);
                //发送成功的CUD
                if (!request.getMethod().equalsIgnoreCase("get") && status.equals("1")) {
                    Response res = JsonUtils.json2Object(responseBody, Response.class);
                    String service = request.getRequestURI();
                    String reg2 = "(?<=api/v1/)[a-zA-Z0-9]*";
                    Pattern p2 = Pattern.compile(reg2);
                    m = p2.matcher(service);
                    if (m.find())
                        res.setMsg(m.group() + ":" + request.getMethod());
                    String infoJson = JsonUtils.object2Json(res);
                    m = p.matcher(infoJson);
                    sendService.send(m.replaceFirst("\"" + uuidMap.getOrDefault(traceId, traceId) + "\""));
                }
            } catch (Exception e) {
                logger.warn("fail to build http log", e);
            } finally {
                //这一行必须添加，否则就一直不返回
                responseWrapper.copyBodyToResponse();
            }
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        logger.info("初始化过滤器：" + arg0.getFilterName());
        uuidMap = new HashMap<>();
//        ServletContext sc = arg0.getServletContext();
//        WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(sc);
//        if (cxt != null && sendService == null) {
//            sendService = cxt.getBean(RabbitSend.class);
//        }
    }
}