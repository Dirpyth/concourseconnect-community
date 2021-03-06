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
<%@ taglib uri="/WEB-INF/concourseconnect-taglib.tld" prefix="ccp" %>
<jsp:useBean id="project" class="com.concursive.connect.web.modules.profile.dao.Project" scope="request"/>
<jsp:useBean id="Requirement" class="com.concursive.connect.web.modules.plans.dao.Requirement" scope="request"/>
<%@ include file="initPage.jsp" %>
<table class="pagedList">
  <thead>
    <tr>
      <th colspan="2">
        <ccp:label name="projectsCenterRequirements.details.outline:">Outline:</ccp:label> <%= toHtml(Requirement.getShortDescription()) %>
      </th>
    </tr>
  </thead>
  <tbody>
    <tr class="containerBody">
      <td nowrap class="formLabel" valign="top"><ccp:label name="projectsCenterRequirements.details.details">Details</ccp:label></td>
      <td>
        <%= toHtml(Requirement.getDescription()) %>
      </td>
    </tr>
    <tr class="containerBody">
      <td nowrap class="formLabel"><ccp:label name="projectsCenterRequirements.details.requestedBy">Requested By</ccp:label></td>
      <td>
        <%= toHtml(Requirement.getSubmittedBy()) %>
      </td>
    </tr>
    <tr class="containerBody">
      <td nowrap class="formLabel" valign="top"><ccp:label name="projectsCenterRequirements.details.deptOrCompany">Department or<br>Company</ccp:label></td>
      <td valign="top">
        <%= toHtml(Requirement.getDepartmentBy()) %>
      </td>
    </tr>
    <tr class="containerBody">
      <td nowrap class="formLabel" valign="top"><ccp:label name="projectsCenterRequirements.details.expectedDates">Expected Dates</ccp:label></td>
      <td>
        <table border="0" cellspacing="0" cellpadding="0" class="empty">
          <tr>
            <td align="right">
              <ccp:label name="projectsCenterRequirements.details.start">Start:</ccp:label>
            </td>
            <td>
              &nbsp;<ccp:tz timestamp="<%= Requirement.getStartDate() %>" dateOnly="true" default="--"/>
            </td>
          </tr>
          <tr>
            <td align="right">
              <ccp:label name="projectsCenterRequirements.details.finish">Finish:</ccp:label>
            </td>
            <td>
              &nbsp;<ccp:tz timestamp="<%= Requirement.getDeadline() %>" dateOnly="true" default="--"/>
            </td>
          </tr>
        </table>
      </td>
    </tr>
    <tr class="containerBody">
      <td nowrap class="formLabel" valign="top"><ccp:label name="projectsCenterRequirements.details.levelOfEffort">Level of Effort</ccp:label></td>
      <td>
        <table border="0" cellspacing="0" cellpadding="0" class="empty">
          <tr>
            <td align="right">
              <ccp:label name="projectsCenterRequirements.details.estimated">Estimated:</ccp:label>
            </td>
            <td>
              &nbsp;<%= toHtml(Requirement.getEstimatedLoeString()) %>
            </td>
          </tr>
          <tr>
            <td align="right">
              <ccp:label name="projectsCenterRequirements.details.actual">Actual:</ccp:label>
            </td>
            <td>
              &nbsp;<%= toHtml(Requirement.getActualLoeString()) %>
            </td>
          </tr>
        </table>
      </td>
    </tr>
    <tr class="containerBody">
      <td nowrap class="formLabel" valign="top"><ccp:label name="projectsCenterRequirements.details.status">Status</ccp:label></td>
      <td>
        <ccp:label name="projectsCenterRequirements.details.outline">Outline</ccp:label>
        <ccp:evaluate if="<%= Requirement.getApproved() %>">
          <ccp:label name="projectsCenterRequirements.details.not">not</ccp:label>
        </ccp:evaluate>
        <ccp:label name="projectsCenterRequirements.details.approved">approved</ccp:label>
        <br>
        <ccp:label name="projectsCenterRequirements.details.outline">Outline</ccp:label>
        <ccp:evaluate if="<%= Requirement.getClosed() %>">
          <ccp:label name="projectsCenterRequirements.details.closed">Closed</ccp:label>
        </ccp:evaluate>
        <ccp:evaluate if="<%= !Requirement.getClosed() %>">
          <ccp:label name="projectsCenterRequirements.details.open">Open</ccp:label>
        </ccp:evaluate>
      </td>
    </tr>
  </tbody>
</table>
<input type="button" value="<ccp:label name="button.close">Close</ccp:label>" onclick="window.close()"/>
