package com.mhp.coding.challenges.mapping.mappers.blocks;

import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.mhp.coding.challenges.mapping.models.db.blocks.TextBlock;
import org.springframework.stereotype.Component;

@Component
public class TextBlockMapper implements ArticleBlockMapper<TextBlock, com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock> {

    @Override
    public com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock map(TextBlock textBlock) {
        com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock textBlockDto = new com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock();
        textBlockDto.setText(textBlock.getText());
        textBlockDto.setSortIndex(textBlock.getSortIndex());

        return textBlockDto;
    }

    @Override
    public boolean isMapper(ArticleBlock articleBlock) {
        return articleBlock instanceof TextBlock;
    }
}
