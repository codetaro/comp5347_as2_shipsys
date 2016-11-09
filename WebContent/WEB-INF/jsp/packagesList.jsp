<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/style.css" media="screen" />
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
</head>
<body>
<h2>Package List</h2>
<table>
  <tr>
    <td>ID</td>
    <td>Warehouse Address</td>
    <td>Delivery Address</td>
    <td>Items</td>
    <td>Status</td>
    <td>Tracking Number</td>
  </tr>
<c:forEach var="pac" items="${pacs}">
  <tr>
    <td>${pac.package_id}</td>
    <td>${pac.warehouse_add}</td>
    <td>${pac.delivery_add}</td>
    <td>${pac.items}</td>
    <td>
      ${pac.status}
      <c:if test="${pac.status == 'in-transit'}">
        <a href="${pageContext.request.contextPath}/packages/update/${pac.package_id}" id="update">update</a>
      </c:if>
    </td>
    <td>${pac.tracking_num}</td>
  </tr>
</c:forEach>
</table>
<a href="${pageContext.request.contextPath}">Back to home</a>
</body>
</html>