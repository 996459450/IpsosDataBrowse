<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!doctype html>
<html lang="en">
 <head>
  <meta charset="UTF-8">
  <meta name="Generator" content="">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>查询页面</title>
  <style>
	 * {
		margin: 0;
		padding: 0;
	}

	img {
		border: 0;
		margin: 0 1px 1px 0;
	}

	body {
	font-size: 14px;
	font-family: "微软雅黑";
	
}
	#showdata table{border-collapse:collapse;border-spacing:0;width:100%;table-layout:fixed;}
	#showdata table td{vertical-align:middle;border:none;}

	#sel{width:1500px;height:700px;border:1px #000 solid;margin:10px auto;}
	#dh{width:1500px;height:70px;margin:10px auto;}
	#xz{width:1500px;height:30px;margin:10px auto;}
	#showdata{width:1500px;height:480px;margin:10px auto;}
	#showdata table{table-layout:fixed;}
	#showdata tr{width:1500px;height:30px;border:1px #000 solid;}
	#showdata td{height:30px;border:1px #000 solid;text-align:center;width:100%;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;}
	.show{width:700px;height:350px;background:#fff;border:1px solid #fff;
	display:none;
	position:absolute;
	z-index:3;
	}
	#showdata a:hover{color:#000;}
	#showdata a:visited{color:#000;}
	#txt{background:#f5f6ec;}
	#xz  ul  li{list-style:none;float:left;line-height:32px;padding:0 20px 0 5px;font-size:14px;color:#BCCAEO;}

	.yy{width:100%;height:100%; background:#8a8c91; position:absolute;top:0;left:0;z-index:2;display:none;opacity:0.5;z-index:1;display:none;}
	#pagin{width:98%;height:30px;line-height:30px;border:1px red solid;margin-top:450px;text-align:right;}
	#btnSelect a {
	width: 50px;
	height: 24px;
	background: #167ED9;
	display: block;
	line-height: 24px;
	color: #ffffff;
	text-align: center;
	font-size: 12px;
}
	#home_box{width:90px;height:70px;float:left;}
	#home_img{width:90px;height:70px;}
	#home_txt{width:60px;height:30px;float:left;margin-top:40px;padding-left:10px;}
	#home_txt a{font-size:14px;line-height:30px;text-align:center;}
	#tit{width:1200px;height:70px;float:left;}
	#download{width:40px;height:40px;float:left;margin-top:30px;margin-left:10px;}
	#home_img img{width:90px;height:70px;}
	#download img{width:40px;height:40px;}
	#exit{width:50px;height:40px;float:left;margin-top:30px;padding-left:5px;}
	#exit a{line-height:40px;text-align:center;}
	#tit a{font-size:35px;line-height:70px;padding-left:450px;}
  </style>

 </head>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/combo.select.css">
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/zcity.css">
  <link href="<%=request.getContextPath()%>/css/animate.css.css" class="<%=request.getContextPath()%>/css/animate.css.css" rel ="stylesheet" type="text/css"></link>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/backstage.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.min.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqPaginator.min.js" ></script>
	<script type="text/javascript">
	/* )
	*/
	$(function(){
                $.jqPaginator('#pageulid', {
                    totalPages: parseInt($("#totalPageNum").val()), //设置分页的总页数，默认0
                    visiblePages: parseInt($("#visiblePages").val()), //设置最多显示的页码数，默认0
                    currentPage: parseInt($("#currentPageNum").val()), //设置当前的页码，默认1
                    first: '<li class="paginItem"><a href="javascript:void(0);">首页</a></li>', //首页
                    prev: '<li class="paginItem"><a href="javascript:void(0);"><span class="pagepre"></span></a></li>', //上一页
                    next: '<li class="paginItem"><a href="javascript:void(0);"><span class="pagenxt"></span></a></li>', //下一页
                    last: '<li class="paginItem"><a href="javascript:void(0);">末页</a></li>', //最后一页
                    page: '<li class="paginItem"><a href="javascript:void(0);">{{page}}</a></li>', //设置页码的Html结构
                    onPageChange: function (num, type) {//页面改变响应事件
						if(type=="change"){
							location.href = "<%=request.getContextPath()%>/keylist/pageQuaryList.php?nowPage="+num+"&allCount="+$("#allCount").val();
						}
                    }
                });
            });

            $(document).ready(function(){
                $(".click").click(function(){
                    $(".tip").fadeIn(200);
                });

                $(".tiptop a").click(function(){
                    $(".tip").fadeOut(200);
                });

                $(".sure").click(function(){
                    $(".tip").fadeOut(100);
                });

                $(".cancel").click(function(){
                    $(".tip").fadeOut(100);
                });
            });

            
            function check1(){
            	var website = $('#zzsc1 option:selected').val()
            	var year = $("#zzsc2 option:selected").val()
            	var quar = $("#zzsc3 option:selected").val()
            	var month = $("#zzsc4 option:selected").val()
            	var thread_or_praise = $("#zzsc5 option:selected").val()
            	var car_name = $("#zzsc6 option:selected").val()
            	var car_model_version = $("#zzsc7 option:selected").val()
            	var brand = $("#zzsc8 option:selected").val()
            	var real_feel = $("#zzsc9 option:selected").val()
            	var market_mess = $("#zzsc10 option:selected").val()
            	var nation = $("#zzsc11 option:selected").val()
            	location.href="<%=request.getContextPath()%>/keylist/secondQuery.php?website="
            		   +website+"&year="+year+"&quar="+quar+"&month="+month+"&thread_or_praise="+thread_or_praise
            		   +"&car_name="+car_name+"&car_model_version="+car_model_version+"&brand="+brand+
            		      "&real_feel="+real_feel+"&market_mess="+market_mess+"&nation"+nation;
            	 <%-- location.href="<%=request.getContextPath()%>/keylist/secondQueryList.php?website="+website+"&year="+year+"
            			"&quar="+quar+"&month="+month+"&thread_or_praise="+thread_or_praise+"&car_name="+car_name+"&car_model_version="
            			+car_model_version+"&brand="+brand+"&real_feel="+real_feel+"&market_mess="+market_mess+"&nation="+nation; --%>
             }
	</script>
 <body>
	<input type="hidden" id="opt" value="${opt}"/>
	<c:forEach items="${parameterValues}" var="s">
		<input style="display: none" name="key_list" type="checkbox"
			value="${s}" checked="checked">
	</c:forEach>
	<input type="hidden" id="totalPageNum" value="${pages.totalPageNum}" />
	<input type="hidden" id="visiblePages" value="${pages.visiblePages}" />
	<input type="hidden" id="currentPageNum" value="${pages.currentPageNum}" />
  <div id="sel">
	<div id="dh">
	<div id="home_box">
	<div id="home_img"><img src="<%=request.getContextPath()%>/image/ipsos.png"/></div>
	</div>
	<div id="home_txt"><a href="#"> ＞ 首页</a></div>
	<div id=tit><a>ipsos&nbsp;&nbsp;舆&nbsp;情&nbsp;检&nbsp;索</a></div>
	<div id="download"><img src="<%=request.getContextPath()%>/image/download.png" /></div>
	<div id="exit"><a href="#">退出登录</a></div>
	</div>
	<div id="xz">
	<form name="form1" >
		<ul id="x_ul">
       <li><div id="zzsc1">
	<select>
			<option value="">网站</option>
		<c:forEach items="${filterOption.website}" var="v">
			<option value="${v}">${v}</option>
		</c:forEach>
	</select>
