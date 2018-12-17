package com.loodeer.dao;

import com.loodeer.dataobject.ItemDO;
import com.loodeer.dataobject.ItemDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Dec 16 20:05:14 CST 2018
     */
    long countByExample(ItemDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Dec 16 20:05:14 CST 2018
     */
    int deleteByExample(ItemDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Dec 16 20:05:14 CST 2018
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Dec 16 20:05:14 CST 2018
     */
    int insert(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Dec 16 20:05:14 CST 2018
     */
    int insertSelective(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Dec 16 20:05:14 CST 2018
     */
    List<ItemDO> selectByExample(ItemDOExample example);

    List<ItemDO> itemList();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Dec 16 20:05:14 CST 2018
     */
    ItemDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Dec 16 20:05:14 CST 2018
     */
    int updateByExampleSelective(@Param("record") ItemDO record, @Param("example") ItemDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Dec 16 20:05:14 CST 2018
     */
    int updateByExample(@Param("record") ItemDO record, @Param("example") ItemDOExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Dec 16 20:05:14 CST 2018
     */
    int updateByPrimaryKeySelective(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Sun Dec 16 20:05:14 CST 2018
     */
    int updateByPrimaryKey(ItemDO record);
}
