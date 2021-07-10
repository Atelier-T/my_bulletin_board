<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!-- フォーム -->
        <c:choose>
            <c:when test="${login_user != null}">
                <label for="u_name">名前:${login_user.u_name}</label><br /><br />

                <label for="content">コメント</label><br />
                <textarea name="content" rows="4" cols="48"></textarea>
                <br /><br />

                <input type="hidden" name="_token" value="${_token}" />
                <button type="submit">投稿</button>
                <input type="reset" value="リセット">
            </c:when>
            <c:otherwise>
                <p><a href="<c:url value='/login' />">書き込む場合はログインして下さい</a></p>
            </c:otherwise>
        </c:choose>

