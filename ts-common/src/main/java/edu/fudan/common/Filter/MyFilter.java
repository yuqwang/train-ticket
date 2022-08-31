package edu.fudan.common.Filter;

import edu.fudan.common.mq.CRUDRabbitSend;
import edu.fudan.common.mq.CovRabbitSend;
import edu.fudan.common.util.CoverageMessage;
import edu.fudan.common.util.JsonUtils;
import edu.fudan.common.util.Response;
import edu.fudan.common.util.StateMessage;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.jacoco.agent.rt.internal_b6258fc.core.data.ExecutionData;
import org.jacoco.agent.rt.internal_b6258fc.core.data.ExecutionDataReader;
import org.jacoco.agent.rt.internal_b6258fc.core.data.IExecutionDataVisitor;
import org.jacoco.agent.rt.internal_b6258fc.core.data.ISessionInfoVisitor;
import org.jacoco.agent.rt.internal_b6258fc.core.data.SessionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.jacoco.agent.rt.RT;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//@WebFilter(urlPatterns = {"/api/v1/*"}, filterName = "MyFilter")
@Component
public class MyFilter extends HttpFilter {
    @Autowired
    private CRUDRabbitSend crudRabbitSend;
    @Autowired
    private CovRabbitSend covRabbitSend;
    @Value("${spring.application.name}")
    private String service;
    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);
    private HashMap<String, String> uuidMap = new HashMap<>();
    private HashMap<String, ArrayList<String>> coverage = new HashMap<>();
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
                int spanId = TraceContext.spanId();
                //获取header
                String uuid = request.getHeader("Uid");
                if (uuid != null) {
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

                uuid = uuidMap.getOrDefault(traceId, traceId);
                //尝试看覆盖率
                boolean flag = false;
                byte[] executionData = RT.getAgent().getExecutionData(true);
                InputStream in = new ByteArrayInputStream(executionData);
                final ExecutionDataReader reader = new ExecutionDataReader(in);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("CLASS ID         HITS/PROBES   CLASS NAME\n");
                HashMap<String, String> hashMap = new HashMap();
                reader.setSessionInfoVisitor(new ISessionInfoVisitor() {
                    public void visitSessionInfo(final SessionInfo info) {
                        stringBuilder.append(String.format("Session \"%s\": %s - %s%n", info.getId(),
                                new Date(info.getStartTimeStamp()),
                                new Date(info.getDumpTimeStamp())));
                    }
                });
                reader.setExecutionDataVisitor(new IExecutionDataVisitor() {
                    public void visitClassExecution(final ExecutionData data) {
                        stringBuilder.append(String.format("%016x  %3d of %3d   %s%n",
                                Long.valueOf(data.getId()),
                                Integer.valueOf(getHitCount(data.getProbes())),
                                Integer.valueOf(data.getProbes().length),
                                data.getName()));
                        hashMap.put(data.getName(), convertToString(data.getProbes()));
                    }
                });
                reader.read();
                in.close();
//                logger.info(stringBuilder.toString());
                if (hasNewCov(hashMap)) {
                    String covJson = JsonUtils.object2Json(new CoverageMessage(uuid, spanId, service, stringBuilder));
                    covRabbitSend.send(covJson);
                }
                //发送成功的CUD
                if (!request.getMethod().equalsIgnoreCase("get") && status.equals("1")) {
                    Response res = JsonUtils.json2Object(responseBody, Response.class);
                    String infoJson = JsonUtils.object2Json(new StateMessage(uuid, spanId, request.getMethod(), service, res.getData()));
                    crudRabbitSend.send(infoJson);
                }
            } catch (Exception e) {
                logger.warn("fail to build http log", e);
            } finally {
                //这一行必须添加，否则就一直不返回
                responseWrapper.copyBodyToResponse();
            }
        }
    }

    private boolean hasNewCov(HashMap<String, String> hashMap) {
        boolean flag = false;
        for (String key : hashMap.keySet()) {
            if (coverage.containsKey(key)) {
                String newV = hashMap.get(key);
                boolean disrupted = false;
                for (String oldV : coverage.get(key)) {
                    if (oldV.equals(newV)) {
                        disrupted = true;
                        break;
                    }
                }
                if (disrupted) {
                    flag = true;
                    coverage.get(key).add(newV);
                }
            } else {
                flag = true;
                ArrayList<String> arrayList = new ArrayList<>();
                arrayList.add(hashMap.get(key));
                coverage.put(key, arrayList);
            }
        }
        return flag;
    }

    private String convertToString(final boolean[] data) {
        StringBuilder res = new StringBuilder();
        for (final boolean hit : data) {
            res.append(hit ? "1" : "0");
        }
        return res.toString();
    }

    private int getHitCount(final boolean[] data) {
        int count = 0;
        for (final boolean hit : data) {
            if (hit) {
                count++;
            }
        }
        return count;
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