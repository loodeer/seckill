package com.loodeer.service.impl;

import com.loodeer.service.ItemService;
import com.loodeer.service.model.ItemModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
        @Override
        @Transactional
        public ItemModel createItem(ItemModel itemModel) {
                // 1. 入参校验

                // 2. 转化 itemModel -> dataObject

                // 3. 写入数据库

                // 4. 返回创建完的对象
                return null;
        }

        @Override public List<ItemModel> listItem() {
                return null;
        }

        @Override public ItemModel getItemById(Integer id) {
                return null;
        }
}
