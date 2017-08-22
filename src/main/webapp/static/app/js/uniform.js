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