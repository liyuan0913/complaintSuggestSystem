// 右击tab页，显示菜单menu
$('#center_tabs').tabs({
    onContextMenu: function(e, title, index) {
        // 过滤‘首页’tab页签
        if(index == 0){
            return;
        }
        e.preventDefault();
        $('#tabMenu').menu('show', {
            left : e.pageX,
            top : e.pageY
        }).data("tabTitle", title);
    }
})

// 左键点击菜单，触发方法
$("#tabMenu").menu({
    onClick : function(item) {
        closeTab(this, item.name);
    }
});

// 菜单方法
function closeTab(menu, type) {
    var obj = $('#center_tabs');
    var allTabs = obj.tabs('tabs');
    var allTabtitle = [];
    $.each(allTabs, function(i, n) {
        var opt = $(n).panel('options');
        if (opt.closable) {
            allTabtitle.push(opt.title);
        }
    });
    var curTabTitle = $(menu).data('tabTitle');

    var curTabIndex = obj.tabs('getTabIndex', obj.tabs('getTab', curTabTitle)) - 1;
    /**
     * <pre>
     *      1:关闭标签页
     *      2:关闭全部标签页
     *      3:关闭其它标签页
     *      4:关闭右侧标签页
     *      5:关闭左侧标签页
     *      9.刷新标签页
     * </pre>
     */
    switch (type) {
        case '1':
            obj.tabs('close', curTabTitle);
            break;
        case '2':
            for (var i = 0, j = allTabtitle.length; i < j; i++) {
                obj.tabs('close', allTabtitle[i]);
            }
            break;
        case '3':
            for (var i = 0, j = allTabtitle.length; i < j; i++) {
                if (curTabTitle != allTabtitle[i]) {
                    obj.tabs('close', allTabtitle[i]);
                }
            }
            obj.tabs('select', curTabTitle);
            break;
        case '4':
            debugger;
            for (var i = curTabIndex + 1 ,j = allTabtitle.length; i<j; i++) {
                obj.tabs('close', allTabtitle[i]);
            }
            obj.tabs('select', curTabTitle);
            break;
        case '5':
            debugger;
            for (var i = 0; i < curTabIndex; i++) {
                obj.tabs('close', allTabtitle[i]);
            }
            obj.tabs('select', curTabTitle);
            break;
        case '9':
            //获得当前选中的tab
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
            break;
    }

}

/**
 * 动态创建标签页
 * @param title 标题
 * @param url 路径
 */
function createTab(title,url) {
    var $aq = top.jQuery;
    if ($aq('#center_tabs').tabs('exists', title)){
        $aq('#center_tabs').tabs('select', title);
    } else {
        var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
        $aq('#center_tabs').tabs('add',{
            title: title,
            content: content,
            closable:true
        });
    }
}
