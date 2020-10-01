<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Comp34 atible" content="IE=edge">
    <meta content="width=device-width,initial-scale=1.0" name="viewport">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">

    <title th:text="${r'#'}{mo.admin.title}">MO 快速开发平台</title>

    <link th:href="@{/css/SYSFrame.css}" rel="stylesheet" type="text/css" />
    <link th:href="@{/css/module.css}" rel="stylesheet" type="text/css" />
    <link th:href="@{/css/loaders.css}" rel="stylesheet" type="text/css" />
    <link th:href="@{/skin/black/skin.css}" rel="stylesheet" type="text/css" id="skin" />
    <link th:href="@{font/iconfont.css}" rel="stylesheet" type="text/css" />

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.bootcss.com/bootstrap-table/1.11.1/bootstrap-table.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.bootcss.com/jquery-treegrid/0.2.0/css/jquery.treegrid.min.css">
    <!-- 引入validator -->
    <link href="http://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet" />
</head>
<body>
<div id="container">
    <div>
        <form class="form-inline" id="searchBox">
            <#list columns as column>
                <#if column.dataType!='Date'>
                <#if column.fieldName!='id' && column.fieldName!='updateAccountNo' && column.fieldName!='createAccountNo'>
            <div class="form-group">
                <label for="search-${column.fieldName}">${column.columnComment}</label>
                <input type="text"  class="form-control" id="search-${column.fieldName}" name="${column.fieldName}" placeholder="">
            </div>
                <#elseif column.fieldName!='updateAccountNo' && column.fieldName!='createAccountNo'>
            <div class="form-group hidden">
                <label for="search-${column.fieldName}">${column.columnComment}</label>
                <input type="text"  class="form-control" id="search-${column.fieldName}" name="${column.fieldName}" placeholder="">
            </div>
                </#if>
                <#else >
            <div class="form-group">
                <label for="${column.fieldName}" class="col-sm-2 col-xs-2 control-label">${column.columnComment}</label>
                <div  class="input-group date col-sm-4 col-xs-4" id="search-${column.fieldName}">
                    <input type='text' class="form-control" name="${column.fieldName}" />
                    <span class="input-group-addon">
                            <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
                </#if>
            </#list>
            <button type="button" class="btn btn-success" onclick="${htmlName}.queryPage()">查询</button>
            <button type="reset" class="btn btn-success">重置</button>
        </form>
        <button class="btn btn-info pull-right" onclick="${htmlName}.insert${className}()">新增</button>
        <button class="btn btn-info pull-right" onclick="${htmlName}.export${className}()">导出</button>
    </div>
    <#-- 表格内容 -->
    <div style="margin-top: 1%">
        <table class="table table-striped" id="${tableName}"></table>
    </div>
    <div style="margin-top: 1%">
        <table class="table table-striped" id="${tableName}1">
            <thead>
            <tr>
                <#if columns??>
                    <#list columns as column>
                <th data-field="${column.fieldName}">[[${r'#'}{mo.admin.${className}.${column.fieldName}}]]</th>
                    </#list>
                </#if>
                <th data-field="">[[#{mo.admin.operation}]]</th>
            </tr>
            </thead>
        </table>
    </div>


    <!--模态框-->
    <div class="modal fade" tabindex="-1" role="dialog" id="updateModal" style="margin-top: 10%;">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title">新增${tableNameColumn}</h4>
                </div>
                <div class="modal-body">
                    <form role="form" class="form-horizontal center-block" id="updateForm">
                        <#list columns as column>
                            <#if column.dataType!='Date'>
                                <#if column.fieldName == 'id' && column.fieldName!='updateAccountNo' && column.fieldName!='createAccountNo'>
                        <div class="form-group hidden">
                            <label for="${column.fieldName}" class="col-sm-2 col-xs-2 control-label">${column.columnComment}</label>
                            <div  class="col-sm-4 col-xs-4 ">
                                <#if column.dataType=='Integer'>
                                <input type="number" class="form-control" id="${column.fieldName}" name="${column.fieldName}" placeholder="">
                                <#else >
                                <input type="text" class="form-control" id="${column.fieldName}" name="${column.fieldName}" placeholder="">
                                </#if>
                            </div>
                        </div>
                                <#elseif column.fieldName!='updateAccountNo' && column.fieldName!='createAccountNo'>
                        <div class="form-group">
                            <label for="${column.fieldName}" class="col-sm-2 col-xs-2 control-label">${column.columnComment}</label>
                            <div  class="col-sm-4 col-xs-4 ">
                                <#if column.dataType=='Integer'>
                                <input type="number" class="form-control" id="${column.fieldName}" name="${column.fieldName}" placeholder="">
                                <#else>
                                <input type="text" class="form-control" id="${column.fieldName}" name="${column.fieldName}" placeholder="">
                                </#if>
                            </div>
                        </div>
                                </#if>
                            <#else >
                        <div class="form-group">
                            <label for="${column.fieldName}" class="control-label">${column.columnComment}</label>
                            <div  class="input-group date" id="${column.fieldName}">
                                <input type='text' class="form-control" name="${column.fieldName}" />
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div>
                        </div>
                            </#if>
                        </#list>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="${htmlName}.save${className}()">保存</button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
</div>
</body>
<script th:src="@{/js/jquery-1.9.1.min.js}" type="text/javascript"></script>
<script th:src="@{/js/jquery.cookie.js}" type="text/javascript"></script>
<script th:src="@{/js/jquery.nicescroll.js}" type="text/javascript"></script>
<script th:src="@{/js/SYSFrame.js}" type="text/javascript"></script>
<script th:src="@{/js/layer/layer.js}" type="text/javascript"></script>
<script th:src="@{/js/sysbox.js}" type="text/javascript"></script>
<!--[if lt IE 9]>
<script th:src="@{/js/html5shiv.js}" type="text/javascript"></script>
<script th:src="@{/js/css3-mediaqueries.js}" type="text/javascript"></script>
<![endif]-->


<script src="https://cdn.bootcss.com/bootstrap-table/1.12.1/bootstrap-table.min.js"></script>
<script src="https://unpkg.com/bootstrap-table@1.12.1/dist/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-table/1.12.0/extensions/treegrid/bootstrap-table-treegrid.js"></script>
<script src="https://cdn.bootcss.com/jquery-treegrid/0.2.0/js/jquery.treegrid.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>


<!-- 初始化表格 -->
<script type="text/javascript">
    $(function () {
        init${className}Table();
        formValidator(); //初始画表单校验规则
        datePickerInit();
    })

    var ${htmlName}={
        action:{
            insert:'[[@{/${classNameLower}/insert}]]',
            deleteById : '[[@{/${classNameLower}/deleteById}]]',
            updateById : '[[@{/${classNameLower}/updateById}]]',
            queryAll : '[[@{/${classNameLower}/queryAll}]]',
            queryPage : '[[@{/${classNameLower}/queryPage}]]',
            export : '[[@{/${classNameLower}/export}]]'
        },
        //新增
        insert${className}:function(){
            $("#updateModal").modal('show');
            <#list columns as column>
            $("#${column.fieldName}").val('')
            </#list >
        },
        //编辑
        edit${className}:function (row) {
            $("#updateModal").modal('show');
            <#list columns as column>
            $("#${column.fieldName}").val(row.${column.fieldName})
            </#list>
        },
        //删除
        delete${className}:function (row) {
            if(confirm("是否确认操作！")){
                $.ajax({
                    type:"POST",
                    url:${htmlName}.action.deleteById,
                    data:{"id":row.id},
                    success:function (res) {
                        if(res.code==200){
                            layer.msg(res.msg,{icon:1,time:2000});
                            init${className}Table();
                        }else {
                            layer.msg("删除失败！",{icon:2,time:2000});
                        }
                    },
                    error:function () {
                        layer.msg("删除失败！",{icon:2,time:2000});
                    }
                })
            }

        },
        //保存
        save${className}:function () {
            var id=$("#id").val();
            var url=id?${htmlName}.action.updateById:${htmlName}.action.insert;
            // /*手动验证表单，当是普通按钮时。*/
            $('#updateForm').data('bootstrapValidator').validate();//启用验证
            var flag = $('#updateForm').data('bootstrapValidator').isValid()
            if(flag){
                $.ajax({
                    type:"POST",
                    url:url,
                    data:$("#updateForm").serialize(),
                    success:function (res) {
                        if(res.code==200){
                            layer.msg(res.msg,{icon:1,time:1000});
                            $("#updateModal").modal('hide');
                            init${className}Table();
                        }else {
                            layer.msg(res.msg,{icon:2,time:1000});
                        }
                    },
                    error:function () {
                        layer.msg("操作失败！",{icon:2,time:1000});
                    }
                })
            }
        },
        //查询
        queryPage:function () {
            //alert("ni")
            $.ajax({
                type:'GET',
                url:${htmlName}.action.queryPage,
                data:$("#searchBox").serialize(),
                success:function (res) {
                    $("#${tableName}").bootstrapTable("load",res);
                },
                error:function () {
                    layer.msg("查询失败！",{icon:2,time:1000})
                }
            })
        },
        //导出文档
        export${className}:function () {
            $.ajax({
                type:"GET",
                url:${htmlName}.action.export,
                success:function (res) {
                    if(res.code==200){
                        layer.msg(res.msg,{icon:1,time:1000});
                    }
                },
                error:function () {
                    layer.msg("操作失败！",{icon:1,time:1000});
                }
            })
        }

    }

    /**
     * 表单校验规则
     */
    function formValidator(){
        $("#updateForm").bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            <#list columns as column>
            <#if column.fieldName !='id'>
            fields: {
                ${column.fieldName}: {
                    message: '${column.columnComment}校验失败',
                    validators: {
                        notEmpty: {message: '${column.columnComment}不能为空'},
                        stringLength: {min: 1, max: 20, message: '${column.columnComment}长度必须在1到20位之间'}
                    }
                }
            },
            </#if>
            </#list>
        })
    }

    function init${className}Table() {
        $table = $("#${tableName}");
        $table.bootstrapTable('destroy')
        $table.bootstrapTable({
            url: ${htmlName}.action.queryPage,//请求路径
            uniqueId: "id",
            striped: true, //是否显示行间隔色
            pageNumber: 1, //初始化加载第一页
            pagination: true,//是否分页
            sidePagination: 'server',//server:服务器端分页|client：前端分页
            pageSize: 10,//单页记录数
            pageList: [5, 10, 15, 20, 30],//可选择单页记录数
            //showRefresh: true,//刷新按钮
            queryParams: function (params) {//上传服务器的参数
                var temp = {
                    current: (params.offset / params.limit) + 1,// SQL语句起始索引
                    size: params.limit, // 每页显示数量
                    offset: params.offset + 1, // SQL语句起始索引
            //搜索框参数
                    <#list columns as column>
                        <#if column.dataType!='Date'>
                            <#if column.fieldName!='id' && column.fieldName!='updateAccountNo' && column.fieldName!='createAccountNo'>
                    ${column.fieldName} : $('#search-${column.fieldName}').val(),
                            </#if>
                        </#if>
                    </#list>
                };
                return temp;
            },
            columns: [
                <#list columns as column>
                    <#if column.dataType=='Date'>
                {field: '${column.fieldName}',sortable: true, formatter: 'dateFormat'},
                    <#else >
                {field: '${column.fieldName}', sortable: true},
                    </#if>
                </#list>
                {field: '', align: 'center', events: operateEvents, formatter: 'operateFormatter'}
            ]
        });
    }
    //转换日期格式(时间戳转换为datetime格式)
    function dateFormat(cellval) {
        var dateVal = cellval + "";
        if (cellval != null) {
            var date = new Date(parseInt(dateVal.replace("/Date(", "").replace(")/", ""), 10));
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
            var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
            var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
            return date.getFullYear() + "-" + month + "-" + currentDate + " " + hours + ":" + minutes + ":" + seconds;
        }
    }
    /* 操作转换器 */
    function operateFormatter(value, row, index) {
        return[
            '<button type="button" class="RoleOfedit btn-small   btn-info" style="margin-right:15px;"><i class="fa fa-pencil-square-o" ></i>&nbsp;修改</button>',
            '<button type="button" class="RoleOfdelete btn-small   btn-danger" style="margin-right:15px;"><i class="fa fa-trash-o" ></i>&nbsp;删除</button>'
        ].join('')
    }

    window.operateEvents={
        'click .RoleOfedit':function (e, value, row, index) {
            ${htmlName}.edit${className}(row);
        },
        'click .RoleOfdelete':function (e, value, row, index) {
            ${htmlName}.delete${className}(row);
        }
    }

    /* 日期控件初始化*/
    function datePickerInit(){
        $('#createTime').datetimepicker({
            format: 'YYYY-MM-DD hh:mm',
            //locale: moment.locale('zh-cn')
        });
        $('#updateTime').datetimepicker({
            format: 'YYYY-MM-DD hh:mm',
            //locale: moment.locale('zh-cn')
        });
        $('#search-createTime').datetimepicker({
            format: 'YYYY-MM-DD hh:mm',
            //locale: moment.locale('zh-cn')
        });
        $('#search-updateTime').datetimepicker({
            format: 'YYYY-MM-DD hh:mm',
            //locale: moment.locale('zh-cn')
        });

    }
</script>
</html>