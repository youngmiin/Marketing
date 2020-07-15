package ipTVShopProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

 @RestController
 public class MarketingController {
  @Autowired
  MarketingRepository marketingRepository;

  @RequestMapping(method=RequestMethod.PATCH, path="/marketings")
  public void marketingRecommend(@RequestParam(value="orderId", required=false, defaultValue="0") Long orderId) {

   Marketing Recommand = marketingRepository.findByOrderId(orderId);
   SimpleDateFormat defaultSimpleDateFormat = new SimpleDateFormat("YYYYMMddHHmmss");
   String today = defaultSimpleDateFormat.format(new Date());
   Recommand.setLastRecommendDate(today);
   marketingRepository.save(Recommand);

  }

 }
