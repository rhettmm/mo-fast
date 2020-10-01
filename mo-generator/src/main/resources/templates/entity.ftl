package ${packages}.entity;

<#if hasDate>
import java.util.Date;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@Accessors(chain = true)
@TableName("${tableName}")
public class ${className} implements Serializable{
<#if columns??>
    <#list columns as column>

        /**
        * ${column.columnComment}
        */
        <#if column.columnKey=='PRI'>
            <#if column.extra=='auto_increment'>
        @TableId(type = IdType.AUTO)
            </#if>
        </#if>
        private ${column.dataType} ${column.fieldName};
    </#list>
</#if>


}