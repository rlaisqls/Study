<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <title>실시간 뉴스</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/gh/moonspam/NanumSquare@1.0/nanumsquare.css">

    <style>
        body{
            font-family: nanumsquare, serif;
            top: 50px;
            text-align: left;
            margin-left: 15%;
            margin-right: 15%;
        }
        a { color: #000000; text-decoration:none !important } a:hover { text-decoration:none !important }
        .News {
            background-color: #FFFFFF;
            padding: 3%;
            width: 100%;
            display: block;
        }
        header {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            height: 30px;
            padding: 10px 5% 10px 5%;
            background-color: #000000;
            display: flex;
            justify-content: space-between;
            align-items: center;
            z-index: 2;color: white;
        }
        header a{
            font-weight: 300;
            font-size: 16.5px;
        }
        .Part {color: #FFFFFF;}
        ul {
            list-style: none;
        }
         li a {
            padding: 0 10px;
            color: #FFFFFF;
            margin: 0;
        }
        #nav li:hover ul{
            opacity: 1;
        }
        #nav li {
            float: left;
            position: relative;
        }
        #nav ul {
            opacity: 0;
            margin: 0;
            padding: 0;
            position: absolute;
            left: 0;
            top: 33px;
            width: 120px;
            background: #333333;
            text-align: center;
        }
        #nav ul li {
            float: none;
            margin: 0;
            padding: 0;
            font-size: 10px;
            line-height: 30px;
        }
        .pagenum{
            display: block;
            text-align: center;
            margin: 0 auto;
        }
    </style>
    <script>
        document.addEventListener("DOMContentLoaded", function() {
            var lazyloadImages = document.querySelectorAll("img._LAZY_LOADING");
            var lazyloadThrottleTimeout;
            function lazyload () {
                if(lazyloadThrottleTimeout) {clearTimeout(lazyloadThrottleTimeout);}
                lazyloadThrottleTimeout = setTimeout(function() {
                    lazyloadImages.forEach(function(img) {
                        img.src = img.dataset.src;
                        img.classList.remove('_LAZY_LOADING');
                    });
                    if(lazyloadImages.length === 0) {
                        document.removeEventListener("scroll", lazyload);
                        window.removeEventListener("resize", lazyload);
                        window.removeEventListener("orientationChange", lazyload);
                    }
                }, 0);
            }
            document.addEventListener("scroll", lazyload);
            window.addEventListener("resize", lazyload);
            window.addEventListener("orientationChange", lazyload);
        });

        function movePage(page){
            const URLSearch = new URLSearchParams(location.search);
            URLSearch.set('page', String(page));
            const newParam = URLSearch.toString();
            window.open(location.pathname + '?' + newParam, '_self');
        }
        function change(favoritename,url){
            const btn = document.getElementById(favoritename);
            if(btn.value === '☆ 즐겨찾기' ) btn.value = '★ 즐겨찾기';
            else btn.value = '☆ 즐겨찾기';
        }
    </script>
</head>

