$.fn.pageSplitBar = function (options) {
    var configs = {
        PageIndex: 1,
        PageSize: 10,
        RecordCount: 0,
        showPageCount: 5,
        onPageClick: function (pageIndex) {
            return false;   //翻页事件
        }
    }
    if (options) {
        $.extend(configs, options);
    }

    var totalpage = Math.ceil(configs.RecordCount / configs.PageSize);

    var createDom = function (totalpage) {
        //显示分页按钮
        var pageNum = [];
        var begin;
        var len;
        if (totalpage > configs.showPageCount) {
            len = configs.showPageCount;
            if (configs.PageIndex >= (totalpage - 2)) {
                begin = totalpage - 4;
            } else if (configs.PageIndex <= 3) {
                begin = 1;
            } else {
                begin = configs.PageIndex - 2;
            }
        } else {
            len = totalpage;
            begin = 1;
        }
        //根据返回的总记录数计算当前页显示的数据
        for (var i = 0; i < len; i++) {
            var cur = configs.PageIndex;
            var showI = begin + i;
            if (cur == showI) {
                pageNum.push({ num: showI, cur: true });
            } else {
                pageNum.push({ num: showI, cur: false });
            }
        }

        var domtag = '';
        for (var item in pageNum) {
            domtag += '<li class="paginate_button ' + (pageNum[item].cur ? 'active' : '') + '" >';
            domtag += '<a class="jq_num_tag_a" data-pageIndex="' + pageNum[item].num + '">' + pageNum[item].num + '</a>';
            domtag += '</li>';
        }
        var dom = "";
        dom += '<div class="row">'
            + '<div class="col-sm-6">'
            + '<div class="dataTables_info pagination">'
            + '共 ' + totalpage + ' 页， ' + configs.RecordCount + ' 条'
            + '</div>'
            + '</div>'
            + '<div class="col-sm-6">'
            //+ '<div class="dataTables_paginate paging_simple_numbers">'
            + '<ul class="pagination pull-right">'
            + '<li class="paginate_button previous" id="DataTables_Table_0_previous">'
            + '<a class="jq_num_tag_a_prev">上一页</a>'
            + '</li>'
            + domtag
            + '<li class="paginate_button next" id="DataTables_Table_0_next">'
            + '<a class="jq_num_tag_a_next">下一页</a>'
            + '</li>'
            + '</ul>'
            //+ '</div>'
            + '</div>'
            + '</div >';
        return dom;
    }
    this.html("");
    var pager = this.html(createDom(totalpage));
    var that = this;

    var clickfunction = function () {
        $(pager).find('a').click(function () {
            if ($(this).hasClass("jq_num_tag_a_prev")) {
                if (configs.PageIndex > 1) {
                    configs.onPageClick(configs.PageIndex - 1);
                    configs.PageIndex = configs.PageIndex - 1;
                    that.html(createDom(totalpage));
                    clickfunction();
                }
            } else if ($(this).hasClass("jq_num_tag_a_next")) {
                if (configs.PageIndex < totalpage) {
                    configs.onPageClick(configs.PageIndex + 1);
                    configs.PageIndex = configs.PageIndex + 1;
                    that.html(createDom(totalpage));
                    clickfunction();
                }
            } else if ($(this).hasClass("jq_num_tag_a")) {
                configs.onPageClick(parseInt($(this).attr("data-pageIndex"), 10));
                configs.PageIndex = parseInt($(this).attr("data-pageIndex"), 10);
                that.html(createDom(totalpage));
                clickfunction();
            }
        });
    }
    clickfunction();
}