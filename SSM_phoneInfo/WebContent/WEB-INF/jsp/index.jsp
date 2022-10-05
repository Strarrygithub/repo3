<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	function show(){
		$.ajax({
			url:"show",
			data:{
				"pageNow":pageNow
			},
			success:function(rs){
				pageBean=rs.pageBean;
				$('tbody').empty();
				$('p').empty();
				if(pageBean.total==0){
					$('tbody').append('<h2>未找到数据</h2>')
				}else{
					$.each(pageBean.list,function(index,info){
						var $tr=$('<tr></tr>');
						var num=(pageBean.pageNow-1)*pageBean.pageSize+index+1;
						$tr.append('<td>'+num+'</td>')
						.append('<td><a href="toshowInfo?id='+info.id+'">'+info.title+'</a></td>')
						.append('<td>'+info.reCount+'/'+info.vCount+'</td>')
						.append('<td>'+info.repTime+'</td>')
						.append('<td>'+info.last+'</td>');
						$('tbody').append($tr);
						if(index%2==0){
							$tr.attr('bgcolor','red');
						}
					})
					
				}
				
				if(pageBean.total!=0){
					$('p').append('<a href="javascript:void(0)" data-p="home">首页</a>')
					.append('<a href="javascript:void(0)" data-p="up">上一页</a>')
					.append('<a href="javascript:void(0)" data-p="down">下一页</a>')
					.append('<a href="javascript:void(0)" data-p="last">末页</a>')
					.append('<span">第'+pageBean.pageNow+'页/共'+pageBean.pages+'页</span>')
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
	
	
	
	
})



</script>
</head>
<body>
<h2>手机资讯</h2>
<table border="1px">
	<thead>
		<tr>
			<td>序号</td>
			<td>标题</td>
			<td>回复/查看</td>
			<td>发表时间</td>
			<td>最新回复</td>
		</tr>
	</thead>
	<tbody>
		
	
	</tbody>
</table>
<p></p>
</body>
</html>