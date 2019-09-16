
//默认的表格初始化值
var tableOptions = {
    url:"",
    method:"get",
    toolbar:'#toolbar',
    queryParams:{},
    singleSelect:false,
    checkbox:false,
    checkOnSelect:false,
    selectOnCheck:false,
    fitColumns:true,
    nowrap:true,//把数据显示在一行里
    striped:true,//奇偶行不同颜色
    // remoteSort:false,
    pagination:true,//底部显示分页工具栏
    nowrap:true,//把数据显示在一行里
    striped:true,//奇偶行不同颜色
    pageSize:10,//默认一页10条数据
    pageNumber:1,//初始化页码 默认为1
    pageList:[5,10,20],//页面尺寸
    onBeforeLoad:function(params){
        params.pageNo = params.page;
        params.pageSize = params.rows;
        delete params.page;
        delete params.rows;
    },
    //对获取的数据进行过滤
    loadFilter:function(data){
        if ($.isArray(data)) {
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
        return data;
    }

};


function isNull(str) {
    return (str == null || (typeof str == 'undefined') || str == "");
}

function uuid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random() * 16 | 0, v = c === 'x' ? r : (r & 0x3 | 0x8);
        return v.toString(16);
    });
};

/**
 * 打开弹窗
 * @param id 弹窗Id
 * @param obj 初始化参数
 */
function openDialog(id,obj) {
    var $jq = top.jQuery;
    //先刪除
    $jq("#"+id).remove();
    $jq("#"+id).dialog("destroy");
    //再动态创建
    var $modalDialog = $jq('<div id="'+ id + '" ></div>');

    var defaults = {
        close:false,
        title:"Dialog",
        width:600,
        height:500,
        queryParams:{},
        modal:true,
    };
    var o = $.extend(defaults,obj);
    $modalDialog.dialog(o);
    $modalDialog.window('center');
}

/**
 * 根据id删除弹窗
 * @param id
 */
function closeDialog(id) {
    //关闭弹窗
    top.jQuery("#"+id).dialog('close');
}
/**
 * 根据id获取父页面向弹窗传的参数
 * @param id
 */
function getDialogQueryParams(id) {
    //获取弹窗中的参数
    var obj = top.jQuery("#"+id).dialog("options");
    return obj["queryParams"];
}

/**
 * 根据paramName获取父页面向tab页传的参数
 * @param id
 */
function getTabUrlParamValue(paramName) {
    // //获得当前选中的tab
    // var tab = top.jQuery('#center_tabs').tabs('getSelected');
    // //获取当前页签的地址src
    // var url = top.jQuery(tab.panel('options').content).attr('src');
    var searchUrl = window.location.search;
    if (searchUrl != ''){
        var reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)");
        var str = decodeURI(searchUrl).substr(1);//去掉问号
        var r = str.match(reg);
        if (r != null) {
            return decodeURI(unescape(r[2]));
        }
    }
    return null;
}

/**
 * 消息窗口
 * @param msg 消息内容
 */
function showMessage(msg) {
    var defaults = {
        showType:false,
        showSpeed:400,
        width:300,
        height:120,
        title:"消息提示",
        msg:msg,
        style:{}.toString(),
        timeout:800,
    };
    $.messager.show(defaults);
}
/**
 * 错误提示窗口
 * @param msg 消息内容
 */
function alertErrorMessage(msg) {
    $.messager.alert('操作提示',msg,'error');
}
/**
 * 问题提示窗口
 * @param msg 消息内容
 * @param fn 回调函数
 */
function alertQuestionMessage(msg,fn) {
    $.messager.alert('操作提示',msg,'question',fn);
}
/**
 * 提示窗口
 * @param msg 消息内容
 */
function alertInfoMessage(msg) {
    $.messager.alert('操作提示',msg,'info');
}
/**
 * 错误提示窗口
 * @param msg 消息内容
 */
function alertWarmingMessage(msg) {
    $.messager.alert('操作提示',msg,'warming');
}

var rootPath = null;
if (rootPath == null){
    rootPath = getRootPath();
}

/**
 * 获得根路径方法
 * @returns {string}
 */
