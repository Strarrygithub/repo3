<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="/SSM_phoneInfo/js/jquery-1.8.3.min.js"></script>
<script>
$(function(){
	var pageBean=null;
	var pageNow=1;
	
	show(pageNow);
	function show(pageNow){
		$.ajax({
			url:"reshow",
			data:{
				"pageNow":pageNow,
				'id':${param.id}
			},
			success:function(rs){
				pageBean=rs.pageBean;
				var Info=rs.Info;
				$('div').empty();
				
				var $p= $('<p></p>');
				$p.append('<h2>'+Info.title+'</h2><br>')
				.append('<label>发表于:'+Info.repTime+'</label><br>')
				.append('<label>:'+Info.content+'</label><br>')
				.append('<h2>读者回应</h2><br>');
				$('div').append($p);
				
				if(pageBean.total==0){
					$('div').append('<h2>暂时无评论</h2>')
				}else{
					$.each(pageBean.list,function(index,r){
						var $p= $('<p></p>');
						$p.append('<lable>发表于:'+r.reTime+'</lable><br>')
						.append('<lable>'+r.content+'</lable>').css('background','#eee')
						$('div').append($p);
					})
				}
			}
		})
	}
	
	$('p').on('click','a',function(){
		if(pageBean!=null){
			if(pageBean.total>0){
				var p=$(this).data('p');
				if(p=="home"){
					pageNow=1;
				}else if(p=="up"){
					pageNow=pageNow-1<1? 1:pageNow-1;
				}else if(p=="down"){
					pageNow=pageNow+1>pageBean.pages? pageBean.pages:pageNow+1;
				}else if(p=="last"){
					pageNow=pageBean.pages;
				}
				show(pageNow);
			}
		}else{
			return;
		}
	})
	
	
	$('#up').click(function(){
		var text=$('#text').val();
		if(text.length>200){
			alert("字数不能超过200");
			$('#text').val("")
			return;
		}
		if(text==""){
			alert("不能为空");
			return;
		}
		$.ajax({
			url:"add",
			data:{
				"infoId":${param.id},
				"content":text
			},
			success:function(rs){
				if(rs=="yes"){
					alert("添加成功");
					show(pageNow);
					$('#text').val("");
				}else{
					alert("添加失败");
				}
			}
		})
	})
	update();
	function update(){
		$.ajax({
			url:"updateInfo",
			data:{
				"id":${param.id}
			},
			success:function(rs){
				if(rs=="yes"){
					alert("查看次数加1")
				}
			}
		})
	}
	
	
})



</script>
</head>
<body>
<div >
<p></p>

</div>

<p>
<a href="javascript:void(0)" data-p="home">首页</a>
<a href="javascript:void(0)" data-p="up">上一页</a>
<a href="javascript:void(0)" data-p="down">下一页</a>
<a href="javascript:void(0)" data-p="last">末页</a>
<span></span>
</p>

<form action="">
<label>快速回复(字数200字以内)<br></label>
<textarea rows="5" cols="20" id="text"></textarea><br>
<input type="button" value="提交" id="up">
<a href="toindex">返回首页</a>
</form>
</body>
</html>