package consignprice.config;

import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Aspect
@Component
public class HttpAspect {

    private final static org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * consignprice.controller.ConsignPriceController.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String url = request.getRequestURI();
        String thisServiceName = request.getServerName();
        String method = request.getMethod();
        String ip = request.getRemoteAddr();
        String remoteHost = request.getRemoteHost();
        String requestArgs  = "";

        Enumeration<String> headers = request.getHeaderNames();
        StringBuilder sb = new StringBuilder();
        sb.append("[Headers:");
        while(headers.hasMoreElements()){
            String headerName = headers.nextElement();
            String headerMap = String.format("[%s:%s]", headerName, request.getHeader(headerName));
            sb.append(headerMap);
        }
        sb.append("]");

        if(joinPoint.getArgs() != null && joinPoint.getArgs().length > 0){
            for(Object c:  joinPoint.getArgs()) {
                if( !(c instanceof HttpServletResponse) && !(c instanceof HttpServletRequest)) {
                    requestArgs += new Gson().toJson(c);
                }
            }
        }

        logger.info(sb.toString() +
                "[Service:" + thisServiceName + "]" +
                "[URI:" + thisServiceName + url + "]" +
                "[Method:" + method + "]" +
                "[Request:" + requestArgs + "]" +
                "[RemoteHost:" + remoteHost + "]" +
                "[IP:" + ip + "]");

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String thisServiceName = request.getServerName();

        if(ret != null){
            logger.info("[Service:" + thisServiceName + "]" + "[Response:" + new Gson().toJson(ret) + "]");
        }else{
            logger.info("[Service:" + thisServiceName + "]" + "[Response:void]");
        }
    }
}