
$(function () {
    $("#appId").combobox({
        onChange: function (value) {
            loadApplicationMenu(value);
        }
    });

    var trees;
    var currentAppId;
    function loadApplicationMenu(appId) {
        currentAppId = appId;
        $("#dgp").hide();
        $("#tree").hide();
        ajax("../../xia/menu/list_by_app", {appId: appId}, function (result) {
            if (assertResult(result)) {
                var menus = result.data;
                for (var i = 0; i < menus.length; i++) {
                    menus[i]._icon = menus[i].icon;
                    if (menus[i].children == 0) {
                        menus[i].icon = 'icon-file';
                    } else {
                        menus[i].icon = 'icon-folder';
                    }
                }
                trees = makeTree(menus);
                $("#tree").tree({
                    data: trees,
                    onClick: function (node) {
                        if (node.type == "0") {
                            $("#dg").datagrid({
                                queryParams: {
                                    appId: appId,
                                    parentId: node.id
                                }
                            })
                        }
                    },
                    onContextMenu: function (e, node) {
                        node.icon = node._icon;
                        console.log(node);
                        e.preventDefault();
                        $('#mm').menu('show', {
                            left: e.pageX,
                            top: e.pageY
                        });

                        $("#mm-update").unbind("click").on("click", function () {
                            showUpdate(node);
                        });


                        $("#mm-delete").unbind("click").on("click", function () {
                            showDelete(node);
                        });
                    }
                });

                $("#parentId").combotree("loadData", trees).combotree({
                    icons:[{
                        iconCls:'icon-clear',
                        handler: function(e){
                            $("#parentId").combotree("setValue", "");
                        }
                    }]
                });
                $("#tree").fadeIn(300);
            }
        })

        $("#allButton").on("click", function() {
            $("#tree").tree("loadData", trees);
            $("#dg").datagrid({
                queryParams: {
                    appId: appId
                }
            })
        });

        $("#dgp").fadeIn(300);

        $("#mm-add").on("click", function () {
            showAdd();
        });
        $("#dg").datagrid({
            url: "../../xia/menu/list",
            queryParams: {
                appId: appId
            },
            onRowContextMenu: function (e, index, row) {
                e.preventDefault();
                $('#mm').menu('show', {
                    left: e.pageX,
                    top: e.pageY
                });

                $("#mm-update").unbind("click").on("click", function () {
                    showUpdate(row);
                });


                $("#mm-delete").unbind("click").on("click", function () {
                    showDelete(row);
                });
            }
        })
    }




    $("#addButton").on("click", function () {
        showAdd();
    })

    function showAdd() {
        $("#ff").form("clear");
        $("#dlg").dialog({
            title: 'Add'
        }).dialog("open");
        $("#formAppId").combobox({
            readonly: false
        })

        $('#ff').form({
            url: '../../xia/menu/add',
            ajax: true,
            iframe: false,
            onSubmit: function () {
                $("#id").val("0");
                return $("#ff").form("validate");
            },
            success: function (result) {
                result = $.parseJSON(result);
                if (assertResult(result)) {
                    alertMessage("添加完成！", function () {
                        $("#dlg").dialog("close");
                        $("#ff").form("clear");

                        loadApplicationMenu(currentAppId);
                    });
                }
            }
        }).form("clear");
    }

    $("#updateButton").on("click", function () {
        var _row = $("#dg").datagrid("getSelected");
        if (!_row) {
            alertMessage("请选择一行数据！");
        } else {
            showUpdate(_row);
        }
    })

    function showUpdate(_row) {
        $("#ff").form("clear");
        $("#dlg").dialog({
            title: 'Edit'
        }).dialog("open");
        $("#formAppId").combobox({
            readonly: true
        })

        $("#ff").form({
            url: '../../xia/menu/update',
            ajax: true,
            iframe: false,
            onSubmit: function () {
                return $("#ff").form("validate");
            },
            success: function (result) {
                result = $.parseJSON(result);
                if (assertResult(result)) {
                    alertMessage("修改完成！", function () {
                        $("#dlg").dialog("close");
                        $("#ff").form("clear");

                        loadApplicationMenu(currentAppId);
                    });
                }
            }
        }).form("load", _row);
    }

    $("#deleteButton").on("click", function () {
        var _row = $("#dg").datagrid("getSelected");
        if (!_row) {
            alertMessage("请选择一行数据！");
        } else {
            showDelete(_row);
        }
    });

    function showDelete(_row) {
        confirmMessage('确定删除？', function (flag) {
            if (flag) {
                ajax("../../xia/menu/delete", {id: _row.id}, function (result) {
                    if (assertResult(result)) {
                        loadApplicationMenu(currentAppId);
                    }
                })
            }
        })
    }
})

function submitForm() {
    $("#ff").submit();
}

function cancelForm() {
    $("#dlg").dialog("close");
}