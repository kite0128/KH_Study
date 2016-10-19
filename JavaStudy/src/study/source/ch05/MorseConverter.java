package study.source.ch05;
class MorseConverter
{
	public static void main(String[] args) 
	{
		if (args.length !=1) {
			System.out.println("usage: java MorseConverter WORD");
			System.exit(0);  // 프로그램을 종료한다.
		}

		System.out.println("source:"+ args[0]);
		String source = args[0].toUpperCase(); // 대문자로 변환한다.
		
		String[] morse = {".-", "-...", "-.-.","-..", "."
						,"..-.", "--.", "....","..",".---"
						, "-.-", ".-..", "--", "-.", "---"
						, ".--.", "--.-",".-.","...","-"
						, "..-", "...-", ".--", "-..-","-.--"
						, "--.." };
		String result="";

		for (int i=0; i < source.length() ; i++ ) {
			result+=morse[source.charAt(i)-'A'];
		}
		
		System.out.println("morse:"+result);
	}
}
