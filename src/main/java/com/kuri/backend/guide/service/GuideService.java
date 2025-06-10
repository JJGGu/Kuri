package com.kuri.backend.guide.service;

import com.kuri.backend.guide.dto.GuideDTO;
import com.kuri.backend.guide.entity.Guide;
import com.kuri.backend.guide.mapper.GuideMapper;
import com.kuri.backend.user.mapper.UserMapper;
import com.kuri.backend.user.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 攻略服务类
 */
@Service
public class GuideService {
    private final GuideMapper guideMapper;
    private final UserMapper userMapper;

    public GuideService(GuideMapper guideMapper, UserMapper userMapper) {
        this.guideMapper = guideMapper;
        this.userMapper = userMapper;
    }

    /**
     * 根据ID查询攻略
     *
     * @param id 攻略ID
     * @return 攻略DTO
     */
    public GuideDTO findById(Long id) {
        Guide guide = guideMapper.selectById(id);
        if (guide != null) {
            guideMapper.incrementViewCount(id);
            return convertToDTO(guide);
        }
        return null;
    }

    /**
     * 查询所有攻略
     *
     * @return 攻略DTO列表
     */
    public List<GuideDTO> findAll() {
        return guideMapper.selectAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * 根据作者ID查询攻略
     *
     * @param authorId 作者ID
     * @return 攻略DTO列表
     */
    public List<GuideDTO> findByAuthorId(Long authorId) {
        return guideMapper.selectByAuthorId(authorId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * 根据分类查询攻略
     *
     * @param category 分类
     * @return 攻略DTO列表
     */
    public List<GuideDTO> findByCategory(String category) {
        return guideMapper.selectByCategory(category).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * 创建攻略
     *
     * @param guideDTO 攻略DTO
     * @return 创建后的攻略DTO
     */
    @Transactional
    public GuideDTO create(GuideDTO guideDTO) {
        Guide guide = new Guide();
        BeanUtils.copyProperties(guideDTO, guide);
        
        guide.setCreatedAt(LocalDateTime.now());
        guide.setUpdatedAt(LocalDateTime.now());
        guide.setIsActive(true);
        guide.setViewCount(0);
        guide.setRating(0.0);

        guideMapper.insert(guide);
        return convertToDTO(guide);
    }

    /**
     * 更新攻略
     *
     * @param id 攻略ID
     * @param guideDTO 攻略DTO
     * @return 更新后的攻略DTO
     */
    @Transactional
    public GuideDTO update(Long id, GuideDTO guideDTO) {
        Guide existingGuide = guideMapper.selectById(id);
        if (existingGuide == null) {
            return null;
        }

        BeanUtils.copyProperties(guideDTO, existingGuide);
        existingGuide.setUpdatedAt(LocalDateTime.now());
        
        guideMapper.update(existingGuide);
        return convertToDTO(existingGuide);
    }

    /**
     * 删除攻略
     *
     * @param id 攻略ID
     * @return 是否成功
     */
    @Transactional
    public boolean delete(Long id) {
        return guideMapper.deleteById(id) > 0;
    }

    /**
     * 评价攻略
     *
     * @param id 攻略ID
     * @param rating 评分
     * @return 是否成功
     */
    @Transactional
    public boolean rateGuide(Long id, double rating) {
        Guide guide = guideMapper.selectById(id);
        if (guide == null) {
            return false;
        }

        int currentRatingCount = guide.getViewCount();
        return guideMapper.updateRating(id, currentRatingCount, rating) > 0;
    }

    /**
     * 转换为DTO
     *
     * @param guide 攻略实体
     * @return 攻略DTO
     */
    private GuideDTO convertToDTO(Guide guide) {
        GuideDTO dto = new GuideDTO();
        BeanUtils.copyProperties(guide, dto);
        
        // 获取作者名称
        User author = userMapper.selectById(guide.getAuthorId());
        if (author != null) {
            dto.setAuthorName(author.getUsername());
        }
        
        return dto;
    }
} 