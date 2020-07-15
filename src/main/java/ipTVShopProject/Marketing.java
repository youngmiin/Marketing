package ipTVShopProject;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Marketing_table")
public class Marketing {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long orderId;
    private Long customerId;
    private String lastRecommendDate;

    @PostPersist
    public void onPostPersist(){
        InfoRegistrated infoRegistrated = new InfoRegistrated();
        BeanUtils.copyProperties(this, infoRegistrated);
        infoRegistrated.publishAfterCommit();


    }

    @PostUpdate
    public void onPostUpdate(){
        Recommended recommended = new Recommended();
        BeanUtils.copyProperties(this, recommended);
        recommended.publishAfterCommit();


    }

    @PostRemove
    public void onPostRemove(){
        InfoDeleted infoDeleted = new InfoDeleted();
        BeanUtils.copyProperties(this, infoDeleted);
        infoDeleted.publishAfterCommit();


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getLastRecommendDate() {
        return lastRecommendDate;
    }

    public void setLastRecommendDate(String lastRecommendDate) {
        this.lastRecommendDate = lastRecommendDate;
    }




}
