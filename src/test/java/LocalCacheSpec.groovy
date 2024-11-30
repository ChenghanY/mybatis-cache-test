import com.james.mapper.FileMapper
import org.springframework.transaction.support.TransactionTemplate

import javax.annotation.Resource

class LocalCacheSpec extends BaseSpec {

    @Resource
    FileMapper mapper

    @Resource
    TransactionTemplate transaction;

    // debug 建议 BaseExecutor.java:154
    def "未使用事务，第二次查询，不命中本地缓存"() {
        given:
        def list1 = mapper.select()
        def list2 = mapper.select()

        expect:
        list1 !== list2
    }

    def "使用事务，命中缓存"() {
        given:
        def list1 = []
        def list2 = []

        when:
        transaction.execute {
            list1 = mapper.select()
            list2 = mapper.select()
        }

        then:
        // 同一个事务使用同一个SqlSession，若引用相同则认为命中缓存
        list1 === list2
    }

}
