<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	String[] rating = request.getParameterValues("rating");
	System.out.println(rating);
	if(rating != null){
		System.out.println(rating + " size :" + rating.length);
	}
	
	FilmDao filmDao = new FilmDao();
	ArrayList<Film> list = filmDao.selectFilmList2(rating);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>검색결과</h1>
	<table border="1">
		<tr>
			<th>필름 제목</th>
			<th>필름 등급</th>
			<th>필름 상영시간</th>
		</tr>
      <%
         for(Film f : list) {
      %>
        <tr>
            <td><%=f.getTitle()%></td>
            <td><%=f.getRating()%></td>
            <td><%=f.getLength()%></td>
        </tr>
      <%      
         }
      %>

	</table>
</body>
</html>