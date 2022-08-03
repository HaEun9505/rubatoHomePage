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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/main.css">
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
    <form action="member_loginOk" method="post">
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
    	<%=sessionId %>님 로그인 중입니다<br><br>
    	<input type="button" value="로그아웃" onclick="location.href='logout'">
    </div>
  </article>
  <%
		}
  %>
  <article id="guestbook">
    <div id="guestbook_title">
      <img src="${pageContext.request.contextPath }/resources/img/ttl_memo.gif">
    </div>
    <ul>
      <li>안녕하세요!</li>
      <li>안녕하세요!</li>
      <li>안녕하세요!</li>
      <li>안녕하세요!</li>
    </ul>
  </article>
</aside>

<section id="main">
  <img src="${pageContext.request.contextPath }/resources/img/main_img.png">
  <section id="notice_free_youtube">
    <article id="notice"> 	<!-- 공지사항 -->
      <div class="latest_title">
        <img class="latest_img" src="${pageContext.request.contextPath }/resources/img/latest1.gif">
        <img class="more" src="${pageContext.request.contextPath }/resources/img/more.gif">
        <div class="clear"></div>					
      </div>
      <div class="latest_content">
        <img class="image" src="${pageContext.request.contextPath }/resources/img/book_pen.gif">
        <ul class="list">
          <li>
            <div class="subject">루바토 개편과 사이트 이용...</div>
            <div class="date">2017-09-20</div>
            <div class="clear"></div>	
          </li>								
          <li>
            <div class="subject">루바토 개편과 사이트 이용...</div>
            <div class="date">2017-09-20</div>
            <div class="clear"></div>	
          </li>		
          <li>
            <div class="subject">루바토 개편과 사이트 이용...</div>
            <div class="date">2017-09-20</div>
            <div class="clear"></div>	
          </li>	
          <li>
            <div class="subject">루바토 개편과 사이트 이용...</div>
            <div class="date">2017-09-20</div>
            <div class="clear"></div>	
          </li>				
        </ul>							
      </div>
    </article>
    <article id="free"> 	<!—자유 게시판 -->
      <div class="latest_title">
        <img class="latest_img" src="${pageContext.request.contextPath }/resources/img/latest2.gif">
        <img class="more" src="${pageContext.request.contextPath }/resources/img/more.gif">
        <div class="clear"></div>					
      </div>
      <div class="latest_content">
        <img class="image" src="${pageContext.request.contextPath }/resources/img/book_pen.gif">
        <ul class="list">
          <li>
            <div class="subject">까스통님의 선물인 보드카...</div>
            <div class="date">2017-09-20</div>
            <div class="clear"></div>		
          </li>
          <li>
            <div class="subject">까스통님의 선물인 보드카...</div>
            <div class="date">2017-09-20</div>
            <div class="clear"></div>	
          </li>		
          <li>
            <div class="subject">까스통님의 선물인 보드카...</div>
            <div class="date">2017-09-20</div>
            <div class="clear"></div>	
          </li>	
          <li>
            <div class="subject">까스통님의 선물인 보드카...</div>
            <div class="date">2017-09-20</div>
            <div class="clear"></div>	
          </li>								
        </ul>							
      </div>
    </article>			
    <article id="youtube">	    <!—YOUTUBE 동영상 -->		
      <div class="latest_title">
        <img class="latest_img" src="${pageContext.request.contextPath }/resources/img/latest3.gif">
        <img class="more" src="${pageContext.request.contextPath }/resources/img/more.gif">
        <div class="clear"></div>					
      </div>				
      <img id="youtube_img" src="${pageContext.request.contextPath }/resources/img/bach.jpg">
    </article>
  </section> <!-- notice_free_youtube -->
  <section id="gallery">
    <img src="${pageContext.request.contextPath }/resources/img/latest4.gif">
    <div id="gallery_box">
      <div id="gallery_list">
        <div class="items">
          <ul>
            <li><img src="${pageContext.request.contextPath }/resources/img/img1.jpg"></li>
            <li>기타 페스티벌 4중주</li>
          </ul>
        </div>
        <div class="items">
          <ul>
            <li><img src="${pageContext.request.contextPath }/resources/img/img1.jpg"></li>
            <li>기타 페스티벌 4중주</li>
          </ul>
        </div>
        <div class="items">
          <ul>
            <li><img src="${pageContext.request.contextPath }/resources/img/img1.jpg"></li>
            <li>기타 페스티벌 4중주</li>
          </ul>
        </div>
      </div> <!-- galley_list -->
    </div> <!-- gallery_box -->		
  </section> <!-- gallery -->
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