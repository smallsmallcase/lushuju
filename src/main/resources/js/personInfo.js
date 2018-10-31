$(function () {
    $('#submit').click(function () {
        var dat = {};
        dat.personName = $('#personName').val();
        dat.personSex = $('#personSex').val();
        dat.personAge = $('#personAge').val();
        dat.personMarriage = $('#personMarriage').val();
        dat.personBirthplace = $('#personBirthplace').val();
        dat.personNation = $('#personNation').val();
        dat.personCareer = $('#personCareer').val();
        dat.personWorkplace = $('#personWorkplace').val();
        dat.personHome = $('#personHome').val();
        dat.personSpeak = $('#personSpeak').val();


        $.ajax({

            type:"POST",
            dataType:'json',
            url: "/waike/add/personInfo",
            xhrFields:{
                withCredentials:true
            },
            data: JSON.stringify(dat),
            contentType: "application/json",
            cache: false,
            success: function (data) {
                console.log(data.code);

                if (data.code == 1) {
                    window.location.href = "/index";
                }

            },

            error:function (data) {

                console.log(data.msg);
                window.location.href = "/index";
            }

        });
    });
});