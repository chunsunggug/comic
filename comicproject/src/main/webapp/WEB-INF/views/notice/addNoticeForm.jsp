<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="/comic/resources/js/jquery-1.11.0.min.js"></script>
<style>
.upload-btn-wrapper {
	position: relative;
	overflow: hidden;
	display: inline-block;
}

.upload-btn {
	border: none;
	color: #333;
	background-color: white;
	/* padding: 8px 20px; */
	border-radius: 8px;
	font-size: 14px;
}

.upload-btn-wrapper input[type=file] {
	font-size: 100px;
	position: absolute;
	left: 0;
	top: 0;
	opacity: 0;
}

#fileDragDesc {
	width: 100%;
	height: 100%;
	margin-left: auto;
	margin-right: auto;
	padding: 5px;
	text-align: center;
	line-height: 10;
	vertical-align: middle;
}
</style>

<script src="http://code.jquery.com/jquery-latest.js"></script>

<script type="text/javascript">
            $(document).ready(function() {
                $("#input_file").bind('change', function() {
                	selectFile(this.files);
                	//readURL(this,this.files);
                	
                	//this.files[0].size gets the size of your file.
                    //alert(this.files[0].size);
                });
            });
        
            // 파일 리스트 번호
            var fileIndex = 0;
            // 등록할 전체 파일 사이즈
            var totalFileSize = 0;
            // 파일 리스트
            var fileList = new Array();
            // 파일 사이즈 리스트
            var fileSizeList = new Array();
            // 등록 가능한 파일 사이즈 MB
            var uploadSize = 50;
            // 등록 가능한 총 파일 사이즈 MB
            var maxUploadSize = 100;
    		
            $(function() {
                // 파일 드롭 다운
                fileDropDown();
            });
    
            // 파일 드롭 다운
            function fileDropDown() {
                var dropZone = $("#dropZone");
                //Drag기능 
                dropZone.on('dragenter', function(e) {
                    e.stopPropagation();
                    e.preventDefault();
                    // 드롭다운 영역 css
                    dropZone.css('background-color', '#E3F2FC');
                });
                dropZone.on('dragleave', function(e) {
                    e.stopPropagation();
                    e.preventDefault();
                    // 드롭다운 영역 css
                    dropZone.css('background-color', '#FFFFFF');
                });
                dropZone.on('dragover', function(e) {
                    e.stopPropagation();
                    e.preventDefault();
                    // 드롭다운 영역 css
                    dropZone.css('background-color', '#E3F2FC');
                });
                dropZone.on('drop', function(e) {
                    e.preventDefault();
                    // 드롭다운 영역 css
                    dropZone.css('background-color', '#FFFFFF');
    
                    var files = e.originalEvent.dataTransfer.files;
                    if (files != null) {
                        if (files.length < 1) {
                            /* alert("폴더 업로드 불가"); */
                            console.log("폴더 업로드 불가");
                            return;
                        } else {
                            selectFile(files)
                        }
                    } else {
                        alert("ERROR");
                    }
                });
                
            }
    
            // 파일 선택시
            function selectFile(fileObject) {
                var files = null;
    
                if (fileObject != null) {
                    // 파일 Drag 이용하여 등록시
                    files = fileObject;
                } 
    
                // 다중파일 등록
                if (files != null) {
                    
                    if (files != null && files.length > 0) {
                        $("#fileDragDesc").hide(); 
                        $("fileListTable").show();
                    } else {
                        $("#fileDragDesc").show(); 
                        $("fileListTable").hide();
                    }
                    
                    for (var i = 0; i < files.length; i++) {
                        // 파일 이름
                        var fileName = files[i].name;
                        var fileNameArr = fileName.split("\.");
                        // 확장자
                        var ext = fileNameArr[fileNameArr.length - 1];
                        
                        var fileSize = files[i].size; // 파일 사이즈(단위 :byte)
                        console.log("fileSize="+fileSize);
                        if (fileSize <= 0) {
                            console.log("0kb file return");
                            return;
                        }
                        
                        var fileSizeKb = fileSize / 1024; 	   /* 파일 사이즈(단위 :kb) */
                        var fileSizeMb = fileSizeKb / 1024;    /* 파일 사이즈(단위 :Mb) */
                        
                        var fileSizeStr = "";
                        if ((1024*1024) <= fileSize) {    // 파일 용량이 1메가 이상인 경우 
                            console.log("fileSizeMb="+fileSizeMb.toFixed(2));
                            fileSizeStr = fileSizeMb.toFixed(2) + " Mb";
                        } else if ((1024) <= fileSize) {
                            console.log("fileSizeKb="+parseInt(fileSizeKb));
                            fileSizeStr = parseInt(fileSizeKb) + " kb";
                        } else {
                            console.log("fileSize="+parseInt(fileSize));
                            fileSizeStr = parseInt(fileSize) + " byte";
                        }
    
                        /* if ($.inArray(ext, [ 'exe', 'bat', 'sh', 'java', 'jsp', 'html', 'js', 'css', 'xml' ]) >= 0) {
                            // 확장자 체크
                            alert("등록 불가 확장자");
                            break; */
                        if ($.inArray(ext, [ 'hwp', 'doc', 'docx', 'xls', 'xlsx', 'ppt', 'pptx', 'txt', 'png', 'pdf', 'jpg', 'jpeg', 'gif', 'zip' ]) <= 0) {
                            // 확장자 체크
                            /* alert("등록이 불가능한 파일 입니다.");
                            break; */
                            alert("등록이 불가능한 파일 입니다.("+fileName+")");
                        } else if (fileSizeMb > uploadSize) {
                            // 파일 사이즈 체크
                            alert("용량 초과\n업로드 가능 용량 : " + uploadSize + " MB");
                            break;
                        } else {
                            
                        	if(fileIndex>=5){
                        	alert("파일은 최대 5개까지 등록 가능합니다.");
                        	}else{
                        	
                        	// 전체 파일 사이즈
                            totalFileSize += fileSizeMb;
    
                            // 파일 배열에 넣기
                            fileList[fileIndex] = files[i];
    
                            // 파일 사이즈 배열에 넣기
                            fileSizeList[fileIndex] = fileSizeMb;
    
                            // 업로드 파일 목록 생성
                            addFileList(fileIndex, fileName, fileSizeStr,fileObject);
    
                            // 파일 번호 증가
                            fileIndex++;
                        	}
                        }
                    }
                } else {
                    alert("ERROR");
                }
            }
    
            // 업로드 파일 목록 생성
            function addFileList(fIndex, fileName, fileSizeStr,files) {
                /* if (fileSize.match("^0")) {
                    alert("start 0");
                } */
    			if(fileName.length>8){
    				fileName = fileName.substring(0, 8)+"...";
    			}
                var html = "";
                html += "<tr id='fileTr_" + fIndex + "'>";
                html += "    <td id='dropZone' class='left' >";
                html += fileName + " (" + fileSizeStr +") " 
                        //+ "<a href='#' onclick='deleteFile(" + fIndex + "); return false;' class='btn small bg_02'> 삭제</a>"
                        
                        + "<input value='삭제' type='button' href='#' onclick='deleteFile(event," + fIndex + "); return false;'>"
                html += "    </td>"
                html += "</tr>"
    		
                
                //미리보기
                var img = "";
                img +="<img src='' id='mainimg"+ fIndex+"' width='70%' style='display: block; margin: 10px auto;'>";
                
                $('#img-group').append(img);
                
                var reader = new FileReader();
             	  
             	  reader.onload = function (e) {
             		  console.log("file index : "+fIndex);
             		  console.log("typeof fileindex : "+typeof(fIndex));
             		  
               		  
             			$('#mainimg'+fIndex).attr('src', e.target.result);  
             		
  	           	}
  	           	 reader.readAsDataURL(files[fIndex]);
                
  	           	 $('#fileTableTbody').append(html);
                
                
                
                
                
            }
    
            
            // 업로드 파일 삭제
            function deleteFile(event,fIndex) {
                console.log("deleteFile.fIndex=" + fIndex);
                // 전체 파일 사이즈 수정
                totalFileSize -= fileSizeList[fIndex];
    
                // 파일 배열에서 삭제
                delete fileList[fIndex];
    
                // 파일 사이즈 배열 삭제
                delete fileSizeList[fIndex];
    
                // 업로드 파일 테이블 목록에서 삭제
                $("#fileTr_" + fIndex).remove();
                $('#mainimg'+ fIndex).remove();  
                console.log("totalFileSize="+totalFileSize);
                
                if (totalFileSize > 0) {
                    $("#fileDragDesc").hide(); 
                    $("fileListTable").show();
                } else {
                    $("#fileDragDesc").show(); 
                    $("fileListTable").hide();
                }
                event.stopPropagation();
            }
    
            // 파일 등록
            function uploadFile() {
                // 등록할 파일 리스트
                var uploadFileList = Object.keys(fileList);
    
                // 파일이 있는지 체크
                if (uploadFileList.length == 0) {
                    // 파일등록 경고창
                    if(confirm("이미지 없이 등록하겠습니까?")){
                        console.log('true');	
                    }else{
                    	return;
                    }
                }
    
                // 용량을 100MB를 넘을 경우 업로드 불가
                if (totalFileSize > maxUploadSize) {
                    // 파일 사이즈 초과 경고창
                    alert("총 용량 초과\n총 업로드 가능 용량 : " + maxUploadSize + " MB");
                    return;
                }
    
                if (confirm("등록 하시겠습니까?")) {
                    // 등록할 파일 리스트를 formData로 데이터 입력
                    var form = $('#uploadForm');
                    var formData = new FormData(form);
                /*     for (var i = 0; i < uploadFileList.length; i++) {
                        formData.append('files', fileList[uploadFileList[i]]);
                    }
    
                    $.ajax({
                        url : "/comic/addNotice.do",
                        data : formData,
                        type : 'post',
                        enctype : 'multipart/form-data',
                        dataType : 'json',
                        cache : false,
                        success : function(data) {
                            if (data.length > 0) {
                                alert("성공");
                                location.replace('/comic/index.do');
                            } else {
                                alert("실패");
                                location.replace('/comic/index.do');
                            }
                        }
                    }); */
                }
            }
        </script>
