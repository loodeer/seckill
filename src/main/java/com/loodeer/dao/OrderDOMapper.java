package com.loodeer.dao;

import com.loodeer.dataobject.OrderDO;
import com.loodeer.dataobject.OrderDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDOMapper {

    int insertSelective(OrderDO record);
}
