var websocket = null;
if ('WebSocket' in window){
    websocket=new WebSocket("ws://localhost:8080/ws")
}else {
    alert('not support');
}
websocket.onerror=function () {
    appendMessage("error");
}

websocket.onopen=function (event) {
   $("#status").html("连接 ")
}

websocket.onmessage=function (event) {
    if($('#chatobj').val()==""){
        $('#chatobj').html(event.data.split(":")[0]);
    }
    appendMessageRight(event.data);
}

websocket.onclose=function () {
    $("#status").html("断开 ")
}

window.onbeforeunload=function () {
    websocket.close();
}

function appendMessageLeft(message) {
    var username = $('#username').val();
    $("#chatContent").append('<p>'+username+':'+message+'</p>');
}

function appendMessageRight(message) {
    $("#chatContent").append('<p align="right" ">'+message+'</p>');
}

function closeWebsocket() {
    websocket.close();
}

function sendMessage() {
    var message=$("#inputWord").val();
    websocket.send($('#chatobj').html()+':'+message);
    appendMessageLeft(message);
    $("#inputWord").val("");
}

$(function(){
    loadFriends();
    $('.list-group-item').click(function () {
        $('#chatobj').html($(this).html())
    })
})

function loadFriends() {
    var friendlist=$('#friends').val();
    var len=friendlist.length;
    var result=friendlist.substring(1,len-1);
    result=result.split(", ")
    for (var i=0;i<result.length;i++){
        $('#group').append("<li class="+"list-group-item" +">"+result[i]+"</li>")
    }
}



