<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/javascript">
	var urno = '';
	var fileList = '';

	$(document).ready(	function() {
						$('#modifyModal').addClass('modifyHide');
						$(document)
								.on(
										'click',
										'.timeline button',
										function() {

											if ($(this).text() == 'delete') {
												$
														.ajax({

															dataType : 'json',
															url : 'replyDelete.do?bno=${boardDTO.bno}&rno='
																	+ $(this)
																			.attr(
																					'id'),
															success : viewMessage,
														})
											} else if ($(this).text() == 'update') {
												urno = $(this).prop('id');
												$('#modifyModal').removeClass(
														'modifyHide');
												$('#modifyModal').addClass(
														'modifyShow');
											}
										})
						$('#replyAddBtn').on('click', reply_list);
						$('#btnClose').on('click', reply_update_close);
						$('#btnModify').on('click', reply_update_send);
						$('#boxdel').on('click', function() {
							$('.fileDrop').empty();
						})
						//내PC 첨부파일 시작

						var userfile = '';
						$('#userpc')
								.on(
										'click',
										function() {
											var userfile = $('<input type="file" id="userfile"/>');
											userfile.click();//강제 이벤트 발생
											userfile
													.change(function(e) {
														console.log($(
																userfile[0])
																.val());
														var partname = $(
																userfile[0])
																.val()
																.substring(
																		$(
																				userfile[0])
																				.val()
																				.lastIndexOf(
																						"\\") + 1);
														str = "<p style='background-color:skyblue'>"
																+ partname
																+ "</p>";
														$('.fileDrop').append(
																str);
														var dataList = userfile[0].files[0];//userfile[0]만 넘기게되면 objeac로 넘어가기때문에 
														filelist = dataList;
													})
										});
						//내PC 첨부파일 끝/////

						///////첨부파일 드래그 시작

						var obj = $('.fileDrop');
						obj.on('dragenter', function(e) {
							e.preventDefault();
							$(this).css('border', '2px solid #0B85A1')
						});

						obj.on('dragover', function(e) {
							e.preventDefault();
						});

						obj.on('drop', function(e) {
							e.preventDefault();

							//input타입이 파일이 아니면 아래작업을 해줘야 된다.
							var files = e.originalEvent.dataTransfer.files;
							//fileList = files[0];
							for (var i = 0; i < files.length; i++) {
								str = "<p style='background-color:yellow'>"
										+ files[i].name + "</p>";
								obj.append(str);
								fileList = files[i];
							}
							//첩부파일이 여러개일때 for문으로  출력할수 있다.
							//한개일때는 배열로 안하고 fileList=files[0];
						})

						////////////첨부파일 드래그 끝

					});//document.reday

	Handlebars.registerHelper("newDate", function(timeValue) {
		var dateObj = new Date(timeValue);
		var year = dateObj.getFullYear();
		var month = dateObj.getMonth() + 1;
		var date = dateObj.getDate();
		return year + "/" + month + "/" + date;
		//return new Date(timeValue);	
	});

	Handlebars.registerHelper("newUpload", function(rno, uploadFile) {
		if (uploadFile != null) {

			var filename = uploadFile.substring(uploadFile.indexOf("_") + 1);
			var ddd = '<a href="download.do?rno=' + rno + '">'
					+ uploadFile.substring(uploadFile.indexOf("_") + 1)
					+ '</a>';

			return new Handlebars.SafeString(ddd);
		} else {
			return "첨부파일 없음";
		}

	});

	function reply_list() {

		var form_data = new FormData();
		form_data.append('bno', '${boardDTO.bno}');
		form_data.append('replyer', $('#newReplyWriter').val());
		form_data.append('replytext', $('#newReplyText').val());
		//if ($('#filename')[0].files[0] != undefined) {
		//form_data.append('filename', $('#filename')[0].files[0]);
		//}
		if (fileList != '') {
			form_data.append('filename', fileList);
		}
		$.ajax({
			type : 'POST',
			dataType : 'json',
			contentType : false,
			enctype : 'multipart/form-data',
			// multipart/form-data일경우에는 contentType, processData를 false해야된다.(어플리캐이션 ->)
			// 그이유는 어플리케이션방식이 true이기때문에 방식변경을 위해서
			processData : false,
			url : 'replyInsertList.do',
			data : form_data,
			success : viewMessage,
		});

		$('#newReplyWriter').val('');
		$('#newReplyText').val('');
		//$('#filename').val('');
		$('.fileDrop').empty();
		fileList = '';
	}

	function reply_update_send() {

		var update_data = new FormData();
		update_data.append('bno', '${boardDTO.bno}');
		update_data.append('rno', urno);
		update_data.append('replytext', $('#updateReplyText').val());
		if ($('#refilename')[0].files[0] != undefined) {
			update_data.append('filename', $('#refilename')[0].files[0]);
		}
		$.ajax({
			type : 'POST',
			dataType : 'json',
			contentType : false,
			enctype : 'multipart/form-data',
			processData : false,
			url : 'replyUpdate.do',
			data : update_data,
			success : viewMessage,

		});
		$('#updateReplyText').val('');
		$('#modifyModal').removeClass('modifyShow');
		$('#modifyModal').addClass('modifyHide');

	}

	function reply_update_close() {
		$('#modifyModal').removeClass('modifyShow');
		$('#modifyModal').addClass('modifyHide');
		urno = '';
	}

	function viewMessage(res) {
		$('.timeline').empty();
		$('.timeline')
				.append(
						'<li class="time-label" id="repliesDiv"><span class="bg-green"> Replies List <small id="replycntSmall"> ['
								+ res.length + '] </small></span>');
		//
		$
				.each(
						res,
						function(index, value) {
							/* $('.timeline').append('<p>'+ value.replyer+ '</p>');
							$('.timeline').append('<p>'+ value.replytext+ '</p>');
							$('.timeline').append('<p>'+ value.regdate+ '</p>');
							$('.timeline').append('<p><button id="${replyDTO.rno }">delete</button><button id="${replyDTO.rno }">update</button></p>');
							 */
							var source = "<li class='time_sub' id='{{rno}}'><p>{{replyer}}</p><p>{{replytext}}</p><p>{{newDate regdate}}</p><p>{{newUpload rno rupload}}</p><p><button id='{{rno}}'>delete</button><button id='{{rno}}'>update</button></p></li>";
							var template = Handlebars.compile(source);
							$('.timeline').append(template(value));
						});
	}
