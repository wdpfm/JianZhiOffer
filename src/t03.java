import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
剑指 Offer 03. 数组中重复的数字
找出数组中重复的数字。
在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
示例 1：
输入：
[2, 3, 1, 0, 2, 5, 3]
输出：2 或 3
限制：
2 <= n <= 100000
*/
public class t03 {
/*  方案一：暴力求解
    暴力求解的思路非常的直接：
    遍历数组中的每个元素，然后在剩下的元素中寻找是否存在相同的元素
    代码如下：*/
    public int findRepeatNumber1(int[] nums){
        for(int i=0;i<nums.length;i++){
            for (int j=i+1;i>nums.length;j++){
                if(nums[i]==nums[j]){
                    return nums[i];
                }
            }
        }
        return -1;
    }
/*
以上算法实现的复杂度分析：
时间复杂度是 O(n^2)
空间复杂度是 O(1)
细心的朋友应该会发现这道题目描述的最后对 n 做了一个限制，也就是：
2 <= n <= 100000
那就是说，数据规模 n 有可能是 10 万级别的，如果时间复杂度是 O(n^2) 的话，那么数据规模就会变成 10 万的平方了，这个级别就高了，所以上面的代码的性能是非常差的。接下来我们来优化。

    方案二：哈希查找
    在暴力解法中，我们先遍历每一个元素，然后再从其余的元素中查找这个元素是否存在，其实这里要的就是能高效的判断一个元素是否已经存在，我们可以使用哈希表，因为哈希表判断是否存在的时间复杂度是 O(1)。
    使用哈希表后的算法步骤是：
    先初始化一个哈希表 (HashSet)
    然后遍历每一个元素，分别对每一个元素做如下的处理：
    先判断哈希表中是否存在这个元素
    如果存在的话，则说明这个元素重复，则直接返回
    否则，将这个元素加入到哈希表中，方便后续的判重
    代码如下：*/
    public int findRepeatNumber2(int[] nums){
        Set<Integer> set=new HashSet<>();
        for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])){
                return nums[i];
            }
            set.add(nums[i]);
        }
        return -1;
    }
/*
以上算法实现的复杂度分析：
时间复杂度是 O(n)
空间复杂度是 O(n)
时间复杂度 O(n)，对于数据规模 10 万级别的话，运行速度是可以接受的。但是这里的空间复杂度则变为 O(n)，因为哈希表需要申请额外的 n 个空间，这里用到的是典型的空间换时间的思想

方案三：数组代替哈希表
在题目中，有一个信息，我们需要注意下，那就是数组中每个元素的大小在 0 ~ n - 1 的范围内。利用这个信息，我们就可以使用数组代替上面方案二的哈希表，主要的思路是：
定义一个长度为 n 的数组 bucket，然后将所有的元素初始化为 -1
在查找处理的时候，使用原数组的元素作为 bucket 的下标，原数组元素对应的下标作为 bucket 的元素值。
我们直接看代码：
    */
    public int findRepeatNumber3(int[] nums){
        int[] bucket=new int[nums.length];
        Arrays.fill(bucket,-1);
        for (int i=0;i<nums.length;i++){
            if(bucket[nums[i]]!=-1){
                return nums[i];
            }
            bucket[nums[i]]=i;
        }
        return -1;
    }
/*
    以上算法实现的复杂度分析：
    时间复杂度是 O(n)
    空间复杂度是 O(n)
    可以看出，时间复杂度和空间复杂度都是和用哈希表的解决方案是一样的。但是使用数组绝对会有性能的提高，主要表现在如下的两个方面：
    哈希表 (HashSet) 底层是使用数组 + 链表或者红黑树组成的，而且它的数组也是用不满的，有加载因子的。所以使用数组来代替哈希表，能节省空间
    哈希表在判重的时候需要经过哈希计算，还可能存在哈希冲突的情况，而使用数组则可以直接计算得到 index 的内存位置，所以使用数组访问性能更好。

    方案四：最优解法
*/
    public int findRepeatNumber4(int[] nums){
        for(int i=0;i<nums.length;i++){
            while(i!=nums[i]){
                if(nums[i]==nums[nums[i]]){
                    return nums[i];
                }
                int tmp=nums[nums[i]];
                nums[nums[i]]=nums[i];
                nums[i]=tmp;
            }
        }
        return -1;
    }
/*    以上算法实现的复杂度分析：
    时间复杂度是 O(n)
    空间复杂度是 O(1)*/
}
