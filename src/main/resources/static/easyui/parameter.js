$(function () {
    manager_tool={
        addParameter:function () {
            $("#addParameterDialog").dialog("open");
        }
    }

    $("#addParameterDialog").dialog({
        width:400,
        closed:true,
        title:'添加配置参数',
        buttons:[{
            text:'提交',
            iconCls:'icon-add-new',
            handler:function () {
                //新增修改方法
                $.ajax({
                    url:'saveParameter',
                    type:'post',
                    data:{
                        appId:$('input[name="appId"]').val(),
                        category:$('input[name="category"]').val(),
                        paraName:$('input[name="paraName"]').val(),
                        paraValue:$('input[name="paraValue"]').val(),
                        remark:$('input[name="remark"]').val()
                    },
                    success:function (data,response,status) {
                        $.messager.progress('close');
                        $.messager.show({
                            title: '提示',
                            msg: '添加配置参数',
                        }),
                            $('#addParameterDialog').dialog("close").form('reset'),
                            $('#easyuiStyle2').datagrid('reload')
                    }
                })

            } },
            {
                text:'取消',
                iconCls:'icon-redo',
                handler:function(){
                    $("#addParameterDialog").dialog("close").form("reset") //取消弹窗方法
                },
            }]
    });
})

$(function () {
    manager_tool={
        updateParameter:function () {
            $("#updateParameterDialog").dialog("open");
        }
    }

    $("#updateParameterDialog").dialog({
        width:400,
        closed:true,
        title:'修改配置参数',
        buttons:[{
            text:'提交',
            iconCls:'icon-add-new',
            handler:function () {
                //新增修改方法
                $.ajax({
                    url:'saveParameter',
                    type:'post',
                    data:{
                        appId:$('input[name="appId"]').val(),
                        category:$('input[name="category"]').val(),
                        paraName:$('input[name="paraName"]').val(),
                        paraValue:$('input[name="paraValue"]').val(),
                        remark:$('input[name="remark"]').val()
                    },
                    success:function (data,response,status) {
                        $.messager.progress('close');
                        $.messager.show({
                            title: '提示',
                            msg: '修改配置参数',
                        }),
                            $('#updateParameterDialog').dialog("close").form('reset'),
                            $('#easyuiStyle2').datagrid('reload')
                    }
                })

            } },
            {
                text:'取消',
                iconCls:'icon-redo',
                handler:function(){
                    $("#updateParameterDialog").dialog("close").form("reset") //取消弹窗方法
                },
            }]
    });
})

/*
function getData(){
    var rows = [];
    for(var i=1; i<=800; i++){
        var amount = Math.floor(Math.random()*1000);
        var price = Math.floor(Math.random()*1000);
        rows.push({
            inv: 'Inv No '+i,
            date: $.fn.datebox.defaults.formatter(new Date()),
            name: 'Name '+i,
            amount: amount,
            price: price,
            cost: amount*price,
            note: 'Note '+i
        });
    }
    return rows;
}

$(function(){
    $('#dg').datagrid({data:getData()}).datagrid('clientPaging');
});
*/

function serachData() {
    var category = $("#category").val();
    var paraName = $("#paraName").val();
    var params = {"category":category,"paraName":paraName};
    loadData(params);
}
function loadData(params) {
    var url = "http://localhost:8080/base/parameter/listPage";
    $("#dg").datagrid({
        title:"安全参数列表",
        method:"get",//远程访问的方法
        url:url,
        queryParams:params,
        toolbar:"#toolbar",
        singleSelect:true,
        nowrap:true,//把数据显示在一行里
        striped:true,//奇偶行不同颜色
        // remoteSort:false,
        pagination:true,//底部显示分页工具栏
        pageNumber:1,//初始化页码 默认为1
        pageSize:3,//初始化每页展示数据数量
        pageList:[2,3,5,10],//初始化页面展示数量的选择列表
        loadFilter:function(data){
            console.log(data);
            if ($.isArray(data)) {	// is array
                data = {
                    total: data.length,
                    rows: data
                }
            }else {
                var result = data.result;
                data = {
                    total: result.total,
                    rows: result.records
                }
            }
            console.log(data)
            return data;
        }
    })
}

function loadFilter(data) {
    console.log(data);
    if ($.isArray(data)) {	// is array
        data = {
            total: data.length,
            rows: data
        }
    }else {
        var result = data.result;
        data = {
            total: result.total,
            rows: result.records
        }
    }
    console.log(data)
    return data;
}
function destroyDict() {
    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $.messager.confirm('你确定删除这条数据吗', function (r) {
            if (r) {
                $.post('/dict/deleteDict', {id: row.id}, function (result) {
                    if (result.success) {
                        $('#dg').datagrid('reload');	// reload the user data
                    } else {
                        $.messager.show({	// show error message
                            title: 'Error',
                            msg: result.message
                        });
                    }
                }, 'json');
            }
        });
    }
}

