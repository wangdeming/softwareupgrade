/**
 * 初始化项目列表详情对话框
 */
var ProjectInfoDlg = {

};

var validateFields = {
    code: {
        validators: {
            notEmpty: {
                message: '项目编号不能为空'
            },
            regexp: {//正则验证
                regexp: /^[A-Z0-9]+-(2[0-9]{3}(0[1-9]|1[0-2]))(-[A-Za-z]+)?$/,
                message: '项目名称英文缩写-初始研发时间-项目分支（选填），如TS-201908-home'
            },
        }
    },
    name: {
        validators: {
            notEmpty: {
                message: '项目名称不能为空'
            },
            regexp: {//正则验证
                regexp: /^.{0,50}$/,
                message: '50个字符之内'
            },
        }
    }
};

/**
 * 关闭此对话框
 */
ProjectInfoDlg.close = function() {
    if ($("#code").prop("disabled")){
        sessionStorage.removeItem("projectId");
        sessionStorage.removeItem("code");
        sessionStorage.removeItem("name");
        sessionStorage.removeItem("intro");
    }
    parent.layer.close(window.parent.Project.layerIndex);
}

/**
 * 提交添加
 */
ProjectInfoDlg.addSubmit = function() {
    if (!validate()) {
        return;
    }
    var code = $("#code").val();
    var name = $("#name").val();
    var intro = $("#intro").val();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/project/add", function(data){
        if(data.code == 200){
            parent.layer.confirm("项目新增成功，是否立即进行软件管理？", {
                btn: ['确定', '取消']
            }, function (index) {
                parent.layer.close(index);
                window.parent.Project.table.refresh();
                sessionStorage.setItem("porjectIdForSoftware",data.id);
                window.parent.location.href = Feng.ctxPath + '/software';
            }, function (index) {
                parent.layer.close(index);
                window.parent.Project.table.refresh();
                ProjectInfoDlg.close();
            });
        }else {
            Feng.error("添加失败!" + data.message + "!");
        }
    },function(data){
        Feng.error("添加失败!" + data.message + "!");
    });
    ajax.set("code",code);
    ajax.set("name",name);
    ajax.set("intro",intro);
    ajax.start();
}

/**
 * 提交修改
 */
ProjectInfoDlg.editSubmit = function() {
    if (!validate()) {
        return;
    }
    var projectId = $("#projectId").val();
    var code = $("#code").val();
    var name = $("#name").val();
    var intro = $("#intro").val();
    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/project/update", function(data){
        if (data.code == 200){
            Feng.success("修改成功!");
            window.parent.Project.table.refresh();
            ProjectInfoDlg.close();
        }else {
            Feng.error("修改失败!" + data.message + "!");
        }
    },function(data){
        Feng.error("修改失败!" + data.message + "!");
    });
    ajax.set("id",projectId);
    ajax.set("code",code);
    ajax.set("name",name);
    ajax.set("intro",intro);
    ajax.start();
}

$(function() {
    if ($("#code").prop("disabled")){
        var projectId = sessionStorage.getItem("projectId");
        var code = sessionStorage.getItem("code");
        var name = sessionStorage.getItem("name");
        var intro = sessionStorage.getItem("intro");
        $("#projectId").val(projectId);
        $("#code").val(code);
        $("#name").val(name);
        $("#intro").val(intro);
    }
    Feng.initValidator("insertSubAccountForm", validateFields);

    $("#intro").keydown(function(event){
        event=document.all?window.event:event;
        if((event.keyCode || event.which)==13){

        }
    });
});

/**
 * 验证数据是否为空
 */
var validate = function () {
    $('#insertSubAccountForm').data("bootstrapValidator").resetForm();
    $('#insertSubAccountForm').bootstrapValidator('validate');
    return $("#insertSubAccountForm").data('bootstrapValidator').isValid();
};

