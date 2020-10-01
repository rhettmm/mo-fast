<#if columns??>
    <#list columns as column>
mo.admin.${className}.${column.fieldName}: ${column.columnComment}
    </#list>
</#if>