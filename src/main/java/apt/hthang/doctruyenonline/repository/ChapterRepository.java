package apt.hthang.doctruyenonline.repository;

import apt.hthang.doctruyenonline.entity.Chapter;
import apt.hthang.doctruyenonline.projections.ChapterOfStory;
import apt.hthang.doctruyenonline.projections.ChapterSummary;
import apt.hthang.doctruyenonline.utils.ConstantsQueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository< Chapter, Long > {
    
    /**
     * Lấy Chapter Đầu Tiên của truyện
     *
     * @param storyId
     * @param listStatus
     * @return Optional<ChapterSummary>
     */
    @Query(value = ConstantsQueryUtils.CHAPTER_HEAD,
            nativeQuery = true)
    Optional< ChapterSummary > findChapterHead(@Param("storyId") Long storyId,
                                               @Param("chapterStatus") List< Integer > listStatus);
    
    /**
     * Lấy Chapter Mới Nhất của truyện
     *
     * @param storyId
     * @param chapterStatus
     * @return Optional<ChapterSummary>
     */
    @Query(value = ConstantsQueryUtils.CHAPTER_NEW,
            nativeQuery = true)
    Optional< ChapterSummary > findChapterNew(@Param("storyId") Long storyId,
                                              @Param("chapterStatus") List< Integer > chapterStatus);
    
    /**
     * Lấy Chapter Theo Truyện
     *
     * @param storyId
     * @param listChapterStatus
     * @param pageable
     * @return Page<ChapterOfStory>
     */
    Page< ChapterOfStory > findByStory_IdAndStatusInOrderBySerialDesc(Long storyId,
                                                                      List< Integer > listChapterStatus,
                                                                      Pageable pageable);
    
    /**
     * Lấy Chapter Theo Truyện
     *
     * @param storyId
     * @param listChapterStatus
     * @return Page<ChapterOfStory>
     */
    List< ChapterOfStory > findByStory_IdAndStatusInOrderBySerialDesc(Long storyId,
                                                                      List< Integer > listChapterStatus);
    
    /**
     * Lấy số lượng chuong đã đăng thành công của User
     *
     * @param userId
     * @param listChapterDisplay
     * @return long
     */
    Long countByUser_IdAndStatusIn(Long userId, List< Integer > listChapterDisplay);
    
    /**
     * Lấy Chapter theo
     *
     * @param storyId
     * @param listStatusStory
     * @param chapterId
     * @param listStatusChapter
     * @return Optional<Chapter>
     */
    Optional< Chapter > findByStory_IdAndStory_StatusInAndIdAndStatusIn(Long storyId, List< Integer > listStatusStory,
                                                                        Long chapterId, List< Integer > listStatusChapter);
    
    /**
     * Lấy Chapter ID Trước
     *
     * @param serial
     * @param storyId
     * @param listStatus
     * @return Optional<Long>
     */
    @Query(value = ConstantsQueryUtils.PREVIOUS_CHAPTER,
            nativeQuery = true)
    Optional< Long > findPreviousChapter(@Param("chapterSerial") float serial,
                                         @Param("storyId") Long storyId,
                                         @Param("chapterStatus") List< Integer > listStatus);
    
    /**
     * Lấy Chapter ID Tiếp Theo
     *
     * @param serial
     * @param storyId
     * @param listStatus
     * @return Optional<Long>
     */
    @Query(value = ConstantsQueryUtils.NEXT_CHAPTER,
            nativeQuery = true)
    Optional< Long > findNextChapter(@Param("chapterSerial") float serial,
                                     @Param("storyId") Long storyId,
                                     @Param("chapterStatus") List< Integer > listStatus);
    
    /**
     * Tìm kiếm Chapter theo
     *
     * @param chapterId
     * @param listStatus
     * @return Optional<Chapter>
     */
    Optional< Chapter > findChapterByIdAndStatusIn(Long chapterId, List< Integer > listStatus);
    
    /**
     * Đếm số chương đã đăng của Story
     *
     * @param id
     * @return
     */
    Long countChapterByStory_Id(Long id);
    
    /**
     *
     * @param userId
     * @param storyId
     * @param pageable
     * @return
     */
    Page<ChapterOfStory> findByUser_IdAndStory_Id(Long userId, Long storyId, Pageable pageable);
    
    /**
     *
     * @param chapterId
     * @param storyId
     * @param number
     * @return
     */
    boolean existsByIdNotAndStory_IdAndSerial(long chapterId, Long storyId, float number);
    
    /**
     *
     * @param id
     * @param number
     * @return
     */
    boolean existsByStory_IdAndSerial(Long id, float number);
    
    /**
     * Cập Nhật Status Chapter Vip Khi Đến Hạn Dealine
     *
     * @param status
     * @param vipStatus
     */
    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query(value = "UPDATE chapter ch SET ch.status = :status"
            + " WHERE ch.status= :vipStatus AND ch.dealine<=NOW()", nativeQuery = true)
    void updateStatusChapterVip(@Param("status") Integer status,
                                @Param("vipStatus") Integer vipStatus);
    
    /**
     *
     * @param date
     * @return
     */
    Long countByCreateDateGreaterThanEqual(Date date);
}
