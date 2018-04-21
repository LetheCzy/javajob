<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<jsp:include page="/WEB-INF/views/top.jsp" />
<title>List</title>
<jsp:include page="/WEB-INF/views/head.jsp" />
</head>
<body>
	<div class="wrapper animated fadeInUp" id="vueDiv">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>任务管理</h5>
                </div>
                <div class="ibox-title">
                    <div class="pull-right">
                        <a href="javascript:void(0)" class="btn btn-primary" v-on:click="modify(0)">JOB新增</a>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="ibox-content">
                    <form role="form" class="form-inline query-container" id="form_search">
                        <div class="form-group  m-l">
                            <label class="sr  m-r-xs">名称</label>
                            <input type="text" id="Name" name="Name" class="form-control" v-model="queryModel.Name">
                        </div>
                        <div class="form-group  m-l">
                            <label class="sr  m-r-xs">状态</label>
                            <select class="form-control" v-model="queryModel.Status">
                                <option v-for="item in statusList" v-bind:value="item.value">{{ item.text }}</option>
                            </select>
                        </div>
                        <div class="form-group  m-l">
                            <button class="btn btn-primary btn-search" type="button" v-on:click="getData(1,true)">搜索</button>
                        </div>
                    </form>
                    <div class="jqGrid_wrapper">
                        <table id="data_grid" class="table table-hover">
                            <colgroup>
                                <col />
                                <col />
                                <col />
                                <col />
                                <col />
                                <col style="width:20%" />
                            </colgroup>
                            <thead>
                                <tr role="row">
                                    <th class="">名称</th>
                                    <th class="">分类</th>
                                    <th class="">CRON</th>
                                    <th class="">状态</th>
                                    <th class="">描述</th>
                                    <th class="">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="data in gridData">
                                    <td>{{ data.jobname }}</td>
                                    <td>{{ data.jobTypeDesc }}</td>
                                    <td>{{ data.jobcron }}</td>
                                    <td>{{ data.jobStatusDesc }}</td>
                                    <td>{{ data.jobdescription }}</td>
                                    <td>
                                        <button type="button" class="btn btn-primary btn-sm" v-on:click="modify(data.id)">编辑</button>
                                        <button type="button" class="btn btn-primary btn-sm" v-on:click="changeStatus(data.id,2)">启动</button>
                                        <!-- <button type="button" class="btn btn-primary btn-sm" v-on:click="changeStatus(data.id,4)">暂停</button> -->
                                        <button type="button" class="btn btn-primary btn-sm" v-on:click="changeStatus(data.id,3)">停止</button>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div id="grid_pager_bar">
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<jsp:include page="/WEB-INF/views/foot.jsp" />
<script src="/mvc/js/plugins/PageSplit/JqueryPageSplitBar.js"></script>
    <script>
        var VueObj = new Vue({
            el: '#vueDiv',
            data: {
                statusList: {},
                queryModel: {
                    Name: "",
                    Status:"-1"
                },
                gridData: {},
                totalCount : 0
            },
            beforeMount: function () {
                this.init();
                this.getData(1,true);
            },
            methods: {
                init: function () {
                    var that = this;
                    $.ajax({
                        url: '/mvc/common/getjobstatuslist?isInit=1',
                        dataType: 'json',
                        async: false,
                        type: 'get',
                        success: function (data) {
                            if (data) {
                                that.statusList = data.data;
                            }
                        },
                        error: function (XMLHttpRequest) {
                            console.log(XMLHttpRequest);
                        },
                        complete: function () {
                        }
                    });
                },
                getData: function (pageindex,isGetCount) {
                    //加载层-默认风格
                    layer.load();
                    var that = this;
                    var reqData = {
                    		jobname: that.queryModel.Name,
                    		jobstatus: that.queryModel.Status,
                        isGetCount: isGetCount ? "1" : "0",
                        pageIndex: pageindex,
                        pageSize: 10
                    };
                    $.ajax({
                        url: '/mvc/job/getjoblist',
                        data: reqData,
                        dataType: 'json',
                        type: 'get',
                        success: function (data) {
                            if (data) {
                                that.gridData = data.data;
                                if (isGetCount) {
                                    that.totalCount = data.totalCount;
                                }
                                //设置分页信息
                                var pageOptions = {
                                    RecordCount: that.totalCount,  //设置数据总数
                                    onPageClick: function (pageIndex) {
                                        that.getData(pageIndex);
                                    }
                                }
                                //初始化分页栏
                                $('#grid_pager_bar').pageSplitBar(pageOptions);
                            } else {
                                that.gridData = {};
                                that.totalCount = 0;
                                $('#grid_pager_bar').pageSplitBar(pageOptions);
                            }
                        },
                        error: function (XMLHttpRequest) {
                            console.log(XMLHttpRequest);
                        },
                        complete: function () {
                            layer.closeAll('loading');
                        }
                    });
                },
                modify: function (id) {
                    layer.open({
                        type: 2,
                        title: 'edit',
                        shadeClose: true,
                        shade: 0.8,
                        area: ['60%', '80%'],
                        content: '/mvc/job/jobdetail?id=' + id
                    });
                },
                closeDialog: function () {
                    layer.closeAll();
                },
                changeStatus: function (_id,status) {
                    var that = this;
                    $.ajax({
                        url: '/mvc/job/changestatus',
                        dataType: "json",
                        data:JSON.stringify({
                            id: _id,
                            jobstatus: status
                        }),
                        type: 'post',
                        contentType:'application/json',
                        success: function (data) {
                            if (data && data.success) {
                                layer.msg('操作成功', {
                                    icon: 1,
                                    time: 1000
                                }, function () {
                                    that.getData(1, true);
                                });
                            }
                            else {
                                layer.open({
                                    content: data ? data.msg : ''
                                });
                            }
                        },
                        error: function (XMLHttpRequest) {
                            console.log(XMLHttpRequest);
                        },
                        complete: function () {
                        }
                    });
                }
            }
        });
    </script>
</html>