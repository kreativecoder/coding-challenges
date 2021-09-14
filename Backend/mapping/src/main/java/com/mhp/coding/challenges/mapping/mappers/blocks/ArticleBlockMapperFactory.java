package com.mhp.coding.challenges.mapping.mappers.blocks;

import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ArticleBlockMapperFactory {
    private final List<ArticleBlockMapper> articleBlockMappers;

    public ArticleBlockMapperFactory(List<ArticleBlockMapper> articleBlockMappers) {
        this.articleBlockMappers = articleBlockMappers;
    }

    public ArticleBlockMapper getMapper(ArticleBlock articleBlock) {
        return articleBlockMappers.stream()
                .filter(blockMapper -> blockMapper.isMapper(articleBlock))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No mapper found for article block " + articleBlock.getClass()));

    }
}
