var versionNo;
var size;
var url;
var filename;
var validateFields = {
    appName: {
        validators: {
            notEmpty: {
                message: '安装包名称不能为空'
            },
            regexp: {//正则验证
                regexp: /^.{0,30}$/,
                message: '30个字符之内'
            },
        }
    }
};
$(function () {
    var softwareType = sessionStorage.getItem("softwareType");
    if(softwareType != null){
        if(softwareType == 1){
            $("#packageType").text(".ipa");
        }else if(softwareType == 2){
            $("#packageType").text(".apk");
        }else {
            $("#packageType").text(".zip");
            $("#packageName").removeAttr("disabled");
            if(sessionStorage.getItem("package_name") != "undefined"){
                $("#packageName").val(sessionStorage.getItem("package_name"));
            }
        }
    }
    Feng.initValidator("insertSubAccountForm", validateFields);
});
/**
 * 验证数据是否为空
 */
var validate = function () {
    $('#insertSubAccountForm').data("bootstrapValidator").resetForm();
    $('#insertSubAccountForm').bootstrapValidator('validate');
    return $("#insertSubAccountForm").data('bootstrapValidator').isValid();
};
function addPackage(source) {
    var file = source.files[0];
    if (file.size > 100 * 1024 * 1024){
        Feng.error("上传文件不可大于100M");
        return false;
    }
    var fileData = new FormData();
    fileData.append('file', file);
    var softwareId = sessionStorage.getItem("softwareId");
    fileData.append('softwareId', softwareId);
    $.ajaxSetup({crossDomain: true, xhrFields: {withCredentials: true}});
    $.ajax({
        url: Feng.ctxPath +"/versionRecord/analyse",
        type: 'POST',
        dataType: 'json',
        data: fileData,
        contentType: false,
        processData: false,
        async: false,
        success: function (data) {
            if(data.code != null &&  data.code != 200){
                Feng.error("上传失败!" + data.message + "!");
            }else {
                Feng.success("上传成功!");
                $("#filename").val(data.filename);
                $("#appName").val(data.appName);
                $('#insertSubAccountForm').bootstrapValidator('validate');
                if(sessionStorage.getItem("softwareType") != 3){
                    $("#packageName").val(data.packageName);
                }
                $("#size").val(getfilesize(data.size));
                $("#versionNo").val(data.versionNo);
                versionNo = data.versionNo;
                size = data.size;
                url = data.url;
                filename = data.filename;
            }
        }
    });
}
function closeCurrentPage(){
    sessionStorage.removeItem("package_name");
    window.parent.layer.closeAll();
}
function addSubmit() {
    if (!validate()) {
        return;
    }
    var softwareId = sessionStorage.getItem("softwareId");
    var appName = $("#appName").val();
    var isForce = $("input[type='radio'][name='isForce']:checked").val();
    var ajax = new $ax(Feng.ctxPath + "/versionRecord/add", function(data){
        if(data.code != 200){
            Feng.error("新增安装包失败!" + data.message + "!");
        }else {
            var projectTestId = sessionStorage.getItem("projectTestId");
            if(projectTestId != null){
                sessionStorage.setItem("porjectIdForSoftware",projectTestId);
            }
            window.parent.location.reload();
            window.parent.Feng.success("新增安装包成功!");
            closeCurrentPage();
        }
    },function(data){
        Feng.error("新增安装包失败!" + data.message + "!");
    });
    ajax.set("softwareId",softwareId);
    ajax.set("appName",appName);
    ajax.set("packageName",$("#packageName").val());
    ajax.set("versionNo",versionNo);
    ajax.set("size",size);
    ajax.set("url",url);
    ajax.set("isForce",isForce);
    ajax.set("filename",filename);
    ajax.start();
}
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