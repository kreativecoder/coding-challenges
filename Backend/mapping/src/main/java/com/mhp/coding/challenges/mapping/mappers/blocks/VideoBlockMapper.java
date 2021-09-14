package com.mhp.coding.challenges.mapping.mappers.blocks;

import com.mhp.coding.challenges.mapping.models.db.blocks.ArticleBlock;
import com.mhp.coding.challenges.mapping.models.db.blocks.VideoBlock;
import org.springframework.stereotype.Component;

@Component
public class VideoBlockMapper implements ArticleBlockMapper<VideoBlock, com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock> {

    @Override
    public com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock map(VideoBlock videoBlock) {
        com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock videoBlockDto = new com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock();
        videoBlockDto.setType(videoBlock.getType());
        videoBlockDto.setUrl(videoBlock.getUrl());
        videoBlockDto.setSortIndex(videoBlock.getSortIndex());

        return videoBlockDto;
    }

    @Override
    public boolean isMapper(ArticleBlock articleBlock) {
        return articleBlock instanceof VideoBlock;
    }
}
