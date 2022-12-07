<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>filmListForm2</title>
</head>
<body>

	<form action="<%=request.getContextPath() %>/filmListAction2.jsp">
		<table border="1">
			<tr>
				<td>상영시간</td>
				<td>
					<input type="number" name="fromMinute">
					~
					<input type="number" name="toMinute">
				</td>
			</tr>
			<tr>
				<td>등급</td>
				<td>
					<input type="checkbox" name="rating" value="G">G
					<input type="checkbox" name="rating" value="PG">PG
					<input type="checkbox" name="rating" value="PG-13">PG-13
					<input type="checkbox" name="rating" value="R">R
					<input type="checkbox" name="rating" value="NC-17">NC-17
				</td>
			</tr>
			<tr>
				<td>검색어(제목)</td>
				<td>
					<input type="text" name="searchTitle">
				</td>
			</tr>
		</table>
		<button type="submit">검색</button>
	</form>

</body>
</html>