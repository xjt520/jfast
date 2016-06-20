package org.fast.core.interceptor;

import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.fast.core.exception.BusinessRuntimeException;
import org.fast.core.kit.ValidateUtil;
import org.fast.core.kit.encoder.md5.MD5Encoder;

import com.google.common.collect.Maps;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * 接口认证拦截器
 */
public class SignInterceptor  implements Interceptor  {
	
	private static final String           DATA      = "data";
    private static final String           TIMESTAMP = "timestamp";
    private static final String           VERSION   = "version";
    private static final String           SIGN      = "sign";

    private static final SimpleDateFormat format    = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Integer          MINUTE    = 3;
    private static final Boolean          BEFORE    = Boolean.TRUE;
    private static final Boolean          AFTER     = Boolean.FALSE;

	@Override
	public void intercept(Invocation me) {
		Controller controller = me.getController();
		HttpServletRequest request = controller.getRequest();
		boolean flag = true;
		Map<String,String> map = Maps.newHashMap();
		try {
			flag = parameterCheck(request);
		} catch (Exception e) {
			if(e instanceof BusinessRuntimeException){
				String code = ((BusinessRuntimeException) e).getErrCode();
				String msg = ((BusinessRuntimeException) e).getErrMsg();
				map.put("code", code);
				map.put("msg", msg);
			}else{
				map.put("code", "500");
				map.put("msg", "系统异常");
			}
			flag = false;
		}
		if(flag)
			me.invoke();
		else
			controller.renderJson(map);
	}
	
	private boolean parameterCheck(HttpServletRequest request) {
        String data = request.getParameter(DATA);
        String timestamp = request.getParameter(TIMESTAMP);
        String version = request.getParameter(VERSION);
        String sign = request.getParameter(SIGN);

        Map<String, String> pMap = new HashMap<String, String>();
        pMap.put(DATA, data);
        pMap.put(TIMESTAMP, timestamp);
        pMap.put(VERSION, version);
        pMap.put(SIGN, sign);

        for (Map.Entry<String, String> m : pMap.entrySet()) {
            parameterCheck(m.getKey(), m.getValue());
        }

        parameterFormatCheck(timestamp);
        
        isValid(data, timestamp, version, sign);

        return true;
    }

	private void parameterCheck(String key, String value) {
		if (!ValidateUtil.isValid(key)) {
            throw new BusinessRuntimeException(ErrorCode.Common.REQUIRED_PARAM, MessageFormat.format("missing required parameter {0}", key));
        }
	}
	
	private void parameterFormatCheck(String value) {
		 try {
	            timeStampCheck(format.parse(value));
	        } catch (ParseException e) {
	            throw new BusinessRuntimeException(ErrorCode.Common.INVALID_PARAM, MessageFormat.format("bad format of parameter {0}", TIMESTAMP));
	        }
		
	}
	
	private void isValid(String data, String timestamp, String version, String sign) {
        StringBuilder orgi = new StringBuilder();
        orgi.append(DATA).append(data);
        orgi.append(TIMESTAMP).append(timestamp);
        orgi.append(VERSION).append(version);

        MD5Encoder md5 = new MD5Encoder();
        if (!md5.isValid(sign, orgi.toString())) {
            throw new BusinessRuntimeException(ErrorCode.Common.INVALID_SIGN, MessageFormat.format("invalid sign {0}",sign));
        }
    }
	
	/**
     * 请求必须在三分钟内完成，否则算无效请求
     */
    private void timeStampCheck(Date time) {
        Calendar timestamp = Calendar.getInstance();
        timestamp.setTime(time);

        Calendar before = getTimeServeralMinuteBefore(MINUTE);
        Calendar after = getTimeServeralMinuteAfter(MINUTE);

        if (timestamp.before(before) || timestamp.after(after)) {
            throw new BusinessRuntimeException(ErrorCode.Common.TIMESTAMP_OUT_RANGE, MessageFormat.format(
                    "timestamp out of range {0} minute", MINUTE));
        }
    }
    
    private Calendar getTimeServeralMinuteBefore(int minute) {
        return getTimeServeralMinute(minute, BEFORE);
    }

    private Calendar getTimeServeralMinuteAfter(int minute) {
        return getTimeServeralMinute(minute, AFTER);
    }

    private Calendar getTimeServeralMinute(Integer minute, Boolean agoOrAfter) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE) + minute * (agoOrAfter ? -1 : 1));
        return cal;
    }
	
	public interface ErrorCode {

	    public interface System {
	        String SUCCESS = "200";
	        String NO_DATA = "404";
	        String UNKNOWN = "400";
	        String ERROR   = "500";
	    }

	    public interface DB {
	        String QUERY_ERROR  = "0001";
	        String SAVE_ERROR   = "0002";
	        String UPDATE_ERROR = "0003";
	        String DELETE_ERROR = "0004";
	    }

	    public interface Common {
	        String INVALID_SIGN        = "1001";
	        String INVALID_PARAM       = "1002";
	        String REQUIRED_PARAM      = "1003";
	        String TIMESTAMP_OUT_RANGE = "1004";
	        String MSG_ID_NOT_EXIST    = "1005";
	        String MSG_ID_EXIST    	   = "1006";
	        String SAVE_IMG_ERROR      = "1007";
	    }

	    public interface Coder {
	        String ENCODE_ERROR         = "2001";
	        String DECODE_ERROR         = "2002";
	        String INVALID_ENCODER_DATA = "2003";
	        String INVALID_JSON         = "2004";
	    }

	    public interface Message {
	    }

	}
	
}


