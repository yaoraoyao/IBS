$(function(){
    $("#dg").datagrid({
        url:"/role/page.do",
        method:"post",
        striped:true,		//是否显示斑马线效果
        nowrap:false,		//如果列的字符串太长，默认是要换行显示，这个属性设置为true表示不换行
        pagination:true,	//是否显示分页工具栏
        rownumbers:true,	//是否在表格左侧显示行号[主键id字段的值往往是不连续的，所以搞了一个行号从1开始显示]
        //singleSelect:true,	//是否只能单选，默认可以多选
        queryParams:{},		//发送请求加载数据的时候的额外请求参数
        frozenColumns:[[{field:'abcdefg',checkbox:true}]],	//在表格左侧显示多选框，全选、全不选
        columns:[[     
            {field:"id",title:'主键',width:100,hidden:true},
            {field:"name",title:'名称',width:100,align:"center"},
            {field:"sn",title:'编码',width:100,align:"center"},
            {field:"permissions",title:'角色拥有的权限',width:500,align:"left",formatter:function (value, row, index) {
                var names = [];
                for (var i=0;i<value.length;i++){
                    names.push(value[i].name);
                }
                return names.join("，");
            }},
            {field:"employees",title:'角色拥有的用户',width:500,align:"left",formatter:function (value, row, index) {
                var names = [];
                for (var i=0;i<value.length;i++){
                    names.push(value[i].username);
                }
                return names.join("，");
            }},
        ]],
        toolbar: "#toolbar"
    });
    //实现翻页功能
    $("#dg").datagrid("getPager").pagination({
        onSelectPage:function(pageNumber, pageSize){
            $("#dg").datagrid('loading');
            //往两个隐藏文本框中填值
            $("#pageNo").val(pageNumber);
            $("#pageSize").val(pageSize);
            //load方法传入一个json对象用来取代datagrid的queryParams属性，将其放入请求中作为请求参数传递到后端，每次翻页的请求地址不变
            $("#dg").datagrid('load', $("#searchForm").toJson());
            //修改一下分页工具栏中显示的当前页码
            $(this).pagination({
                pageNumber:pageNumber,
                pageSize:pageSize
            });
            $("#dg").datagrid('loaded');
        }
    });

    //新增和编辑角色的窗口中左侧的已选权限列表
    $("#selectedPermissions").datagrid({
        striped:true,		//是否显示斑马线效果
        nowrap:true,		//如果列的字符串太长，默认是要换行显示，这个属性设置为true表示不换行
        singleSelect:true,	//是否只能单选，默认可以多选
        height:"350px",
        columns:[[
            {field:"id",title:'id',width:100,hidden:true},
            {field:"name",title:'名称',width:100,align:"center"},
            {field:"url",title:'资源路径',width:120,align:"center"},
            {field:"sn",title:'权限编码',width:120,align:"center"}
        ]],
        //左边的表格每一行被双击之后直接移除当前行
        onDblClickRow:function (index, row) {
            //deleteRow index 删除行。
            $(this).datagrid("deleteRow",index);
        }
    });
    //新增和编辑角色的窗口中右侧的所有备选权限列表
    $("#allPermissions").datagrid({
        url:"/permission/page.do",
        method:"post",
        striped:true,		//是否显示斑马线效果
        nowrap:true,		//如果列的字符串太长，默认是要换行显示，这个属性设置为true表示不换行
        pagination:true,	//是否显示分页工具栏
        rownumbers:true,	//是否在表格左侧显示行号[主键id字段的值往往是不连续的，所以搞了一个行号从1开始显示]
        singleSelect:true,	//是否只能单选，默认可以多选
        queryParams:{},		//发送请求加载数据的时候的额外请求参数
        height:"350px",
        columns:[[
            {field:"id",title:'id',width:100,hidden:true},
            {field:"name",title:'名称',width:100,align:"center"},
            {field:"url",title:'资源路径',width:120,align:"center"},
            {field:"sn",title:'权限编码',width:120,align:"center"}
        ]],
        //在用户双击一行的时候触发，参数包括： index：点击的行的索引值，该索引值从0开始。 row：对应于点击行的记录。
        onDblClickRow:function (index, row) {
            //判断一下左边表格中是否已选当前双击的权限数据
            var leftRows = $("#selectedPermissions").datagrid("getRows");
            for (var i=0;i<leftRows.length;i++){
                //如果左边表格中某一行的id与当前双击的row的id相同，就表示已选择该权限，不能重复选
                if(leftRows[i].id == row.id){
                    $.messager.show({
                        title:'错误提示',
                        msg:'亲，请不要重复选择权限.....'
                    });
                    return;
                }
            }
            //双击之后添加到左边的datagrid中
            $("#selectedPermissions").datagrid("appendRow", row);
        }
    });
    //右侧的表格实现翻页功能
    $("#allPermissions").datagrid("getPager").pagination({
        onSelectPage:function(pageNumber, pageSize){
            $("#allPermissions").datagrid('loading');
            //load方法传入一个json对象用来取代datagrid的queryParams属性，将其放入请求中作为请求参数传递到后端，每次翻页的请求地址不变
            $("#allPermissions").datagrid('load', {pageNo:pageNumber,pageSize:pageSize});
            //修改一下分页工具栏中显示的当前页码
            $(this).pagination({
                pageNumber:pageNumber,
                pageSize:pageSize
            });
            $("#allPermissions").datagrid('loaded');
        }
    });
});

