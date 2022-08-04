<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head> 
<meta charset="utf-8">
<title>클래식기타 커뮤니티</title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/common.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/header.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/footer.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/board_left.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/board_list_main.css">
</head>
<body>
<div id="wrap">
<header>
  <a href="index"><img id="logo" src="${pageContext.request.contextPath }/resources/img/logo.png"></a>
<nav id="top_menu">
  HOME | 
  <%
  	String sessionId = (String)session.getAttribute("sessionId");    
	if(sessionId == null) { 
  
  %>
  LOGIN | 
 <%
	} else {
 %>
  <a href="logout">LOGOUT | </a>
  <%
	}
  %>
  
  <a href="member_join">JOIN</a> | 
  NOTICE
</nav>
<nav id="main_menu">
  <ul>
    <li><a href="board_list">자유 게시판</a></li>
    <li><a href="#">기타 연주</a></li>
    <li><a href="#">공동 구매</a></li>
    <li><a href="#">연주회 안내</a></li>
    <li><a href="#">회원 게시판</a></li>
  </ul>
</nav>
</header> <!-- header -->
<aside>
  <%
		if(sessionId == null) {
	
	%>
  <article id="login_box">
    <img id="login_title" src="${pageContext.request.contextPath }/resources/img/ttl_login.png">    
    <div id="input_button">
    <form action="memberLoginOk" method="post">
    <ul id="login_input">
      <li><input type="text" name="mid"></li>
      <li><input type="password" name="mpw"></li>
    </ul>
    <!-- 
    <img id="login_btn" src="${pageContext.request.contextPath }/resources/img/btn_login.gif">
     -->
    <input type="image" src="${pageContext.request.contextPath }/resources/img/btn_login.gif">
    </form>
    </div> 
    <div class="clear"></div>
    <div id="join_search">
      <img src="${pageContext.request.contextPath }/resources/img/btn_join.gif" href="">
      <img src="${pageContext.request.contextPath }/resources/img/btn_search.gif">    
    </div>
  </article>
  <%
		} else {
  %>
  	<article id="login_box">
    <img id="login_title" src="${pageContext.request.contextPath }/resources/img/ttl_login.png">    
    <div id="input_button">
    	<%=sessionId %>님 로그인 중입니다<br>
    	<a href="logout">로그아웃</a>
    </div>
  </article>
  <%
		}
  %>
  <nav id="sub_menu">
    <ul>
      <li><a href="board_list">+ 자유 게시판</a></li>
      <li><a href="#">+ 방명록</a></li>
      <li><a href="#">+ 공지사항</a></li>
      <li><a href="#">+ 등업 요청</a></li>
      <li><a href="#">+ 포토갤러리</a></li>
    </ul>
  </nav>
  <article id="sub_banner">
    <ul>
      <li><img src="${pageContext.request.contextPath }/resources/img/banner1.png"></li>
      <li><img src="${pageContext.request.contextPath }/resources/img/banner2.png"></li>		
      <li><img src="${pageContext.request.contextPath }/resources/img/banner3.png"></li>
    </ul>	
  </article>
</aside>
  <section id="main">
    <img src="${pageContext.request.contextPath }/resources/img/comm.gif">
    <h2 id="board_title">자유 게시판 </h2>
    <div id="total_search">
      <div id="total">▷ 총 ${listCount }개의 게시물이 있습니다.</div>
      <form action="board_list">
      <div id="search">
        <div id="select_img"><img src="${pageContext.request.contextPath }/resources/img/select_search.gif"></div>
        <div id="search_select">
          <select name="searchOption">
            <option value="title">제목</option>
            <option value="content">내용</option>
            <option value="writer">글쓴이</option>
          </select> 
        </div>
        <div id="search_input"><input type="text" name="searchKeyword"></div>
        <div id="search_btn"><input type="image" src="${pageContext.request.contextPath }/resources/img/search_button.gif"></div>      
      </div>
      </form>
    </div>
    <table>
      <tr>
        <th>번호</th>
        <th>제목</th>
        <th>글쓴이</th>
        <th>일시</th>
        <th>조회수</th>
      </tr>
      
      <c:forEach items="${fblist}" var="fbdto">
      <tr>
      
        <td class="col1">${fbdto.fbnum }</td>
        <td class="col2">
          <a href="board_view?fbnum=${fbdto.fbnum }">${fbdto.fbtitle }</a>
        </td>
        <td class="col3">${fbdto.mname }</td>
        <td class="col4">
        	<c:out value="${fn:substring(fbdto.fbdate,0,10) }"></c:out> 
        </td>
        <td class="col5">${fbdto.fbhit }</td>
      </tr>
      </c:forEach>
      
    </table>
    <div id="buttons">
    <!-- 
      <div class="col1">◀ 이전 1 다음 ▶</div>
       -->
      <div class="col2">
        <a href="board_list"><img src="${pageContext.request.contextPath }/resources/img/list.png"></a> 
        <a href="board_write"><img src="${pageContext.request.contextPath }/resources/img/write.png"></a>
      </div>
    </div>
  </section> <!-- section main -->
  <div class="clear"></div>

  <footer>
    <img id="footer_logo" src="${pageContext.request.contextPath }/resources/img/footer_logo.gif">
    <ul id="address">
      <li>서울시 강남구 삼성동 1234 우 : 123-1234</li>  
      <li>TEL : 031-123-1234  Email : email@domain.com</li>
      <li>COPYRIGHT (C) 루바토 ALL RIGHTS RESERVED</li>
    </ul>
    <ul id="footer_sns">
      <li><img src="${pageContext.request.contextPath }/resources/img/facebook.gif"></li>  
      <li><img src="${pageContext.request.contextPath }/resources/img/blog.gif"></li>
      <li><img src="${pageContext.request.contextPath }/resources/img/twitter.gif"></li>
    </ul>
  </footer> <!-- footer -->
</div> <!-- wrap -->
</body>
</html>