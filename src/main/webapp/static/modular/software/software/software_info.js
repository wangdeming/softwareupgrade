/**
 * 初始化软件管理详情对话框
 */
var SoftwareInfoDlg = {

};

var validateFields = {
    code: {
        validators: {
            notEmpty: {
                message: '软件代号不能为空'
            },
            regexp: {//正则验证
                regexp: /^[A-Za-z0-9-]+-(exe|apk|ipa)$/,
                message: '软件名称-软件安装包后缀名，如ticket-inspector-apk'
            },
        }
    },
    name: {
        validators: {
            notEmpty: {
                message: '软件名称不能为空'
            },
            regexp: {//正则验证
                regexp: /^.{0,30}$/,
                message: '30个字符之内'
            },
        }
    }
};

/**
 * 关闭此对话框
 */
SoftwareInfoDlg.close = function() {
    sessionStorage.removeItem("projectId");
    parent.layer.close(window.parent.Software.layerIndex);
}

/**
 * 提交添加
 */
SoftwareInfoDlg.addSubmit = function() {
    if (!validate()) {
        return;
    }
    var projectId = sessionStorage.getItem("projectId");
    var code = $("#code").val();
    var name = $("#name").val();
    var type = $("#softwareType option:selected").attr("data-type");
    var description = $("#intro").val();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/software/add", function(data){
        if (data.code == 200){
            parent.layer.confirm("软件新增成功，是否立即上次安装包？", {
                btn: ['确定', '取消']
            }, function (index) {
                parent.layer.close(index);
                window.parent.Software.table.refresh();
                var softwareId = data.id;
                sessionStorage.setItem("softwareId",softwareId);
                sessionStorage.setItem("softwareType",type);
                sessionStorage.setItem("projectTestId",projectId);
                window.parent.layer.open({
                    type: 2,
                    title: '上传安装包',
                    area: ['850px', '560px'], //宽高
                    fix: false, //不固定
                    maxmin: true,
                    content: Feng.ctxPath + '/versionRecord/toUploadPackage/'+softwareId
                });
                SoftwareInfoDlg.close();
            }, function (index) {
                parent.layer.close(index);
                window.parent.Software.table.refresh();
                SoftwareInfoDlg.close();
            });
        }else {
            Feng.error("添加失败!" + data.message + "!");
        }
    },function(data){
        Feng.error("添加失败!" + data.message + "!");
    });
    ajax.set("projectId",projectId);
    ajax.set("code",code);
    ajax.set("name",name);
    ajax.set("type",type);
    ajax.set("description",description);
    ajax.start();
}

/**
 * 提交修改
 */
SoftwareInfoDlg.editSubmit = function() {
    if (!validate()) {
        return;
    }
    var id = $("#softwareId").val();
    var code = $("#code").val();
    var name = $("#name").val();
    var type = $("#softwareType option:selected").attr("data-type");
    var description = $("#intro").val();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/software/update", function(data){
        Feng.success("修改成功!");
        window.parent.Software.table.refresh();
        SoftwareInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set("id",id);
    ajax.set("code",code);
    ajax.set("name",name);
    ajax.set("type",type);
    ajax.set("description",description);
    ajax.start();
}

$(function() {
    if ($("#code").prop("disabled")){
        var id = sessionStorage.getItem("id");
        var code = sessionStorage.getItem("code");
        var name = sessionStorage.getItem("name");
        var intro = sessionStorage.getItem("intro");
        if (intro == "undefined"){
            intro ='';
        }
        var type = sessionStorage.getItem("type");
        var hasPackage = sessionStorage.getItem("hasPackage");
        $("#softwareId").val(id);
        $("#code").val(code);
        $("#name").val(name);
        $("#intro").val(intro);
        $("#softwareType option").each(function () {
            if($(this).attr("data-type") == type){
                $(this).prop("selected","selected");
            }
        });
        if(hasPackage == 1){
            $("#softwareType").prop("disabled","disabled");
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