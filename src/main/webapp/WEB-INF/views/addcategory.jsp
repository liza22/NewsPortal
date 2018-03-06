<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<div class="w3-display-container">
    <div class="w3-card w3-panel w3-center w3-padding w3-pale-yellow w3-display-topmiddle w3-third">

        <form:form method="post" action="category" modelAttribute="category"
                   class="w3-selection">
            <h3><spring:message code="label.addcategory"/></h3>
            <form:label path="name">
                <spring:message code="label.name"/>
            </form:label>
            <form:input path="name" maxlength="40" class="w3-input w3-border w3-round-large"/>

            <p>${exception}</p>

            <input type="submit" value="<spring:message code="label.addcategory" />"
                   class="w3-btn w3-yellow w3-round-large w3-margin"/>
            <input type="button" onclick="location.href='/'" value="<spring:message code="label.cancel"
                   class="w3-btn w3-yellow w3-round-large w3-margin"/>
        </form:form>

    </div>
</div>
</body>
</html>
