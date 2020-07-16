
function show_contents() {

	$.ajax({
		url : "/comic/book/searchmore.do",
		dataType: "json",
		success : function(json_object) {
			var container = document.getElementById("contents_container");
			var more_button = document.getElementById("btn_more_load");
			var contents = json_object.documents;
			var meta = json_object.meta;
			
			for(var i=0; i < contents.length; i++){
				console.log('isbn check : '+contents[i].isbn);
				var div = document.createElement("div");
				div.setAttribute("class", "col-6 col-md-3 col-lg-2");
				Console.log("isbn check : "+contents[i].isbn);
				var img = document.createElement("img");
				img.setAttribute("class", "img-thumbnail img-responsive" );
				img.setAttribute("src", contents[i].thumbnail );

				var h5 = document.createElement("h5");
				h5.innerHTML = contents[i].title;

				div.appendChild(img);
				div.appendChild(h5);

				container.appendChild(div);
			}
			
			if( meta.is_end ) more_button.classList.add("sr-only");
		}
	})
}