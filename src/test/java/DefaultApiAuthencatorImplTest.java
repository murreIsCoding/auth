
import com.murre.auth.inter.ApiAuthencator;
import com.murre.auth.AuthToken;
import com.murre.auth.DefaultApiAuthencatorImpl;
import org.junit.Test;

public class DefaultApiAuthencatorImplTest {

    @Test
    public void test() {
        ApiAuthencator apiAuthencator = new DefaultApiAuthencatorImpl();
        String baseUrl = "www.murre.com";
        String appId = "id1";
        String password = "p1";
        long timestamp = System.currentTimeMillis();
        String token = AuthToken.genToken(baseUrl, appId, password, timestamp);
        String finalUrl = baseUrl + "?appId=" + appId + "&token=" + token + "&ts=" + timestamp;
        System.out.println(finalUrl);
        apiAuthencator.auth(finalUrl);
    }
}