</script>
<style type="text/css">
.modifyShow {
	display: block;
	position: absolute;
	top: 150px;
	left: 200px;
	width: 400px;
	height: 150px;
	z-index: 1000;
	border: 1px solid black;
	background-color: yellow;
	text-align: center;
}

.modifyHide {
	visibility: hidden;
	width: 0px;
	height: 0px;
}

.fileDrop {
	width: 200px;
	height: 50px;
	border: 1px dotted blue;
}
</style>
<body>
	<div class="wrap">
		<div class="box-body">
			<div class="form-group">
				<label for="exampleInputEmail1">Title</label> <input type="text"
					name='title' class="form-control" value="${boardDTO.title}"
					readonly="readonly">
			</div>
			<div class="form-group">
				<label for="exampleInputPassword1">Content</label>
				<textarea class="form-control" name="content" rows="3"
					readonly="readonly">${boardDTO.content}</textarea>
			</div>
			<div class="form-group">
				<label for="exampleInputEmail1">Writer</label> <input type="text"
					name="writer" class="form-control" value="${boardDTO.writer}"
					readonly="readonly">
			</div>
		</div>

		<div class="box-footer">
			<button type="submit" class="btn btn-warning" id="modifyBtn">Modify</button>
			<button type="submit" class="btn btn-danger" id="removeBtn">REMOVE</button>
			<button type="submit" class="btn btn-primary" id="goListBtn">GO
				LIST</button>
		</div>
		<hr />

		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">ADD NEW REPLY</h3>

			</div>
			<div class="box-body">
				<label for="exampleInputEmail1">Writer</label> <input
					class="form-control" type="text" placeholder="USER ID"
					id="newReplyWriter"> <label for="exampleInputEmail1">Reply
					Text</label> <input class="form-control" type="text"
					placeholder="REPLY TEXT" id="newReplyText">
				<!--  <label
					for="filename">Upload</label> <input type="file" id="filename"
					name="filename" /> -->
				<div>
					<span id="userpc" style="cursor: pointer">내PC</span>
				</div>
				<div class="fileDrop"></div>

			</div>
			<!-- /.box-body -->
			<div class="box-footer">
				<button type="button" class="btn btn-primary" id="replyAddBtn">ADD
					REPLY</button>
				<button id="boxdel">delete</button>

			</div>
		</div>


		<!-- The time line -->
		<ul class="timeline">
			<!-- timeline time label -->
			<li class="time-label" id="repliesDiv"><span class="bg-green">
					Replies List <small id='replycntSmall'> [
						${fn:length(boardDTO.replyList)} ] </small>
			</span></li>

			<c:forEach items="${boardDTO.replyList}" var="replyDTO">
				<li class="time_sub" id="${replyDTO.rno}">
					<p>${replyDTO.replyer}</p>
					<p>${replyDTO.replytext }</p>
					<p>
						<fmt:formatDate dateStyle="short" pattern="yyyy/MM/dd"
							value="${boardDTO.regdate}" />

					</p>
					<p>
						<c:if test="${!empty replyDTO.rupload}">
							<a href="download.do?rno=${replyDTO.rno}"> <%--substringbefor 는 첨부터 --%>
								${fn:substringAfter(replyDTO.rupload,"_")}
							</a>
						</c:if>
						<c:if test="${empty replyDTO.rupload }">
							<c:out value="첨부파일 없음" />
						</c:if>
					</p>
					<p>
						<button id="${replyDTO.rno }">delete</button>
						<button id="${replyDTO.rno }">update</button>
					</p>




				</li>
			</c:forEach>
		</ul>


		<div class='text-center'>
			<ul id="pagination" class="pagination pagination-sm no-margin ">

			</ul>
		</div>
		<!-- Modal -->
		<div id="modifyModal">

			<p>
				<label for="updateReplyText">Reply Text</label> <input
					class="form-control" type="text" placeholder="REPLY TEXT"
					id="updateReplyText">
			</p>

			<p>
				<label for="filename">Upload</label> <input type="file"
					id="refilename" name="filename" />
			</p>

			<p>
				<button id="btnModify">Modify</button>
				<button id="btnClose">Close</button>

			</p>
		</div>

	</div>
</body>
</html>