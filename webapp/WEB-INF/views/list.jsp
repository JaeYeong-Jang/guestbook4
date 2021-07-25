<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/guestbook4/insert" method="get">
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value=""></td>
				<td>비밀번호</td>
				<td><input type="text" name="pw" value=""></td>
			</tr>

			<tr>
				<td colspan="4"><textarea name="content"></textarea></td>
			</tr>

			<tr>
				<td colspan="4"><button type="submit">확인</button></td>
			</tr>
		</table>

	</form>
	<br>

	<c:forEach items="${gList}" var="pList">
		<table border="1">
			<tr>
				<td>${pList.no }</td>
				<td>${pList.name }</td>
				<td>${pList.date }</td>
				<td><a
					href="/guestbook4/deleteForm?no=${pList.no}&pw=${pList.pw}">[삭제]</a></td>
			</tr>

			<tr>
				<td colspan="4">${pList.content }</td>
			</tr>
		</table>
	</c:forEach>

</body>
</html>