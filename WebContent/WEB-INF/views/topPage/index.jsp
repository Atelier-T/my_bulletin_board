<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <c:choose>
            <c:when test="${login_user!=null}">
                <h2>Atelier-T専用掲示板へようこそ、${login_user.u_name}さん</h2>
            </c:when>

            <c:otherwise>
                <h2>Atelier-T専用掲示板へようこそ</h2>
            </c:otherwise>
        </c:choose>

        <c:if test="${login_user.user_flag==2}">
            <h3>※申し訳ございません。${login_user.u_name}さんのユーザ権限は只今凍結中です。</h3>
        </c:if>
    </c:param>
</c:import>