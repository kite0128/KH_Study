package study.source.ch06.연습문제;
class Exercise6_23
{
	/*
		(1) max메서드를 작성하시오.
	*/

	public static void main(String[] args) 
	{
		int[] data = {3,2,9,4,7};
		System.out.println(java.util.Arrays.toString(data));
		System.out.println("최대값:"+max(data));
		System.out.println("최대값:"+max(null));
		System.out.println("최대값:"+max(new int[]{})); // 크기가 0인 배열
	}
}
