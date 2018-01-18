<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>

<html>
<title>Ipsos 舆情检索</title>
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
	color: #666;
	background: #f5f6ec;
}

.auto_hidden {
	width: 204px;
	border-top: 1px solid #333;
	border-bottom: 1px solid #333;
	border-left: 1px solid #333;
	border-right: 1px solid #333;
	position: absolute;
	display: none;
}

.auto_show {
	width: 204px;
	border-top: 1px solid #333;
	border-bottom: 1px solid #333;
	border-left: 1px solid #333;
	border-right: 1px solid #333;
	position: absolute;
	z-index: 9999; /* 设置对象的层叠顺序 */
	display: block;
}

.auto_onmouseover {
	color: #ffffff;
	background-color: highlight;
	width: 100%;
}

.auto_onmouseout {
	color: #000000;
	width: 100%;
	background-color: #ffffff;
}

#img_box {
	width: 550px;
	height: 80px;
	margin: 10px auto;
	margin-top: 130px;
}

#img_box a {
	width: 550px;
	height: 70px;
}

#img_log {
	width: 100px;
	height: 70px;
	float: left;
	margin-left: 100px;
}

#txt_box {
	width: 200px;
	height: 70px;
	float: left;
	font-size: 35px;
	text-align: center;
	line-height: 70px;
	font-family: "Arial";
}

#searchbox {
	margin: 0px auto;
	height: 36px;
	width: 550px;
}

#searchbox div {
	float: left;
}

#o {
	height: 35px;
	width: 440px;
	line-height: 35px;
	border: solid 1px #4791FF;
	font-size: 20px;
	padding-left:5px;
}

#btnSelect a {
	width: 100px;
	height: 37px;
	background: #167ED9;
	display: block;
	line-height: 36px;
	color: #ffffff;
	text-align: center;
	font-size: 16px;
}

#ipsos_check {
	width:200px;
	height:0px;
	position: absolute;
	margin: 10px auto;
	float: left;
	line-height: 30px;
}

#ipsos_check a {
	line-height: 30px;
	font-size: 18px;
}

a:link {
	text-decoration: none;
}

a:hover {
	cursor: pointer;
}

#dtitles {
	width: 540px;
	height: 90px;
	border: solid 1px #CCCCCC;
	display: none;
	font-size: 12px;
}

.li1 {
	background: #F0F0F0;
}
</style>

