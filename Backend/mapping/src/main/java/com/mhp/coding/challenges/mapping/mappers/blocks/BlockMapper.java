package com.mhp.coding.challenges.mapping.mappers.blocks;

import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;

public interface BlockMapper<T extends ArticleBlock, E extends ArticleBlockDto> {
    E map(T articleBlock);
    boolean isMapper(ArticleBlock articleBlock);
}
