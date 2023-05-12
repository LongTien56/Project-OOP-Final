package apt.hthang.doctruyenonline.projections;

import org.springframework.beans.factory.annotation.Value;


public interface UserSummary {
    
    Long getId();
    
    @Value("#{@myComponent.getDisplayName(target.username, target.displayName)}")
    String getDisplayName();
    
    String getAvatar();
}
