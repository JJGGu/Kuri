package com.kuri.backend.guide.controller;

import com.kuri.backend.guide.dto.GuideDTO;
import com.kuri.backend.guide.service.GuideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 攻略控制器
 */
@RestController
@RequestMapping("/api/guides")
public class GuideController {
    private final GuideService guideService;

    public GuideController(GuideService guideService) {
        this.guideService = guideService;
    }

    /**
     * 获取攻略详情
     *
     * @param id 攻略ID
     * @return 攻略详情
     */
    @GetMapping("/{id}")
    public ResponseEntity<GuideDTO> getGuide(@PathVariable Long id) {
        GuideDTO guide = guideService.findById(id);
        return guide != null ? ResponseEntity.ok(guide) : ResponseEntity.notFound().build();
    }

    /**
     * 获取所有攻略
     *
     * @return 攻略列表
     */
    @GetMapping
    public ResponseEntity<List<GuideDTO>> getAllGuides() {
        return ResponseEntity.ok(guideService.findAll());
    }

    /**
     * 获取作者的攻略列表
     *
     * @param authorId 作者ID
     * @return 攻略列表
     */
    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<GuideDTO>> getGuidesByAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(guideService.findByAuthorId(authorId));
    }

    /**
     * 获取特定分类的攻略列表
     *
     * @param category 分类
     * @return 攻略列表
     */
    @GetMapping("/category/{category}")
    public ResponseEntity<List<GuideDTO>> getGuidesByCategory(@PathVariable String category) {
        return ResponseEntity.ok(guideService.findByCategory(category));
    }

    /**
     * 创建攻略
     *
     * @param guideDTO 攻略DTO
     * @return 创建后的攻略
     */
    @PostMapping
    public ResponseEntity<GuideDTO> createGuide(@RequestBody GuideDTO guideDTO) {
        return ResponseEntity.ok(guideService.create(guideDTO));
    }

    /**
     * 更新攻略
     *
     * @param id 攻略ID
     * @param guideDTO 攻略DTO
     * @return 更新后的攻略
     */
    @PutMapping("/{id}")
    public ResponseEntity<GuideDTO> updateGuide(@PathVariable Long id, @RequestBody GuideDTO guideDTO) {
        GuideDTO updatedGuide = guideService.update(id, guideDTO);
        return updatedGuide != null ? ResponseEntity.ok(updatedGuide) : ResponseEntity.notFound().build();
    }

    /**
     * 删除攻略
     *
     * @param id 攻略ID
     * @return 无内容
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuide(@PathVariable Long id) {
        return guideService.delete(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    /**
     * 评价攻略
     *
     * @param id 攻略ID
     * @param rating 评分
     * @return 无内容
     */
    @PostMapping("/{id}/rate")
    public ResponseEntity<Void> rateGuide(@PathVariable Long id, @RequestParam double rating) {
        return guideService.rateGuide(id, rating) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
} 