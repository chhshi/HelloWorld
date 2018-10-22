####Binary Search 时候选 Start + 1 < end  还是  start < end ?
- 先确定所有情况下得条件更新方式：start = mid的更新会在只剩两个元素时候出现死循环。end = mid在只剩1个元素时候才会出现死循环
- Start + 1 < end  统一处理至少3个elements的情况
- Start < end 统一处理至少2个elements的情况
    - 剩下两个元素时候, mid和start指向同一个元素, 如果出现 start = mid (不能丢掉mid所以不能用mid+1) 的更新条件 就会死循环
    - Search existence 这类问题因为总是可以丢掉mid不怕死循环，更新start/end永远可以mid+1或mid-1