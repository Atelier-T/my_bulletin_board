<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:import url="../layout/app.jsp">
    <c:param name="content">

        <!-- フラッシュメッセージ -->
        <c:if test="${flush != null}">
            <div id="flush_success">
                <c:out value="${flush}"></c:out>
            </div>
        </c:if>

        <h2>ユーザ情報</h2>

        <!-- ユーザリスト -->
        <table id=" user_list">
            <tbody>
                <tr>
                    <th>ユーザ名</th>
                    <th>ステータス</th>
                    <th>機能</th>
                </tr>

                <!-- サーブレットからのデータを順番に展開 -->
                <c:forEach var="user" items="${users}" varStatus="status">
                    <tr class="row${status.count % 2}">
                        <td><c:out value="${user.u_name}" /></td>
                        <td>
                            <c:choose>
                                <c:when test="${user.user_flag==0}">
                                    管理者
                                </c:when>
                                <c:when test="${user.user_flag==1}">
                                    ユーザ
                                </c:when>
                                <c:when test="${user.user_flag==2}">
                                    凍結中
                                </c:when>
                            </c:choose>
                        </td>
                        <td>
                            <a href="<c:url value='/users/show?id=${user.u_id}' />">表示</a>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>

        <!-- ユーザ情報をページ分割 -->
        <div id="pagination">
            （全 ${users_count} 件）<br />
            <c:forEach var="i" begin="1" end="${((users_count - 1) / 15) + 1}" step="1">
                <c:choose>
                    <c:when test="${i == page}">
                        <c:out value="${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/users/index?page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>

        <p><a href="<c:url value='/users/new' />">新規ユーザの登録</a></p>

    </c:param>
</c:import>