package apt.hthang.doctruyenonline.projections;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;


public interface ChapterSummary {

    Long getId();

    String getChapterNumber();

    Float getSerial();

    String getName();

    Integer getStatus();

    @Value("#{@myComponent.getBetewwen(target.createDate)}")
    String getTimeUpdate();
}
