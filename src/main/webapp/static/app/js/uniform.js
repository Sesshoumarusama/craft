/**
 * Created by ppcug on 2017/8/18.
 */
function hasText(str) {
    return str != undefined && str != null && str != '';
}

function parseAjaxReturnInfo(data, callback) {
    $.messager.alert('提示', data['message'], 'info');
    if('true' === data['success']){
        if(callback){
            callback();
        }
    }
}

function closeWindow(selector) {
    selector.window('close');
}

// jQuery添加全局Ajax默认选项：complete回调函数
$.ajaxSetup( {
    global: false,
    type: 'POST',
    complete: function ( XMLHttpRequest, textStatus ) {
        var data = XMLHttpRequest.responseText;
        if (data == "timeout") {
            $.messager.show( {
                title: '提示',
                msg: '登录超时，系统将在3秒后自动跳转到登录界面...',
                timeout: 3000,
                showType: 'slide',
                style:{
                    right: '',
                    top: document.body.scrollTop + document.documentElement.scrollTop,
                    bottom: ''
                }
            } );
            setTimeout( function () {
                window.top.location.href = contextPath + "/sys/login/index";
            }, 3000 );
        }
    }
} );