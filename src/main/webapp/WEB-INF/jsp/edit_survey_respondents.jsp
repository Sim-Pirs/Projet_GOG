<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/WEB-INF/jsp/utils/include.jsp"%>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>Index</title>
    <link rel="stylesheet" type="text/css" href="/style.css">
    <%@ include file="/WEB-INF/jsp/utils/head-bootstrap.jsp"%>
</head>
<body>
<%@ include file="/WEB-INF/jsp/utils/menu.jsp"%>
<div class="container">
    <h1 class="title topShift" align="center">Modification de la liste des personnes sondées</h1>
    <form:form method="POST" modelAttribute="respondents" action="${saveSurveyRespondents}" cssClass="needs-validation">
        <input name="id_survey" type="hidden" value="${id_survey}"/>
        <div class="input-group topShift">
            <div class="input-group-prepend">
                <span class="input-group-text">Sondés</span>
            </div>
            <textarea name="respondents_string" class="form-control" maxlength="10000" rows="8"><c:if test="${respondents != null}">${respondents}</c:if></textarea>
            <!--<errors cssClass="alert alert-warning" element="div"></errors>-->
        </div>

        <div class="form-group topShift" align="center">
            <button type="submit" class="btn btn-outline-success my-2 my-sm-0">Sauvegarder</button>
        </div>
    </form:form>

    <a href="#" class="btn btn-outline-warning" role="button" aria-pressed="true">Notifier tout le monde</a>
</div>
</body>
</html>
