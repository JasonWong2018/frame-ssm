package com.frame.ssm.service;

import com.frame.ssm.domain.Function;
import com.frame.ssm.mapper.FunctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("functionService")
public class FunctionServiceImpl implements FunctionService {

    @Autowired
    private FunctionMapper functionMapper;

    public List<Function> getFunctionList() {
        return functionMapper.select(null);
    }
}
