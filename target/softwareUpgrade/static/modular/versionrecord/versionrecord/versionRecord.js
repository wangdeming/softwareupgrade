/**
 * 版本记录管理初始化
 */
var VersionRecord = {
    id: "VersionRecordTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
VersionRecord.initColumn = function () {
    return [
        {field: 'selectItem', radio: false,visible: false,},
        {title: '序号', field: 'appName', visible: true, align: 'center', valign: 'middle',
            formatter:function(value,row,index){
                return (index + 1);
            }
        },
        {title: '安装包名称', field: 'appName', visible: true, align: 'center', valign: 'middle'},
        {title: '包名', field: 'packageName', visible: true, align: 'center', valign: 'middle'},
        {title: '版本号', field: 'versionNo', visible: true, align: 'center', valign: 'middle'},
        {title: '大小', field: 'size', visible: true, align: 'center', valign: 'middle'},
        {title: '上传时间', field: 'uploadTime', visible: true, align: 'center', valign: 'middle'},
        {title: '安装包', field: 'url', visible: true, align: 'center', valign: 'middle'},
        {title: '是否强制更新', field: 'isForce', visible: true, align: 'center', valign: 'middle',
            formatter:function(value,row,index){
                if (row.isForce == 0){
                    return '否';
                }else {
                    return '是';
                }
            }
        },
        {title: '操作', field: 'appName', visible: true, align: 'center', valign: 'middle',
            formatter:function(value,row,index){
                return '<a onclick="downloadPackage()">下载</a>';
            }
        }
    ];
};

function goToSoftware() {
    window.location.href = Feng.ctxPath + '/software';
}
function goToProject() {
    window.location.href = Feng.ctxPath + '/project';
}

function downloadPackage() {
    var id = sessionStorage.getItem("softwareId");
    //下载
    var ajax = new $ax(Feng.ctxPath + "/versionRecord/download", function(data){
        Feng.success("下载成功!");

    },function(data){
        Feng.error("下载失败!" + data.message + "!");
    });
    ajax.set("id",id);
    ajax.start();
}
/**
 * 点击添加版本记录
 */
VersionRecord.openAddVersionRecord = function () {
    var index = layer.open({
        type: 2,
        title: '添加版本记录',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/versionRecord/versionRecord_add'
    });
    this.layerIndex = index;
};

$(function () {
    var name = sessionStorage.getItem("name");
    $("#softwareName").text(name);
    sessionStorage.removeItem("name");
    var defaultColunms = VersionRecord.initColumn();
    var table = new BSTable(VersionRecord.id, "/versionRecord/list", defaultColunms);
    table.setPaginationType("server");
    var softwareId = sessionStorage.getItem("softwareId");
    table.queryParams={'softwareId':softwareId};
    VersionRecord.table = table.init();
});
