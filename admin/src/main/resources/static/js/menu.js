$(function () {
    getMenu();
});

//设置菜单渲染
function getMenu() {
    $.ajax({
        url: IP + "/user/menuControl",
        type: "get",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        xhrFields: {
            withCredentials: true
        },

        beforeSend: function (xhr) {
            xhr.withCredentials = true;
        },
        success: function (res) {
            if (res.code === 0) {
                let data = res.data;
                var ul = $(".box .conter .nav-item>ul");
                var html = template("menu", {data: data});
                ul.html(html);
                clickMenu();
            } else {
                alert(res.data)
            }
        },
    });
}

function clickMenu() {
    //菜单跳转
    var lis = $(".box .conter .nav-item>ul>li");
    var loadUrl = '';
    lis.on("click", function () {
        var url = $(this).attr("data-url");
        loadUrl = url;
        $(this).addClass("active").siblings().removeClass("active");
        menuSkip(url);
    });
    if (sessionStorage.getItem("loadUrl")) {
        var storageLoadUrl = sessionStorage.getItem("loadUrl")
        menuSkip(storageLoadUrl);
        for (var i = 0; i < lis.length; i++) {
            if (storageLoadUrl === lis.eq(i).attr("data-url")) {
                lis.eq(i).addClass("active").siblings().removeClass("active")
            }
        }
    } else {
        menuSkip('set-mine');
    }

    // 刷新
    window.onbeforeunload = function (ev) {
        sessionStorage.setItem("loadUrl", loadUrl);
    }
}


// 跳转
function menuSkip(url) {
    $.ajax({
        url: IP + '/' + url + '.include',
        dataType: "html",
        success: function (data) {
            $('#main-content').html(data);
        }
    });
}

$(function () {
    $("#main-content").on("click", function (event) {
        event.stopPropagation();
        $(".dyui_select ul").slideUp(300)
    })
})

$(function () {
    $.ajax({
        url: IP + "/user/self",
        type: "get",
        contentType: "application/x-www-form-urlencoded;charset=utf-8",
        xhrFields: {
            withCredentials: true
        },
        beforeSend: function (xhr) {
            xhr.withCredentials = true;
        },
        success: function (res) {
            if (res.code === 0) {
                let data = res.data[0];
                let headImg = data.headImg;
                if (headImg) {
                    $(".user_info img").attr('src', IP + headImg);
                }
            }
        },
    });
})