$(function () {
    $(document).on('click','#submit-btn',function(){
        let values = $('#loginform1').serializeArray();
        let name = values[0].value;
        let password = values[1].value;
        $.post('../login',{
            name:name,
            password:password
        },function(result){
            if ("success" === result){
                console.log(result);
                toastr.success('正在跳转页面.', '登录成功!');
                window.location.href = "../admins/index.html"
            }else if ("error" === result){
                console.log(result);
                toastr.error('用户名格式有误.', '错误!');
            } else if ("fail" === result){
                console.log(result);
                toastr.error('账户密码不正确.', '错误!');
            }

        });
    });
    $(document).on('click','.img-code',function(){
       let src =  "../foc/gvc?" + new Date().getTime();
       $('.img-code').attr('src',src);
    });
});