package ticketinfo.config;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Enumeration;

@Aspect
@Component
public class HttpAspect {

    private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HttpAspect.class);


    ThreadLocal<String> parentSpanId = new ThreadLocal<String>();
    ThreadLocal<String> requestId = new ThreadLocal<String>();
    ThreadLocal<String> traceId = new ThreadLocal<String>();
    ThreadLocal<String> spanId = new ThreadLocal<String>();

    ////////////////////////////////// 拦截API调用 //////////////////////////////////////////

    @Pointcut("execution(public * ticketinfo.controller.*.*(..))")
    public void webLog(){
    }

    @Before("webLog()")
    public void doBeforeWeb(JoinPoint joinPoint){
        requestId.set("");
        parentSpanId.set("");
        requestId.set("");
        traceId.set("");
        spanId.set("");

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURI();
        String method = request.getMethod();
        String ip = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();

        Enumeration<String> headers = request.getHeaderNames();
        StringBuilder sb = new StringBuilder();
        while(headers.hasMoreElements()){
            String headerName = headers.nextElement();
            String headerMap;
            switch (headerName){
                case("x-request-id"):{
                    requestId.set(request.getHeader(headerName));
                    headerMap = String.format("[%s:%s]", "RequestId", request.getHeader(headerName));
                    break;
                }
                case("x-b3-traceid"):{
                    traceId.set(request.getHeader(headerName));
                    headerMap = String.format("[%s:%s]", "TraceId", request.getHeader(headerName));
                    break;
                }
                case("x-b3-spanid"):{
                    spanId.set(request.getHeader(headerName));
                    headerMap = String.format("[%s:%s]", "SpanId", request.getHeader(headerName));
                    break;
                }
                case("x-b3-parentspanid"):{
                    parentSpanId.set(request.getHeader(headerName));
                    headerMap = String.format("[%s:%s]", "ParentSpanId", request.getHeader(headerName));
                    break;
                }
                default:{
                    headerMap = String.format("[%s:%s]", headerName, request.getHeader(headerName));
                }
            }
            sb.append(headerMap);
        }

        String requestArgs  = "";
        if(joinPoint.getArgs() != null && joinPoint.getArgs().length > 0){
            for(Object c:  joinPoint.getArgs()) {
                if( !(c instanceof HttpServletResponse) && !(c instanceof HttpServletRequest) &&  !(c instanceof HttpHeaders)) {
                    requestArgs += new Gson().toJson(c);
                }
            }
        }

        logger.info(sb.toString() +
                "[URI:" + url + "]" +
                "[Method:" + method + "]" +
                "[RemoteHost:" + remoteHost + "]" +
                "[IP:" + ip + "]" +
                "[LogType:InvocationRequest]" +
                "[Request:" + requestArgs + "]" );
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturningWeb(Object ret) throws Throwable {

        String traceInfo = "[ParentSpanId:" + parentSpanId.get() + "]" +
                "[RequestId:" + requestId.get() + "]" +
                "[TraceId:" + traceId.get() + "]" +
                "[SpanId:" + spanId.get() + "]" +
                "[LogType:InvocationResponse]";

        if(ret != null){
            logger.info(traceInfo + "[Response:" + new Gson().toJson(ret) + "]");
        }else{
            logger.info(traceInfo + "[Response:void]");
        }
    }

    /////////////////////////////////// 打印异常日志 //////////////////////////////////////////////

    @AfterThrowing(value = "execution(public * ticketinfo.controller.*.*(..))", throwing = "e")
    private void doAfterThrow(Throwable e) {

        logger.info("[ParentSpanId:" + parentSpanId.get() + "]" +
                "[RequestId:" + requestId.get() + "]" +
                "[TraceId:" + traceId.get() + "]" +
                "[SpanId:" + spanId.get() + "]" +
                "[LogType:InternalMethod]" +
                "[ExceptionMessage:" + e.toString() + "]" +
                "[ExceptionCause:" + e.getCause() + "]" +
                "[ExceptionStack:" + Arrays.toString(e.getStackTrace()) + "]");

    }

    ///////////////////////////////////// 拦截方法内部日志 //////////////////////////////////////////////////

    @Pointcut("execution(public * ticketinfo.config.MockLog.printLog(..))")
    public void methodLog(){
    }

    @Before("methodLog()")
    public void doBeforeMethod(JoinPoint joinPoint){
        String content = "";
        if(joinPoint.getArgs() != null && joinPoint.getArgs().length == 1){
            content = (String)joinPoint.getArgs()[0];
        }
        logger.info("[ParentSpanId:" + parentSpanId.get() + "]" +
                "[RequestId:" + requestId.get() + "]" +
                "[TraceId:" + traceId.get() + "]" +
                "[SpanId:" + spanId.get() + "]" +
                "[LogType:InternalMethod]" +
                "[Content:" + content + "]");
    }


}