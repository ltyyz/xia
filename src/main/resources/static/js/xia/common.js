
var APP_ID_DIC_URL = '../../xia/application/dic';
var USER_ID_DIC_URL = '../../xia/user/dic';

$(function () {
    $("body").hide().fadeIn(300);
});

function ajax(url, data, callback, type) {
    data = data || {};
    type = type || "get";
    callback = callback || function () {};
    $.ajax({
        url: url,
        data: data,
        cache: false,
        dataType: "json",
        type: type,
        success: function (result) {
            callback(result);
        },
        complete: function () {

        }
    })
}

function alertMessage(msg, callback, type) {
    callback = callback || function () {};
    type = type || 'info';
    $.messager.alert("Message", msg, type, callback);
}

function confirmMessage(msg, callback) {
    callback = callback || function () {};
    $.messager.confirm('Confirm', msg, callback);
}

function assertResult(result) {
    if (result && result.code == '0000') {
        return true;

    } else if (result && result.code == '9999') {
        alertMessage(result.msg, null, "warning");
        return false;

    } else if (result && result.code == '0401') {
        alertMessage(result.msg, function () {
            top.location.href = "../../html/xia/login.html";
        }, "warning");
        return false;

    } else if (result && result.code == '0403') {
        alertMessage(result.msg, null, "warning");
        return false;

    }  else {
        alert("处理失败！");
        return false;
    }
}

function makeTree(nodes) {
    var tree = [];
    for (var i = 0; i < nodes.length; i++) {
        if (!nodes[i].parentId) {
            var _node = nodes[i];
            _node.children = getChildren(_node, nodes);
            _node.text = _node.name;
            _node.state = 'open';
            _node.iconCls = _node.icon || '';
            tree.push(_node);
        }
    }
    return tree;
}

function getChildren(node, nodes) {
    var children = [];
    for (var i = 0; i < nodes.length; i++) {
        if (node.id == nodes[i].parentId) {
            var _node = nodes[i];
            if (_node.children > 0) {
                _node.children = getChildren(_node, nodes);
            }
            _node.text = _node.name;
            _node.iconCls = _node.icon || '';
            children.push(_node);
        }
    }
    return children;
}

function formatDic(code) {
    return formatData("../../xia/dic/dic?code=" + code, "value", "name");
}

function formatData(url, valueField, textField) {
    valueField  = valueField || "value";
    textField = textField || "text";
    var dicListKey = "_DIC_LIST_" + url;
    ajax(url, {}, function (list) {
        window[dicListKey] = list;
    });

    return function (value) {
        var dicList = window[dicListKey];
        for (var i = 0; i < dicList.length; i++) {
            if (dicList[i][valueField] == value) {
                return dicList[i][textField];
            }
        }
        return value;
    }
}

$.extend($.fn.validatebox.defaults.rules, {
    equal: {
        validator: function(value, param){
            var value2 = $("#" + param[0]).textbox("getValue");
            return value == value2;
        },
        message: '两次值不一致'
    }
});

function arrayHasValue(array, value) {
    for (var i = 0; i < array.length; i++) {
        if (array[i] == value) {
            return true;
        }
    }
    return false;
}

function addDefaultIcon(nodes, folderTypes) {
    for (var i = 0; i < nodes.length; i++) {
        nodes[i]._icon = nodes[i].icon;
        if (arrayHasValue(folderTypes, nodes[i].type)) {
            nodes[i].icon = 'icon-folder';
        } else {
            nodes[i].icon = 'icon-file';
        }
    }
    return nodes;
}

function showLoading(msg) {
    $.messager.progress({
        title: '提示',
        msg: msg,
        text: ''
    });
}

function closeLoading() {
    $.messager.progress("close");
}