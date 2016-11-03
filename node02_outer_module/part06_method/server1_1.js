/**
 * http://usejsdoc.org/
 */

var http = require('http');
var fs = require('fs');

var server = http.createServer(function(req, resp){
	//동기화:요청이 들어왔을 때 서버에서 응답이 올 때까지 클라이언트에서 다른 동작을 못함
	//비동기화:요청했을 때 응답과는 상관없이 클라이언트가 동작가능
	if(req.method=='GET'){
		fs.readFile('part06_method/main.html',function(error, data){
			resp.writeHead(200,{'Content-Type':'text/html;charset=UTF-8'});
			resp.end(data);
		});
	}else if(req.method=='POST'){
		var res='';
		//'data'이벤트가 발생이 되면 res변수에 parameter값을 추가한다.
		req.on('data',function(data){
			res+=data;
		});
		
		//client 요청이 끝났으면..
		req.on('end',function(){
			console.log(typeof(res));//string
			resp.writeHead(200,{'Content-Type':'text/html;charset=UTF-8'});
			resp.end(res);
		})
	}
});//end server

//웹 서버 실행
server.listen(3000,function(){
	console.log('server running at http://127.0.0.1:3000');
});