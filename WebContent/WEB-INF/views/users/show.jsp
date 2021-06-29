<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:import url="../layout/app.jsp">
    <c:param name="content">

        <!-- フラッシュメッセージ -->
        <c:choose>
            <c:when test="${user != null}">
                <h2>id : ${user.u_id} の従業員情報　詳細ページ</h2>

                <table>
                    <tbody>
                        <tr>
                            <th>ユーザ名</th>
                            <td><c:out value="${user.u_name}" /></td>
                        </tr>
                        <tr>
                            <th>ステータス</th>
                            <td>
                                <c:choose>
                                    <c:when test="${user.user_flag == 0}">管理者</c:when>
                                    <c:when test="${user.user_flag == 1}">ユーザ</c:when>
                                    <c:otherwise>凍結中</c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                        <tr>
                            <th>詳細情報</th>
                            <td><c:out value="${user.u_info}" /></td>
                        </tr>
                        <tr>
                            <th>登録日時</th>
                            <td>
                                <fmt:formatDate value="${user.created_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                        <tr>
                            <th>更新日時</th>
                            <td>
                                <fmt:formatDate value="${user.updated_at}" pattern="yyyy-MM-dd HH:mm:ss" />
                            </td>
                        </tr>
                    </tbody>
                </table>

                <p><a href="<c:url value='/users/edit?id=${user.u_id}' />">ユーザ情報を編集する</a></p>
            </c:when>
            <c:otherwise>
                <h2>お探しのユーザ情報は見つかりませんでした。</h2>
            </c:otherwise>
        </c:choose>

        <p><a href="<c:url value='/users/new' />">新規ユーザの登録</a></p>

    </c:param>
</c:import>