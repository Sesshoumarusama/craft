<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../../static/globalStyle_insdep.jsp"%>
<!DOCTYPE html>
<head>
    <title>Title</title>
    <style type="text/css">
        table input{
            width: 250px;
        }

        table.default-table{
            width: 100%;
            overflow-y: hidden;
            border-collapse: separate;
            border-spacing: 0 20px;
        }

        tr td:nth-child(1){
            width: 100px;
            text-align: right;
        }
    </style>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'west'" style="width: 200px;">
        <a class="easyui-linkbutton" href="${ctx}/sys/chat/chatRoom">聊天室</a>
    </div>
    <div data-options="region:'center'" style="background:#eee;">
        <table id="user-dg"></table>
    </div>
<div style="display: none">
    <!--用户信息对话框-->
    <div id="user-dialog" class="easyui-dialog" style="width: 400px;height: 300px;"
            data-options="modal:true,closed:true">
        <form id="user-form" data-options="fit:true">
            <table class="default-table">
                <tr>
                    <td>用户名：</td>
                    <td><input class="easyui-textbox" name="userName"
                            data-options="required:true"/></td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td><input class="easyui-passwordbox" name="userPassword"/></td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td><input class="easyui-textbox" name="userEmail"/></td>
                </tr>
                <tr>
                    <td>手机号码：</td>
                    <td><input class="easyui-textbox" name="userPhone"/></td>
                </tr>
            </table>
        </form>
    </div>


</div>
<script>

$(function () {
    loadUserInfos();
});

function loadUserInfos() {
    $('#user-dg').datagrid({
        url: '${ctx}/sys/user/listUserInfos',
        fitColumns: false,
        striped: true,
        fit: true,
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        ctrlSelect: true,
        columns: [[
            {field:'userName',title:'用户名',align:'left'},
            {field:'userPhone',title:'手机号码',align:'left'},
            {field:'userEmail',title:'邮箱',align:'left'},
            {field:'userStatusCd',title:'用户状态',align:'left'}
        ]],
        toolbar: [
            {
                text: '添加用户',
                iconCls: 'icon-add',
                width: 120,
                handler: function () {
                    openUserInfodialog('s')
                }
            },'-',
            {
                text: '删除用户',
                iconCls: 'icon-remove',
                width: 120,
                handler: deleteUser
            }
        ]
    });
}

function deleteUser() {
    var userRow = $('#user-dg').datagrid('getSelected');
    if(!userRow){
        $.messager.alert('提示', '请选择要删除的用户', 'info');
        return;
    }
    var userId = userRow.userId;
    $.post('${ctx}/sys/user/deleteUser', {userId: userId}, function (data) {
        parseAjaxReturnInfo(data, function () {
            var index = $('#user-dg').datagrid('getRowIndex');
            $('#user-dg').datagrid('deleteRow', index);
        })
    }, 'json');
}

function openUserInfodialog(type) {
    var title;
    if(type === 's'){
        title = '添加用户';
    }else {
        title = '修改用户';
    }

    $('#user-dialog').dialog({
        title: title,
        iconCls: 'icon-add',
        buttons: [
            {
                text: '保存',
                iconCls: 'icon-add',
                handler: function () {
                    saveUser(type);
                }
            },
            {
                text: '关闭',
                iconCls: 'icon-remove',
                handler:  function () {
                    $('#user-dialog').dialog('close');
                }
            }
        ]

    });


    if(type === 's'){
        $('#user-dialog> form').form('clear');
        $('#user-dialog').dialog('open');
    }
    if(type === 'e'){
        //加载用户信息
        $('#user-dialog').dialog('open');
    }
    $('#user-dialog').dialog('open');
}

function saveUser(type) {
    if('s' === type){
        $.post('${ctx}/sys/user/addUser', $('#user-form').serialize(), function (data) {
            parseAjaxReturnInfo(data, function () {
                $('#user-dg').datagrid('reload');
                closeWindow($('#user-dg'));
            })
        }, 'json');
    }
}


</script>
</body>
</html>
