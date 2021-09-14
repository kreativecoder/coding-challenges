package com.mhp.coding.challenges.mapping.mappers;

import com.mhp.coding.challenges.mapping.mappers.blocks.ArticleBlockMapperFactory;
import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.mhp.coding.challenges.mapping.models.dto.ArticleDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ArticleMapper {

    private final ArticleBlockMapperFactory articleBlockMapperFactory;

    public ArticleMapper(ArticleBlockMapperFactory articleBlockMapperFactory) {
        this.articleBlockMapperFactory = articleBlockMapperFactory;
    }

    public ArticleDto map(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setId(article.getId());
        articleDto.setAuthor(article.getAuthor());
        articleDto.setDescription(article.getDescription());
        Collection<ArticleBlockDto> blocks = mapBlocks(article.getBlocks());
        articleDto.setBlocks(blocks);

        return articleDto;
    }

    private List<ArticleBlockDto> mapBlocks(Set<ArticleBlock> blocks) {
        return blocks.stream()
                .sorted(Comparator.comparingInt(ArticleBlock::getSortIndex))
                .map(articleBlock -> articleBlockMapperFactory.getMapper(articleBlock).map(articleBlock))
                .collect(Collectors.toList());
    }

    public Article map(ArticleDto articleDto) {
        // Nicht Teil dieser Challenge.
        return new Article();
    }
}
