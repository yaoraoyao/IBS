$.extend({
	postJSON : function(url, params, fn){
		$.post(url, params, function(data){
			fn(data);
		},"json");
	}
});
$.fn.extend({
	toJson: function(){
		var paramArray = $(this).serializeArray();  
        /*请求参数转json对象*/
        var jsonObj = {}; 
        //each是jQuery的foreach循环
        $(paramArray).each(function(){  
        	jsonObj[this.name] = this.value;  
        });  
        // json对象
        return jsonObj;
	}
});
$(function () {
    //给所有的拥有data-method属性的按钮绑定点击事件
    $("[data-method]").click(function () {
        //获取当前按钮的data-method属性值【当做一个方法名称】
        var methodName = $(this).data("method");	//等同于  $(this).attr("data-method")
        window.methods[methodName]();
    });
});