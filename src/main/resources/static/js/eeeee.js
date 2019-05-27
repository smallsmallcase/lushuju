$(function () {
    $('#submit').click(function () {
        var shopImg = $('#img-file')[0].files[0];
        var formData = new FormData();
        var personId = getQueryString('personId');
        var uploadURL = '/upload/img?personId=' + personId;
        formData.append('file', shopImg);

        $.ajax({
            // submit针对注册和修改调用不同的URL
            url: uploadURL,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.code === 1) {
                    $.toast('上传文件成功');
                } else {
                    $.toast('失败' + data.msg);
                }
            }
        });

    });

    $('#extract').click(function () {
        var excel = $('#excel')[0].files[0];
        var formData = new FormData();
        // var personId = getQueryString('personId');
        var URL = '/upload/excel';
        formData.append('excel', excel);

        $.ajax({
            // submit针对注册和修改调用不同的URL
            url: URL,
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            cache: false,
            success: function (data) {
                if (data.code === 1) {
                    $.toast('上传文件成功');
                } else {
                    $.toast('失败' + data.msg);
                }
            }

        });

    });

});


function getQueryString(name) {
    var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return '';
}