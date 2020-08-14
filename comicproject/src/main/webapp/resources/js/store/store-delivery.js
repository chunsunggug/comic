 $( document ).ready( function() {
        $( '.allcb' ).click( function() {
          $( '.cb' ).prop( 'checked', this.checked );
        } );
      } );
 
 $('#ok').click( function() {
	var items = $('.cb:checked');
	var values = new Array();
	var type = $('#type option:selected').val();
	var step = $('#step option:selected').val();
	var state = type.concat(step);
	var replaceurl = "/comic/store/deliverymanage.do?state=".concat(state);
	
	items.each(function(index,item){
		values.push(item.value);
	});
	
	$.ajax({
		url : "/comic/store/breqok.do",
		type : "post",
		data : JSON.stringify(values),
		dataType : "text",
		success : function(result){
			if(result > 0){
				alert("완료되었습니다");
				location.replace(replaceurl);
			}else{
				alert("실패하였습니다");
			}
		}
	});
 });
 
 $('#view').click( function() {
	 var type = $('#type option:selected').val();
	 var step = $('#step option:selected').val();
	 var state = type.concat(step);
	 var url = "/comic/store/deliverymanage.do?state=".concat(state);
	
	 location.replace(url);
 });