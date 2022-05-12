<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>으악</title>
    <style>
        .title {
            text-align: left;
            font-size: 20pt;
            height: 60px;
            margin-bottom: 10px;
        }
        table tr {
            text-align: center;
        }
        .big {
            align: center;
            margin-top: 20px;
            border-radius: 15px;
        }
        .seongbukNews {
            width: 100%;
            margin: auto;
        }
        .seongbukNews tr {
            height: 50px;
        }
        div {
            text-align: center;
        }
        .seongbukNews tr, .seongbukNews td {
            text-align: center;
            border-collapse: separate;
            border-spacing: 1px;
            border: 3px solid #ccc;
        }
    </style>
</head>


<body>
<table class=big>
    <colgroup>
        <col width="50%">
        <col width="50%">
    </colgroup>
    <tr class="title">
        <th colspan="3">실시간 뉴스를 알려드립니다 ^^</th>
    </tr>
    <thead>
    <tbody>
    <tr>
        <td>
            <table class="seongbukNews">
                <c:forEach var="url" items="${urls}" varStatus="status" begin="0" end="19">
                    <c:if test="${titles[status.index]!=''}">
                        <tr>
                            <td>${status.index+1}</td>
                            <td><a href="${url}">${titles[status.index]}</a></td>
                            <td>${content[status.index]}</td>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        </td>
    </tr>
    </tbody>
</table>
</body>

</html>