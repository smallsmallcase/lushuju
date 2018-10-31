$(function () {


    $('#submit').click(function () {
        var dat = {};

        dat.userName = $('#userName').val();
        dat.passWord = $('#password').val();


        $.ajax({

            type:"POST",
            // async:false,
            xhrFields:{
                withCredentials:true
            },
            dataType:'json',
            url: "/login",
            data: JSON.stringify(dat),
            contentType: "application/json",
            cache: false,
            success: function (data) {
                if (data.code == 1) {
                    //跳转到录入主表信息界面
                    window.location.href = "/personInfo";

                }

            }

        });
    });
});