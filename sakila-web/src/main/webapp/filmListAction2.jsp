<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
	String[] rating = request.getParameterValues("rating");
// 2) fromMinute & toMinute
int fromMinute = 0;
int toMinute = 0;

// 둘다 공백값이 아니면 -> 정상적인 숫자값이 넘겨져 왔다면
if(!request.getParameter("fromMinute").equals("")
      && !request.getParameter("toMinute").equals("")) {
   
   fromMinute = Integer.parseInt(request.getParameter("fromMinute")); //숫자값으로 대체
   toMinute = Integer.parseInt(request.getParameter("toMinute"));      
}
// 디버깅
System.out.println(fromMinute + ", " + toMinute + " <--fromMinute, toMinute");

	
	System.out.println(rating);
	if(rating != null){
		System.out.println(rating + " size :" + rating.length);
	}
	
	FilmDao filmDao = new FilmDao();
	ArrayList<Film> list = filmDao.selectFilmList2(rating,fromMinute,toMinute);
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