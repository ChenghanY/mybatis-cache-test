import com.james.mapper.FileCacheMapper

import javax.annotation.Resource

class NamespaceCacheSpec extends BaseSpec {

    @Resource
    FileCacheMapper fileCacheMapper

    def "二级缓存生效"() {
        given:
        def list1  = fileCacheMapper.select()
        def list2  = fileCacheMapper.select()

        expect:
        //  CachingExecutor.java:96 二级缓存管理使用的反序列化，会创造不一样的实例。但是内容还是一样的
        list1 !== list2
        list1 == list2
    }
}
