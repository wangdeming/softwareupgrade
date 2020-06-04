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
        {title: 'id', field: 'versionRecordId', visible: false, align: 'center', valign: 'middle'},
        {title: '序号', field: 'appName', visible: true, align: 'center', valign: 'middle',
            formatter:function(value,row,index){
                return (index + 1);
            }
        },
        {title: '安装包名称', field: 'appName', visible: true, align: 'center', valign: 'middle'},
        {title: '包名', field: 'packageName', visible: true, align: 'center', valign: 'middle'},
        {title: '版本号', field: 'versionNo', visible: true, align: 'center', valign: 'middle'},
        {title: '大小', field: 'size', visible: true, align: 'center', valign: 'middle',
            formatter:function(value,row,index){
                return getfilesize(row.size);
            }
        },
        {title: '上传时间', field: 'uploadTime', visible: true, align: 'center', valign: 'middle'},
        {title: '安装包', field: 'fileName', visible: true, align: 'center', valign: 'middle'},
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
                return '<a onclick="downloadPackage(\''+ row.versionRecordId + '\')">下载</a>';
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

function downloadPackage(id) {
    //下载
    var options = {url:Feng.ctxPath + "/versionRecord/download",
        data:{id:id}
    };
    var config = $.extend(true, { method: 'post' }, options);
    var $iframe = $('<iframe id="down-file-iframe" />');
    var $form = $('<form target="down-file-iframe" method="' + config.method + '" />');
    $form.attr('action', config.url);
    for (var key in config.data) {
        $form.append('<input type="hidden" name="' + key + '" value="' + config.data[key] + '" />');
    }
    $iframe.append($form);
    $(document.body).append($iframe);
    $form[0].submit();
    $iframe.remove();
}
/**
 * 上传安装包
 */
VersionRecord.openUploadPackage = function () {
    var softwareId = sessionStorage.getItem("softwareId");
    var index = layer.open({
        type: 2,
        title: '上传安装包',
        area: ['850px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/versionRecord/toUploadPackage/'+softwareId
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