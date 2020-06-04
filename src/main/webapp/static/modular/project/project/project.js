/**
 * 项目列表管理初始化
 */
var Project = {
    id: "ProjectTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Project.initColumn = function () {
    return [
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '排序',field:"sequence",visible: true, align: 'center',valign: 'middle',width:60,
            formatter:function(value,row,index){
                var x, y, z;
                var totalNum = Project.table.btInstance.bootstrapTable('getData').length;
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
        {title: '项目代号', field: 'code', visible: true, align: 'center', valign: 'middle',width:100},
        {title: '项目名称', field: 'name', visible: true, align: 'center', valign: 'middle',width:100},
        {title: '简介', field: 'intro', visible: true, align: 'center', valign: 'middle',width:200},
        {title: '包含软件数量', field: 'software_num', visible: true, align: 'center', valign: 'middle',width:100,
            formatter:function(value,row,index){
                var software_num=row.software_num;
                if(software_num == null){
                    return 0;
                }else {
                    return software_num;
                }
            }
        },
        {title: '操作', field: 'software_num', visible: true, align: 'center', valign: 'middle',width:250,
            formatter:function(value,row,index){
                var software_num=row.software_num;
                var z;
                if(software_num==0 || software_num == null){
                    var x = '<a style="margin-right: 30px;" onclick="Project.openProjectDetail(\''+ row.id + '\',\''+ row.code + '\',\''+ row.name + '\',\''+ row.intro + '\')">编辑</a>';
                    var y = '<a style="margin-right: 30px;" onclick="openSoftware(\''+ row.id + '\')">软件管理</a>';
                    var e = '<a onclick="Project.delete(\''+ row.id + '\')">删除</a>';
                    z=x+y+e;
                }else {
                    var x = '<a style="margin-right: 30px;" onclick="Project.openProjectDetail(\''+ row.id + '\',\''+ row.code + '\',\''+ row.name + '\',\''+ row.intro + '\')">编辑</a>';
                    var y = '<a onclick="openSoftware(\''+ row.id + '\')">软件管理</a>';
                    z=x+y;
                }
                return z;
            }
        },
    ];
};

function up(id) {
    var ajax = new $ax(Feng.ctxPath + "/project/movePre", function(data){
        if(data.code == 200){
            Feng.success("上移成功!");
            Project.table.refresh();
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
    var ajax = new $ax(Feng.ctxPath + "/project/moveNext", function(data){
        if(data.code == 200){
            Feng.success("下移成功!");
            Project.table.refresh();
        }else {
            Feng.error(data.message);
        }
    },function(data){
        Feng.error(dat.message);
    });
    ajax.set("id",id);
    ajax.start();
};
/**
 * 点击添加项目列表
 */
Project.openAddProject = function () {
    var index = layer.open({
        type: 2,
        title: '新增项目',
        area: ['800px', '480px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/project/project_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看项目列表详情
 */
Project.openProjectDetail = function (id,code,name,intro) {
    sessionStorage.setItem("projectId",id);
    sessionStorage.setItem("code",code);
    sessionStorage.setItem("name",name);
    sessionStorage.setItem("intro",intro);
    var index = layer.open({
        type: 2,
        title: '编辑项目',
        area: ['800px', '480px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/project/project_update/'
    });
    this.layerIndex = index;
};

/**
 * 删除项目列表
 */
Project.delete = function (id) {
    Feng.confirm("确定删除该项目?",function () {
        var ajax = new $ax(Feng.ctxPath + "/project/delete", function (data) {
            if(data.code == 200){
                Feng.success("删除成功!");
                Project.table.refresh();
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

function openSoftware(id) {
    sessionStorage.setItem("porjectIdForSoftware",id);
    window.location.href = Feng.ctxPath + '/software';
}
/**
 * 查询项目列表列表
 */
Project.search = function () {
    var queryData = {};
    queryData['keyword'] = $("#projectName").val();
    Project.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = Project.initColumn();
    var table = new BSTable(Project.id, "/project/list", defaultColunms);
    Project.table = table.init();
});

