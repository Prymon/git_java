package five_questions;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * ��дһ����1��2������9��˳���ܱ䣩����֮�����+��-��ʲô�������룬ʹ�ü���������100�ĳ��򣬲�������еĿ����ԡ�
 * ���磺1 + 2 + 34 �C 5 + 67 �C 8 + 9 = 100��
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
	 * ִ�нű�demo
	 * @throws ScriptException
	 */
    public static void test2() throws ScriptException {  
        String str = "0-1-2-3-4-5-6-7-9";  
        ScriptEngineManager manager = new ScriptEngineManager();  
        ScriptEngine engine = manager.getEngineByName("js");  
        Object result = engine.eval(str);  
        System.out.println("�������:" + result.getClass().getName() + ",������:" + result);  
    }  
      
    public static void main(String[] args) throws ScriptException {  
    	ss();
    }  
}
