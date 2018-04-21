<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title></title>
    <!--[if lt IE 8]>
    <script>
        alert('站点 不支持IE6-8，请使用谷歌、火狐等浏览器\n或360、QQ等国产浏览器的极速模式浏览本页面！');
    </script>
    <![endif]-->
    <link href="/mvc/css/css/bootstrap.min.css?v=1.0.0" rel="stylesheet">
    <link href="/mvc/css/css/font-awesome.min.css?v=1.0.0" rel="stylesheet">
    <link href="/mvc/css/css/animate.min.css" rel="stylesheet">
    <link href="/mvc/css/css/style.min.css?v=1.0.0" rel="stylesheet">
</head>
<body class="fixed-sidebar full-height-layout gray-bg">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close">
                <i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span>
                                <img alt="image" width="64" height="64" class="img-circle" src="/mvc/css/img/header.jpg" />
                            </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="javascript:void(0)">
                                <span class="clear">
                                    <span class="block m-t-xs"><strong class="font-bold">Account</strong></span>
                                    <span class="text-muted text-xs block">Account<b class="caret"></b></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <!-- <li class="divider"></li>
                                    <li><a href="~/Account/Logout">安全退出</a>
                                </li> -->
                            </ul>
                        </div>
                        <div class="logo-element">
                            Jobs
                        </div>
                    </li>
                    <li>
                        <a href="javascript:void(0)">
                            <i class="fa menuParent.Icon"></i>
                            <span class="nav-label">JOB任务管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="/mvc/job/joblist">任务管理</a>
                                <a class="J_menuItem" href="/BackgroundJobLog/">运行日志</a>
                                <a class="J_menuItem" href="/mvc/job/cron">cron表达式</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft">
                    <i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="/Main/Welcome">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight">
                    <i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">
                        关闭操作<span class="caret"></span>
                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive">
                            <a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll">
                            <a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther">
                            <a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="~/Account/Logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i>退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="/mvc/job/joblist" frameborder="0" data-id="/mvc/job/joblist" seamless></iframe>
            </div>
            <div class="footer">
                <div style="float: left">
                    <a class="navbar-minimalize btn btn-primary btn-xs " href="javascript:;">
                        <i class="fa fa-exchange"></i>
                    </a>
                </div>
                <div class="pull-right">
                    &copy; 2018 <a href="###" target="_blank">czy</a>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>
    <!-- 全局js -->
    <script src="/mvc/js/jquery/jquery-2.1.1.min.js" type="text/javascript"></script>
    <script src="/mvc/js/bootstrap/bootstrap.min.js?v=1.0.0" type="text/javascript"></script>
    <script src="/mvc/js/plugins/metisMenu/jquery.metisMenu.js" type="text/javascript"></script>
    <script src="/mvc/js/plugins/slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src="/mvc/js/plugins/layer-v2.0/layer.js?v=1.0.0" type="text/javascript"></script>
    <!-- 自定义js -->
    <script src="/mvc/js/js/hplus.min.js?v=1.0.0" type="text/javascript"></script>
    <script src="/mvc/js/js/contabs.min.js?v=1.0.2" type="text/javascript"></script>
    <script type="text/javascript">

    </script>
</body>
</html>