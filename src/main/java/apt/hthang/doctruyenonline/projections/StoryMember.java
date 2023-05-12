package apt.hthang.doctruyenonline.projections;

import org.springframework.beans.factory.annotation.Value;

public interface StoryMember {
    
    Long getId();
    
    String getVnName();
    
    String getImages();
    
    String getAuthor();
    
    Long getCountView();
    
    Integer getDealStatus();
    
    @Value("#{@myComponent.getBetewwen(target.updateDate)}")
    String getTimeUpdate();
    
    @Value("#{@myComponent.getNewChapter(target.id)}")
    ChapterSummary getNewChapter();
    
}
