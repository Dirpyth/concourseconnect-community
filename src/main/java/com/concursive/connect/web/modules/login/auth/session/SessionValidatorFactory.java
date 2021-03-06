/*
 * ConcourseConnect
 * Copyright 2009 Concursive Corporation
 * http://www.concursive.com
 *
 * This file is part of ConcourseConnect, an open source social business
 * software and community platform.
 *
 * Concursive ConcourseConnect is free software: you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, version 3 of the License.
 *
 * Under the terms of the GNU Affero General Public License you must release the
 * complete source code for any application that uses any part of ConcourseConnect
 * (system header files and libraries used by the operating system are excluded).
 * These terms must be included in any work that has ConcourseConnect components.
 * If you are developing and distributing open source applications under the
 * GNU Affero General Public License, then you are free to use ConcourseConnect
 * under the GNU Affero General Public License.
 *
 * If you are deploying a web site in which users interact with any portion of
 * ConcourseConnect over a network, the complete source code changes must be made
 * available.  For example, include a link to the source archive directly from
 * your web site.
 *
 * For OEMs, ISVs, SIs and VARs who distribute ConcourseConnect with their
 * products, and do not license and distribute their source code under the GNU
 * Affero General Public License, Concursive provides a flexible commercial
 * license.
 *
 * To anyone in doubt, we recommend the commercial license. Our commercial license
 * is competitively priced and will eliminate any confusion about how
 * ConcourseConnect can be used and distributed.
 *
 * ConcourseConnect is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with ConcourseConnect.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Attribution Notice: ConcourseConnect is an Original Work of software created
 * by Concursive Corporation
 */
package com.concursive.connect.web.modules.login.auth.session;

import com.concursive.commons.db.ConnectionElement;
import com.concursive.commons.db.ConnectionPool;
import com.concursive.connect.config.ApplicationPrefs;
import com.concursive.connect.web.modules.login.dao.AuthenticationClassesLookup;
import com.concursive.connect.web.modules.login.dao.AuthenticationClassesLookupList;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

/**
 * Serves as a tool for the creation of the appropriate
 * <code>SessionValidator</code> for the given login method as set in the
 * <i>master.properties</i> file.
 *
 * @author Artem.Zakolodkin
 * @created Jul 19, 2007
 */
public class SessionValidatorFactory {

  private static Log LOG = LogFactory.getLog(SessionValidatorFactory.class);

  public static final String DEFAULT_SESSION_VALIDATOR = "com.concursive.connect.web.modules.login.auth.session.SessionValidator";
  private static SessionValidatorFactory instance = null;

  public static SessionValidatorFactory getInstance() {
    if (instance == null) {
      instance = new SessionValidatorFactory();
    }
    return instance;
  }

  private SessionValidatorFactory() {
  }

  public ISessionValidator getSessionValidator(ServletContext context, HttpServletRequest request) {
    // Retrieve prefs to use global connection info
    ApplicationPrefs prefs = (ApplicationPrefs) context.getAttribute("applicationPrefs");
    // Connection info
    ConnectionElement ce = new ConnectionElement();
    ce.setDriver(prefs.get("SITE.DRIVER"));
    ce.setUrl(prefs.get("SITE.URL"));
    ce.setUsername(prefs.get("SITE.USER"));
    ce.setPassword(prefs.get("SITE.PASSWORD"));
    ConnectionPool sqlDriver = (ConnectionPool) context.getAttribute("ConnectionPool");
    // Determine session validator class to use, or default
    Connection db = null;
    String className = null;
    try {
      String loginMode = ((ApplicationPrefs) context.getAttribute("applicationPrefs")).get(ApplicationPrefs.LOGIN_MODE);
      LOG.debug("Seeking Session Authenticator for Mode: " + loginMode);
      // Get connection
      db = sqlDriver.getConnection(ce);
      // Get specified authentication class
      AuthenticationClassesLookupList aclList = new AuthenticationClassesLookupList();
      aclList.setLoginMode(loginMode);
      aclList.buildList(db);
      if (aclList.size() == 0) {
        className = DEFAULT_SESSION_VALIDATOR;
      } else {
        AuthenticationClassesLookup acl = aclList.get(0);
        className = acl.getLoginAuthenticator();
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      if (db != null) {
        sqlDriver.free(db);
      }
    }
    // Instantiate the authenticator class
    try {
      if (className == null) {
        className = DEFAULT_SESSION_VALIDATOR;
      }
      Class clazz = Class.forName(className);
      return (ISessionValidator) clazz.newInstance();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
