package com.loodeer.service.impl;

import com.loodeer.dao.ItemDOMapper;
import com.loodeer.dao.ItemStockDOMapper;
import com.loodeer.dataobject.ItemDO;
import com.loodeer.dataobject.ItemStockDO;
import com.loodeer.error.BussinessException;
import com.loodeer.error.EmBussinessError;
import com.loodeer.service.ItemService;
import com.loodeer.service.model.ItemModel;
import com.loodeer.validator.ValidationResult;
import com.loodeer.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

        @Resource
        private ValidatorImpl validator;

        @Resource
        private ItemDOMapper itemDOMapper;

        @Resource
        private ItemStockDOMapper itemStockDOMapper;

        @Override
        @Transactional
        public ItemModel createItem(ItemModel itemModel) throws BussinessException {
                // 1. 入参校验
                ValidationResult validationResult = validator.validate(itemModel);
                if (validationResult.isHasErrors()) {
                        throw new BussinessException(EmBussinessError.PARAM_INVALID, validationResult.getErrMsg());
                }
                // 2. 转化 itemModel -> dataObject
                ItemDO itemDO = convertItemDOFromItemModel(itemModel);
                // 3. 写入数据库
                itemDOMapper.insertSelective(itemDO);
                itemModel.setId(itemDO.getId());
                ItemStockDO itemStockDO = convertItemStockDOFromItemModel(itemModel);
                itemStockDOMapper.insertSelective(itemStockDO);
                // 4. 返回创建完的对象
                return getItemById(itemModel.getId());
        }

        @Override public List<ItemModel> listItem() {
                return null;
        }

        @Override public ItemModel getItemById(Integer id) {
                ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
                if (itemDO == null) {
                        return null;
                }
                ItemStockDO itemStockDO = itemStockDOMapper.selectByItemId(id);

                // 将 dataObject 转为 model
                ItemModel itemModel = convertFromDataObject(itemDO, itemStockDO);
                return itemModel;
        }

        private ItemModel convertFromDataObject(ItemDO itemDO, ItemStockDO itemStockDO) {
                ItemModel itemModel = new ItemModel();
                BeanUtils.copyProperties(itemDO, itemModel);
                itemModel.setStock(itemStockDO.getStock());
                return itemModel;
        }

        private ItemDO convertItemDOFromItemModel(ItemModel itemModel) {
                if (itemModel == null) {
                        return null;
                }
                ItemDO itemDO = new ItemDO();
                BeanUtils.copyProperties(itemModel, itemDO);

                return itemDO;
        }

        private ItemStockDO convertItemStockDOFromItemModel(ItemModel itemModel){
                if (itemModel == null) {
                        return  null;
                }
                ItemStockDO itemStockDO = new ItemStockDO();
                BeanUtils.copyProperties(itemModel, itemStockDO);
                return itemStockDO;
        }
}
