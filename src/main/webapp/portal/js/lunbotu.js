$(document).ready(function(){
    var page = 0;
    var len = $(".img-box a").length;
    var stop = false;
    var t;
    /**定义轮播函数**/
    function slide(){
        if(!stop){
            if(page == len){
                page = 0;
            }
            $(".page-con li").eq(page).siblings().css({"background":"#3e3e3e"});
            $(".img-box a").eq(page).siblings().fadeOut(1000);
            $(".page-con li").eq(page).css({"background":"#ccc"});
            $(".img-box a").eq(page).fadeIn(1000);
            page++;
        }
        t=setTimeout(slide,1500);
    }
    slide();
    /**悬浮到轮播图  **/
    $(".focus").hover(function(){
        stop = true;//停止轮播
    },function(){
        stop = false;//鼠标离开 开始轮播
    });
    /**按钮点击**/
    $(".page-con li").click(function(){
        clearTimeout(t);//清除定时
        page = $(this).index();//将page 设置成当前点击按钮的 下标值
        slide();
    });

    <!--下边轮播的新闻-->
    var animateEnd = 1;
    var $index=$(".text_l li").index()+1;
    //var $dex=$(this).$index;
    $(".ck_left").on('click',function() {
        //alert($dex)
        nextscroll()
        $(".text_c").html($index)
    });
    function nextscroll() {
        var vcon = $(".text_l");
        var offset = ($(".text_l li").width()) * -1;
        vcon.stop().animate({left: offset}, "slow", function() {
            var firstItem = $(".text_l ul li").first();
            vcon.find("ul").append(firstItem);
            $(this).css("left", "0px");
        })
    };
    $(".ck_right").on('click',function() {
        $(".text_c").html($index)
        var vcon = $(".text_l ");
        var offset = ($(".text_l li").width() * -1);
        var lastItem = $(".text_l ul li").last();
        vcon.find("ul").prepend(lastItem);
        vcon.css("left", offset);
        vcon.animate({left: "0px"},"slow")
    });
})