<script language="javascript">
	var $ = function(id) {

		return "string" == typeof id ? document.getElementById(id) : id;

	}

	var Bind = function(object, fun) {

		return function() {

			return fun.apply(object, arguments);

		}

	}

	function AutoComplete(obj, autoObj, arr) {

		this.obj = $(obj); //输入框

		this.autoObj = $(autoObj);//DIV的根节点

		this.value_arr = arr; //不要包含重复值

		this.index = -1; //当前选中的DIV的索引

		this.search_value = ""; //保存当前搜索的字符

	}

	AutoComplete.prototype = {

		//初始化DIV的位置

		init : function() {

			this.autoObj.style.left = this.obj.offsetLeft + "px";

			this.autoObj.style.top = this.obj.offsetTop + this.obj.offsetHeight
					+ "px";

			this.autoObj.style.width = this.obj.offsetWidth - 2 + "px";//减去边框的长度2px

		},

		//删除自动完成需要的所有DIV

		deleteDIV : function() {

			while (this.autoObj.hasChildNodes()) {

				this.autoObj.removeChild(this.autoObj.firstChild);

			}

			this.autoObj.className = "auto_hidden";

		},

		//设置值

		setValue : function(_this) {

			return function() {

				_this.obj.value = this.seq;

				_this.autoObj.className = "auto_hidden";

			}

		},

		//模拟鼠标移动至DIV时，DIV高亮

		autoOnmouseover : function(_this, _div_index) {

			return function() {

				_this.index = _div_index;

				var length = _this.autoObj.children.length;

				for (var j = 0; j < length; j++) {

					if (j != _this.index) {

						_this.autoObj.childNodes[j].className = 'auto_onmouseout';

					} else {

						_this.autoObj.childNodes[j].className = 'auto_onmouseover';

					}

				}

			}

		},

		//更改classname

		changeClassname : function(length) {

			for (var i = 0; i < length; i++) {

				if (i != this.index) {

					this.autoObj.childNodes[i].className = 'auto_onmouseout';

				} else {

					this.autoObj.childNodes[i].className = 'auto_onmouseover';

					this.obj.value = this.autoObj.childNodes[i].seq;

				}

			}

		}

		,

		//响应键盘

		pressKey : function(event) {

			var length = this.autoObj.children.length;

			//光标键"↓"

			if (event.keyCode == 40) {

				++this.index;

				if (this.index > length) {

					this.index = 0;

				} else if (this.index == length) {

					this.obj.value = this.search_value;

				}

				this.changeClassname(length);

			}

			//光标键"↑"

			else if (event.keyCode == 38) {

				this.index--;

				if (this.index < -1) {

					this.index = length - 1;

				} else if (this.index == -1) {

					this.obj.value = this.search_value;

				}

				this.changeClassname(length);

			}

			//回车键

			else if (event.keyCode == 13) {

				this.autoObj.className = "auto_hidden";

				this.index = -1;

			} else {

				this.index = -1;

			}

		},

		//程序入口

		start : function(event) {

			if (event.keyCode != 13 && event.keyCode != 38
					&& event.keyCode != 40) {

				this.init();

				this.deleteDIV();

				this.search_value = this.obj.value;

				var valueArr = this.value_arr;

				valueArr.sort();

				if (this.obj.value.replace(/(^\s*)|(\s*$)/g, '') == "") {
					return;
				}//值为空，退出

				try {
					var reg = new RegExp("(" + this.obj.value + ")", "i");
				}

				catch (e) {
					return;
				}

				var div_index = 0;//记录创建的DIV的索引

				for (var i = 0; i < valueArr.length; i++) {

					if (reg.test(valueArr[i])) {

						var div = document.createElement("div");

						div.className = "auto_onmouseout";

						div.seq = valueArr[i];

						div.onclick = this.setValue(this);

						div.onmouseover = this.autoOnmouseover(this, div_index);

						div.innerHTML = valueArr[i].replace(reg,
								"<strong>$1</strong>");//搜索到的字符粗体显示

						this.autoObj.appendChild(div);

						this.autoObj.className = "auto_show";

						div_index++;

					}

				}

			}

			this.pressKey(event);

			window.onresize = Bind(this, function() {
				this.init();
			});

		}

	}
</script>

<body>

	<div id="img_box">
		<div id="img_log">
			<a href="#"><img src="<%=request.getContextPath()%>/img/ipsos.png" alt="ipsos" width="100"
				height="70" /></a>
		</div>
		<div id="txt_box">
			<a href="#">&nbsp;&nbsp;舆情分析</a>
		</div>
	</div>

	<div id="searchbox">
		<form action="<%=request.getContextPath()%>/keylist/keyQuery.php" name="form1" method="post">
			<div>
				<input type="text" id="o" name="opt" placeholder="" autocomplete="off"
					onkeyup="autoComplete.start(event)" />
			</div>
			<div id="btnSelect">
				<a><input style="border:0px;background-color:transparent;outline:none; font-size:16px" type="submit" value="搜&nbsp;&nbsp;索"></a>
			</div>
			<div id="ipsos_check">
				<br> <a><label><input type="checkbox"
						name="checkbox" checked="checked"  value="key">
						关键字&nbsp;&nbsp;&nbsp;&nbsp;</label></a> 
						<a><label><input
						type="checkbox" name="checkbox" value="text">
						原话&nbsp;&nbsp;&nbsp;&nbsp;</label></a>
			</div>
		</form>
	</div>

	<c:forEach items="${list}" var="s">
		<input style="display: none" name="key_list" type="checkbox"
			value="${s}" checked="checked">
	</c:forEach>

	<div class="auto_hidden" id="auto">
		<!--自动完成 DIV-->
	</div>
	<script type="text/javascript">
	var obj = document.getElementsByName("key_list");
	var arr = [];
	for(var key in obj){
		if(obj[key].checked){
			arr.push(obj[key].value)
		}
	} 
    var autoComplete=new AutoComplete('o','auto',arr);
    /* function submit1(){
    	var key = document.getElementById("o").value
    	var keystat = document.getElementsByName("checkbox")
    	var karr = []
   		for(var key in keystat){
	   		if(keystat[key].checked){
	   			arr.push(keystat[key].value)
	   		}
    	} 
    	alert(key)
    	alert(typeof(karr))
    } */
</script>

</body>

</html>