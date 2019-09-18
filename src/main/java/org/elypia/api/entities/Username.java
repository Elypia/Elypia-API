/*
 * Copyright (C) 2019  Elypia CIC
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.elypia.api.entities;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "usernames")
@Table
public class Username {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "username_id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "username")
    private String username;

    @Column(name = "set_date")
    private Date setDate;

    public Username() {
        // Do nothing
    }

    public Username(int userId, String username) {
        this(userId, username, new Date());
    }

    public Username(int userId, String username, Date setDate) {
        this.userId = userId;
        this.username = username;
        this.setDate = setDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getSetDate() {
        return setDate;
    }

    public void setSetDate(Date setDate) {
        this.setDate = setDate;
    }
}
