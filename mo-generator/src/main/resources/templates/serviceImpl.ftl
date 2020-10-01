package ${packages}.service.impl;


import ${packages}.entity.${className};
import ${packages}.service.${className}Service;
import ${packages}.dao.${className}Mapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Date;
import java.util.List;

@Service
public class ${className}ServiceImpl implements ${className}Service {
    @Autowired
    private ${className}Mapper ${classNameFirstLower}Mapper;

    @Override
    public int insert(${className} ${classNameFirstLower}) {
        Date now = new Date();
        ${classNameFirstLower}.setUpdateTime(now);
        ${classNameFirstLower}.setCreateTime(now);
        return  ${classNameFirstLower}Mapper.insert(${classNameFirstLower});
    }

    @Override
    public int deleteById(int id) {
        return  ${classNameFirstLower}Mapper.deleteById(id);
    }

    @Override
    public int updateById(${className} ${classNameFirstLower}) {
        Date now = new Date();
        ${classNameFirstLower}.setUpdateTime(now);
        return  ${classNameFirstLower}Mapper.updateById(${classNameFirstLower});
    }

    @Override
    public List<${className}> queryAll(${className} ${classNameFirstLower}) {
        if(${classNameFirstLower}!=null){
            QueryWrapper<${className}> queryWrapper = new QueryWrapper<>();
            <#if columns??>
                <#list columns as column>
                    <#if column.dataType=='String'>
            if (${classNameFirstLower}.get${column.fieldNameFirstUpper}()!=null && !"".equals(${classNameFirstLower}.get${column.fieldNameFirstUpper}())){
                queryWrapper.eq("${column.dbFieldName}",${classNameFirstLower}.get${column.fieldNameFirstUpper}());
            }
                    <#else >
            if (${classNameFirstLower}.get${column.fieldNameFirstUpper}()!=null){
                queryWrapper.eq("${column.dbFieldName}",${classNameFirstLower}.get${column.fieldNameFirstUpper}());
            }
                    </#if>
                </#list>
            </#if>
            return  ${classNameFirstLower}Mapper.selectList(queryWrapper);
        }
        return  ${classNameFirstLower}Mapper.selectList(null);
    }

    @Override
    public Page<${className}>  queryPage(Page<${className}> page, ${className} ${classNameFirstLower}) {
        if(${classNameFirstLower}!=null){
            QueryWrapper<${className}> queryWrapper = new QueryWrapper<>();
            <#if columns??>
                <#list columns as column>
                    <#if column.dataType=='String'>
            if (${classNameFirstLower}.get${column.fieldNameFirstUpper}()!=null && !"".equals(${classNameFirstLower}.get${column.fieldNameFirstUpper}())){
                queryWrapper.eq("${column.dbFieldName}",${classNameFirstLower}.get${column.fieldNameFirstUpper}());
            }
                    <#else >
            if (${classNameFirstLower}.get${column.fieldNameFirstUpper}()!=null){
                queryWrapper.eq("${column.dbFieldName}",${classNameFirstLower}.get${column.fieldNameFirstUpper}());
            }
                    </#if>
                </#list>
            </#if>
            Integer count = ${classNameFirstLower}Mapper.selectCount(queryWrapper);
            if(count==0){
                return page;
            }
            page.setTotal(count);
            return  ${classNameFirstLower}Mapper.selectPage(page,queryWrapper);
        }
            return  ${classNameFirstLower}Mapper.selectPage(page,null);
    }

}