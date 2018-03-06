<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <title><spring:message code="label.header"/></title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body class="w3-white">
<div class="w3-container w3-lime w3-center">
    <h2><spring:message code="label.header"/></h2>
</div>

<div class="w3-container w3-center">
    <button onclick="location.href='/add'" class="w3-btn w3-yellow w3-round-large w3-margin">
        <spring:message code="label.addnews"/>
    </button>

    <button onclick="location.href='/category'" class="w3-btn w3-yellow w3-round-large w3-margin">
        <spring:message code="label.addcategory"/>
    </button>
</div>

<div class="w3-container w3-center">
    <form:form method="post" commandName="query"
               class="w3-selection">
        <form:label path="title">
            <spring:message code="label.title"/>
        </form:label>
        <form:input path="title" maxlength="40" class="w3-input w3-border w3-round-large"  style="display:inline-block;width:15%"/>
        <form:label path="body">
            <spring:message code="label.body"/>
        </form:label>
        <form:input path="body" maxlength="800" class="w3-input w3-border w3-round-large"  style="display:inline-block;width:15%"/>
        <form:label path="category">
            <spring:message code="label.category"/>
        </form:label>
        <form:select path="category" class="w3-input w3-border w3-round-large"   style="display:inline-block;width:15%">
            <form:option value="0" label=""/>
            <form:options items="${categoriesList}" itemValue="id" itemLabel="name"/>
        </form:select>
        <input name="type" type="submit" value="<spring:message code='label.searchnews' />"
               class="w3-btn w3-yellow w3-round-large w3-margin"  style="display:inline-block"/>
        <input name="type" type="submit" value="<spring:message code="label.reset" />"
               class="w3-btn w3-yellow w3-round-large w3-margin"  style="display:inline-block"/>
    </form:form>
</div>

<div class="w3-content w3-container">
    <div class="w3-panel">
        <c:if test="${!empty newsList}">

            <c:forEach items="${newsList}" var="news">
                <div class="w3-card w3-panel w3-padding w3-pale-yellow w3-display-container">
                    <p style="font-weight: bold">${news.title}</p>
                    <div class="w3-display-topright w3-padding">
                        <a href="edit/${news.id}"
                           class="w3-hover-yellow w3-round w3-padding w3-margin"
                           style="text-decoration:none">
                            <spring:message code="label.edit"/>
                        </a>
                        <a href="delete/${news.id}"
                           class="w3-hover-yellow w3-round w3-padding"
                           style="text-decoration:none">
                            <spring:message code="label.delete"/>
                        </a>
                    </div>
                    <p>${news.body}</p>
                    <p><span style="font-weight: bold"><spring:message code="label.category"/>: </span>
                            ${news.category.name}
                    </p>
                    <p class="w3-display-bottomright w3-padding">${news.created}</p>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>

</body>
</html>