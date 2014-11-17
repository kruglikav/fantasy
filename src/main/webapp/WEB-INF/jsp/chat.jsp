<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
#chat{
    overflow:auto;
    width:30%;
    height:100px;
    border:1px solid #D3D3D3;
    color:#696969;
    margin:5px;
}
</style>

    <div id="chat">
    </div>
    <br/>
    <input id="msg" type="text" style="margin: 5px"/>
    <button id="submit">Send</button>


<script>
    jQuery(function($) {
        $('#submit').click(function(){

            $.ajax({
                type: 'POST',
                url: '/fantasy/postMsg',
                data: 'msg='+$('#msg').val(),
                success: function(data){
                }
            });
            $('#msg').val('');
        });
        function getMsg() {
            $.ajax({
                type: 'GET',
                url: '/fantasy/getMsg',
                success: function(data){
                    $('#chat').append('<p>'+data+'</p>');
                    $('#chat').animate({
                        scrollTop: $('#chat').get(0).scrollHeight}, 100);
                    getMsg();
                },
                error: function(){
                    getMsg();
                }
            });
        }

        getMsg();
    });
</script>