
function show_contents() {

	$.ajax({
		url : "/comic/searchmore.do",
		dataType: "json",
		success : function(json_object) {
			var container = document.getElementById("contents_container");
			var more_button = document.getElementById("btn_more_load");
			var contents = json_object.documents;
			var is_end = json_object.meta.is_end;
			
			for(var i=0; i < contents.length; i++){
				
				var div = document.createElement("div");
				div.setAttribute("class", "col-6 col-md-3 col-lg-2");
				
				var a = document.createElement("a");
				a.setAttribute( "href", "https://www.naver.com");
				
				var img = document.createElement("img");
				img.setAttribute("class", "img-thumbnail img-responsive" );
				
				if( contents[i].thumbnail != "")
					img.setAttribute("src", contents[i].thumbnail );
				else
					img.setAttribute("src", "http://localhost:8080/comic/resources/img/book/unknown_cover.png" );
				
				var h5 = document.createElement("h5");
				h5.innerHTML = contents[i].title;

				a.appendChild(img);
				a.appendChild(h5);
				div.appendChild(a);
				
				container.appendChild(div);
			}
			
			if( is_end ) more_button.classList.add("sr-only");
		}
	})
}