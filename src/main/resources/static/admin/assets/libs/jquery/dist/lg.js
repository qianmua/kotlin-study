$(function () {
    $(document).on('click','#submit-btn',function(){
        let values = $('#loginform1').serializeArray();
        let name = values[0].value;
        let password = values[1].value;
        $.post('../admin/login',{
            name:name,
            password:password
        },function(result){
            if ("success" === result){
                console.log(result);

            }else if ("error" === result){
                console.log(result);

            } else if ("fail" === result){
                console.log(result);

            }

        });
    });
    $(document).on('click','.img-code',function(){
       let src =  "../foc/gvc?" + new Date().getTime();
       $('.img-code').attr('src',src);
    });
});