function getRootPath() {
    // debugger;
    var curtPath = window.document.location.href;
    var pathName = window.document.location.pathname;
    var position = curtPath.indexOf(pathName);
    return curtPath.substring(0,position);
}
function refresh() {
    var tab = $('#center_tabs').tabs('getSelected');
    //获取当前页签的地址src
    var url = $(tab.panel('options').content).attr('src');
    //更新页签
    $('#center_tabs').tabs('update',{
        tab:tab,
        options:{
            href:url
        }
    });
}
/**
 * 树：添加子节点
 * @param tree：根节点树对象
 * @param id：子节点的id
 * @param name：子节点的名字
 * */
function addOrgTree(tree,id,name){
    var selected = tree.tree('getSelected');
    if(selected == null || selected == undefined || selected == ''){
        // 从根节点添加
        tree.tree('append', {
            data: [{
                id: id,
                text: name
            }]
        });
    }else {
        // 从某一节点添加
        tree.tree('append', {
            parent: selected.target,
            data: [{
                id: id,
                text: name
            }]
        });
    }
}

/**
 * 树：修改子节点
 * @param tree：根节点树对象
 * @param id：子节点的id
 * @param name：子节点的名字
 * */
function changeOrgTree(tree,id,name){
    var node = tree.tree('find',id); // 在tree中去找到指定的节点，并返回节点对象
    tree.tree('update', {
        target: node.target,
        text: name
    });
}


/**
 * 树：删除某个子节点
 * @param tree：根节点树对象
 * @param id：要删除子节点的id
 * */
function delOrgTree(tree,id){
    var node = tree.tree('find',id); // 在tree中去找到指定的节点，并返回节点对象
    tree.tree('remove', node.target); // 从tree树中移除指定的节点对象
}

/**
 * 树：删除多个子节点
 * @param tree：根节点树对象
 * @param row：要删除子节点的集合（复选框被选中的元素）
 * */
function delMoreOrgTree(tree,ids){
    for(var i=0;i < ids.length;i++){
        var node = tree.tree('find',ids[i]); // 在tree中去找到指定的节点，并返回节点对象
        tree.tree('remove', node.target); // 从tree树中移除指定的节点对象
    }
}

/**
 * 清空text select
 *
 */
function clearData() {
    $('.easyui-textbox').textbox("setValue",''); // 清空input框
    $('.easyui-combobox').combobox("select",'请选择'); // 把select框重置为‘请选择’
}


// /**
//  * 动态创建tab
//  * @param id
//  * @param title
//  * @param url
//  */
// function craeateTab(id,title,url) {
//     if ($('#center_tabs').tabs('exists', id)){
//         $('#center_tabs').tabs('select',id);
//     }
//     var content = '<iframe scrolling="auto" frameborder="0"  src="'+rootPath+url+'" style="width:100%;height:100%;"></iframe>';
//     $('#center_tabs').tabs('add',{
//         title: title,
//         content: content,
//         closable:true
//     });
// }

// function refreshDataGrid(tableId) {
//     var $aq = top.jQuery;
//     debugger;
//     var tab =  $aq('#center_tabs').tabs('getSelected');
//     var sss = tab.find('#dg_dict');
//     console.log(sss);
//     top.jQuery("#dict").dialog({closed:true});
//     sss.datagrid('reload');
// }


// /**
//  * 根据id创建table
//  * @param tableId
//  * @param toolbarId
//  * @param obj 初始化参数
//  */
// function initTable(tableId,toolbarId,obj) {
//     var defaults = {
//         title:"数据列表",
//         method:"get",//远程访问的方法
//         queryParams:{},
//         toolbar:"#"+toolbarId,
//         singleSelect:false,
//         checkbox:false,
//         checkOnSelect:false,
//         selectOnCheck:false,
//         nowrap:true,//把数据显示在一行里
//         striped:true,//奇偶行不同颜色
//         // remoteSort:false,
//         pagination:true,//底部显示分页工具栏
//         pageNumber:1,//初始化页码 默认为1
//         pageSize:10,//初始化每页展示数据数量
//         pageList:[5,10,15,20],//初始化页面展示数量的选择列表
//         onBeforeLoad:function(params){
//             params.pageNo = params.page;
//             params.pageSize = params.rows;
//             delete params.page;
//             delete params.rows;
//         },
//         loadFilter:function(data){
//             if ($.isArray(data)) {	// is array
//                 data = {
//                     total: data.length,
//                     rows: data
//                 }
//             }else {
//                 var result = data.result;
//                 data = {
//                     total: result.total,
//                     rows: result.records
//                 }
//             }
//             return data;
//         }
//     };
//
//     var o = $.extend(defaults,obj);
//     console.log(o);
//     $("#"+tableId).dialog(o);
// }