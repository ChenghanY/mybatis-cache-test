import com.james.mapper.FileMapper
import org.springframework.transaction.support.TransactionTemplate

import javax.annotation.Resource

class LocalCacheSpec extends BaseSpec {

    @Resource
    FileMapper mapper

    @Resource
    TransactionTemplate transaction;

    def "事务开启后本地缓存生效"() {
        given:
        // 有事务的两个查询
        def list1 = []
        def list2 = []
        transaction.execute {
            list1 = mapper.select()
            list2 = mapper.select()
        }

        // 无事务的查询
        def list3  = mapper.select()
        def list4  = mapper.select()

        expect:
        list1 === list2
        list3 !== list4
    }
}
