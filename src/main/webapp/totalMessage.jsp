<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!doctype html>
<html>
   
  <title>查询页面</title>
  <meta charset="utf-8">
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
	background: #f5f6ec;
}
	#showdata table{border-collapse:collapse;border-spacing:0;width:100%;table-layout:fixed;}
	#showdata table td{vertical-align:middle;border:none;}

	#sel{width:1500px;height:700px;border:1px red solid;margin:10px auto;}
	#dh{width:1500px;height:50px;border:1px red solid;margin:10px auto;}
	#xz{width:1500px;height:30px;border:1px red solid;margin:10px auto;}
	#showdata{width:1500px;height:480px;border:1px red solid;margin:10px auto;}
	#showdata table{table-layout:fixed;}
	#showdata tr{width:1500px;height:30px;border:1px red solid;}
	#showdata td{height:30px;border:1px red solid;text-align:center;width:100%;overflow:hidden;text-overflow:ellipsis;white-space:nowrap;}
	.show{width:900px;height:500px;background:#fff;border:1px solid #fff;
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
		height: 30px;
		background: #167ED9;
		display: block;
		line-height: 30px;
		color: #ffffff;
		text-align: center;
		font-size: 12px;
	}
	.div123{
		width:790px;
	}
	.div123 table{
		border:1px black solid;
	}
	.div123 td{
		height:25px;
	}

  </style>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/combo.select.css">
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/zcity.css">
  <link href="<%=request.getContextPath()%>/css/animate.css.css" class="<%=request.getContextPath()%>/css/animate.css.css" rel ="stylesheet" type="text/css"></link>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/backstage.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.11.1.min.js" ></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jqPaginator.min.js" ></script>
	<script type="text/javascript">
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
            function myAlert(){
            	var mess = document.getElementById("message").val()
            	if(mess!=null && mess != ""){
            		alert(mess)
            		return;
            	}
            }
	</script>
 <body onload="myAlert()">
	<input type="hidden" id="opt" value="${opt}"/>
	<c:forEach items="${parameterValues}" var="s">
		<input style="display: none" name="key_list" type="checkbox"
			value="${s}" checked="checked">
	</c:forEach>
	<input type="hidden" id="totalPageNum" value="${pages.totalPageNum}" />
	<input type="hidden" id="visiblePages" value="${pages.visiblePages}" />
	<input type="hidden" id="currentPageNum" value="${pages.currentPageNum}"/>
	<input type="hidden" id="message" value="${message}">
  <div id="sel">
	<div id="dh">
		<div position="right" height="100px" width="50px" border="1px solid red"><a href="<%=request.getContextPath()%>/keylist/download.php">下载</a>></div>
		<div position="right" height="100px" width="50px" border="1px solid red"><a href="<%=request.getContextPath()%>/keylist/redirct.php">首页</a>></div>
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
			<option  value="${v}"><a title="${v}">${v}</a></option>
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
		<tr border="1px red solid" >
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
		</tr>
		<c:forEach items="${pages.entitys}" var="v" varStatus="i">
			<tr border="1px red solid">
				<td border="1px red solid">${i.count}</td>
				<td border="1px red solid">${v.website}</td>
				<td border="1px red solid"><a href="${v.link}" target="_blank">${v.link}</a></td>
				<td border="1px red solid"><a href="#" class="btn"  onclick="replace('jsahfd')">${v.original_text}</a></td>
				<td border="1px red solid">${v.thread_or_praise}</td>
				<td border="1px red solid">${v.year}</td>
				<td border="1px red solid">${v.quar}</td>
				<td border="1px red solid">${v.month}</td>
				<td border="1px red solid">${v.brand}</td>
				<td border="1px red solid">${v.car_name}</td>
				<td border="1px red solid">${v.car_model_version}</td>
				<td border="1px red solid">${v.first_property}</td>
				<td border="1px red solid">${v.second_property}</td>
				<td border="1px red solid">${v.third_classify}</td>
				<td border="1px red solid">${v.comment_key}</td>
				<td border="1px red solid">${v.real_feel}</td>
				<td border="1px red solid">${v.nation}</td>
				<td border="1px red solid">${v.market_mess}</td>
				<td border="1px red solid">${v.real_feel}</td>
 				<%-- <div id="txt${i.count}">
					${v.totalDiv}
				</div> --%>
			</tr>
		</c:forEach>	
		</table>
		<div hidden="true" class="div123" id="jsahfd">
		<br/>
		<table>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>网站</td><td width='600px' valign='middle'>汽车之家</td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>链接</td><td width='600px' valign='middle'><a href=123 target='_blank'>123</a></td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>年度</td><td width='600px' valign='middle'>2017</td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>季度</td><td width='600px' valign='middle'>Q2</td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>月份</td><td width='600px' valign='middle'>4</td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>口碑/论坛</td><td width='600px' valign='middle'>口碑</td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>车系</td><td width='600px' valign='middle'>英朗</td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>车型版本</td><td width='600px' valign='middle'>2017款 15N 手动进取型</td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>一级属性</td><td width='600px' valign='middle'>外观</td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>二级属性</td><td width='600px' valign='middle'>车头外观</td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>三级分类</td><td width='600px' valign='middle'>3</td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>关键词</td><td width='600px' valign='middle'>好看</td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>关键词</td><td width='600px' valign='middle'>好看</td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>真实情感</td><td width='600px' valign='middle'>正面</td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>国家</td><td width='600px' valign='middle'>美系</td></tr>
			<tr><td width='180px'  height='25px' valign='middle' align='center'>细分市场</td><td width='600px' valign='middle'>Low-med</td></tr>
		</table>
		</div>
		</c:if>
		<input id="allCount" type="hidden" value="${pages.totalCount}">
		 <div class="pagin">
				<div class="message" id="message"><a>共<i class="blue">${pages.totalCount}</i>条记录，当前显示第&nbsp;<i class="blue">${pages.currentPageNum}&nbsp;</i>页</a></div>
				<ul class="paginList" id="pageulid"></ul>
		</div>
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
function replace(str){
	document.getElementById("show").innerHTML=document.getElementById(str).innerHTML;
}
</script>

 </body>
</html>
