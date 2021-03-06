<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!-- フラッシュメッセージ -->
        <c:if test="${errors != null}">
            <div id="flush_error">
                入力内容にエラーがあります。<br />
                <c:forEach var="error" items="${errors}">
                    ・<c:out value="${error}" /><br />
                </c:forEach>
            </div>
        </c:if>

        <!-- フォーム -->
        <label for="u_name">ユーザ名</label><br />
        <input type="text" name="u_name" value="${user.u_name}" />
        <br /><br />

        <label for="password">パスワード</label><br />
        <input type="password" name="password" />
        <br /><br />

        <label for="user_flag">ステータス</label><br />
        <select name="user_flag">
            <option value="0"<c:if test="${user.user_flag == 0}"> selected</c:if>>管理者</option>
            <option value="1"<c:if test="${user.user_flag == 1}"> selected</c:if>>ユーザ</option>
            <option value="2"<c:if test="${user.user_flag == 2}"> selected</c:if>>凍結中</option>
        </select>
        <br /><br />

        <label for="u_info">詳細情報</label><br />
        <textarea name="u_info" rows="16" cols="48">${user.u_info}</textarea>
        <br /><br />

        <input type="hidden" name="_token" value="${_token}" />
        <button type="submit">投稿</button>