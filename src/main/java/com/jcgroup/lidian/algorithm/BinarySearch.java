package com.jcgroup.lidian.algorithm;
/**
* 密级别：classify:p2#-
* User: li dian
* Date: 2018/10/8 17:41
* Description: 二分查找
*/
public class BinarySearch {
	public static void main(String[] args){
		//测试数据
		int[] a = new int[]{0,1,2,3,4,5,6,7};
		System.out.print(rank(5, a));
	}

	/**
	 * @param key 待查找的int
	 * @param a int数组，必须为有序数据
	 * @return
	 */
	public static int rank(int key, int[] a){
		int lo = 0;
		int hi = a.length - 1;

		while (lo <= hi){
			int mid = lo + (hi - lo)/2;
			if(key < a[mid]){
				hi = mid - 1;
			}else if (key > a[mid]){
				lo = mid + 1;
			}else {
				return mid;
			}
		}
		return -1;
	}
}
