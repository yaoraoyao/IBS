<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Permission列表</title>
		<meta charset="utf-8">
		<%@include file="../common.jsp"%>
		<script type="text/javascript" src="/js/permission/permission.js"></script>
		<style type="text/css">
			#ffff>div{
				width: 100%;
				height: 40px;
				text-align: center;
			}
			#ffff>div:first-child {margin-top: 20px;}
			.in{width: 200px;height: 20px;border:1px solid #ccc;border-radius: 5px;}
			.in:focus{border:1px solid #66AFE9;box-shadow: 0px 0px 10px 1px #66AFE9;}
			.searchIn{width: 100px;}
		</style>
	</head>
	<body>
		<table id="dg"></table>
		<div id="toolbar">
			<form id="searchForm">
				<input type="hidden" name="pageNo" id="pageNo">
				<input type="hidden" name="pageSize" id="pageSize">
				<a data-method="add" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">新增</a>
				<a data-method="edit" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">编辑</a>
				<a data-method="remove" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
				<input type="text" class="in searchIn" name="name" placeholder="按名称模糊查询" />
				<a data-method="search" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
				<a data-method="searchAll" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索全部</a>
			</form>
		</div>
		<div id="win" class="easyui-window" title="新增/编辑Permission信息" style="width:300px;height:385px;"   
		        data-options="iconCls:'icon-save',modal:true,closed:true,collapsible:false,minimizable:false,maximizable:false,resizable:false">   
		    <form id="ffff">
		        <input type="hidden" name="id" id="permissionid">		        
			    <div>
			        <input class="in" type="text" name="name" placeholder="name" />
			    </div>    
			    <div>
			        <input class="in" type="text" name="url" placeholder="url" />
			    </div>    
			    <div>
			        <input class="in" type="text" name="descs" placeholder="descs" />
			    </div>    
			    <div>
			        <input class="in" type="text" name="sn" placeholder="sn" />
			    </div>    
			    <div>
			        <input class="in" type="text" name="menuId" placeholder="menuId" />
			    </div>    
			    <div style="text-align: right;box-sizing: border-box;padding-right: 38px;">
			    	<a data-method="cancel" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">取消</a>
			    	<a data-method="submit" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">提交</a>
			    </div>  
			</form> 
		</div>
	</body>
</html>