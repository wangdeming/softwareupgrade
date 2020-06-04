/**
 * 初始化版本记录详情对话框
 */
var VersionRecordInfoDlg = {
    versionRecordInfoData : {}
};

/**
 * 清除数据
 */
VersionRecordInfoDlg.clearData = function() {
    this.versionRecordInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VersionRecordInfoDlg.set = function(key, val) {
    this.versionRecordInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
VersionRecordInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
VersionRecordInfoDlg.close = function() {
    parent.layer.close(window.parent.VersionRecord.layerIndex);
}

/**
 * 收集数据
 */
VersionRecordInfoDlg.collectData = function() {
    this.set('id');
}

/**
 * 提交添加
 */
VersionRecordInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/versionRecord/add", function(data){
        Feng.success("添加成功!");
        window.parent.VersionRecord.table.refresh();
        VersionRecordInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.versionRecordInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
VersionRecordInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/versionRecord/update", function(data){
        Feng.success("修改成功!");
        window.parent.VersionRecord.table.refresh();
        VersionRecordInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.versionRecordInfoData);
    ajax.start();
}

$(function() {

});
