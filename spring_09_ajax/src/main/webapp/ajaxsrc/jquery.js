$(document).ready(function(){
	$('#btn').on('click',process);
});

function process(){
	$.ajax({
		type:'GET',//메소드 get,post
		dataType:'text',//응답형식
		url:'customer.do?name='+$('#name').val(),//get방식이기 때문에 파라미터를 넘기기 위해 url 추가(요청주소)
		success:function(res){
			$('div').html(res);
		}
	});
}//end process