package com.frame.ssm.mapper;

import com.frame.ssm.domain.Role;
import com.github.abel533.mapper.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("roleMapper")
public interface RoleMapper extends Mapper<Role> {
    List<Role> getRoleListByUserId(@Param("userId") int userId);
}
