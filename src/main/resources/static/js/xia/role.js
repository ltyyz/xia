$(function () {
    $("#mm-add").on("click", function () {
        showAdd();
    });

    $("#dg").datagrid({
        url: '../../xia/role/list',
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
    });

    $("#searchKey").combobox({
        onClickButton: function () {
            searchKey();
        },
        icons:[{
            iconCls:'icon-clear',
            handler: function(e){
                $("#searchKey").combobox("setValue", "");
            }
        }]
    });

    function searchKey() {
        var appId = $("#searchKey").combobox("getValue");
        $("#dg").datagrid({
            queryParams:{
                appId: appId
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


        $('#ff').form({
            url: '../../xia/role/add',
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
                        $("#dg").datagrid("reload");
                        $("#ff").form("clear");
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


        $("#ff").form({
            url: '../../xia/role/update',
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
                        $("#dg").datagrid("reload");
                        $("#ff").form("clear");
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
        confirmMessage('删除角色将一并删除该角色的菜单、权限和用户配置，确定删除？', function (flag) {
            if (flag) {
                ajax("../../xia/role/delete", {id: _row.id}, function () {
                    $("#dg").datagrid("reload");
                })
            }
        })
    }
});

function submitForm() {
    $("#ff").submit();
}

function cancelForm() {
    $("#dlg").dialog("close");
}