</div></li>
	   <li><div id="zzsc2">
	<select>
			<option value="">年份</option>
		<c:forEach items="${filterOption.year}" var="v">
			<option  value="${v}">${v}</option>
		</c:forEach>
	</select>
</div></li>
       <li><div id="zzsc3">
	<select>
			<option value="">季度</option>
		<c:forEach items="${filterOption.quar}" var="v">
			<option value="${v}">${v}</option>
		</c:forEach>
	</select>
</div></li>
       <li><div id="zzsc4">
	<select>
			<option value="">月份</option>
		<c:forEach items="${filterOption.month}" var="v">
			<option value="${v}">${v}</option>
		</c:forEach>
	</select>
</div></li>
       <li><div id="zzsc5">
	<select>
			<option value="">口碑/论坛</option>
		<c:forEach items="${filterOption.thread_or_praise}" var="v">
			<option value="${v}">${v}</option>
		</c:forEach>
	</select>
</div></li>
	   <li><div id="zzsc6">
	<select>
			<option value="">车系</option>
		<c:forEach items="${filterOption.car_name}" var="v">
			<option value="${v}">${v}</option>
		</c:forEach>
	</select>
</div></li>
	   <li><div id="zzsc7">
	<select>
			<option value="">车型版本</option>
		<c:forEach items="${filterOption.car_model_version}" var="v">
			<option  value="${v}">${v}</option>
		</c:forEach>
	</select>
