
function show_contents() {

	$.ajax({
		url : "/comic/book/searchmore.do",
		dataType: "json",
		success : function(json_object) {
			var container = document.getElementById("contents_container");

			for(var i=0; i < json_object.length; i++){
				var div = document.createElement("div");
				div.setAttribute("class", "col-6 col-md-3 col-lg-2");

				var img = document.createElement("img");
				img.setAttribute("class", "img-thumbnail img-responsive" );
				img.setAttribute("src", json_object[i].thumbnail );

				var h5 = document.createElement("h5");
				h5.innerHTML = json_object[i].title;

				div.appendChild(img);
				div.appendChild(h5);

				container.appendChild(div);
			}
		}
	})
}