var validateFields = {
    password: {
        validators: {
            notEmpty: {
                message: '密码不能为空'
            },
            regexp: {
                regexp: /^[\da-zA-Z!#$%^&*]{6,30}$/,
                message: '支持大小写字母、阿拉伯数字、符号或组合，6~30个字符'
            },
            identical: {
                field: 'rePassword',
                    message: '两次密码不一致'
            }
        }
    },
    rePassword: {
        validators: {
            notEmpty: {
                message: '密码不能为空'
            },
            regexp: {
                regexp: /^[\da-zA-Z!#$%^&*]{6,30}$/,
                message: '支持大小写字母、阿拉伯数字、符号或组合，6~30个字符'
            },
            identical: {
                field: 'password',
                    message: '两次密码不一致'
            }
        }
    }
};
$(function () {
    if(sessionStorage.getItem("account") != null){
        $("#accountName").val(sessionStorage.getItem("account"));
    }
    Feng.initValidator("insertSubAccountForm", validateFields);
});

function closeLayer() {
    window.parent.layer.closeAll();
}

function submitEditPwd() {
    if (!validate()) {
        return;
    }
    var oldPwd = $("#oldPwd").val();
    var newPwd = $("#password").val();
    var rePwd = $("#rePassword").val();
    var ajax = new $ax(Feng.ctxPath + "/mgr/changePwd", function (data) {
        if(data.code == 200){
            Feng.success("修改成功!");
        }else {
            Feng.error("修改失败!" + data.message + "!");
        }
    }, function (data) {
        Feng.error("修改失败!" + data.message + "!");
    });
    ajax.set("oldPwd",oldPwd);
    ajax.set("newPwd",newPwd);
    ajax.set("rePwd",rePwd);
    ajax.start();
}

/**
 * 验证数据是否为空
 */
var validate = function () {
    $('#insertSubAccountForm').data("bootstrapValidator").resetForm();
    $('#insertSubAccountForm').bootstrapValidator('validate');
    return $("#insertSubAccountForm").data('bootstrapValidator').isValid();
};
