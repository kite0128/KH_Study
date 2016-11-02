/**
 * http://usejsdoc.org/
 */

var http=require('http');
var url=require('url'); 
var fs=require('fs');



//웹 서버 생성
var server = http.createServer(function(req, resp){
	var userpath = __dirname + req.url;
	//C:\study\workspace_spring\node02_outer_module\part05_http_fs/
	//console.log(userpath);
	if(req.url=="/"){
		resp.writeHeader(200,{'Content-Type':'text/html;charset=UTF-8'});
        resp.end('<html><body><h1>Hello Image</h1><img src="images/aa.jpg"/></body></html>');
		return;
	}

	//C:\study\workspace_spring\node02_outer_module\part05_http_fs/images/aa.jpg
	//console.log(userpath);
	
	var userpath = __dirname + req.url;
	
	//해당 이미지 파일이 존재하는지 확인
	fs.exists(userpath,function(exists){
			//이미지가 존재하면
			if(exists){
				resp.writeHead(200, {'Content-Type':'image/*'});
				//이미지 파일을 스트림으로 읽고 클라이언트에 응답한다.
				fs.createReadStream(userpath).pipe(resp);
			}else{
				//이미지가 존재하지 않으면
				resp.statusCode=404;
				resp.end('Not Found');
			}
	});
});


//웹 서버 실행
server.listen(3000,function(){
	console.log('server running at http://127.0.0.1:3000');
});
