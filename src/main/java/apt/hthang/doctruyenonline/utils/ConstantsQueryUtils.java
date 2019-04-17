package apt.hthang.doctruyenonline.utils;

/**
 * @author Huy Thang
 */

public class ConstantsQueryUtils {
    
    public static final String STORY_NEW_UPDATE_BY_CATEGORY = "SELECT s.id, s.vnName, s.images, s.author, s.updateDate,"
            + " c.id as chapterId, c.chapterNumber,"
            + " u.displayName, u.username, s.dealStatus"
            + " FROM Story s LEFT JOIN (SELECT c.* FROM Chapter c INNER JOIN"
            + " (SELECT MAX(c.id) AS maxChapterID FROM Story s"
            + " LEFT JOIN Chapter c"
            + " ON s.id = c.storyId GROUP BY s.id) d"
            + " ON c.id = d.maxChapterID "
            + " WHERE c.status IN :chapterStatus) c "
            + " ON s.id = c.storyID "
            + " LEFT JOIN user u on c.userPosted = u.id"
            + " LEFT JOIN `story_category` sc on s.id = sc.storyId"
            + " WHERE  sc.categoryId = :categoryId AND s.status IN :storyStatus"
            + " GROUP BY s.id"
            + " ORDER BY s.updateDate DESC";
    
    public static final String COUNT_STORY_NEW_UPDATE_BY_CATEGORY = "SELECT COUNT(*)"
            + " FROM Story s LEFT JOIN (SELECT c.* FROM Chapter c INNER JOIN"
            + " (SELECT MAX(c.id) AS maxChapterID FROM Story s"
            + " LEFT JOIN Chapter c"
            + " ON s.id = c.storyId GROUP BY s.id) d"
            + " ON c.id = d.maxChapterID "
            + " WHERE c.status IN :chapterStatus) c "
            + " ON s.id = c.storyID "
            + " LEFT JOIN user u on c.userPosted = u.id"
            + " LEFT JOIN `story_category` sc on s.id = sc.storyId"
            + " WHERE  sc.categoryId = :categoryId AND s.status IN :storyStatus"
            + " GROUP BY s.id"
            + " ORDER BY s.updateDate DESC";
    
    public static final String STORY_TOP_VIEW_BY_CATEGORY = "SELECT s.id, s.vnName, s.images, s.infomation, s.dealStatus, s.author,"
            + " COALESCE(d.countTopView,0) AS cnt, ca.id as categoryId, ca.name as categoryName FROM Story s"
            + " LEFT JOIN (SELECT c.storyId, COUNT(c.storyId) AS countTopView FROM Chapter c"
            + " LEFT JOIN `history` f ON  c.id = f.chapterId"
            + " LEFT JOIN Story st on c.storyId = st.id"
            + " WHERE st.status IN :storyStatus"
            + " AND f.dateView BETWEEN :startDate AND :endDate"
            + " AND f.status = :historyStatus"
            + " GROUP BY c.storyId) d ON s.id = d.storyId"
            + " LEFT JOIN `story_category` sc on s.id = sc.storyId"
            + " LEFT JOIN Category ca on sc.categoryId = ca.id"
            + " WHERE  s.status IN :storyStatus AND sc.categoryId = :categoryID"
            + " GROUP BY s.id"
            + " ORDER BY cnt DESC, s.countView DESC";
    
    public static final String COUNT_STORY_TOP_VIEW_BY_CATEGORY = "SELECT COUNT(*) FROM (SELECT s.id, COALESCE(d.countView,0) AS cnt FROM Story s"
            + " LEFT JOIN (SELECT c.storyId, COUNT(c.storyId) AS countTopView FROM Chapter c"
            + " LEFT JOIN `history` f ON  c.id = f.chapterId"
            + " LEFT JOIN Story st on c.storyId = st.id"
            + " WHERE st.status IN :storyStatus"
            + " AND f.dateView BETWEEN :startDate AND :endDate"
            + " AND f.status = :historyStatus"
            + " GROUP BY c.storyId) d ON s.id = d.storyId"
            + " LEFT JOIN `story_category` sc on s.id = sc.storyId"
            + " LEFT JOIN Category ca on sc.categoryId = ca.id"
            + " WHERE  s.status IN :storyStatus AND sc.categoryId = :categoryID"
            + " GROUP BY s.id"
            + " ORDER BY cnt DESC, s.countView DESC) rs";
    
