<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Package Info</title>
</head>
<body>
<h2>Package</h2>
<table>
<tr>
  <th>ID</th>
  <th>Warehouse Address</th>
  <th>Delivery Address</th>
  <th>Items</th>
  <th>Status</th>
  <th>Tracking Number</th>
</tr>
<tr>
  <td>${pac.package_id}</td>
  <td>${pac.warehouse_add}</td>
  <td>${pac.delivery_add}</td>
  <td>${pac.items}</td>
  <td>${pac.status}</td>
  <td>${pac.tracking_num}</td>
</tr>
</table>
<p class="error">${error}</p>
</body>
</html>