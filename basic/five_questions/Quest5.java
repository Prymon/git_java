package five_questions;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 编写一个在1，2，…，9（顺序不能变）数字之间插入+或-或什么都不插入，使得计算结果总是100的程序，并输出所有的可能性。
 * 例如：1 + 2 + 34 + 5 + 67 + 8 + 9 = 100。
 * @author Deamon
 */
public class Quest5 {
	
	private static int[] sign;
	private static ScriptEngineManager manager = new ScriptEngineManager();  
	private static ScriptEngine engine = manager.getEngineByName("js");  
	
	
	static void ss() throws ScriptException{
		for(int a1=0;a1<3;a1++){
			for(int a2=0;a2<3;a2++){
				for(int a3=0;a3<3;a3++){
					for(int a4=0;a4<3;a4++){
						for(int a5=0;a5<3;a5++){
							for(int a6=0;a6<3;a6++){
								for(int a7=0;a7<3;a7++){
									for(int a8=0;a8<3;a8++){
										sign = new int[]{a1,a2,a3,a4,a5,a6,a7,a8};
										StringBuilder str = new StringBuilder();
										for(int i=0;i<8;i++){
											if(sign[i]==0)
												str.append(i+"");
											if(sign[i]==1)
												str.append(i+"+");
											if(sign[i]==2)
												str.append(i+"-");
										}
										str.append("9");
										Object result = engine.eval(str.toString());
										if(result.toString().equals("100.0"))
											System.out.println(str);
									}	
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 执行脚本demo
	 * @throws ScriptException
	 */
    public static void test2() throws ScriptException {  
        String str = "0-1-2-3-4-5-6-7-9";  
        ScriptEngineManager manager = new ScriptEngineManager();  
        ScriptEngine engine = manager.getEngineByName("js");  
        Object result = engine.eval(str);  
        System.out.println("结果类型:" + result.getClass().getName() + ",计算结果:" + result);  
    }  
      
    public static void main(String[] args) throws ScriptException {  
    	ss();
    }  
}