<header id="topbar">
    <a href="/?sid1=001&sid2="><h1 style="font-size: 30px; color: #FFFFFF"> 실시간 뉴스</h1></a>
    <ul id="nav">
        <li><a class="Part" href="/?sid1=001&sid2=">전체</a></li>
        <li>
            <a class="Part" href="/?sid1=100&sid2=">정치</a>
            <ul>
                <li><a href="/?sid1=100&sid2=">전체</a></li>
                <li><a href="/?sid1=100&sid2=264">청와대</a></li>
                <li><a href="/?sid1=100&sid2=265">국회/정당</a></li>
                <li><a href="/?sid1=100&sid2=266">행정</a></li>
                <li><a href="/?sid1=100&sid2=267">국방/외교</a></li>
                <li><a href="/?sid1=100&sid2=268">북한</a></li>
                <li><a href="/?sid1=100&sid2=269">정치일반</a></li>
            </ul>
        </li>
        <li>
            <a class="Part" href="/?sid1=101&sid2=">경제</a>
            <ul>
                <li><a href="/?sid1=101&sid2=">전체</a></li>
                <li><a href="/?sid1=101&sid2=260">부동산</a></li>
                <li><a href="/?sid1=101&sid2=259">금융</a></li>
                <li><a href="/?sid1=101&sid2=258">증권</a></li>
                <li><a href="/?sid1=101&sid2=261">산업/재계</a></li>
                <li><a href="/?sid1=101&sid2=262">글로벌경제</a></li>
                <li><a href="/?sid1=101&sid2=263">경제일반</a></li>
                <li><a href="/?sid1=101&sid2=771">중기/벤처</a></li>
            </ul>
        </li>
        <li>
            <a class="Part" href="/?sid1=102&sid2=">사회</a>
            <ul>
                <li><a href="/?sid1=102&sid2=">전체</a></li>
                <li><a href="/?sid1=102&sid2=249">사건사고</a></li>
                <li><a href="/?sid1=102&sid2=250">교육</a></li>
                <li><a href="/?sid1=102&sid2=251">노동</a></li>
                <li><a href="/?sid1=102&sid2=252">환경</a></li>
                <li><a href="/?sid1=102&sid2=254">언론</a></li>
                <li><a href="/?sid1=102&sid2=59b">인권/복지</a></li>
                <li><a href="/?sid1=102&sid2=256">지역</a></li>
                <li><a href="/?sid1=102&sid2=276">인물</a></li>
                <li><a href="/?sid1=102&sid2=257">사회일반</a></li>
            </ul>
        </li>
        <li>
            <a class="Part" href="/?sid1=103&sid2=">생활/문화</a>
            <ul>
                <li><a href="/?sid1=103&sid2=237">전체</a></li>
                <li><a href="/?sid1=103&sid2=237">여행/레저</a></li>
                <li><a href="/?sid1=103&sid2=239">자동차/시승기</a></li>
                <li><a href="/?sid1=103&sid2=240">도로/교통</a></li>
                <li><a href="/?sid1=103&sid2=241">건강정보</a></li>
                <li><a href="/?sid1=103&sid2=242">공연/전시</a></li>
                <li><a href="/?sid1=103&sid2=243">책</a></li>
                <li><a href="/?sid1=103&sid2=244">종교</a></li>
                <li><a href="/?sid1=103&sid2=245">생활/문화일반</a></li>
            </ul>
        </li>
        <li>
            <a class="Part" href="/?sid1=104&sid2=">세계</a>
            <ul>
                <li><a href="/?sid1=104&sid2=">전체</a></li>
                <li><a href="/?sid1=104&sid2=231">아시아/호주</a></li>
                <li><a href="/?sid1=104&sid2=232">미국/중남미</a></li>
                <li><a href="/?sid1=104&sid2=233">유럽</a></li>
                <li><a href="/?sid1=104&sid2=234">중동/아프리카</a></li>
                <li><a href="/?sid1=104&sid2=64f">영문</a></li>
            </ul>
        </li>
        <li>
            <a class="Part" href="/?sid1=105">IT/과학</a>
            <ul>
                <li><a href="/?sid1=105&sid2=">전체</a></li>
                <li><a href="/?sid1=105&sid2=226">인터넷/SNS</a></li>
                <li><a href="/?sid1=105&sid2=228">과학일반</a></li>
                <li><a href="/?sid1=105&sid2=229">게임/리뷰</a></li>
                <li><a href="/?sid1=105&sid2=283">컴퓨터</a></li>
                <li><a href="/?sid1=105&sid2=230">IT일반</a></li>
            </ul>
        </li>
        <li>
            <a class="Part" href="/login">로그인</a>
        </li>
    </ul>
</header>

<body>
<br><br><br>
<table class="News">
    <div>
        <c:forEach var="url" items="${urls}" varStatus="status">
            <c:if test="${titles[status.index]!=''}">
                <tr>
                    <h1 class="newstitle"><a href="${url}">${titles[status.index]}</a></h1>
                    ${time[status.index]} &nbsp; <input type='button' id="${id[status.index]}" value="☆ 즐겨찾기" onclick="change('${id[status.index]}','${url}')"/><br>
                    <img src="${media[status.index]}" height=50px>
                    ${content[status.index]}
                </tr>
            </c:if>
        </c:forEach>
    </div>
</table>
<div class="pagenum">
    현재 페이지: ${page}
    <br>
    <a onclick="movePage(1)">1</a>
    <a onclick="movePage(2)">2</a>
    <a onclick="movePage(3)">3</a>
    <a onclick="movePage(4)">4</a>
    <a onclick="movePage(5)">5</a>
    <a onclick="movePage(6)">6</a>
    <a onclick="movePage(7)">7</a>
    <a onclick="movePage(8)">8</a>
    <a onclick="movePage(9)">9</a>
    <a onclick="movePage(10)">10</a>
    <br>
    <br>
    <br>
</div>

</body>
</html>
