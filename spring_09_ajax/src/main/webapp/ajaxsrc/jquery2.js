$(document).ready(function(){
	$('#btn').on('click',process);
});

function process(){
	$.ajax({
		type:'POST',
		dataType:'text',
		url:'loginPro.do',
		data:'id='+$('#id').val()+"&pass="+$('#pass').val(),
		success:function(res){
			$('div').html(res);
		}
	});
}//end process()