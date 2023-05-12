package apt.hthang.doctruyenonline.projections;

import java.util.Date;


public interface UserAdmin {
    
    Long getId();
    
    String getUsername();
    
    String getDisplayName();
    
    Date getCreateDate();
    
    Integer getStatus();
}
