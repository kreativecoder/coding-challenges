package com.mhp.coding.challenges.mapping.mappers.blocks;

import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleBlockMapperFactory {
    private final List<BlockMapper> blockMappers;

    public ArticleBlockMapperFactory(List<BlockMapper> blockMappers) {
        this.blockMappers = blockMappers;
    }

    public BlockMapper getMapper(ArticleBlock articleBlock) {
        return blockMappers.stream()
                .filter(blockMapper -> blockMapper.isMapper(articleBlock))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No mapper found for article block " + articleBlock.getClass()));

    }
}
