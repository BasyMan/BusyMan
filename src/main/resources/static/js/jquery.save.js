$(function() {
	$("#submitUserForm").submit(function(e) {
		e.preventDefault();
		var frm = $("#submitUserForm");
		var data = {};
		$.each(this, function(i, v){
			var input = $(v);
			data[input.attr("name")] = input.val();
			delete data["undefined"];
		});
		saveRequestedData(frm, data, "user");
	});

});
$(function() {
	$("#submitTaskForm").submit(function(e) {
		e.preventDefault();
		var frm = $("#submitTaskForm");
		var data = {};
		$.each(this, function(i, v){
			var input = $(v);
			data[input.attr("name")] = input.val();
			delete data["undefined"];
		});
		saveRequestedData(frm, data, "task");
	});

});

function saveRequestedData(frm, data, type) {
	$.ajax({
		contentType:"application/json; charset=utf-8",
		type:frm.attr("method"),
		url:frm.attr("action"),
		dataType:'json',
		data:JSON.stringify(data),
		success:function(data) {
			if(data.status == "success") {
				toastr.success(data.message, data.title, {
					closeButton:true
				});
				refresh(type);
				//fetchList(type);
			} else {
				toastr.error(data.message, data.title, {
					allowHtml:true,
					closeButton:true
				});
			}
		}
	});
}