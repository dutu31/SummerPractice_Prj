<%--
  Created by IntelliJ IDEA.
  User: dutu
  Date: 8/1/25
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<div id="admin-page">

    <h2 class="admin-title">User Administration</h2>

    <section class="admin-section admin-add-user-section">
        <h3 class="admin-subtitle">Add new user</h3>
        <div class="inputs-container">
            <label class="admin-label">Username:
                <input type="text" id="input-username" class="admin-input" required autocomplete="off">
            </label>
            <label class="admin-label">Email:
                <input type="email" id="input-mail" class="admin-input" required autocomplete="off">
            </label>
            <label class="admin-label">Password:
                <input type="password" id="input-password" class="admin-input" required autocomplete="off">
            </label>
            <label class="admin-label">Role:
                <select id="select-role" class="admin-select">
                    <option value="user">USER</option>
                    <option value="admin">ADMIN</option>
                </select>
            </label>
            <button id="btn-add-user" class="admin-button admin-button-submit">Add User</button>
        </div>
    </section>

    <section class="admin-section admin-users-list-section">
        <h3 class="admin-subtitle">Users List</h3>
        <table id="admin-user-table" class="admin-table" border="1">
            <thead class="admin-table-head">
            <tr>
                <th class="admin-table-header">ID</th>
                <th class="admin-table-header">Username</th>
                <th class="admin-table-header">Email</th>
                <th class="admin-table-header">Role</th>
                <th class="admin-table-header">Actions</th>
            </tr>
            </thead>
            <tbody class="admin-table-body">
            <c:forEach var="user" items="${users}">
                <tr data-user-id="${user.id}" class="admin-table-row">
                    <td class="admin-table-cell">${user.id}</td>
                    <td class="admin-table-cell">${user.username}</td>
                    <td class="admin-table-cell">${user.mail}</td>
                    <td class="admin-table-cell">${user.role}</td>
                    <td class="admin-table-cell admin-actions-cell">
                        <button class="admin-button delete-user">Delete</button>
                        <button class="admin-button promote-user">Promote</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </section>

    <a href="${pageContext.request.contextPath}/" class="admin-button btn-back-home-admin">Back to homepage</a>

</div>