</div></li>
       <li><div id="zzsc8">
	<select>
			<option value="">品牌</option>
		<c:forEach items="${filterOption.brand}" var="v">
			<option value="${v}">${v}</option>
		</c:forEach>
	</select>
</div></li>
       <li><div id="zzsc9">
	<select>
			<option value="">情感</option>
		<c:forEach items="${filterOption.real_feel}" var="v">
			<option value="${v}">${v}</option>
		</c:forEach>
	</select>
</div></li>
       <li><div id="zzsc10">
	<select>
			<option value="">细分市场</option>
		<c:forEach items="${filterOption.market_mess}" var="v">
			<option  value="${v}">${v}</option>
		</c:forEach>
	</select>
</div></li>
	   <li><div id="zzsc11">
	<select>
			<option value="">国家</option>
		<c:forEach items="${filterOption.nation}" var="v">
			<option value="${v}">${v}</option>
		</c:forEach>
	</select>
</div></li>
<li><div id="btnSelect">
				<a onclick="check1()">搜&nbsp;&nbsp;索</a>
			</div></li>
	   </ul>
	   </form>
	</div>

	<div id="showdata">
	<c:if test="${pages == null}">未查到数据....</c:if>
	<c:if test="${pages != null}">
		<table>
		<tr border="1px red sloid" >
			<th>编号</th>
			<th>网站</th>
			<th>链接</th>
			<th>原话</th>
			<th>口碑/论坛</th>
			<th>年份</th>
			<th>季度</th>
			<th>月份</th>
			<th>品牌</th>
			<th>车系</th>
			<th>车型版本</th>
			<th>一级车型</th>
			<th>二级车型</th>
			<th>三级分类</th>
			<th>评价关键词</th>
			<th>实体情感</th>
			<th>国别</th>
			<th>细分市场</th>
			<th>省/区域</th>
			
		<!-- 	<th><a href="#">撒案件</a></td>
			<th><a href="#" class="btn" id="txt" onclick="replace()">撒案件撒案件撒案件</a></td> -->
		</tr>
		<c:forEach items="${pages.entitys}" var="v" varStatus="i">
			<tr border="1px red sloid">
				<td border="1px red sloid">${i.count}</td>
				<td border="1px red sloid">${v.website}</td>
				<td border="1px red sloid"><a href="${v.link}" target="_blank">${v.link}</a></td>
				<td border="1px red sloid"><a href="#" class="btn" id="txt" onclick="replace()">${v.original_text}</a></td>
				<td border="1px red sloid">${v.thread_or_praise}</td>
				<td border="1px red sloid">${v.year}</td>
				<td border="1px red sloid">${v.quar}</td>
				<td border="1px red sloid">${v.month}</td>
				<td border="1px red sloid">${v.brand}</td>
				<td border="1px red sloid">${v.car_name}</td>
				<td border="1px red sloid">${v.car_model_version}</td>
				<td border="1px red sloid">${v.first_property}</td>
				<td border="1px red sloid">${v.second_property}</td>
				<td border="1px red sloid">${v.third_classify}</td>
				<td border="1px red sloid">${v.comment_key}</td>
				<td border="1px red sloid">${v.real_feel}</td>
				<td border="1px red sloid">${v.nation}</td>
				<td border="1px red sloid">${v.market_mess}</td>
				<td border="1px red sloid">${v.real_feel}</td>
			</tr>
		</c:forEach>	
		</table>
		</c:if>
		
</div>
<input id="allCount" type="hidden" value="${pages.totalCount}">
		 <div class="pagin">
				<div class="message" id="message"><a>共<i class="blue">${pages.totalCount}</i>条记录，当前显示第&nbsp;<i class="blue">${pages.currentPageNum}&nbsp;</i>页</a></div>
				<ul class="paginList" id="pageulid"></ul>
		</div>
			
	</div>
	<div class="show" id="show"> </div>
	<div class="yy"></div>
  </div>
<script src="<%=request.getContextPath()%>/js/jquery.combo.select.js"></script>
<script type ="text/javascript">
  $(function(){

   $(".btn").click(function(){

	var _left =( $(window).width()-700) / 2;
    var _top = ($(window).height()-350) / 2;
	
    $(".show").addClass("animated boundIN").show().css({
       left:_left,
	   top:_top
	});
     $(".yy").toggle(0);
   });

  
	$(".yy").click(function(){
		   $(".show,.yy").toggle(0)
	   });
$('select').comboSelect();
	  });
function replace(){
	document.getElementById("show").innerText=document.getElementById("txt").innerText;
}
</script>

 </body>
</html>
