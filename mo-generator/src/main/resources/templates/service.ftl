package ${packages}.service;


import ${packages}.entity.${className};
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;


public interface ${className}Service {

    int insert(${className} ${classNameFirstLower});

    int deleteById(int id);

    int updateById(${className} ${classNameFirstLower});

    List<${className}> queryAll(${className} ${classNameFirstLower});

    Page<${className}> queryPage(Page<${className}> page,${className} ${classNameFirstLower});
}