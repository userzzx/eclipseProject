<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
#tbd tr:nth-child(odd){
	background-color:gray;
}
#tbd tr:hover{
	background-color:pink;
}
</style>
<script type="text/javascript" src="/Java18_Factory/js/jquery-1.8.2.js"></script>
<script type="text/javascript">
	$(function(){
		//queryByPage(1);
		
		//queryAllBill();
		//setDate();
		//getNo();
	});
	
	function setDate(){
		$.post("/Java18_Factory/getDate","",
				function(data){
					$("#billdate").val(data);
			});
	}
	
	function getNo(){
		$("#billdate").on("blur",function(){
			$.post("/Java18_Factory/getNo",
					{
						billdate:$("#billdate").val()
					},
					function(billno){
				$("#billno").val(billno);
				
			});
		});
	}
	
	function queryAllBill(){
		$.post("/Java18_Factory/queryAllBill","",
				function(data){
			$.each(data,function(i,obj){
				var tr=$("#tempTr").clone(true).show();
				tr.find("td:eq(0)").html(i+1);
				tr.find("td:eq(1)").html(obj.billno);
				tr.find("td:eq(2)").html(obj.billdate);
				tr.find("td:eq(3)").html(obj.totalMoney);
				tr.appendTo($("#tbd"));
				/*tr.on("click",function(){
					queryBillByBillno2(obj.billno);
				});*/
			});
		});
	}
	
	function queryBillByBillno(obj){
		var billno=$(obj).find("td:eq(1)").html();
		$.post("/Java18_Factory/queryBillByBillno",
				{billno:billno},function(result){
					$("#billno").val(result.billno);
					$("#billdate").val(result.billdate);
					$("#suppliername").val(result.suppliername);
					queryDetailByBillno(billno);
					
				});
		
		
	}
	
	function queryBillByBillno2(billno){
	
	}
	
	
	function queryByPage(currentPage){
		$.post("/Java18_Factory/queryByPage",
				{currentPage:currentPage},
				function(result){
					$("#billno").val(result.billno);
					$("#billdate").val(result.billdate);
					$("#suppliername").val(result.suppliername);
					$("#preNum").off("click");//取消绑定
					$("#nextNum").off("click");//取消绑定
					//绑定上一页方法
					$("#preNum").on("click",function(){
						queryByPage(result.preNum);
					});
					//绑定下一页方法
					$("#nextNum").on("click",function(){
						queryByPage(result.nextNum);
					});
					
					queryDetailByBillno(result.billno);
				}		
		);
	}
	
	function queryDetailByBillno(billno){
		$.post("/Java18_Factory/queryDetailByBillno",
				{billno:billno},
				function(result){
					var str=JSON.stringify(result);
					$("#details").val(str);
				}		
		);
	}
	
	
	function deleteBillno(){
		if(!confirm("你是否删除该单据?"))return ;
	
		
		$.post("/Java18_Factory/deleteByBillno",
				{billno:$("#billno").val()},
				function(result){
					alert("删除成功");
					location.reload();					
				}		
		);
	}
	
	
	function saveBill(){
		var param={};
		param.billno=$("#billno").val();
		param.billdate=$("#billdate").val();
		param.suppliername=$("#suppliername").val();
		var str=$("#details").val();
		var json=$.parseJSON(str);
		$.each(json,function(i,obj){
			param["details["+i+"].goodsname"]=obj.goodsname;
			param["details["+i+"].goodsnum"]=obj.goodsnum;
			param["details["+i+"].goodsprice"]=obj.goodsprice;
			param["details["+i+"].goodsmoneyamt"]=obj.goodsmoneyamt;
			param["details["+i+"].ispresent"]=obj.ispresent;
		});
		
		$.post("/Java18_Factory/saveBill",
				param,
				function(result){
					if(result=="0"){
						alert("单号重复");
					}else{
						alert("添加成功");
						location.reload();
					}
				}		
		);
	}
</script>
</head>
<body>
	<div>
		<table>
			<tr>
				<td>编号</td>
				<td><input name="billno" id="billno"></td>
				
				<td>日期</td>
				<td><input name="billdate" id="billdate"></td>
			</tr>
			<tr>
				<td>供应商</td>
				<td><input name="suppliername" id="suppliername"></td>
				
			</tr>
			<tr>
				<td>明细</td>
				<td colspan="3">
					<textarea name="details" id="details" rows="10" cols="50"></textarea>
				</td>
			</tr>
		</table>
		<table cellspacing="0">
			<thead>
				<tr>
					<td>序号</td>
					<td>编号</td>
					<td>日期</td>
					<td>总金额</td>
				</tr>
				<tr id="tempTr" 
				style="display:none"
				onclick="queryBillByBillno(this)"
				>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</thead>
			<tbody id="tbd">
				<c:forEach items="${list }" 
				var="bill" varStatus="vs">
					<tr>
						<td>${vs.count }</td>
						<td>${bill.billno }</td>
						<td>
						<fmt:formatDate value="${bill.billdate }"
						pattern="yyyy-MM-dd"/>
						</td>
						<td>${bill.totalMoney }</td>
					</tr>
				</c:forEach>
			</tbody>
			
		</table>
		<input type="button" value="上一页" id="preNum">
		<input type="button" value="下一页" id="nextNum">
		<input type="button" value="保存" onclick="saveBill()">
		<input type="button" value="删除" onclick="deleteBillno()">
	</div>
	
</body>
</html>