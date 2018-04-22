//function createReactTablePageSplit(gridOptions) {
//    var _options = gridOptions;
//
//    var PageComponent = React.createClass({
//        render: function () {
//            let _this = this;
//            //当前页码
//            let cur = this.props.current;
//            //显示分页按钮
//            let pageNum = [];
//            let begin;
//            let len;
//            if (_this.props.totalPage > 5) {
//                len = 5;
//                if (cur >= (_this.props.totalPage - 2)) {
//                    begin = _this.props.totalPage - 4;
//                } else if (cur <= 3) {
//                    begin = 1;
//                } else {
//                    begin = cur - 2;
//                }
//            } else {
//                len = _this.props.totalPage;
//                begin = 1;
//            }
//            //根据返回的总记录数计算当前页显示的数据
//            for (let i = 0; i < len; i++) {
//                let cur = this.props.current;
//                let showI = begin + i;
//                if (cur == showI) {
//                    pageNum.push({ num: showI, cur: true });
//                } else {
//                    pageNum.push({ num: showI, cur: false });
//                }
//            }
//            return (
//                <div className="row">
//                    <div className="col-sm-6">
//                        <div className="dataTables_info" id="DataTables_Table_0_info" role="alert" aria-live="polite" aria-relevant="all">
//                            共 {_this.props.totalPage} 页， {_this.props.total} 条
//                        </div>
//                    </div>
//                    <div className="col-sm-6">
//                        <div className="dataTables_paginate paging_simple_numbers" id="DataTables_Table_0_paginate">
//                            <ul className="pagination">
//                                <li className="paginate_button previous" id="DataTables_Table_0_previous">
//                                    <a onClick={this.props.goPrev.bind(this)}>上一页</a>
//                                </li>
//                                {
//                                    pageNum.map(function (curPageNum) {
//                                        return (
//                                            <li className="paginate_button " className={curPageNum.cur ? 'active' : ''}>
//                                                <a onClick={_this.props.pageClick.bind(_this, curPageNum.num)}>{curPageNum.num}</a>
//                                            </li>
//                                        )
//                                    })
//                                }
//                                <li className="paginate_button next" id="DataTables_Table_0_next">
//                                    <a onClick={this.props.goNext.bind(this)}>下一页</a>
//                                </li>
//                            </ul>
//                        </div>
//                    </div>
//                </div>
//            );
//        }
//    });
//
//    var ReactTable = React.createClass({
//        getInitialState: function () {
//            let _pageSize = parseInt(this.props.gridOptions.PageSize, 10);
//            if (_pageSize < 1 || isNaN(_pageSize)) {
//                _pageSize = 10;
//            }
//            return {
//                totalNum: 0,//总记录数
//                gridData: [],//表格数据
//                current: 1, //当前页码
//                pageSize: _pageSize, //每页显示的条数5条
//                totalPage: 0,//总页数
//                hidSelectdTr: ""
//            };
//        },
//        componentWillMount: function () {
//            //在渲染前调用,在客户端也在服务端。
//            this.pageClick(1);
//        },
//        componentDidMount: function () {
//            let that = this;
//            /*在第一次渲染后调用，只在客户端。
//            之后组件已经生成了对应的DOM结构，可以通过this.getDOMNode()来进行访问。
//            如果你想和其他JavaScript框架一起使用，可以在这个方法中调用setTimeout, setInterval或者发送AJAX请求等操作(防止异部操作阻塞UI)。*/
//            window.ReactObj = this;
//            //$("table tbody tr").click(function (e) {
//            //    that.chooseTr($(e.currentTarget).attr("data-index"));
//            //});
//        },
//        componentDidUpdate: function () {
//            
//        },
//        //点击翻页
//        pageClick: function (pageNum) {
//            let _this = this;
//            if (pageNum != _this.state.current) {
//                _this.state.current = pageNum;
//            }
//            _this.state.gridData = [];//清空之前的数据
//            let reqData = this.props.gridOptions.ajaxRequestData;
//            reqData.PageIndex = pageNum;
//            reqData.PageSize = _this.state.pageSize;
//            $.ajax({
//                url: this.props.gridOptions.ajaxUrl,
//                data: reqData,
//                dataType: 'json',
//                async: false,
//                success: function (data) {
//                    _this.setState({ gridData: data.gridData });
//                    _this.setState({ totalNum: data.totalNum });
//                    //计算总页数= 总记录数 / 每页显示的条数
//                    let totalPageCount = Math.ceil(data.totalNum / _this.state.pageSize);
//                    _this.setState({ totalPage: totalPageCount });
//                },
//                error: function (XMLHttpRequest) {
//                    console.log(XMLHttpRequest);
//                }
//            });
//        },
//        //上一步
//        goPrev: function () {
//            var _this = this;
//            let cur = this.state.current;
//            if (cur > 1) {
//                _this.pageClick(cur - 1);
//            }
//        },
//        //下一步
//        goNext: function () {
//            var _this = this;
//            let cur = _this.state.current;
//            if (cur < _this.state.totalPage) {
//                _this.pageClick(cur + 1);
//            }
//        },
//        chooseTr: function (_id) {
//            this.setState({ hidSelectdTr: _id });
//        },
//        render: function () {
//            let that = this;
//            let setData = function (data, field) {
//               
//                if (field.fields == that.props.gridOptions.controllerField) {
//                    return (<div dangerouslySetInnerHTML={{ __html: data[field.fields] }}></div>);
//                } else {
//                    return data[field.fields];
//                }
//            };
//            return (
//                <div className="ibox-content">
//                    <div id="DataTables_Table_0_wrapper" className="dataTables_wrapper form-inline" role="grid">
//                        <table className="table table-bordered table-hover">
//                            <thead>
//                                <tr role="row">
//                                    {
//                                        that.props.gridOptions.theadInfo.map(function (data) {
//                                            return (<th className="">{data.title}</th>)
//                                        })
//                                    }
//                                </tr>
//                            </thead>
//                            <tbody>
//                                {
//
//                                    that.state.gridData ? that.state.gridData.map(function (trData) {
//                                        if (trData) {
//                                            return (
//                                                <tr onClick={trData[that.props.gridOptions.trKey]?that.chooseTr.bind(this, trData[that.props.gridOptions.trKey]):""}>
//                                                    {
//                                                        that.props.gridOptions.tbodyInfo.map(function (dataField) {
//                                                            return (
//                                                                <td>
//                                                                    {
//                                                                        setData(trData, dataField)
//                                                                    }
//                                                                </td>
//                                                            )
//                                                        })
//                                                    }
//                                                </tr>
//                                            )
//                                        }
//                                    }) : ""
//
//                                }
//                            </tbody>
//                        </table>
//                        <PageComponent total={this.state.totalNum}
//                            current={this.state.current}
//                            totalPage={this.state.totalPage}
//                            pageClick={this.pageClick.bind(this)}
//                            goPrev={this.goPrev.bind(this)}
//                            goNext={this.goNext.bind(this)} />
//                        <input id="hidReactTableSelectedTr" type="hidden" value={this.state.hidSelectdTr} />
//                    </div>
//                </div>
//            );
//        }
//    });
//
//    ReactDOM.render(
//        <ReactTable gridOptions={_options} />,
//        document.getElementById('reactTable')
//    );
//}