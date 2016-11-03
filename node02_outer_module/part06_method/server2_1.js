/**
 * http://usejsdoc.org/
 */

//get방식일 때는 url을 가져와서 처리.
//POST방식일 때는 이벤트를 걸어서 가져와서 처리
var http = require('http');
var fs = require('fs');
var url = require('url');
var querystring=require('querystring');


var server = http.createServer(function(req, resp){
	//	/
	//	/list?data_1=erw&data_b=r
	
	var parsed = url.parse(req.url,true);
	
	if(parsed.pathname=='/'){
		fs.readFile('part06_method/main2.html',function(error, data){
			resp.writeHead(200,{'Content-Type':'text/html;charset=UTF-8'});
			resp.end(data);
		});
	}else if(parsed.pathname=='/list'){
		//data_1=erw&data_b=r
		
		var aParam = parsed.query.data_a;
		var bParam = parsed.query.data_b;
		
		resp.writeHead(200,{'Content-Type':'text/html;charset=UTF-8'});
		resp.end('<div><p>' + aParam + '</p><p>' + bParam + '</p></div>');
		
	}
});//end server

//웹 서버 실행
server.listen(3000,function(){
	console.log('server running at http://127.0.0.1:3000');
});