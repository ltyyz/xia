
$(function () {
    var users = [];
    $("#searchKey").textbox({
        onClickButton: function () {
            searchUser();
        },
        inputEvents: $.extend({}, $.fn.textbox.defaults.inputEvents,{
            keyup:function(event){
                if(event.keyCode == 13){
                    searchUser();
                }
            }
        })
    });
    $("#dlp").hide().fadeIn(300);
    $("#userList").datalist({
        onSelect: function (index, user) {
            ajax("../../xia/userRole/list", {userId: user.id}, function (result) {
                var roleList = $("#roleList");
                roleList.datalist("uncheckAll");
                $("#dlp").hide().fadeIn(300);
                if (assertResult(result)) {
                    var userRoles = result.data;
                    var rows = roleList.datalist("getRows");
                    for (var i = 0; i < rows.length; i++) {
                        for (var j = 0; j < userRoles.length; j++) {
                            if (rows[i].value == userRoles[j].roleId) {
                                roleList.datalist("checkRow", i);
                            }
                        }
                    }
                }
            })
        }
    });

    function searchUser() {
        var _users = [];
        var key = $("#searchKey").textbox("getValue");
        for (var i = 0; i < users.length; i++) {
            var _user = users[i];
            if (_user.account.indexOf(key) != -1
                || _user.username.indexOf(key) != -1) {
                _users.push(_user);
            }
        }
        $("#userList").datalist({
            data: _users
        })
    }

    ajax("../../xia/user/list_all", {}, function (result) {
        if (assertResult(result)) {
            users = result.data;
            $("#userList").datalist({
                valueField: "id",
                textField: "username",
                data: users
            });
        }
    });

    $("#saveButton").on("click", function () {
        var user = $("#userList").datalist("getSelected");
        if (!user) {
            alertMessage("请选择一个用户并选择角色！");
        } else {
            confirmMessage("保存当前选择的角色吗？", function (flag) {
                if (flag) {
                    showLoading("正在保存");
                    var nodes = $("#roleList").datalist("getChecked");
                    var roleIds = [];
                    for (var i = 0; i < nodes.length; i++) {
                        roleIds.push(nodes[i].value);
                    }
                    ajax("../../xia/userRole/save", {
                        userId: user.id,
                        roleIds: roleIds.join(",")
                    }, function (result) {
                        closeLoading();
                        if (assertResult(result)) {
                            alertMessage("保存完成！");
                        }
                    })
                }
            })
        }
    })
});