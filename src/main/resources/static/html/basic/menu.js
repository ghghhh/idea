var change=false;
var type=$("#menu_type")
$('#menu_manage').tree({
		onClick : function(node) {
			if(node.menuType=="true"){
				node.menuType="叶子节点"
			}else{
				node.menuType="根节点"
			}
			  $('#menu_info').form('load',node);
			  change=false;
			   if(node.state=="closed"){
			        $('#menu_manage').tree('expand',node.target);
			    }else{
			        $('#menu_manage').tree('collapse',node.target);
			    }
		}
	})

function a(){
	if(change){
		$("#menu_info").form('submit',{
			url:path+'/menu/update'
		})
		change=false;
	}else{
		alert("没有改动")
	}
	var n=$('#menudd').val();
	alert(n);
}

function aa(){
	$("#menu_info").submit();
}
$("#menu_info").form({
	url:'/cs/menu/update',
	onChange: function(target){
		change=true;
	},
	success:function(data){
		if(!(data instanceof Object)){
    		data=JSON.parse(data);
    	}  
		alert(data.msg)
	}
})