    public static final String STORY_TOP_VOTE_BY_CATEGORY = "SELECT s.id, s.vnName, s.images, s.infomation, s.dealStatus, s.author,"
            + " COALESCE(d.countVote,0) AS cnt, ca.id as categoryId, ca.name as categoryName FROM Story s"
            + " LEFT JOIN (SELECT p.storyId, COALESCE(SUM(p.vote), 0) AS countVote FROM Pay p"
            + " WHERE p.createDate BETWEEN :startDate AND :endDate "
            + " AND p.type = :payType "
            + " AND p.status = :payStatus"
            + " GROUP BY p.storyId) d ON s.id = d.storyId"
            + " LEFT JOIN `story_category` sc on s.id = sc.storyId"
            + " LEFT JOIN Category ca on sc.categoryId = ca.id"
            + " WHERE  s.status IN :storyStatus AND sc.categoryId = :categoryID"
            + " GROUP BY s.id"
            + " ORDER BY cnt DESC, s.status, s.countAppoint DESC";
    
    public static final String COUNT_STORY_TOP_VOTE_BY_CATEGORY = "SELECT COUNT(*) FROM (SELECT s.id, COALESCE(d.countVote,0) AS cnt FROM Story s"
            + " LEFT JOIN (SELECT p.storyId, COALESCE(SUM(p.vote), 0) AS countVote FROM Pay p"
            + " WHERE p.createDate BETWEEN :startDate AND :endDate "
            + " AND p.type = :payType "
            + " AND p.status = :payStatus"
            + " GROUP BY p.storyId) d ON s.id = d.storyId"
            + " LEFT JOIN `story_category` sc on s.id = sc.storyId"
            + " LEFT JOIN Category ca on sc.categoryId = ca.id"
            + " WHERE  s.status IN :storyStatus AND sc.categoryId = :categoryID"
            + " GROUP BY s.id"
            + " ORDER BY cnt DESC, s.status, s.countAppoint DESC) rs";
    
    public static final String SEARCH_STORY = "SELECT s.id, s.vnName, s.images, s.author, s.updateDate, "
            + " c.id as chapterId, c.chapterNumber,"
            + " u.displayName, u.username, s.dealStatus"
            + " FROM Story s LEFT JOIN (SELECT c.* FROM Chapter c INNER JOIN"
            + " (SELECT MAX(c.id) AS maxChapterId FROM Story s"
            + " LEFT JOIN Chapter c"
            + " ON s.id = c.storyId"
            + " WHERE c.status IN :chapterStatus"
            + " AND LOWER(s.vnName) LIKE %:search%"
            + " AND s.status IN :storyStatus"
            + " GROUP BY s.id) d"
            + " ON c.id = d.maxChapterId ) c "
            + " ON s.id = c.storyId "
            + " LEFT JOIN user u on c.userPosted = u.id"
            + " WHERE s.status IN :storyStatus"
            + " AND LOWER(s.vnName) LIKE %:search%"
            + " ORDER BY s.updateDate DESC";
    
    public static final String COUNT_SEARCH_STORY = "SELECT COUNT(*)"
            + " FROM Story s LEFT JOIN (SELECT c.* FROM Chapter c INNER JOIN"
            + " (SELECT MAX(c.id) AS maxChapterId FROM Story s"
            + " LEFT JOIN Chapter c"
            + " ON s.id = c.storyId"
            + " WHERE c.status IN :chapterStatus"
            + " AND LOWER(s.vnName) LIKE %:search%"
            + " AND s.status IN :storyStatus"
            + " GROUP BY s.id) d"
            + " ON c.id = d.maxChapterId ) c "
            + " ON s.id = c.storyId "
            + " LEFT JOIN user u on c.userPosted = u.id"
            + " WHERE s.status IN :storyStatus"
            + " AND LOWER(s.vnName) LIKE %:search%"
            + " ORDER BY s.updateDate DESC";
    
    public static final String CHAPTER_HEAD = "SELECT ch.* FROM Chapter ch"
            + " WHERE ch.storyId = :storyId"
            + " AND ch.status IN :chapterStatus"
            + " ORDER BY ch.serial ASC"
            + " LIMIT 1";
    
    public static final String CHAPTER_NEW = "SELECT ch.* FROM chapter ch"
            + " WHERE ch.storyId = :storyId"
            + " AND ch.status IN :chapterStatus"
            + " ORDER BY ch.serial DESC"
            + " LIMIT 1";
    
    public static final String NEXT_CHAPTER = "SELECT ch.id FROM chapter ch"
            + " WHERE ch.serial > :chapterSerial"
            + " AND ch.storyId = :storyId"
            + " AND ch.status IN :chapterStatus"
            + " ORDER BY ch.serial ASC"
            + " LIMIT 1";
    
    public static final String PREVIOUS_CHAPTER = "SELECT ch.id FROM chapter ch"
            + " WHERE ch.serial < :chapterSerial"
            + " AND ch.storyId = :storyId"
            + " AND ch.status IN :chapterStatus"
            + " ORDER BY ch.serial DESC"
            + " LIMIT 1";
}
