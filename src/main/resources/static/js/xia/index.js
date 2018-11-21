
var user;
var currentAppId;
var currentApp;
var applications;

$(function () {
    loadApplications();

    setMenuCollapse();
});

function loadApplications() {
    ajax("../../xia/index/applications", {}, function (result) {
        if (assertResult(result)) {
            if (!result.data || result.data.length == 0) {
                alertMessage("No system found for current user.");

            } else {
                applications = result.data;
                currentApp = result.data[0];
                if (currentApp.type != 1) {
                    location.href = currentApp.url;
                    return;
                }
                currentAppId = currentApp.id;
                loadMenus(currentApp.id);

                var mmHtml = '';

                for (var i = 0; i < applications.length; i++) {
                    var application = applications[i];
                    mmHtml += '<div onclick="switchApp('+application.id+')" class="custom-item" style="height: 32px;vertical-align: middle;cursor: pointer;">'+application.name+'</div>';
                }
                $("#appList").html(mmHtml);

                ajax("../../xia/index/currentUser", {}, function (result) {
                    if (assertResult(result)) {
                        user = result.data;
                        $("#user_menu").menubutton({
                            text: result.data.username
                        });
                    }
                })
            }
        }
    });
}

function loadMenus(appId) {
    $("#APP_NAME").html(currentApp.name);
    document.title = currentApp.name;

    var tabsLength = $("#TABS").tabs("tabs").length;
    for (var i = tabsLength-1; i >= 0; i--) {
        $("#TABS").tabs("close", i);
    }

    ajax("../../xia/index/menus", {appId: appId}, function (result) {
        if (assertResult(result)) {
            $("#sm").sidemenu({
                border: false,
                data: makeTree(result.data),
                onSelect: function (item) {
                    addTab(item);
                }
            })
        }
    })

    initialDashboard(currentAppId);
}


function initialDashboard(appId) {
    addTab({
        id: 0,
        text: 'Dashboard',
        url: '../../html/xia/main.html',
        closable: -1
    })
}

function addTab(item) {
    var tabs = $("#TABS");

    var _tabs = tabs.tabs("tabs");
    var exists = false;
    for (var i = 0; i < _tabs.length; i++) {
        if (_tabs[i].panel("options").menuId == item.id) {
            tabs.tabs("select", i);
            exists = true;
            break;
        }
    }
    if (item.closable == -1) {
        item.closable = false;
    } else {
        item.closable = true;
    }
    if (!exists) {
        $("#TABS").tabs('add', {
            title: item.text,
            selected: true,
            menuId: item.id,
            closable: item.closable,
            content: '<iframe id="IFRAME_'+item.id+'" src="'+item.url+'" frameborder="0" width="100%" >'
        });

        document.getElementById("IFRAME_" + item.id).height = document.body.clientHeight - 88;
    }
}

function checkProfile() {
    $("#dlg").dialog({
        title: 'PROFILE'
    }).dialog("open");

    $("#ff").form({
        url: '../../xia/index/updateUser',
        ajax: true,
        iframe: false,
        onSubmit: function () {
            return $("#ff").form("validate");
        },
        success: function (result) {
            result = $.parseJSON(result);
            if (assertResult(result)) {
                alertMessage("更新完成！", function () {
                    $("#dlg").dialog("close");
                    $("#ff").form("clear");
                    top.location.href="../../logout";
                });
            }
        }
    }).form("load", user);
}

function modifyPassword() {
    checkProfile();
}

function signOut() {
    confirmMessage('确定退出系统？', function (flag) {
        if (flag) {
            top.location.href="../../logout";
        }
    })

}

function submitForm() {
    $("#ff").submit();
}

function cancelForm() {
    $("#dlg").dialog("close");
}

function switchApp(appId) {
    if (appId != currentAppId) {
        $("#switchAppId").val(appId);
        $("#dlg2").dialog("open");

        for (var i = 0; i < applications.length; i++) {
            if (applications[i].id == appId) {
                $("#switchAppName").html("切换到系统：" + applications[i].name);
                break;
            }
        }
    }
}

function switchToApp(setDefault) {
    var appId = $("#switchAppId").val();
    currentAppId = appId;
    if (setDefault) {
        ajax("../../xia/index/switchDefaultApp", {appId: appId}, function (result) {
            assertResult(result);
        })
    }
    for (var i = 0; i < applications.length; i++) {
        var _app = applications[i];
        if (_app.id == appId) {
            if (_app.type == 1) {
                loadMenus(appId);
                switchCancel();
                currentApp = _app;

            } else {
                location.href=_app.url;
            }
            break;
        }
    }

}

function switchCancel() {
    $("#dlg2").dialog("close");
}

function setMenuCollapse() {
    $("#LOGO").css("cursor", "pointer").on("click", toggle);
    $("body").layout({
        onCollapse: function (region) {
            if (region == "west") {
                _IS_COLLAPSED = true;
            }
        },
        onExpand: function (region) {
            if (region == "west") {
                _IS_COLLAPSED = false;
            }
        }
    })
}

var _IS_COLLAPSED = false;
function toggle(){
    if (_IS_COLLAPSED) {
        $("body").layout("expand", "west");
    } else {
        $("body").layout("collapse", "west");
    }
    _IS_COLLAPSED = !_IS_COLLAPSED;
}