/**
 * 有一些function名称会被js或者jQuery的方法覆盖，这个就称为污染
 * 下面这个做法可以有效防止污染
 */
window.methods = {
    add:function (){
        //重置一下表单
        $("#ffff").form("reset");
        //手动再清空一下employeeid
        $("#roleid").val("");
        //打开新增窗口的时候清空一下左侧表格
        $("#selectedPermissions").datagrid("loadData",[]);
        //弹出模态窗口
        $('#win').window('open');  // open a window
    },
    edit:function (){
        //重置一下表单
        $("#ffff").form("reset");
        //手动再清空一下employeeid
        $("#roleid").val("");

        //获取到datagrid内已经被选中的行
        var rows = $("#dg").datagrid("getSelections");
        if(rows.length == 0){
            $.messager.alert('错误',"请选择你要编辑的员工！","error");
            return;
        }
        if(rows.length > 1){
            $.messager.alert('错误',"你只能选择一行进行修改！","error");
            return;
        }
        var row = rows[0];
        $.getJSON("/role/findOne.do",{id:row.id},function(data){
            //表单回填
            $("#ffff").form("load", data);
            //点击编辑按钮之后，回填一下左侧的表格数据【当前角色已选哪些权限】
            $("#selectedPermissions").datagrid("loadData",data.permissions);
            //回填一下部门下拉框
            //$("#departmentId").combobox("setValue",data.department.id);
            //弹出模态窗口
            $('#win').window('open');  // open a window
        });
    },
    remove:function (){
        //获取到datagrid内已经被选中的行
        var rows = $("#dg").datagrid("getSelections");
        if(rows.length == 0){
            $.messager.alert('错误',"请选择你要编辑的员工！","error");
            return;
        }
        var ids = [];
        for(var i=0;i<rows.length;i++){
            ids.push(rows[i].id);
        }
        //发送请求
        $.messager.confirm('确认','您确认想要删除记录吗？',function(r){
            if (r){
                // delete from employee where id in (12,2,5)
                $.getJSON("/role/delete.do",{ids:ids.join(",")},function(data){
                    if(data.status == 200){
                        $.messager.alert('消息',data.msg,"info");
                        //重新刷新表格的数据
                        $("#dg").datagrid('load',{
                            pageNo:1,
                            pageSize:10
                        });
                        $("#dg").datagrid("getPager").pagination({
                            pageNumber:1,
                            pageSize:10
                        });
                    }else{
                        $.messager.alert('错误',data.msg,"error");
                    }
                });
            }
        });
    },
    search:function () {
        $("#dg").datagrid('loading');
        //往两个隐藏文本框中填值
        $("#pageNo").val(1);
        $("#pageSize").val(10);
        //load方法传入一个json对象用来取代datagrid的queryParams属性，将其放入请求中作为请求参数传递到后端，每次翻页的请求地址不变
        $("#dg").datagrid('load', $("#searchForm").toJson());
        //修改一下分页工具栏中显示的当前页码
        $(this).pagination({
            pageNumber:1,
            pageSize:10
        });
        $("#dg").datagrid('loaded');
    },
    searchAll:function () {
        $("#searchForm").form("reset");
        this.search();
    },
    submit:function (){
        var params = $("#ffff").toJson();
        //获取到左侧表格所有行
        var leftRows = $("#selectedPermissions").datagrid("getRows");
        for(var i=0;i<leftRows.length;i++){
            params["permissions["+i+"].id"] = leftRows[i].id;
        }
        $.postJSON("/role/save.do",params,function(data){
            if(data.status == 200){
                $.messager.alert('消息',data.msg,"info");
                //关闭模态窗口
                $('#win').window('close');  // close a window
                //重新刷新表格的数据
                $("#dg").datagrid('load',{
                    pageNo:1,
                    pageSize:10
                });
                $("#dg").datagrid("getPager").pagination({
                    pageNumber:1,
                    pageSize:10
                });
            }else{
                $.messager.alert('错误',data.msg,"error");
            }
        });
    },
    cancel:function () {
        $("#ffff").form("reset");
        $("#win").window("close");
    }
};