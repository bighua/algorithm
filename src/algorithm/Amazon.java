package algorithm;

import java.util.Scanner;
import java.util.Stack;

import sun.misc.Regexp;

public class Amazon {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        excelCol(1);
        excelCol(26);
        excelCol(27);
        excelCol(54);
        excelCol(702);
        excelCol(731);
        
        excelWithS(1);
        excelWithS(26);
        excelWithS(27);
        excelWithS(54);
        excelWithS(702);
        excelWithS(731);
        
        Amazon a = new Amazon();
        Calculator c = a.getCalculator("3-1+5+6/2*3");
        System.out.println(c.calculate());
//        System.out.println(calc1("3+4-5-2*3/6"));
//        System.out.println(calc1("(4+2)*3/(2+1)"));
//        System.out.println(calc1("2*3/(1+2*1)-2+3*2"));
//        System.out.println("2*3/(1+2*1)-2+3*2".matches("^([0-9]|[\\+\\-\\/\\*\\(\\)])*$"));
    }
    
    public Calculator getCalculator(String s) {
    	return new Calculator(s);
    }

    private static void excelCol(int num) {
//        int ch = 'A';
//        StringBuilder sb = new StringBuilder();
        String r = "";
        while (num != 0) {
            r = (char)('A' + (num - 1) %26) + r;
            num = (num - 1) / 26;
        }
        System.out.println(r);
    }
    
    private static void excelWithS(int num) {
        Stack<Integer> s = new Stack<Integer>();
        while (num != 0) {
            s.push((num - 1) % 26);
            num = (num - 1) / 26;
        }
        StringBuilder sb = new StringBuilder();
        while (!s.isEmpty()) {
            sb.append((char)('A' + s.pop()));
        }
        System.out.println(sb);
    }
    
    public class Operator {

    		private char op;
    		public Operator (char op) {
    			this.op = op;
    		}
    		
    		public char getOp() {
    			return this.op;
    		}
    		
    		public String calculate(String num1, String num2) {
    			switch (this.op) {
    			case '+' :
    				return String.valueOf((Integer.valueOf(num1) + Integer.valueOf(num2)));
    			case '-' :
    				return String.valueOf((Integer.valueOf(num1) - Integer.valueOf(num2)));
    			case '*' :
    				return String.valueOf((Integer.valueOf(num1) * Integer.valueOf(num2)));
    			case '/' :
    				return String.valueOf((Integer.valueOf(num1) / Integer.valueOf(num2)));
    			default :
    				return "";
    			}
    		}
    		
//    		public static boolean isOp(char c) {
//    			return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '#';
//    		}

    		public int compare(Operator op2) {
    			char c1 = this.op;
    			char c2 = op2.getOp();
    	        if (c2 == '#') {
    	            return 1;
    	        }
    	        if (c1 == '#') {
    	            if (c2 == '#') return 0;
    	            return -1;
    	        } else if (c1 == '(') {
    	            if (c2 == ')') return 0;
    	            return -1;
    	        } else if (c1 == ')') {
    	            if (c2 == '(') return -1;
    	            return 1;
    	        }else if (c1 == '+' || c1 == '-') {
    	            if (c2 == '*' || c2 == '/' || c2 == '(') {
    	                return -1;
    	            }
    	            return 1;
    	        } else if (c1 == '*' || c1 == '/') {
    	            if (c2 == '(') return -1;
    	            return 1;
    	        }
    	        return 0;
    		}
    	}
       public class Calculator {

        	private char[] cs;
        	private Stack<String> num = new Stack<String>();
        	private Stack<Operator> op = new Stack<Operator>();
        	
        	public Calculator (String s) {
        		this.cs = (s + "#").toCharArray();
        		op.push(new Operator('#'));
        	}
        	
        	public String calculate() {
        		StringBuilder numV = new StringBuilder();
        		int i = 0;
        		while (cs[i] != '#' || op.peek().getOp() != '#') {
        			char c = cs[i];
        			if (isOp(c)) {
        				if (numV.length() != 0) {
        					num.push(numV.toString());
        					numV.delete(0, numV.length());
        				}
        				Operator op2 = new Operator(c);
        				switch (op.peek().compare(op2)) {
        					case 0 :
        						op.pop();
        						break;
        					case -1 :
        						op.push(op2);
        						break;
        					case 1 :
        						String num2 = num.pop();
        						String num1 = num.pop();
        						num.push(op.pop().calculate(num1, num2));
        						continue;
        				}
        			} else {
        				numV.append(c);
        			}
        			if (c != '#') {
        				i++;
        			}
        		}
        		return num.pop();
        	}
        }
    
    private static String calc(String s) {
        Stack<String> num = new Stack<String>();
        Stack<Character> op = new Stack<Character>();
        char[] cs = (s + "#").toCharArray();
        StringBuilder numV = new StringBuilder();
        boolean inputNum = false;
        op.push('#');
        int i = 0;
        while (cs[i] != '#' || op.peek() != '#') {
            char c = cs[i];
            if (isOp(c)) {
                if (inputNum) {
                    num.push(numV.toString());
                    numV.delete(0, numV.length());
                    inputNum = false;
                }
                if (op.isEmpty()) {
                    op.push(c);
                } else {
                    switch (compare(op.peek(), c)) {
                    case 0 :
                        op.pop();
                        break;
                    case -1 :
                        op.push(c);
                        break;
                    case 1 :
                        String num2 = num.pop();
                        String num1 = num.pop();
                        num.push(calculate(op.pop(), num1, num2));
                        continue;
                    }
                }
            } else {
                numV.append(c);
                inputNum = true;
            }
            if (c != '#') {
                i++;
            }
        }
        return num.pop();
    }
    
    private static String calculate(char operator, String num1, String num2) {
        switch (operator) {
        case '+' :
            return String.valueOf((Integer.valueOf(num1) + Integer.valueOf(num2)));
        case '-' :
            return String.valueOf((Integer.valueOf(num1) - Integer.valueOf(num2)));
        case '*' :
            return String.valueOf((Integer.valueOf(num1) * Integer.valueOf(num2)));
        case '/' :
            return String.valueOf((Integer.valueOf(num1) / Integer.valueOf(num2)));
        default :
            return "";
        }
    }
    
    private static boolean isOp(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')' || c == '#';
    }
    
    private static int compare(char c1, char c2) {
        if (c2 == '#') {
            return 1;
        }
        if (c1 == '#') {
            if (c2 == '#') return 0;
            return -1;
        } else if (c1 == '(') {
            if (c2 == ')') return 0;
            return -1;
        } else if (c1 == ')') {
            if (c2 == '(') return -1;
            return 1;
        }else if (c1 == '+' || c1 == '-') {
            if (c2 == '*' || c2 == '/' || c2 == '(') {
                return -1;
            }
            return 1;
        } else if (c1 == '*' || c1 == '/') {
            if (c2 == '(') return -1;
            return 1;
        }
        return 0;
    }
    
    
}
