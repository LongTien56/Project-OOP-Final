package apt.hthang.doctruyenonline.repository;

import apt.hthang.doctruyenonline.entity.Story;
import apt.hthang.doctruyenonline.projections.StoryTop;
import apt.hthang.doctruyenonline.projections.StoryUpdate;
import apt.hthang.doctruyenonline.utils.ConstantsQueryUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @author Huy Thang
 */

@Repository
public interface StoryRepository extends JpaRepository< Story, Long > {
    
    /**
     * Lấy Danh sách Truyện Mới Theo Thể Loại
     *
     * @param listChStatus
     * @param cID
     * @param listStatus
     * @param pageable
     * @return Page<StoryUpdate>
     */
    @Query(value = ConstantsQueryUtils.STORY_NEW_UPDATE_BY_CATEGORY,
            countQuery = ConstantsQueryUtils.COUNT_STORY_NEW_UPDATE_BY_CATEGORY,
            nativeQuery = true)
    Page< StoryUpdate > findStoryNewByCategory(@Param("categoryId") Integer cID,
                                               @Param("storyStatus") List< Integer > listStatus,
                                               @Param("chapterStatus") List< Integer > listChStatus,
                                               Pageable pageable);
    
    /**
     * Lấy Danh sách Truyện Top  View Theo Category
     *
     * @param cID
     * @param favoritesStatus
     * @param startDate
     * @param endDate
     * @param listStatus
     * @param pageable
     * @return Page<TopStory>
     */
    @Query(value = ConstantsQueryUtils.STORY_TOP_VIEW_BY_CATEGORY,
            countQuery = ConstantsQueryUtils.COUNT_STORY_TOP_VIEW_BY_CATEGORY,
            nativeQuery = true)
    Page< StoryTop > findTopViewByCategory(@Param("categoryID") Integer cID,
                                           @Param("favoritesStatus") Integer favoritesStatus,
                                           @Param("storyStatus") List< Integer > listStatus,
                                           @Param("startDate") Date startDate,
                                           @Param("endDate") Date endDate, Pageable pageable);
    
    /**
     * Lấy Danh sách Truyện Top  Đề Cử Theo Category
     *
     * @param cID
     * @param listStatus
     * @param payType
     * @param payStatus
     * @param startDate
     * @param endDate
     * @param pageable
     * @return Page<StoryTop>
     */
    @Query(value = ConstantsQueryUtils.STORY_TOP_VOTE_BY_CATEGORY,
            countQuery = ConstantsQueryUtils.COUNT_STORY_TOP_VOTE_BY_CATEGORY,
            nativeQuery = true)
    Page< StoryTop > findTopVoteByCategory(@Param("categoryID") Integer cID,
                                           @Param("storyStatus") List< Integer > listStatus,
                                           @Param("payType") Integer payType, @Param("payStatus") Integer payStatus,
                                           @Param("startDate") Date startDate, @Param("endDate") Date endDate,
                                           Pageable pageable);
    
    
    @Query(value = ConstantsQueryUtils.SEARCH_STORY,
            countQuery = ConstantsQueryUtils.COUNT_SEARCH_STORY,
            nativeQuery = true)
    Page< StoryUpdate > findStoryBySearchKey(@Param("chapterStatus") List< Integer > listChStatus,
                                    @Param("search") String search,
                                    @Param("storyStatus") List< Integer > listStatus,
                                    Pageable pageable);
}
