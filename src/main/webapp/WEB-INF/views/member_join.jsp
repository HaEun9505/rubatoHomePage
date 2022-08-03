<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head> 
<meta charset="utf-8">
<title>클래식기타 커뮤니티</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/common.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/header.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/footer.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/board_left.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/css/member_join.css">
</head>
<body>
<div id="wrap">
<header>
  <a href="index"><img id="logo" src="${pageContext.request.contextPath }/resources/img/logo.png"></a>
<nav id="top_menu">
  HOME | LOGIN | <a href="member_join">JOIN</a> | NOTICE
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
  <article id="login_box">
    <img id="login_title" src="${pageContext.request.contextPath }/resources/img/ttl_login.png">
    <div id="input_button">
    <ul id="login_input">
      <li><input type="text"></li>
      <li><input type="password"></li>
    </ul>
    <img id="login_btn" src="${pageContext.request.contextPath }/resources/img/btn_login.gif">
    </div> 
    <div class="clear"></div>
    <div id="join_search">
      <img src="${pageContext.request.contextPath }/resources/img/btn_join.gif">
      <img src="${pageContext.request.contextPath }/resources/img/btn_search.gif">
    </div>
  </article>
  <nav id="sub_menu">
    <ul>
      <li><a href="board_list">+ 자유 게시판</a></li>
      <li><a href="#">+ 방명록</a></li>
      <li><a href="#">+ 공지사항</a></li>
      <li><a href="#">+ 등업요청</a></li>
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
  <h2 id="board_title">RUBATO 회원 가입</h2>
  <div id="write_title"><h2>회원 정보 입력</h2></div>
  <form action="member_joinOk" method="post">
  <table>
    <tr id="memberid">
      <td class="col1">아이디</td>
      <td class="col2"><input type="text" name="mid"></td>
    </tr>
    <tr id="memberpw">
      <td class="col1">비밀번호</td>
      <td class="col2"><input type="password" name="mpw"></td>
    </tr>		
    <tr id="membername">
      <td class="col1">이름</td>
      <td class="col2"><input type="text" name="mname"></td>
    </tr>	
    <tr id="memberemail">
      <td class="col1">이메일</td>
      <td class="col2"><input type="text" name="memail"></td>
    </tr>	
  </table>
  <div id="buttons">
    <!-- <a href="#"><img src="${pageContext.request.contextPath }/resources/img/ok.png"></a>-->
    <input type="image" src="${pageContext.request.contextPath }/resources/img/ok.png">
    <a href="board_list"><img src="${pageContext.request.contextPath }/resources/img/list.png"></a>
  </div>
  </form>
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