<main id="all">

	<div id="content">
		<div class="container">

			<div class="col-md-12">

				<ul class="breadcrumb">
					<li><a href="#">Home</a></li>
					<li>게시판</li>
				</ul>

			</div>

			<div class="col-md-12">
				<div class="box">


					<h1>게시판 글쓰기</h1>
					<p class="lead">대여점 또는 도서의 대한 리뷰를 남겨주세요.</p>
					<p class="text-muted">자유롭게 기재해주세요.</p>
					<form name="uploadForm" id="uploadForm" action="addNotice.do" method="post" enctype="multipart/form-data">
						<div class="row">
							<div class="col-sm-8">
								<div class="form-group">
									<label for="title">제목</label> <input type="text" name="title"
										class="form-control" id="title" required="required">
								</div>
							</div>
							<div class="col-sm-2">
								<div class="form-group">
									<label for="writer">작성자 (익명 <input type="checkbox"
										name="noname" value="Y" style="position: relative; top: 1px;"></input>
									</label> )<input type="text" name="writer" class="form-control"
										id="writer" value="name 만약 익명 체크 시 게시물 번호를 주자"
										readonly="readonly">
								</div>
							</div>
							<div class="col-sm-2">
								<div class="form-group">
									<label for="writer">카테고리</label> <input type="text"
										class="form-control" id="notice" value="게시판"
										readonly="readonly">
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-sm-12">

								<div class="upload-btn-wrapper">
									<input type="file" id="input_file"
										accept="image/png,image/jpeg,image/jpg" multiple="multiple"
										style="height: 100%;" name="img" />
									<button class="upload-btn" style="padding: 0px;">이미지
										선택</button>
									(최대 100MB 이내 5장까지 업로드 가능)
								</div>
								<br />


								<div id="dropZone"
									style="width: 100%; height: 150px; border-style: solid; border-color: #ccc;"
									onclick="$('#input_file').click();">
									<div id="fileDragDesc">이미지를 선택 또는 드래그 해주세요.</div>


									<table id="fileListTable" width="100%" border="0px">
										<tbody id="fileTableTbody">

										</tbody>
									</table>
								</div>


							</div>
						</div>

						<div class="row">
							<div class="col-sm-12" id="img-group">
							<br>
							<!-- 이미지 들어갈 자리 -->
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<label for="content">내용</label>
									<textarea name="content" rows="10" cols="5"
										class="form-control" id="content" required></textarea>
								</div>
							</div>
							<div class="col-sm-12 text-center">
								<button type="submit" class="btn btn-primary"
									onsubmit="uploadFile(); return false;">
									<i class="fa fa-save"></i> 등록
								</button>
							</div>
						</div>
						<!-- /.row -->
					</form>
				</div>
			</div>

		</div>
		<!-- /.container -->
	</div>
	<!-- /#content -->