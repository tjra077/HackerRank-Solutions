import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
Given an array of integers, print out an array where at each index i,
the total distance from i to every other duplicate element of i in the array is shown.
For example, if the array was [1,3,1,1,2], the answer would be: [5,0,3,4,0].

Solution:--------------
1) create an empty hashmap hm<key, ListOfArrayIndexesForNumbers>
2) create an empty answer array with length == nums array
3) iterate through nums array and fill up the hashmap for each number.
    - if number is available in hashmap as a key -- add arrayIndex to the corresponding valueList
    - if number is not available in hashmap as a key -- add that key and add arrayIndex to the corresponding valueList
4) iterate through hashmap and for each key-value pair having value(arrayList).size >1
    --iterate through the arrayList and calculate sum
    --iterate though this same arrayList again and sum = sum - iterationValue, add value of the sum to answerArray
*/
public class ArrayDistance {
    public static void main(String[] args) {
        int[] nums = {1,3,1,1,2};
        HashMap<Integer, List<Integer>> hm = new HashMap<Integer, List<Integer>>();
        int[] answer = new int[nums.length];

        //iterating through nums array and populating the hashmap
        for (int i=0; i<nums.length; i++) {
            List<Integer> hmLookupArrayList =new ArrayList<>();
//            System.out.println("checking if list is empty ="+hmLookupArrayList.size());
            if (hm.get(nums[i]) != null) {
//                System.out.println("available in hm");
                hmLookupArrayList = hm.get(nums[i]);
                hmLookupArrayList.add(i);
                hm.put(nums[i],hmLookupArrayList);
            } else {
//                System.out.println("not available in hm");
//                System.out.println("nums[i] ="+nums[i]+" |i ="+i);
                hmLookupArrayList.add(i);
                hm.put(nums[i],hmLookupArrayList);
            }
        }

        //counting sum for arraylists having size>1
        for(Integer n: hm.keySet()) {
            int sum = 0;
            List<Integer> indexArrayList = new ArrayList<>();
            indexArrayList = hm.get(n);
//            System.out.println("key ="+n+" | valueArrayList ="+indexArrayList);
            //counting sum for arrayList having size>1
            if(indexArrayList.size() > 1) {
                for(int number : indexArrayList) {
                    sum = sum+number;
                }
//                System.out.println("sum ="+sum);
            //adding values in answer array
                for(int number: indexArrayList) {
                    sum = sum - number;
                    answer[number] = sum;
                }
            }
        }
        System.out.println("answer array ="+ Arrays.toString(answer));
    }
}
