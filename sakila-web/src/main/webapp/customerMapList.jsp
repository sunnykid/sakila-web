<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="vo.*" %>
<%@ page import="dao.*" %>
<%@ page import="java.util.*" %>
<%
// 페이징
int currentPage = 1;
if(request.getParameter("currentPage") != null) {
	currentPage = Integer.parseInt(request.getParameter("currentPage"));
}
int rowPerPage = 10;
int beginRow = (currentPage-1)*rowPerPage;

// 모델
CustomerDao customerDao = new CustomerDao();
int lastPage = customerDao.countCustomer() / rowPerPage;
ArrayList<HashMap<String, Object>> list = customerDao.selectCustomerMapList(beginRow, rowPerPage);
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr class=header>
			<th class="col">FirstName</th>
			<th>Address</th>
			<th>City</th>
			<th>Country</th>			
		</tr>
			<%
				for(HashMap<String, Object> cacc : list) {
			%>
					<tr>
						<td><%=cacc.get("firstName")%></td>
						<td><%=cacc.get("address")%></td>
						<td><%=cacc.get("city")%></td>
						<td><%=cacc.get("country")%></td>					
					</tr>
			<%
					}								
			%>
	</table>
	<!-- paging -->
	<span>
		<%
			if(currentPage > 1) {
		%>
				<a href="<%=request.getContextPath()%>/customerJoinList.jsp?currentPage=1">처음</a>
				<a href="<%=request.getContextPath()%>/customerJoinList.jsp?currentPage=<%=currentPage-1%>">이전</a>
		<%
			}
		%>
	</span>
	<span><%=currentPage%></span>
	<span>
		<%
			if(currentPage < lastPage) { 
		%>
				<a href="<%=request.getContextPath()%>/customerJoinList.jsp?currentPage=<%=currentPage+1%>">다음</a>
		<%
			}
		%>		
	</span>
	<span>
		<%
			if(currentPage < lastPage) {
		%>
			<a href="<%=request.getContextPath()%>/customerJoinList.jsp?currentPage=<%=lastPage%>">마지막</a>
		<%
			}
		%>
	</span>
</body>
</html>