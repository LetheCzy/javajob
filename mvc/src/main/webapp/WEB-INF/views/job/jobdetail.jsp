<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<jsp:include page="/WEB-INF/views/top.jsp" />
<title>Edit</title>
<jsp:include page="/WEB-INF/views/head.jsp" />
<link href="/mvc/css/plugins/Cron/themes/bootstrap/easyui.min.css"
	rel="stylesheet" />
<link href="/mvc/css/plugins/Cron/themes/icon.css" rel="stylesheet" />
<link href="/mvc/css/plugins/Cron/icon.css" rel="stylesheet" />
<style type="text/css">
.line {
	height: 25px;
	line-height: 25px;
	margin: 3px;
}

.imp {
	padding-left: 25px;
}

.col {
	width: 95px;
}

ul {
	list-style: none;
	padding-left: 10px;
}

li {
	height: 20px;
}
</style>
</head>
<body>
	<div class="wrapper animated fadeInUp" id="vueDiv">
    <div class="row" style="position:relative">
        <div class="col-sm-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5>任务管理</h5>
                </div>
                <div class="ibox-content">
                    <form role="form" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Job分类</label>
                            <div class="col-sm-10">
                                <select class="form-control" id="JobType" v-model="entity.jobtype">
                                    <option v-for="item in levelList" v-bind:value="item.value">{{ item.text }}</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Job名称<br />(与代码中一致)</label>
                            <div class="col-sm-10">
                                <input type="text" class="form-control" id="JobName" v-model="entity.jobname">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">Cron表达式</label>
                            <div class="col-sm-10">
                                <div class="easyui-layout" style="height:560px;border: 1px rgb(202, 196, 196) solid;border-radius: 5px;">
                                    <div style="height: 100%;">
                                        <div class="easyui-tabs" data-options="fit:true,border:false">
                                            <div title="秒">
                                                <div class="line">
                                                    <input type="radio" checked="checked" name="second" onclick="everyTime(this)">
                                                    每秒 允许的通配符[, - * /]
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="second" onclick="cycle(this)">
                                                    周期从
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:1,max:58" value="1"
                                                           id="secondStart_0">
                                                    -
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:2,max:59" value="2"
                                                           id="secondEnd_0">
                                                    秒
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="second" onclick="startOn(this)">
                                                    从
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:0,max:59" value="0"
                                                           id="secondStart_1">
                                                    秒开始,每
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:1,max:59" value="1"
                                                           id="secondEnd_1">
                                                    秒执行一次
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="second" id="sencond_appoint">
                                                    指定
                                                </div>
                                                <div class="imp secondList">
                                                    <input type="checkbox" value="0">00
                                                    <input type="checkbox" value="1">01
                                                    <input type="checkbox" value="2">02
                                                    <input type="checkbox" value="3">03
                                                    <input type="checkbox" value="4">04
                                                    <input type="checkbox" value="5">05
                                                    <input type="checkbox" value="6">06
                                                    <input type="checkbox" value="7">07
                                                    <input type="checkbox" value="8">08
                                                    <input type="checkbox" value="9">09
                                                </div>
                                                <div class="imp secondList">
                                                    <input type="checkbox" value="10">10
                                                    <input type="checkbox" value="11">11
                                                    <input type="checkbox" value="12">12
                                                    <input type="checkbox" value="13">13
                                                    <input type="checkbox" value="14">14
                                                    <input type="checkbox" value="15">15
                                                    <input type="checkbox" value="16">16
                                                    <input type="checkbox" value="17">17
                                                    <input type="checkbox" value="18">18
                                                    <input type="checkbox" value="19">19
                                                </div>
                                                <div class="imp secondList">
                                                    <input type="checkbox" value="20">20
                                                    <input type="checkbox" value="21">21
                                                    <input type="checkbox" value="22">22
                                                    <input type="checkbox" value="23">23
                                                    <input type="checkbox" value="24">24
                                                    <input type="checkbox" value="25">25
                                                    <input type="checkbox" value="26">26
                                                    <input type="checkbox" value="27">27
                                                    <input type="checkbox" value="28">28
                                                    <input type="checkbox" value="29">29
                                                </div>
                                                <div class="imp secondList">
                                                    <input type="checkbox" value="30">30
                                                    <input type="checkbox" value="31">31
                                                    <input type="checkbox" value="32">32
                                                    <input type="checkbox" value="33">33
                                                    <input type="checkbox" value="34">34
                                                    <input type="checkbox" value="35">35
                                                    <input type="checkbox" value="36">36
                                                    <input type="checkbox" value="37">37
                                                    <input type="checkbox" value="38">38
                                                    <input type="checkbox" value="39">39
                                                </div>
                                                <div class="imp secondList">
                                                    <input type="checkbox" value="40">40
                                                    <input type="checkbox" value="41">41
                                                    <input type="checkbox" value="42">42
                                                    <input type="checkbox" value="43">43
                                                    <input type="checkbox" value="44">44
                                                    <input type="checkbox" value="45">45
                                                    <input type="checkbox" value="46">46
                                                    <input type="checkbox" value="47">47
                                                    <input type="checkbox" value="48">48
                                                    <input type="checkbox" value="49">49
                                                </div>
                                                <div class="imp secondList">
                                                    <input type="checkbox" value="50">50
                                                    <input type="checkbox" value="51">51
                                                    <input type="checkbox" value="52">52
                                                    <input type="checkbox" value="53">53
                                                    <input type="checkbox" value="54">54
                                                    <input type="checkbox" value="55">55
                                                    <input type="checkbox" value="56">56
                                                    <input type="checkbox" value="57">57
                                                    <input type="checkbox" value="58">58
                                                    <input type="checkbox" value="59">59
                                                </div>
                                            </div>
                                            <div title="分钟">
                                                <div class="line">
                                                    <input type="radio" checked="checked" name="min" onclick="everyTime(this)">
                                                    分钟 允许的通配符[, - * /]
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="min" onclick="cycle(this)">
                                                    周期从
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:1,max:58" value="1"
                                                           id="minStart_0">
                                                    -
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:2,max:59" value="2"
                                                           id="minEnd_0">
                                                    分钟
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="min" onclick="startOn(this)">
                                                    从
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:0,max:59" value="0"
                                                           id="minStart_1">
                                                    分钟开始,每
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:1,max:59" value="1"
                                                           id="minEnd_1">
                                                    分钟执行一次
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="min" id="min_appoint">
                                                    指定
                                                </div>
                                                <div class="imp minList">
                                                    <input type="checkbox" value="0">00
                                                    <input type="checkbox" value="1">01
                                                    <input type="checkbox" value="2">02
                                                    <input type="checkbox" value="3">03
                                                    <input type="checkbox" value="4">04
                                                    <input type="checkbox" value="5">05
                                                    <input type="checkbox" value="6">06
                                                    <input type="checkbox" value="7">07
                                                    <input type="checkbox" value="8">08
                                                    <input type="checkbox" value="9">09
                                                </div>
                                                <div class="imp minList">
                                                    <input type="checkbox" value="10">10
                                                    <input type="checkbox" value="11">11
                                                    <input type="checkbox" value="12">12
                                                    <input type="checkbox" value="13">13
                                                    <input type="checkbox" value="14">14
                                                    <input type="checkbox" value="15">15
                                                    <input type="checkbox" value="16">16
                                                    <input type="checkbox" value="17">17
                                                    <input type="checkbox" value="18">18
                                                    <input type="checkbox" value="19">19
                                                </div>
                                                <div class="imp minList">
                                                    <input type="checkbox" value="20">20
                                                    <input type="checkbox" value="21">21
                                                    <input type="checkbox" value="22">22
                                                    <input type="checkbox" value="23">23
                                                    <input type="checkbox" value="24">24
                                                    <input type="checkbox" value="25">25
                                                    <input type="checkbox" value="26">26
                                                    <input type="checkbox" value="27">27
                                                    <input type="checkbox" value="28">28
                                                    <input type="checkbox" value="29">29
                                                </div>
                                                <div class="imp minList">
                                                    <input type="checkbox" value="30">30
                                                    <input type="checkbox" value="31">31
                                                    <input type="checkbox" value="32">32
                                                    <input type="checkbox" value="33">33
                                                    <input type="checkbox" value="34">34
                                                    <input type="checkbox" value="35">35
                                                    <input type="checkbox" value="36">36
                                                    <input type="checkbox" value="37">37
                                                    <input type="checkbox" value="38">38
                                                    <input type="checkbox" value="39">39
                                                </div>
                                                <div class="imp minList">
                                                    <input type="checkbox" value="40">40
                                                    <input type="checkbox" value="41">41
                                                    <input type="checkbox" value="42">42
                                                    <input type="checkbox" value="43">43
                                                    <input type="checkbox" value="44">44
                                                    <input type="checkbox" value="45">45
                                                    <input type="checkbox" value="46">46
                                                    <input type="checkbox" value="47">47
                                                    <input type="checkbox" value="48">48
                                                    <input type="checkbox" value="49">49
                                                </div>
                                                <div class="imp minList">
                                                    <input type="checkbox" value="50">50
                                                    <input type="checkbox" value="51">51
                                                    <input type="checkbox" value="52">52
                                                    <input type="checkbox" value="53">53
                                                    <input type="checkbox" value="54">54
                                                    <input type="checkbox" value="55">55
                                                    <input type="checkbox" value="56">56
                                                    <input type="checkbox" value="57">57
                                                    <input type="checkbox" value="58">58
                                                    <input type="checkbox" value="59">59
                                                </div>
                                            </div>
                                            <div title="小时">
                                                <div class="line">
                                                    <input type="radio" checked="checked" name="hour" onclick="everyTime(this)">
                                                    小时 允许的通配符[, - * /]
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="hour" onclick="cycle(this)">
                                                    周期从
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:0,max:23" value="0"
                                                           id="hourStart_0">
                                                    -
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:2,max:23" value="2"
                                                           id="hourEnd_1">
                                                    小时
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="hour" onclick="startOn(this)">
                                                    从
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:0,max:23" value="0"
                                                           id="hourStart_1">
                                                    小时开始,每
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:1,max:23" value="1"
                                                           id="hourEnd_1">
                                                    小时执行一次
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="hour" id="hour_appoint">
                                                    指定
                                                </div>
                                                <div class="imp hourList">
                                                    AM:
                                                    <input type="checkbox" value="0">00
                                                    <input type="checkbox" value="1">01
                                                    <input type="checkbox" value="2">02
                                                    <input type="checkbox" value="3">03
                                                    <input type="checkbox" value="4">04
                                                    <input type="checkbox" value="5">05
                                                    <input type="checkbox" value="6">06
                                                    <input type="checkbox" value="7">07
                                                    <input type="checkbox" value="8">08
                                                    <input type="checkbox" value="9">09
                                                    <input type="checkbox" value="10">10
                                                    <input type="checkbox" value="11">11
                                                </div>
                                                <div class="imp hourList">
                                                    PM:
                                                    <input type="checkbox" value="12">12
                                                    <input type="checkbox" value="13">13
                                                    <input type="checkbox" value="14">14
                                                    <input type="checkbox" value="15">15
                                                    <input type="checkbox" value="16">16
                                                    <input type="checkbox" value="17">17
                                                    <input type="checkbox" value="18">18
                                                    <input type="checkbox" value="19">19
                                                    <input type="checkbox" value="20">20
                                                    <input type="checkbox" value="21">21
                                                    <input type="checkbox" value="22">22
                                                    <input type="checkbox" value="23">23
                                                </div>
                                            </div>
                                            <div title="日">
                                                <div class="line">
                                                    <input type="radio" checked="checked" name="day" onclick="everyTime(this)">
                                                    日 允许的通配符[, - * / L W]
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="day" onclick="unAppoint(this)">
                                                    不指定
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="day" onclick="cycle(this)">
                                                    周期从
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:1,max:31" value="1"
                                                           id="dayStart_0">
                                                    -
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:2,max:31" value="2"
                                                           id="dayEnd_0">
                                                    日
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="day" onclick="startOn(this)">
                                                    从
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:1,max:31" value="1"
                                                           id="dayStart_1">
                                                    日开始,每
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:1,max:31" value="1"
                                                           id="dayEnd_1">
                                                    天执行一次
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="day" onclick="workDay(this)">
                                                    每月
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:1,max:31" value="1"
                                                           id="dayStart_2">
                                                    号最近的那个工作日
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="day" onclick="lastDay(this)">
                                                    本月最后一天
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="day" id="day_appoint">
                                                    指定
                                                </div>
                                                <div class="imp dayList">
                                                    <input type="checkbox" value="1">1
                                                    <input type="checkbox" value="2">2
                                                    <input type="checkbox" value="3">3
                                                    <input type="checkbox" value="4">4
                                                    <input type="checkbox" value="5">5
                                                    <input type="checkbox" value="6">6
                                                    <input type="checkbox" value="7">7
                                                    <input type="checkbox" value="8">8
                                                    <input type="checkbox" value="9">9
                                                    <input type="checkbox" value="10">10
                                                    <input type="checkbox" value="11">11
                                                    <input type="checkbox" value="12">12
                                                    <input type="checkbox" value="13">13
                                                    <input type="checkbox" value="14">14
                                                    <input type="checkbox" value="15">15
                                                    <input type="checkbox" value="16">16
                                                </div>
                                                <div class="imp dayList">
                                                    <input type="checkbox" value="17">17
                                                    <input type="checkbox" value="18">18
                                                    <input type="checkbox" value="19">19
                                                    <input type="checkbox" value="20">20
                                                    <input type="checkbox" value="21">21
                                                    <input type="checkbox" value="22">22
                                                    <input type="checkbox" value="23">23
                                                    <input type="checkbox" value="24">24
                                                    <input type="checkbox" value="25">25
                                                    <input type="checkbox" value="26">26
                                                    <input type="checkbox" value="27">27
                                                    <input type="checkbox" value="28">28
                                                    <input type="checkbox" value="29">29
                                                    <input type="checkbox" value="30">30
                                                    <input type="checkbox" value="31">31
                                                </div>
                                            </div>
                                            <div title="月">
                                                <div class="line">
                                                    <input type="radio" checked="checked" name="mouth" onclick="everyTime(this)">
                                                    月 允许的通配符[, - * /]
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="mouth" onclick="unAppoint(this)">
                                                    不指定
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="mouth" onclick="cycle(this)">
                                                    周期从
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:1,max:12" value="1"
                                                           id="mouthStart_0">
                                                    -
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:2,max:12" value="2"
                                                           id="mouthEnd_0">
                                                    月
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="mouth" onclick="startOn(this)">
                                                    从
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:1,max:12" value="1"
                                                           id="mouthStart_1">
                                                    日开始,每
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:1,max:12" value="1"
                                                           id="mouthEnd_1">
                                                    月执行一次
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="mouth" id="mouth_appoint">
                                                    指定
                                                </div>
                                                <div class="imp mouthList">
                                                    <input type="checkbox" value="1">1
                                                    <input type="checkbox" value="2">2
                                                    <input type="checkbox" value="3">3
                                                    <input type="checkbox" value="4">4
                                                    <input type="checkbox" value="5">5
                                                    <input type="checkbox" value="6">6
                                                    <input type="checkbox" value="7">7
                                                    <input type="checkbox" value="8">8
                                                    <input type="checkbox" value="9">9
                                                    <input type="checkbox" value="10">10
                                                    <input type="checkbox" value="11">11
                                                    <input type="checkbox" value="12">12
                                                </div>
                                            </div>
                                            <div title="周">
                                                <div class="line">
                                                    <input type="radio" checked="checked" name="week" onclick="everyTime(this)">
                                                    周 允许的通配符[, - * / L #]
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="week" onclick="unAppoint(this)">
                                                    不指定
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="week" onclick="startOn(this)">
                                                    周期 从星期<input class="numberspinner" style="width: 60px;" data-options="min:1,max:7"
                                                                 id="weekStart_0" value="1">
                                                    -
                                                    <input class="numberspinner" style="width: 60px;" data-options="min:2,max:7" value="2"
                                                           id="weekEnd_0">
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="week" onclick="weekOfDay(this)">
                                                    第<input class="numberspinner" style="width: 60px;" data-options="min:1,max:4" value="1"
                                                            id="weekStart_1">
                                                    周 的星期<input class="numberspinner" style="width: 60px;" data-options="min:1,max:7"
                                                                id="weekEnd_1" value="1">
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="week" onclick="lastWeek(this)">
                                                    本月最后一个星期<input class="numberspinner" style="width: 60px;" data-options="min:1,max:7"
                                                                   id="weekStart_2" value="1">
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="week" id="week_appoint">
                                                    指定
                                                </div>
                                                <div class="imp weekList">
                                                    <input type="checkbox" value="1">1
                                                    <input type="checkbox" value="2">2
                                                    <input type="checkbox" value="3">3
                                                    <input type="checkbox" value="4">4
                                                    <input type="checkbox" value="5">5
                                                    <input type="checkbox" value="6">6
                                                    <input type="checkbox" value="7">7
                                                </div>
                                            </div>
                                            <div title="年">
                                                <div class="line">
                                                    <input type="radio" checked="checked" name="year" onclick="unAppoint(this)">
                                                    不指定 允许的通配符[, - * /] 非必填
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="year" onclick="everyTime(this)">
                                                    每年
                                                </div>
                                                <div class="line">
                                                    <input type="radio" name="year" onclick="cycle(this)">周期 从
                                                    <input class="numberspinner" style="width: 90px;" data-options="min:2013,max:3000"
                                                           id="yearStart_0" value="2013">
                                                    -
                                                    <input class="numberspinner" style="width: 90px;" data-options="min:2014,max:3000"
                                                           id="yearEnd_0" value="2014">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div data-options="region:'south',border:false" style="height:250px">
                                        <fieldset style="border-radius: 3px; height: 220px;">
                                            <legend>表达式</legend>
                                            <table style="height: 100px;">
                                                <tbody>
                                                    <tr>
                                                        <td align="center">
                                                            秒
                                                        </td>
                                                        <td align="center">
                                                            分钟
                                                        </td>
                                                        <td align="center">
                                                            小时
                                                        </td>
                                                        <td align="center">
                                                            日
                                                        </td>
                                                        <td align="center">
                                                            月<br />
                                                        </td>
                                                        <td align="center">
                                                            星期
                                                        </td>
                                                        <td align="center">
                                                            年
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td>
                                                            <input type="text" name="v_second" class="col" value="*" readonly="readonly" />
                                                        </td>
                                                        <td>
                                                            <input type="text" name="v_min" class="col" value="*" readonly="readonly" />
                                                        </td>
                                                        <td>
                                                            <input type="text" name="v_hour" class="col" value="*" readonly="readonly" />
                                                        </td>
                                                        <td>
                                                            <input type="text" name="v_day" class="col" value="*" readonly="readonly" />
                                                        </td>
                                                        <td>
                                                            <input type="text" name="v_mouth" class="col" value="*" readonly="readonly" />
                                                        </td>
                                                        <td>
                                                            <input type="text" name="v_week" class="col" value="?" readonly="readonly" />
                                                        </td>
                                                        <td>
                                                            <input type="text" name="v_year" class="col" readonly="readonly" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style="text-align:center">Cron：</td>
                                                        <td colspan="6">
                                                            <input type="text" name="cron" style="width: 100%;border:0px" id="cron" v-model="entity.jobcron" onfocus="this.blur();" />
                                                        </td>
                                                        <td><!-- <input type="button" value="反解析到UI " id="btnFan" onclick="btnFan()" /> -->
															</td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="8">最近5次运行时间:</td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="8" id="runTime"></td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </fieldset>
                                    </div>
                                    <div>
                                    </div>
                                    <input id="hidRunTimeAddress" type="hidden" value="/mvc/job/gettaskefiretime" />
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">描述</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" rows="3" id="jobDescription" v-model="entity.jobdescription"></textarea>
                            </div>
                        </div>
                    </form>
                    <div class="pull-right">
                        <button type="button" class="btn btn-primary" v-on:click="save()">保存</button>
                        <button type="button" class="btn btn-primary" onclick="parent.VueObj.closeDialog();">关闭</button>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>
</div>
	<input type="hidden" id="hidId" value="${id}">
</body>
<jsp:include page="/WEB-INF/views/foot.jsp" />
<script src="/mvc/js/plugins/Cron/jquery.easyui.min.js"></script>
<script src="/mvc/js/plugins/Cron/cron.js?v=1"></script>
<script>
        var VueObj = new Vue({
            el: '#vueDiv',
            data: {
                Id: parseInt($("#hidId").val(), 10),
                entity: {
                	id:0,
                    jobtype: "-1",
                    jobcron: "* * * * * ?"
                },
                statusList: {},
                levelList: {}
            },
            beforeMount: function () {
                this.init();
                if (this.Id > 0) {
                    this.getData();
                }
            },
            updated: function () {
                btnFan();
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

                    $.ajax({
                        url: '/mvc/common/getjoblevellist?isInit=1',
                        dataType: 'json',
                        async: false,
                        type: 'get',
                        success: function (data) {
                            if (data) {
                                that.levelList = data.data;
                            }
                        },
                        error: function (XMLHttpRequest) {
                            console.log(XMLHttpRequest);
                        },
                        complete: function () {
                        }
                    });
                },
                getData: function () {
                    //加载层-默认风格
                    layer.load();
                    var that = this;
                    var reqData = {
                        id: that.Id
                    };
                    $.ajax({
                        url: '/mvc/job/getjobdetail',
                        data: reqData,
                        dataType: 'json',
                        type: 'get',
                        success: function (data) {
                            if (data) {
                                that.entity = data;
                                //setTimeout(btnFan, 500);
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
                save: function () {
                    //加载层-默认风格
                    var that = this;
                    that.entity.jobcron = $.trim($("#cron").val());
                    var reqData = that.entity;
                    $.ajax({
                        url: '/mvc/job/savejobdata',
                        data: JSON.stringify(reqData),
                        dataType: 'json',
                        type: 'post',
                        contentType:'application/json;charset=utf-8',
                        success: function (data) {
                            if (data && data.success) {
                                layer.msg('保存成功', {
                                    icon: 1,
                                    time: 1000
                                }, function () {
                                    parent.VueObj.getData(1, true);
                                    parent.VueObj.closeDialog();
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