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

package com.concursive.connect.web.modules.profile.dao;

import com.concursive.commons.db.DatabaseUtils;
import com.concursive.commons.web.mvc.beans.GenericBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Description of the Class
 *
 * @author matt rajkowski
 * @version $Id$
 * @created August 11, 2003
 */
public class PermissionCategoryLookup extends GenericBean {
  // Properties
  private int id = -1;
  private String description = null;
  private boolean defaultItem = false;
  private int level = -1;
  private boolean enabled = true;
  private int groupId = 1;
  // Contains
  private PermissionLookupList permissions = null;


  /**
   * Constructor for the PermissionCategoryLookup object
   */
  public PermissionCategoryLookup() {
  }


  /**
   * Constructor for the PermissionCategoryLookup object
   *
   * @param rs Description of the Parameter
   * @throws SQLException Description of the Exception
   */
  public PermissionCategoryLookup(ResultSet rs) throws SQLException {
    buildRecord(rs);
  }


  /**
   * Sets the id attribute of the PermissionCategoryLookup object
   *
   * @param tmp The new id value
   */
  public void setId(int tmp) {
    this.id = tmp;
  }


  /**
   * Sets the id attribute of the PermissionCategoryLookup object
   *
   * @param tmp The new id value
   */
  public void setId(String tmp) {
    this.id = Integer.parseInt(tmp);
  }


  /**
   * Sets the description attribute of the PermissionCategoryLookup object
   *
   * @param tmp The new description value
   */
  public void setDescription(String tmp) {
    this.description = tmp;
  }


  /**
   * Gets the id attribute of the PermissionCategoryLookup object
   *
   * @return The id value
   */
  public int getId() {
    return id;
  }


  /**
   * Gets the description attribute of the PermissionCategoryLookup object
   *
   * @return The description value
   */
  public String getDescription() {
    return description;
  }

  public boolean getDefaultItem() {
    return defaultItem;
  }

  public void setDefaultItem(boolean defaultItem) {
    this.defaultItem = defaultItem;
  }

  public void setDefaultItem(String tmp) {
    this.defaultItem = DatabaseUtils.parseBoolean(tmp);
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public void setLevel(String tmp) {
    this.level = DatabaseUtils.parseInt(tmp, -1);
  }

  public boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public void setEnabled(String tmp) {
    this.enabled = DatabaseUtils.parseBoolean(tmp);
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public void setGroupId(String tmp) {
    this.groupId = DatabaseUtils.parseInt(tmp, -1);
  }

  /**
   * Gets the permissions attribute of the PermissionCategoryLookup object
   *
   * @return The permissions value
   */
  public PermissionLookupList getPermissions() {
    return permissions;
  }


  /**
   * Description of the Method
   *
   * @param rs Description of the Parameter
   * @throws SQLException Description of the Exception
   */
  protected void buildRecord(ResultSet rs) throws SQLException {
    id = rs.getInt("code");
    description = rs.getString("description");
    defaultItem = rs.getBoolean("default_item");
    level = rs.getInt("level");
    enabled = rs.getBoolean("enabled");
    groupId = rs.getInt("group_id");
  }

  public void insert(Connection db) throws SQLException {
    int i = 0;
    PreparedStatement pst = db.prepareStatement(
        "INSERT INTO lookup_project_permission_category " +
            "(" +
            (id > -1 ? "code, " : "") +
            "description, default_item, level, enabled, group_id) VALUES " +
            "(" +
            (id > -1 ? "?, " : "") +
            "?, ?, ?, ?, ?)"
    );
    if (id > -1) {
      pst.setInt(++i, id);
    }
    pst.setString(++i, description);
    pst.setBoolean(++i, defaultItem);
    pst.setInt(++i, level);
    pst.setBoolean(++i, enabled);
    pst.setInt(++i, groupId);
    pst.execute();
    pst.close();
    id = DatabaseUtils.getCurrVal(db, "lookup_project_permission_category_code_seq", id);
  }


  public void update(Connection db) throws SQLException {
    if (id == -1) {
      throw new SQLException("ID is -1");
    }
    int i = 0;
    PreparedStatement pst = db.prepareStatement(
        "UPDATE lookup_project_permission_category " +
            "SET description = ?, default_item = ?, level = ?, enabled = ?, group_id = ? " +
            "WHERE code = ? "
    );
    pst.setString(++i, description);
    pst.setBoolean(++i, defaultItem);
    pst.setInt(++i, level);
    pst.setBoolean(++i, enabled);
    pst.setInt(++i, groupId);
    pst.setInt(++i, id);
    pst.execute();
    pst.close();
  }


  /**
   * Description of the Method
   *
   * @param db             Description of the Parameter
   * @param includeEnabled Description of the Parameter
   * @throws SQLException Description of the Exception
   */
  public void buildResources(Connection db, int includeEnabled) throws SQLException {
    permissions = new PermissionLookupList(db, id, includeEnabled);
  }
}

