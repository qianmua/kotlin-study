$(function () {
    $(document).on('click','#submit-btn',function(){
        let values = $('form').serializeArray();
        console.log(values);
    });
});