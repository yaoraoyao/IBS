$(function(){
    $("#dg").datagrid({
        url:"/department/page.do",
        method:"post",
        striped:true,		//是否显示斑马线效果
        nowrap:true,		//如果列的字符串太长，默认是要换行显示，这个属性设置为true表示不换行
        pagination:true,	//是否显示分页工具栏
        rownumbers:true,	//是否在表格左侧显示行号[主键id字段的值往往是不连续的，所以搞了一个行号从1开始显示]
        //singleSelect:true,	//是否只能单选，默认可以多选
        queryParams:{},		//发送请求加载数据的时候的额外请求参数
        frozenColumns:[[{field:'abcdefg',checkbox:true}]],	//在表格左侧显示多选框，全选、全不选
        columns:[[     
            {field:"id",title:'id',width:100,hidden:true},
            {field:"name",title:'name',width:100,align:"center"},
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
        $("#departmentid").val("");
        //弹出模态窗口
        $('#win').window('open');  // open a window
    },
    edit:function (){
        //重置一下表单
        $("#ffff").form("reset");
        //手动再清空一下employeeid
        $("#departmentid").val("");
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
        $.getJSON("/department/findOne.do",{id:row.id},function(data){
            //表单回填
            $("#ffff").form("load", data);
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
                $.getJSON("/department/delete.do",{ids:ids.join(",")},function(data){
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
        $.postJSON("/department/save.do",params,function(data){
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