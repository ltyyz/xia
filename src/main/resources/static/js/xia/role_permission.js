
$(function () {
    $("#appId").combobox({
        onChange: function (value) {
            loadApplicationTree(value);
        }
    });

    var currentAppId;
    var currentRoleId;
    var appPermissions = [];
    function loadApplicationTree(appId) {
        currentAppId = appId;
        currentRoleId = null;
        uncheckALlNodes();
        appPermissions = [];
        $("#tg").treegrid("loadData", appPermissions);

        $("#dlp").hide();
        ajax("../../xia/role/list_by_app", {appId: appId}, function (result) {
            if (assertResult(result)) {
                $("#dlp").fadeIn(300);
                $("#roleList").datalist({
                    data: result.data,
                    valueField: "id",
                    textField: "name",
                    onSelect: function (index, node) {
                        $("#tgp").hide().fadeIn(300);
                        currentRoleId = node.id;
                        uncheckALlNodes();
                        ajax("../../xia/rolePermission/list", {roleId: node.id}, function (result) {
                            if (assertResult(result)) {
                                var rolePermissions = result.data;
                                for (var i = 0; i < appPermissions.length; i++) {
                                    var appPermission = appPermissions[i];
                                    if (appPermission.children == 0) {
                                        appPermission.checked = false;
                                        for (var j = 0; j < rolePermissions.length; j++) {
                                            if (appPermission.id == rolePermissions[j].permissionId) {
                                                appPermission.checked = true;
                                                break;
                                            }
                                        }
                                        if (appPermission.checked) {
                                            $("#tg").treegrid("checkNode", appPermission.id);

                                        } else {
                                            $("#tg").treegrid("uncheckNode", appPermission.id);
                                        }
                                    }
                                }
                            }
                        })
                    }
                })
            }
        });

        ajax("../../xia/permission/list_by_app", {appId: appId}, function (result) {
            if (assertResult(result)) {
                $("#tgp").hide().fadeIn(300);
                appPermissions = addDefaultIcon(result.data, [1,2]);
                $("#tg").treegrid("loadData", makeTree(appPermissions));
            }
        });
    }

    $("#saveButton").on("click", function () {
        if (currentRoleId) {
            confirmMessage("保存当前选择的权限吗？", function (flag) {
                if (flag) {
                    showLoading("正在保存");
                    var nodes = $("#tg").treegrid("getCheckedNodes");
                    var permissionIds = [];
                    for (var i = 0; i < nodes.length; i++) {
                        permissionIds.push(nodes[i].id);
                    }
                    ajax("../../xia/rolePermission/save", {
                        roleId: currentRoleId,
                        permissionIds: permissionIds.join(",")
                    }, function (result) {
                        closeLoading();
                        if (assertResult(result)) {
                            alertMessage("保存完成！");
                        }
                    })
                }
            })
        } else {
            alertMessage("请选择角色后在选择权限！");
        }
    })

    function uncheckALlNodes() {
        for (var i = 0; i < appPermissions.length; i++) {
            $("#tg").treegrid("uncheckNode", appPermissions[i].id);
        }
    }

    $("#expandButton").on("click", function () {
        $("#tg").treegrid("expandAll");
    })
    $("#collapseButton").on("click", function () {
        $("#tg").treegrid("collapseAll");
    })
});