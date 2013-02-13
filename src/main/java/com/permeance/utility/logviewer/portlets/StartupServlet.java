/**
 * Copyright (C) 2013 Permeance Technologies
 * 
 * This program is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package com.permeance.utility.logviewer.portlets;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * StartupServlet
 * 
 * In testing, we are experiencing some cases where after the plugin security manager has been
 * enabled, the LogViewerListener would not fire (plausibly due to timing between when the deploy
 * event is set and when SecurePluginContextListener registers it). This happens on both CE and EE.
 * 
 * This startup servlet is a workaround for those cases that need it.
 * 
 * @author Chun Ho <chun.ho@permeance.com.au>
 */
public class StartupServlet extends HttpServlet {

    public static final Log _log = LogFactoryUtil.getLog(StartupServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        LogViewerListener.startApplication();
    }

    @Override
    public void destroy() {
        LogViewerListener.stopApplication();
        super.destroy();
    }
}
