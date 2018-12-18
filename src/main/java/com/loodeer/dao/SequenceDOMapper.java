package com.loodeer.dao;

import com.loodeer.dataobject.SequenceDO;
import com.loodeer.dataobject.SequenceDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SequenceDOMapper {
    SequenceDO getSequenceByName(String name);
    int updateSequenceByName(SequenceDO sequenceDO);
}
