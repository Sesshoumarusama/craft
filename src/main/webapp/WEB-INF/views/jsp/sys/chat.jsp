<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../../static/globalStyle_insdep.jsp"%>
<!DOCTYPE html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/static/insdep/plus/chat/chat.css"/>
    <style type="text/css">
        textarea{
            -webkit-transition: all 0.30s ease-in-out;
            -moz-transition: all 0.30s ease-in-out;
            -o-transition: all 0.30s ease-in-out;
            outline: none;
            padding: 3px 0px 3px 3px;
            margin: 5px 1px 3px 0px;
            border: 1px solid #ddd;
            resize: none;
            width: 99%;
            height: 100px;
            font-size: 14px;
        }
        textarea:focus{
            box-shadow: 0 0 5px rgba(81, 203, 238, 1);
            padding: 3px 0px 3px 3px;
            margin: 5px 1px 3px 0px;
            border: 1px solid rgba(81, 203, 238, 1);
        }
    </style>
</head>
<body class="easyui-layout" data-options="fit:true,border:true" style="background: #eee;">
    <div data-options="region:'north'" data-options="border:false" style="height: 50px;">
        <span>轻松一刻在线聊天</span>
        <a id="signin" class="easyui-linkbutton button-line-black" onclick="connect()" style="width: 100px;">登&nbsp;&nbsp;录</a>
        <a id="signout" class="easyui-linkbutton button-line-black" onclick="disconnect()" style="width: 100px;">离&nbsp;&nbsp;线</a>
    </div>
    <div data-options="region:'west'" data-options="border:false" style="width: 250px;"></div>
    <div data-options="region:'center'">
        <div class="easyui-layout" data-options="fit:true,border:false">
            <div data-options="region:'center'">
                <div id="chatlist" class="chatlist" style="width: 98%; padding: 0.5% 0.5%;">
                    <dl>
                        <img src="${ctx}/static/insdep/images/custom/people.png">
                        <dt>
                            <b>John</b>
                        </dt>
                        <dd>明年起所有的iPhone都要用OLED屏幕 三星成大赢家</dd>
                    </dl>
                    <dl class="call">
                        <img src="${ctx}/static/insdep/images/custom/people.png">
                        <dd>诺基亚与蔡司签署独家合作 目标打造智能手机成像新标准</dd>
                    </dl>
                    <div class="chatlist-time"><span>2017年07月07日 10:00</span></div>
                </div>
            </div>

            <div data-options="region:'south'" style="height: 150px;">
                <div>
                    <textarea spellcheck="false" id="message"></textarea>
                </div>
                <div style="text-align: right;">
                    <button class="easyui-linkbutton button-line-black" onclick="sendMessage()" style="width: 100px;">发&nbsp;&nbsp;送</button>
                </div>
            </div>
        </div>
    </div>
<script src="http://cdn.bootcss.com/sockjs-client/1.1.4/sockjs.min.js"></script>
<script src="http://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js"></script>
<script type="text/javascript">
    var stompClient = null;

    function setConnected(connected) {
        if(connected){
            $("#signin").linkbutton('disable');
            $("#signout").linkbutton('enable');
        }else {
            $("#signin").linkbutton('enable');
            $("#signout").linkbutton('disable');
        }
    }

    function connect() {
        /*RMS为上下文起始路径*/
        var socket = new SockJS('/RMS/chat-websocket');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            setConnected(true);
            showGreeting('Connected: ' + frame);
            stompClient.subscribe('/topic/greetings', function (greeting) {
                showGreeting(JSON.parse(greeting.body));
            });
        });
    }

    function disconnect() {
        if (stompClient != null) {
            stompClient.disconnect();
        }
        setConnected(false);
        showGreeting("Disconnected");
    }

    function sendMessage() {
        var message = $('#message').val();
        var userId = currentUserId;
        var userName = currentUserName;
        var params = {
            userId: userId,
            userName: userName,
            content: message
        };
        stompClient.send("/app/hello", {}, JSON.stringify(params));
    }

    function showGreeting(message){
        if(!message['userId']){
            $("#chatlist").append("<dl><dd>" + message + "</dd></dl>");
            return;
        }
        var userId = currentUserId;
        if(userId == message['userId']){
            $("#chatlist").append(
                '<dl class="call">' +
                '<img src="${ctx}/static/insdep/images/custom/people.png">' +
                '<dd>' + message['content'] + '</dd>' +
                '</dl>');
        }else {
            $('#chatlist').append(
                '<dl>' +
                '<img src="${ctx}/static/insdep/images/custom/people.png">' +
                '<dt>' +
                '<b>' + message['userName'] + '</b>' +
                '</dt>' +
                ' <dd>'+ message['content'] + '</dd>' +
                '</dl>');
        }
    }

    $(function () {

    });
</script>
</body>
</html>
