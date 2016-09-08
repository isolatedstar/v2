/**
 * Created by MyPC on 2016/8/11.
 */
/***************** header down *********/
$(function(){
    /**********header 连续点击事件*************/
    $("#down").click(function(){
        $("#header").toggle(3000,function(){
        });
    })

    /*news手风琴效果和banner图*/
    var img_index;
    $(".news_list_ul_li").click(function(){
        img_index =$(this).index();
        //$(this).addClass("news_list_ul_li_one").siblings().removeClass("news_list_ul_li_one");
        $(".news_box_li").eq(img_index).show().siblings().hide();
        //$("news_list_banner").eq(img_index).toggle();
    })
    var accordion_head = $(".news_list_ul>li>p");
        accordion_main = $(".news_list_ul>li .news_list_banner");
    accordion_head.first().addClass("news_list_ul_li_one").next().slideDown(500);
    accordion_head.on("click", function(event) {
            event.preventDefault();
        if ($(this).attr("class")!="news_list_ul_li_one"){
            accordion_main.slideUp(500);
            $(this).next().stop(true,true).slideToggle(500);
            accordion_head.removeClass("news_list_ul_li_one");
            $(this).addClass("news_list_ul_li_one");
        }
    });
    /*更多地区*/
    $(".news_shcool_city").click(function(){
        $(".city_box").toggle();
    });

    <!--下边轮播的新闻-->
    var animateEnd = 1;
    $(".ck_left").on('click',function() {
        nextscroll()
    });
    function nextscroll() {
        var vcon = $(".info_box_list ");
        var offset = ($(".info_box_list li").width()) * -1;
        vcon.stop().animate({
            left: offset
        }, "slow", function() {
            var firstItem = $(".info_box_list ul li").first();
            vcon.find("ul").append(firstItem);
            $(this).css("left", "0px");
        })
    };
    $(".ck_right").on('click',function() {
        var vcon = $(".info_box_list ");
        var offset = ($(".info_box_list li").width() * -1);
        var lastItem = $(".info_box_list ul li").last();
        vcon.find("ul").prepend(lastItem);
        vcon.css("left", offset);
        vcon.animate({left: "0px"}, "slow")
    });
})
