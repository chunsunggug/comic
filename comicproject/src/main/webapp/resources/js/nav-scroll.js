// add padding top to show content behind navbar

$('body').css('padding-top', $('.smart-scroll').outerHeight() + 'px');


// detect scroll top or down
if ($('.smart-scroll').length > 0) { // check if element exists
	console.log($('.smart-scroll').length);

    var last_scroll_top = 0;
    $(window).on('scroll', function() {
        scroll_top = $(this).scrollTop();
    	console.log("scrolltop:"+scroll_top);
    	console.log("lastltop:"+last_scroll_top);

        if(scroll_top < 600) {
        	$('.smart-scroll').removeClass('scrolled-down').addClass('scrolled-up');
        	
        }
        else {
        	if(last_scroll_top>scroll_top){
        		$('.smart-scroll').removeClass('scrolled-down').addClass('scrolled-up');
        	}else{
                $('.smart-scroll').removeClass('scrolled-up').addClass('scrolled-down');
        	}
        }
        last_scroll_top = scroll_top;
    });
}

//내리는건 스크롤이 크고 올리는건 라스트가 크다