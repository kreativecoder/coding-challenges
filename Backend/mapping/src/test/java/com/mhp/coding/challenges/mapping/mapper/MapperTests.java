package com.mhp.coding.challenges.mapping.mapper;

import com.mhp.coding.challenges.mapping.mappers.ArticleMapper;
import com.mhp.coding.challenges.mapping.mappers.blocks.*;
import com.mhp.coding.challenges.mapping.models.db.Article;
import com.mhp.coding.challenges.mapping.models.db.Image;
import com.mhp.coding.challenges.mapping.models.db.ImageSize;
import com.mhp.coding.challenges.mapping.models.db.blocks.*;
import com.mhp.coding.challenges.mapping.models.dto.ImageDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.ArticleBlockDto;
import com.mhp.coding.challenges.mapping.models.dto.blocks.GalleryBlockDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTests {

    @Autowired
    ImageMapper imageMapper;

    @Autowired
    ArticleBlockMapperFactory blockMapperFactory;

    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void testImageMapping() {
        Image image = createImage(1L);
        ImageDto imageDto = imageMapper.map(image);

        assertEquals(image.getId(), imageDto.getId());
    }

    @Test
    public void testTextBlockMapping() {
        TextBlock textBlock = createTextBlock();

        ArticleBlockMapper mapper = blockMapperFactory.getMapper(textBlock);
        assertTrue(mapper instanceof TextBlockMapper);
        com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock textBlockDto = (com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock) mapper.map(textBlock);

        assertEquals(textBlock.getText(), textBlockDto.getText());
        assertEquals(textBlock.getSortIndex(), textBlockDto.getSortIndex());
    }

    @Test
    public void testVideoBlockMapping() {
        VideoBlock videoBlock = createVideoBlock();

        ArticleBlockMapper mapper = blockMapperFactory.getMapper(videoBlock);
        assertTrue(mapper instanceof VideoBlockMapper);
        com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock videoBlockDto = (com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock) mapper.map(videoBlock);

        assertEquals(videoBlock.getType(), videoBlockDto.getType());
        assertEquals(videoBlock.getUrl(), videoBlockDto.getUrl());
        assertEquals(videoBlock.getSortIndex(), videoBlockDto.getSortIndex());
    }

    @Test
    public void testImageBlockMapping() {
        ImageBlock imageBlock = createImageBlock();

        ArticleBlockMapper mapper = blockMapperFactory.getMapper(imageBlock);
        assertTrue(mapper instanceof ImageBlockMapper);
        com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock imageBlockDto = (com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock) mapper.map(imageBlock);

        assertEquals(imageBlock.getImage().getId(), imageBlockDto.getImage().getId());
        assertEquals(imageBlock.getImage().getImageSize(), imageBlockDto.getImage().getImageSize());
        assertEquals(imageBlock.getImage().getUrl(), imageBlockDto.getImage().getUrl());
        assertEquals(imageBlock.getSortIndex(), imageBlockDto.getSortIndex());
    }

    @Test
    public void testGalleryBlockMapping() {
        GalleryBlock galleryBlock = createGalleryBlock();

        ArticleBlockMapper mapper = blockMapperFactory.getMapper(galleryBlock);
        assertTrue(mapper instanceof GalleryBlockMapper);
        GalleryBlockDto galleryBlockDto = (GalleryBlockDto) mapper.map(galleryBlock);

        assertEquals(galleryBlock.getImages().size(), galleryBlockDto.getImages().size());
        assertEquals(galleryBlock.getImages().get(0).getImageSize(), galleryBlockDto.getImages().get(0).getImageSize());
        assertEquals(galleryBlock.getImages().get(0).getUrl(), galleryBlockDto.getImages().get(0).getUrl());
        assertEquals(galleryBlock.getSortIndex(), galleryBlockDto.getSortIndex());
    }

    @Test
    public void testSortingOrder() {
        Article article = createArticle(10L);

        List<ArticleBlockDto> articleBlocks = (List<ArticleBlockDto>) articleMapper.map(article).getBlocks();

        assertEquals(article.getBlocks().size(), articleBlocks.size());
        assertTrue(articleBlocks.get(0) instanceof com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock);
        assertTrue(articleBlocks.get(1) instanceof com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock);
        assertTrue(articleBlocks.get(2) instanceof GalleryBlockDto);
        assertTrue(articleBlocks.get(3) instanceof com.mhp.coding.challenges.mapping.models.dto.blocks.ImageBlock);
        assertTrue(articleBlocks.get(4) instanceof com.mhp.coding.challenges.mapping.models.dto.blocks.TextBlock);
        assertTrue(articleBlocks.get(5) instanceof com.mhp.coding.challenges.mapping.models.dto.blocks.VideoBlock);
    }

    @Test
    public void testNewArticleBlockImplementation() {
        TweetBlock tweetBlock = new TweetBlock();

        final Throwable exception = Assertions.catchThrowable(() -> blockMapperFactory.getMapper(tweetBlock));

        //then
        Assertions.assertThat(exception).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("No mapper found for article block TweetBlock");
    }

    class TweetBlock extends ArticleBlock {

    }

    private Image createImage(Long imageId) {
        final Image result = new Image();
        result.setId(imageId);
        result.setLastModified(new Date());
        result.setLastModifiedBy("Max Mustermann");
        result.setImageSize(ImageSize.LARGE);
        result.setUrl("https://someurl.com/image/" + imageId);
        return result;
    }

    private TextBlock createTextBlock() {
        final TextBlock textBlock = new TextBlock();
        textBlock.setText("Some Text for random...");
        textBlock.setSortIndex(0);

        return textBlock;
    }

    private VideoBlock createVideoBlock() {
        final VideoBlock videoBlock = new VideoBlock();
        videoBlock.setType(VideoBlockType.YOUTUBE);
        videoBlock.setUrl("https://youtu.be/myvideo");
        videoBlock.setSortIndex(5);

        return videoBlock;
    }

    private ImageBlock createImageBlock() {
        final ImageBlock imageBlock = new ImageBlock();
        imageBlock.setImage(createImage(1L));
        imageBlock.setSortIndex(1);

        return imageBlock;
    }

    private GalleryBlock createGalleryBlock() {
        final GalleryBlock galleryBlock = new GalleryBlock();

        final List<Image> galleryImages = new ArrayList<>();
        galleryImages.add(createImage(2L));
        galleryImages.add(createImage(3L));
        galleryBlock.setImages(galleryImages);

        return galleryBlock;
    }

    private Set<ArticleBlock> createBlocks(Long articleId) {
        final Set<ArticleBlock> result = new HashSet<>();

        final TextBlock textBlock = new TextBlock();
        textBlock.setText("Some Text for " + articleId);
        textBlock.setSortIndex(0);
        result.add(textBlock);

        final ImageBlock imageBlock = new ImageBlock();
        imageBlock.setImage(createImage(1L));
        imageBlock.setSortIndex(12);
        result.add(imageBlock);

        final TextBlock secondTextBlock = new TextBlock();
        secondTextBlock.setText("Second Text for " + articleId);
        secondTextBlock.setSortIndex(8);
        result.add(secondTextBlock);

        final GalleryBlock galleryBlock = new GalleryBlock();
        secondTextBlock.setSortIndex(3);

        final List<Image> galleryImages = new ArrayList<>();
        galleryImages.add(createImage(2L));
        galleryImages.add(createImage(3L));
        galleryBlock.setImages(galleryImages);
        galleryBlock.setSortIndex(10);

        result.add(galleryBlock);

        final TextBlock thirdTextBlock = new TextBlock();
        thirdTextBlock.setText("Third Text for " + articleId);
        thirdTextBlock.setSortIndex(20);
        result.add(thirdTextBlock);

        final VideoBlock videoBlock = new VideoBlock();
        videoBlock.setType(VideoBlockType.YOUTUBE);
        videoBlock.setUrl("https://youtu.be/myvideo");
        videoBlock.setSortIndex(50);

        result.add(videoBlock);

        return result;
    }

    private Article createArticle(Long id) {
        final Article result = new Article();
        result.setId(id);
        result.setAuthor("Max Mustermann");
        result.setDescription("Article Description " + id);
        result.setTitle("Article Nr.: " + id);
        result.setLastModifiedBy("Hans Müller");
        result.setLastModified(new Date());
        result.setBlocks(createBlocks(id));
        return result;
    }
}
