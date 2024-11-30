import com.james.mapper.FileCacheMapper

import javax.annotation.Resource

class NamespaceCacheSpec extends BaseSpec {

    @Resource
    FileCacheMapper fileCacheMapper

    // debug 建议 CachingExecutor.java:96
    def "Namespace缓存需要在xml文件中使用 <cache/> 标签启用"() {
        given:
        def list1  = fileCacheMapper.select()
        def list2  = fileCacheMapper.select()

        expect:
        // 由于 SerializedCache.java:64 使用的是由byte[]序列化方式存储元素，所以实例的地址必然不同
        list1 !== list2
        list1 == list2
    }
}
