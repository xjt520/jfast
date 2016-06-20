package org.fast.core.listener;

import org.fast.core.kit.ServletRequestUtils;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class WebServiceListener
        implements ServletRequestListener {
    public void requestDestroyed(ServletRequestEvent arg0) {
    }

    public void requestInitialized(ServletRequestEvent arg0) {
        ServletRequestUtils.putRequest((HttpServletRequest) arg0.getServletRequest());
    }
}