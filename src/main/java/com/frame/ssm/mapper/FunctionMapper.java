package com.frame.ssm.mapper;

import com.frame.ssm.domain.Function;
import com.github.abel533.mapper.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("functionMapper")
public interface FunctionMapper extends Mapper<Function> {

    List<Function> getFunctionListByRoleId(@Param("roleId") int roleId);
}
