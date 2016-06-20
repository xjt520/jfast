package org.fast.core.kit;

import com.jfinal.log.Log;

import javax.servlet.http.HttpServletRequest;

public class ServletRequestUtils
{
  private static final Log logger = Log.getLog(ServletRequestUtils.class);
  private static final ThreadLocal<HttpServletRequest> reqs = new ThreadLocal();
  
  public static void putRequest(HttpServletRequest req)
  {
    String path = req.getServletPath();
    if (path.endsWith(".js") || path.endsWith(".css") || path.endsWith(".png") || path.endsWith(".jpg") || path.endsWith(".gif")){
      return;
    }else{
    //logger.info("================ServletPath================"+req.getServletPath());
      reqs.set(req);
    }
  }
  
  public static void removeReqeust()
  {
    reqs.remove();
  }
  
  public static HttpServletRequest getRequest()
  {
    return (HttpServletRequest)reqs.get();
  }
}