/**
 * 软件管理管理初始化
 */
var Software = {
    id: "SoftwareTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Software.initColumn = function () {
    return [
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '排序',field:"sequence",visible: true, align: 'center',valign: 'middle',width:60,
            formatter:function(value,row,index){
                var x, y, z;
                var totalNum = Software.table.btInstance.bootstrapTable('getData').length;
                if (index == 0){
                    x = '<a disabled="disable" style="display: inline-block;cursor:not-allowed;width: 10px;height: 20px;background-image: url('+Feng.ctxPath+'/static/img/up_disable.svg);background-size: 100% 100%;background-repeat: no-repeat;margin-right: 20px;"></a>';

                }else {
                    x = '<a onclick="up(\''+ row.id + '\');" style="display: inline-block;width: 10px;height: 20px;background-image: url('+Feng.ctxPath+'/static/img/up_able.svg);background-size: 100% 100%;background-repeat: no-repeat;margin-right: 20px;"></a>';
                }
                if (index == totalNum - 1){
                    y = '<a disabled="disable" style="display: inline-block;cursor:not-allowed;width: 10px;height: 20px;background-image: url('+Feng.ctxPath+'/static/img/down_disable.svg);background-size: 100% 100%;background-repeat: no-repeat;"></a>';
                }else {
                    y = '<a onclick="down(\''+ row.id + '\');" style="display: inline-block;width: 10px;height: 20px;background-image: url('+Feng.ctxPath+'/static/img/down_able.svg);background-size: 100% 100%;background-repeat: no-repeat;"></a>';
                }
                z = x+y;
                return z;
            }
        },
        {title: '软件代号', field: 'code', visible: true, align: 'center', valign: 'middle'},
        {title: '软件名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
        {title: '软件类型', field: 'type', visible: true, align: 'center', valign: 'middle',
            formatter:function(value,row,index){
                if(row.type == 1){
                    return 'ios应用(.ipa)';
                }else if(row.type == 2){
                    return '安卓应用（.apk）';
                }else {
                    return 'windows应用（.exe）';
                }
            }
        },
        {title: '说明', field: 'description', visible: true, align: 'center', valign: 'middle'},
        {title: '最新安装包名称', field: 'app_name', visible: true, align: 'center', valign: 'middle',width:120},
        {title: '包名', field: 'package_name', visible: true, align: 'center', valign: 'middle'},
        {title: '最新版本号', field: 'version_no', visible: true, align: 'center', valign: 'middle',width:100},
        {title: '最新安装包大小', field: 'size', visible: true, align: 'center', valign: 'middle',width:120,
            formatter:function(value,row,index){
                return getfilesize(row.size);
            }
        },
        {title: '安装包', field: 'filename', visible: true, align: 'center', valign: 'middle'},
        {title: '更新时间', field: 'createdTime', visible: true, align: 'center', valign: 'middle'},
        {title: '是否强制更新', field: 'is_force', visible: true, align: 'center', valign: 'middle',width:120,
            formatter:function(value,row,index){
                if(row.is_force == 1){
                    return '是';
                }else {
                    return '否';
                }
            }
        },
        {title: '操作', field: 'package_name', visible: true, align: 'center', valign: 'middle',width:250,
            formatter:function(value,row,index){
                var package_name = row.package_name;
                var z;
                if(package_name == null){
                    var x = '<a style="margin-right: 30px;" onclick="Software.uploadPackage(\''+ row.id + '\',\''+ row.type + '\',\''+ row.package_name + '\')">上传安装包</a>';
                    var y = '<a style="margin-right: 30px;" onclick="Software.openSoftwareDetail(\''+ row.id + '\',\''+ row.code + '\',\''+ row.name + '\',\''+ row.type + '\',\''+ row.description + '\',0)">编辑</a>';
                    var e = '<a onclick="Software.delete(\''+ row.id + '\')">删除</a> ';
                    z=x+y+e;
                }else {
                    var x = '<a style="margin-right: 30px;" onclick="Software.uploadPackage(\''+ row.id + '\',\''+ row.type + '\',\''+ row.package_name + '\')">上传安装包</a>';
                    var y = '<a style="margin-right: 30px;" onclick="Software.openSoftwareDetail(\''+ row.id + '\',\''+ row.code + '\',\''+ row.name + '\',\''+ row.type + '\',\''+ row.description + '\',1)">编辑</a>';
                    var e = '<a onclick="Software.goToVersionrecord(\''+ row.id + '\',\''+ row.name + '\',\''+ row.type + '\',\''+ row.package_name + '\')">版本记录</a>';
                    z=x+y+e;
                }
                return z;
            }
        },
    ];
};
Software.uploadPackage = function (id,type,package_name) {
    sessionStorage.setItem("softwareId",id);
    sessionStorage.setItem("softwareType",type);
    if(type == 3){
        sessionStorage.setItem("package_name",package_name);
    }
    var projectId = $("#chooseProject option:selected").attr("data-id");
    sessionStorage.setItem("projectTestId",projectId);
    var index = layer.open({
        type: 2,
        title: '上传安装包',
        area: ['850px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/versionRecord/toUploadPackage/'+id
    });
    this.layerIndex = index;
};
function up(id) {
    var ajax = new $ax(Feng.ctxPath + "/software/movePre", function(data){
        if (data.code == 200){
            Feng.success("上移成功!");
            Software.table.refresh();
        }else {
            Feng.error(data.message);
        }
    },function(data){
        Feng.error(data.message);
    });
    ajax.set("id",id);
    ajax.start();
};
function down (id) {
    var ajax = new $ax(Feng.ctxPath + "/software/moveNext", function(data){
        if(data.code == 200){
            Feng.success("下移成功!");
            Software.table.refresh();
        }else {
            Feng.error(data.message);
        }
    },function(data){
        Feng.error(data.message);
    });
    ajax.set("id",id);
    ajax.start();
};
/**
 * 点击添加软件管理
 */
Software.openAddSoftware = function () {
    var projectId = $("#chooseProject option:selected").attr("data-id");
    sessionStorage.setItem("projectId",projectId);
    var index = layer.open({
        type: 2,
        title: '新增软件',
        area: ['800px', '520px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/software/software_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看软件管理详情
 */
Software.openSoftwareDetail = function (id,code,name,type,intro,hasPackage) {
    sessionStorage.setItem("id",id);
    sessionStorage.setItem("code",code);
    sessionStorage.setItem("name",name);
    sessionStorage.setItem("type",type);
    sessionStorage.setItem("intro",intro);
    sessionStorage.setItem("hasPackage",hasPackage);
    var index = layer.open({
        type: 2,
        title: '编辑软件',
        area: ['800px', '520px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/software/software_update/'
    });
    this.layerIndex = index;
};

/**
 * 删除软件管理
 */
Software.delete = function (id) {
    Feng.confirm("确定删除该软件？",function () {
        var ajax = new $ax(Feng.ctxPath + "/software/delete", function (data) {
            if (data.code == 200){
                Feng.success("删除成功!");
                Software.table.refresh();
            }else {
                Feng.error("删除失败!" + data.message + "!");
            }
        }, function (data) {
            Feng.error("删除失败!" + data.message + "!");
        });
        ajax.set("id",id);
        ajax.start();
    });
};
function goToProject() {
    window.location.href = Feng.ctxPath + '/project';
}
Software.goToVersionrecord = function (id,name,type,package_name) {
    sessionStorage.setItem("softwareId",id);
    sessionStorage.setItem("name",name);
    sessionStorage.setItem("softwareType",type);
    if(type == 3){
        sessionStorage.setItem("package_name",package_name)
    }
    window.location.href = Feng.ctxPath + '/versionRecord';
}
/**
 * 查询软件管理列表
 */
Software.search = function () {
    var queryData = {};
    queryData['projectId'] = $("#chooseProject option:selected").attr("data-id");
    Software.table.refresh({query: queryData});
};

$(function () {
    var ajax = new $ax(Feng.ctxPath + "/software/getProjectName", function (data) {
        if (data.code == 200){
            var projectData = data.data;
            var tempOptionsHtml = '';
            for (var i = 0;i<projectData.length;i++){
                tempOptionsHtml += '<option data-id="'+ projectData[i].id +'">'+ projectData[i].name +'</option>';
            }
            $("#chooseProject").empty().append(tempOptionsHtml);
        }else {
            Feng.error(data.message + "!");
        }
    }, function (data) {
        Feng.error(data.message + "!");
    });
    ajax.start();

    if (sessionStorage.getItem("porjectIdForSoftware") != null){
        var porjectIdForSoftware = sessionStorage.getItem("porjectIdForSoftware");
        $("#chooseProject option").each(function () {
            if($(this).attr("data-id") == porjectIdForSoftware){
                $(this).prop("selected","selected");
            }
        });
    }
    sessionStorage.removeItem("porjectIdForSoftware");
    var projectId = $("#chooseProject option:selected").attr("data-id");
    var defaultColunms = Software.initColumn();
    var table = new BSTable(Software.id, "/software/list", defaultColunms);
    table.queryParams={'projectId':projectId};
    Software.table = table.init();

    $("#chooseProject").change(function () {
        Software.search();
    });
});

// 计算文件大小函数(保留两位小数),Size为字节大小
// size：初始文件大小
function getfilesize(size) {
    if (!size)
        return "";

    var num = 1024.00; //byte

    if (size < num)
        return size + "B";
    if (size < Math.pow(num, 2))
        return (size / num).toFixed(2) + "K"; //kb
    if (size < Math.pow(num, 3))
        return (size / Math.pow(num, 2)).toFixed(2) + "M"; //M
    if (size < Math.pow(num, 4))
        return (size / Math.pow(num, 3)).toFixed(2) + "G"; //G
    return (size / Math.pow(num, 4)).toFixed(2) + "T"; //T
}
