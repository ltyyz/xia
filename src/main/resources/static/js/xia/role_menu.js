
$(function () {
    $("#appId").combobox({
        onChange: function (value) {
            loadApplicationTree(value);
        }
    });

    var currentAppId;
    var currentRoleId;
    var appMenus = [];
    function loadApplicationTree(appId) {
        currentAppId = appId;
        currentRoleId = null;
        uncheckALlNodes();
        appMenus = [];
        $("#tg").treegrid("loadData", appMenus);

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
                        ajax("../../xia/roleMenu/list", {roleId: node.id}, function (result) {
                            if (assertResult(result)) {
                                var roleMenus = result.data;
                                for (var i = 0; i < appMenus.length; i++) {
                                    var appMenu = appMenus[i];
                                    if (appMenu.children == 0) {
                                        appMenu.checked = false;
                                        for (var j = 0; j < roleMenus.length; j++) {
                                            if (appMenu.id == roleMenus[j].menuId) {
                                                appMenu.checked = true;
                                                break;
                                            }
                                        }
                                        if (appMenu.checked) {
                                            $("#tg").treegrid("checkNode", appMenu.id);

                                        } else {
                                            $("#tg").treegrid("uncheckNode", appMenu.id);
                                        }
                                    }
                                }
                            }
                        })
                    }
                })
            }
        });

        ajax("../../xia/menu/list_by_app", {appId: appId}, function (result) {
            if (assertResult(result)) {
                $("#tgp").hide().fadeIn(300);
                appMenus = addDefaultIcon(result.data, [0]);
                $("#tg").treegrid("loadData", makeTree(appMenus));
            }
        });
    }

    $("#saveButton").on("click", function () {
        if (currentRoleId) {
            confirmMessage("保存当前选择的菜单吗？", function (flag) {
                if (flag) {
                    showLoading("正在保存");
                    var nodes = $("#tg").treegrid("getCheckedNodes");
                    var menuIds = [];
                    for (var i = 0; i < nodes.length; i++) {
                        menuIds.push(nodes[i].id);
                    }
                    ajax("../../xia/roleMenu/save", {
                        roleId: currentRoleId,
                        menuIds: menuIds.join(",")
                    }, function (result) {
                        closeLoading();
                        if (assertResult(result)) {
                            alertMessage("保存完成！");
                        }
                    })
                }
            })
        } else {
            alertMessage("请选择角色后在选择菜单！");
        }
    });

    function uncheckALlNodes() {
        for (var i = 0; i < appMenus.length; i++) {
            $("#tg").treegrid("uncheckNode", appMenus[i].id);
        }
    }

    $("#expandButton").on("click", function () {
        $("#tg").treegrid("expandAll");
    })
    $("#collapseButton").on("click", function () {
        $("#tg").treegrid("collapseAll");
    })
});