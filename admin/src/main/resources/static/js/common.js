$(function () {
    select();
    selectAll();
});

function select() {
    var inText;
    $(".dyui_select .dyui_select-text").click(function (event) {
        event.stopPropagation();
        $(this).siblings("ul").slideToggle(300, function () {
            if ($(this).css("display") == "none") {
                $(this).siblings(".dyui_select-icon").find("img").attr("src", "images/down12.png");
            } else {
                $(this).siblings(".dyui_select-icon").find("img").attr("src", "images/down11.png");
            }
        });
    });

    $(".dyui_select li").click(function (event) {
        event.stopPropagation();
        $(this).addClass("active").siblings().removeClass("active");
        inText = $(this).text();
        $(this).parent().siblings(".dyui_select-text").text(inText);
        $(this).parent().siblings(".dyui_select-text").attr('data-select', $(this).attr('data-select'));
        $(this).parent().slideUp(300, function () {
            $(this).siblings(".dyui_select-icon").find("img").attr("src", "images/down12.png");
        });
    })
}

// 表格点击全选或者取消
function selectAll() {
    $("thead .dyui_checkbox-original").change(function () {
        if ($(this).is(':checked')) {
            $("tbody .dyui_checkbox-original").prop('checked', true)
        } else {
            $("tbody .dyui_checkbox-original").prop('checked', false)
        }
    })
}

//时间选择插件
function chooseTime(dom) {
    dom.ECalendar({
        type: "date",   //模式，time: 带时间选择; date: 不带时间选择;
        stamp: false,   //是否转成时间戳，默认true;
        offset: [0, 2],   //弹框手动偏移量;
        format: "yyyy-mm-dd",   //时间格式 默认 yyyy-mm-dd hh:ii;
        skin: '#01454E',   //皮肤颜色，默认随机，可选值：0-8,或者直接标注颜色值;
        callback: function (v, e) {
        } //回调函数
    });
}

$(function () {
    $(".quit").on("click", function () {
        $.ajax({
            url: IP + "/logout",
            type: "POST",
            dataType: "json",
            contentType: "application/x-www-form-urlencoded;charset=utf-8",
            xhrFields: {
                withCredentials: true
            },
            beforeSend: function (xhr) {
                xhr.withCredentials = true;
            },
            success: function (res) {
                if (res.code === 0) {
                    window.location.href = IP + "/login.html"
                } else {
                    alert(res.data)
                }
            }
        });

    })
});
