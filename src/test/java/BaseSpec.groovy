import com.james.Application
import com.james.mapper.FileMapper
import com.james.mapper.PrepareMapper
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import javax.annotation.Resource

@SpringBootTest(classes = Application.class)
class BaseSpec extends Specification{

    @Resource
    PrepareMapper prepareMapper

    def setup() {
        prepareMapper.insertFile("1", "a.groovy")
    }

    def cleanup() {
        prepareMapper.deleteFile("1")
    }
}
