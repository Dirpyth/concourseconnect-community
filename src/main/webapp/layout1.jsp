<%--
  ~ ConcourseConnect
  ~ Copyright 2009 Concursive Corporation
  ~ http://www.concursive.com
  ~
  ~ This file is part of ConcourseConnect, an open source social business
  ~ software and community platform.
  ~
  ~ Concursive ConcourseConnect is free software: you can redistribute it and/or
  ~ modify it under the terms of the GNU Affero General Public License as published
  ~ by the Free Software Foundation, version 3 of the License.
  ~
  ~ Under the terms of the GNU Affero General Public License you must release the
  ~ complete source code for any application that uses any part of ConcourseConnect
  ~ (system header files and libraries used by the operating system are excluded).
  ~ These terms must be included in any work that has ConcourseConnect components.
  ~ If you are developing and distributing open source applications under the
  ~ GNU Affero General Public License, then you are free to use ConcourseConnect
  ~ under the GNU Affero General Public License.
  ~
  ~ If you are deploying a web site in which users interact with any portion of
  ~ ConcourseConnect over a network, the complete source code changes must be made
  ~ available.  For example, include a link to the source archive directly from
  ~ your web site.
  ~
  ~ For OEMs, ISVs, SIs and VARs who distribute ConcourseConnect with their
  ~ products, and do not license and distribute their source code under the GNU
  ~ Affero General Public License, Concursive provides a flexible commercial
  ~ license.
  ~
  ~ To anyone in doubt, we recommend the commercial license. Our commercial license
  ~ is competitively priced and will eliminate any confusion about how
  ~ ConcourseConnect can be used and distributed.
  ~
  ~ ConcourseConnect is distributed in the hope that it will be useful, but WITHOUT
  ~ ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
  ~ FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more
  ~ details.
  ~
  ~ You should have received a copy of the GNU Affero General Public License
  ~ along with ConcourseConnect.  If not, see <http://www.gnu.org/licenses/>.
  ~
  ~ Attribution Notice: ConcourseConnect is an Original Work of software created
  ~ by Concursive Corporation
  --%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.concursive.commons.http.RequestUtils" %>
<%@ taglib uri="/WEB-INF/concourseconnect-taglib.tld" prefix="ccp" %>
<%@ include file="initPage.jsp" %>
<%
  response.setHeader("Pragma", "no-cache"); // HTTP 1.0
  response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
  response.setDateHeader ("Expires", 0); //prevents caching at the proxy server
%>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=8" />
  <title>Window</title>
  <jsp:include page="css_include.jsp" flush="true"/>
</head>
<body style="background: #FFFFFF none;  margin: 0 !important; padding: 0 !important;">
  <% boolean isPrintButton = "true".equals(request.getParameter("printButton")); %>
  <ccp:evaluate if="<%= isPrintButton %>">
    <table border="0" cellpadding="4" cellspacing="0" id="noPrint" width="100%">
      <tr>
        <td align="right">
          <img src="<%= ctx %>/images/icons/stock_print-16.gif" align="absmiddle" alt="" border="0"/>
          <a href="javascript:window.print();">Print</a>
        </td>
      </tr>
    </table>
  </ccp:evaluate>
  <div class="yui-content">
    <div class="projectCenterPortalContainer">
      <% String body = (String) request.getAttribute("PageBody"); %>
      <jsp:include page="<%= body %>" flush="true"/>
    </div>
  </div>
  <div class="yui-skin-sam">
    <div id="popupCalendar"></div>
  </div>
  <div class="yui-skin-sam">
    <div id="popupLayer"></div>
  </div>
</body>
</html>
