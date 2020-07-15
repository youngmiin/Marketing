package ipTVShopProject;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface MarketingRepository extends PagingAndSortingRepository<Marketing, Long>{
    Marketing findByOrderId(Long orderId);

}