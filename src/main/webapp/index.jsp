<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="page" tagdir="/WEB-INF/tags" %>

<page:template>

    <jsp:attribute name="title">My CRUD</jsp:attribute>

    <jsp:body>
<body>
        <div class="generic-container">
            <h1>My CRUD</h1>
            <ul>
                <li><h3><a href="<c:url value="/users"/>">CRUD на SPringMVC</a></h3></li>
                <li><h3><a href="<c:url value="/usersajax"/>">CRUD на AngularJS</a></h3></li>
                <li><h3><a href="<c:url value="/generate"/>">Generated User Data</a></h3></li>
            </ul>
        </div>
    </jsp:body>

</page:template>